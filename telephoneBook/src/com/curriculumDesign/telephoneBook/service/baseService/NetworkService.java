package com.curriculumDesign.telephoneBook.service.baseService;

import com.curriculumDesign.telephoneBook.model.Friend;
import com.curriculumDesign.telephoneBook.model.Header;
import com.curriculumDesign.telephoneBook.model.User;
import com.curriculumDesign.telephoneBook.model.message.ClientMessage;
import com.curriculumDesign.telephoneBook.model.message.Message;
import com.curriculumDesign.telephoneBook.model.message.ServiceMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;

/**
 * @ Author: MaCode
 * @ Date: 2020-06-20
 * @ Github: HappyOnion801
 */
public class NetworkService {
    private static final String ServiceIP = "127.0.0.1";
    private static final int port = 8089;

    private static NetworkService instance = new NetworkService();
    private static String cookie = "";

    private NetworkService() {
    }

    public static NetworkService getInstance() {
        return instance;
    }

    private ClientMessage createMessage(int operation, Object body) {
        InetAddress inetAddress = null;
        try {
            inetAddress = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        assert inetAddress != null;
        Header header = new Header(
                Header.CODE_REQUESTS,
                operation,
                inetAddress.getHostAddress(),
                System.currentTimeMillis(),
                cookie
        );
        return new ClientMessage(header, body);
    }

    private int spend(Message message) {
        int res = -1;
        Socket socket;

        try {
            socket = new Socket(ServiceIP, port);
        } catch (IOException e) {
            return res;
        }

        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(message);

            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            ServiceMessage serviceMessage = (ServiceMessage) ois.readObject();

            if (message.getHeader().getOperation() == Header.OPERATION_LOGIN)
                cookie = serviceMessage.getHeader().getCookie();

            if (serviceMessage.getHeader().getCode() == Header.CODE_SUCCESS) {
                res = 1;
            } else {
                res = 0;
                System.out.println(serviceMessage.getBody());
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            res = -2;
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return res;
    }


    public int login(User user) {
        ClientMessage message = this.createMessage(Header.OPERATION_LOGIN, user);
        return this.spend(message);
    }

    public void logout(){
        ClientMessage message = this.createMessage(Header.OPERATION_LOGOUT,null);
        try{
            Socket socket = new Socket(ServiceIP,port);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(message);
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int insert(Friend friend) {
        ClientMessage message = this.createMessage(Header.OPERATION_INSERT, friend);
        return this.spend(message);
    }

    public int delete(Friend friend) {
        ClientMessage message = this.createMessage(Header.OPERATION_DELETE, friend);
        return this.spend(message);
    }

    public int update(Friend friend) {
        ClientMessage message = this.createMessage(Header.OPERATION_UPDATE, friend);
        return this.spend(message);
    }

    public LinkedList<Friend> getFriends() {
        LinkedList<Friend> res = null;
        ClientMessage message = this.createMessage(Header.OPERATION_GET, null);

        Socket socket;
        try {
            socket = new Socket(ServiceIP,port);
        } catch (IOException e) {
            System.out.println("link error");
            return null;
        }

        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(message);

            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            ServiceMessage serviceMessage = (ServiceMessage) ois.readObject();
            if (serviceMessage.getHeader().getCode() == Header.CODE_SUCCESS) {
                if(serviceMessage.getBody() instanceof LinkedList){
                    res = (LinkedList<Friend>) serviceMessage.getBody();
                }
            } else {
                System.out.println(serviceMessage.getBody());
            }

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return res;
    }
}

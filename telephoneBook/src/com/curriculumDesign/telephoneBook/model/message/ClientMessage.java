package com.curriculumDesign.telephoneBook.model.message;

import com.curriculumDesign.telephoneBook.model.Header;

import java.io.Serializable;

/**
 * @ Author: MaCode
 * @ Date: 2020-06-19
 * @ Github: HappyOnion801
 */
public class ClientMessage implements Message {
    private static final long serialVersionUID = -3523431819995961655L;
    private Header header;
    private Object body;

    public ClientMessage(Header header, Object body) {
        this.header = header;
        this.body = body;
    }

    @Override
    public Header getHeader() {
        return this.header;
    }

    @Override
    public Object getBody() {
        return this.body;
    }

    @Override
    public String toString() {
        return "ClientMessage{" +
                "header=" + header +
                ", body=" + body +
                '}';
    }
}

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @ Author: MaCode
 * @ Date: 2020-06-13
 * @ Github: HappyOnion801
 */
public class Jdbc {

    private static String driverPackage = "com.mysql.cj.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/srm?useSSL=false&serverTimezone=UTC";
    private static String user = "root";
    private static String password = "123456";

    public static void main(String[] args) {
        insert();
//        delete();
//        update();
//        query();
    }

    private static Connection getMysqlConnection() {
        Connection res;
        try {
            Class.forName(driverPackage);
            res = DriverManager.getConnection(url, user, password);
            return res;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void insert() {
        Connection connection = getMysqlConnection();
        if (connection == null) {
            System.out.println("数据库连接失败！");
            return;
        }
        String sql = "INSERT INTO people VALUES(NULL,?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "猪八戒");
            ps.setString(2, "1980-3-5");
            ps.setString(3, "山东");
            ps.setString(4, "男");
            ps.setInt(5, 40);
            int lines = ps.executeUpdate();
            ps.close();
            connection.close();
            System.out.println("插入成功！" + lines + "行受影响");
            return;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("插入失败！");
        }
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void delete() {
        Connection connection = getMysqlConnection();
        if (connection == null) {
            System.out.println("数据库连接失败！");
            return;
        }
        String sql = "DELETE FROM people WHERE name=?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "猪八戒");
            int lines = ps.executeUpdate();
            ps.close();
            connection.close();
            System.out.println("删除成功！" + lines + "行受影响");
            return;
        } catch (SQLException e) {
            System.out.println("删除失败！");
            e.printStackTrace();
        }
        try {
            connection.close();
            connection = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void update() {
        Connection connection = getMysqlConnection();
        if (connection == null) {
            System.out.println("数据库连接失败！");
            return;
        }
        String sql = "UPDATE people SET name=?,addr=?,age=? WHERE id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "猪八戒");
            ps.setString(2, "高老庄");
            ps.setInt(3, 34);
            ps.setInt(4, 1);
            int lines = ps.executeUpdate();
            ps.close();
            connection.close();
            System.out.println("更新成功！" + lines + "行受影响");
            return;
        } catch (SQLException e) {
            System.out.println("更新失败");
            e.printStackTrace();
        }
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void query() {
        Connection connection = getMysqlConnection();
        if (connection == null) {
            System.out.println("数据库连接失败！");
            return;
        }
        String sql = "SELECT * FROM people WHERE age=?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, 34);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getString(2));
            }
            ps.close();
            connection.close();
            return;
        } catch (SQLException e) {
            System.out.println("查询失败！");
            e.printStackTrace();
        }
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
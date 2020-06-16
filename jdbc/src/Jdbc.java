import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Jdbc {

    public static void main(String[] args) {
        //testInsert();
        testUpdate();
    }

    private static void testInsert() {
        Connection connection = null;
        PreparedStatement pstmt = null;
        //1、注册驱动
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //2、获得连接,通过DriverManager获得连接
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/srm", "root", "123456");
            //3、执行sql
            //3.1 定义sql
            String sql = "INSERT INTO people VALUES(null,?,?,?,?,?)";
            //3.2 获得PreparedStatement
            pstmt = connection.prepareStatement(sql);
            //3.3设置参数
            pstmt.setString(1, "猪八戒");
            pstmt.setString(2, "1980-3-5");
            pstmt.setString(3, "山东");
            pstmt.setString(4, "男");
            pstmt.setInt(5, 40);
            //3.4执行sql,增删改用executeUpdate
            int lines = pstmt.executeUpdate();//收影响的行数
            if (lines > 0) {
                System.out.println("插入成功");
            } else {
                System.out.println("插入失败");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            //4、释放资源
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                pstmt = null;
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                connection = null;
            }
        }
    }

    private static void testUpdate() {
        Connection connection = null;
        PreparedStatement pstmt = null;
        //1、注册驱动
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //2、获得连接,通过DriverManager获得连接
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/srm", "root", "123456");
            //3、执行sql
            //3.1 定义sql
            String sql = "UPDATE people SET name=?,addr=? WHERE id=?";
            //3.2 获得PreparedStatement
            pstmt = connection.prepareStatement(sql);
            //3.3设置参数
            pstmt.setString(1, "沙僧");
            pstmt.setString(2, "流沙河");
            pstmt.setLong(3, 6);
            //3.4执行sql,增删改用executeUpdate
            int lines = pstmt.executeUpdate();//收影响的行数
            if (lines > 0) {
                System.out.println("更新成功");
            } else {
                System.out.println("更新失败");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            //4、释放资源
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                pstmt = null;
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                connection = null;
            }
        }
    }

    public void testDelete() {
        Connection connection = null;
        PreparedStatement pstmt = null;
        //1、注册驱动
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //2、获得连接,通过DriverManager获得连接
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/srm", "root", "123456");
            //3、执行sql
            //3.1 定义sql
            String sql = "DELETE FROM people WHERE id=?";
            //3.2 获得PreparedStatement
            pstmt = connection.prepareStatement(sql);
            //3.3设置参数

            pstmt.setLong(1, 5);
            //3.4执行sql,增删改用executeUpdate
            int lines = pstmt.executeUpdate();//收影响的行数
            if (lines > 0) {
                System.out.println("删除成功");
            } else {
                System.out.println("删除失败");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            //4、释放资源
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                pstmt = null;
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                connection = null;
            }
        }
    }

    public void testQuery() {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        //1、注册驱动
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //2、获得连接,通过DriverManager获得连接
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/srm", "root", "123456");
            //3、执行sql
            //3.1 定义sql
            String sql = "SELECT * FROM people WHERE id>?";
            //3.2 获得PreparedStatement
            pstmt = connection.prepareStatement(sql);
            //3.3设置参数

            pstmt.setLong(1, 1);
            //3.4执行sql,增删改用executeQuery
            rs = pstmt.executeQuery();
            //3.5迭代结果集
            while (rs.next()) {
                Long id = rs.getLong(1);//获得id
                String name = rs.getString(2);//获得name
                String birthday = rs.getString(3);
                System.out.println(id + "," + name + "," + birthday);

            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            //4、释放资源
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                rs = null;
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                pstmt = null;
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                connection = null;
            }
        }
    }
}

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Main {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // 加载数据库驱动类
            Class.forName("com.mysql.jdbc.Driver");
            // 通过驱动管理类获取数据库连接对象
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hello?characterEncoding=utf-8", "root", "root");
            String sql = "select * from user where username = ?";
            // 获取预处理语句对象
            stmt = conn.prepareStatement(sql);
            // 设置参数，参数索引从1开始
            stmt.setString(1, "admin");
            // 发送sql查询
            rs  = stmt.executeQuery();

            // 遍历结果集
            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                // 封装user对象
                User user = new User();
                user.setId(id);
                user.setUsername(username);
                System.out.println(user);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

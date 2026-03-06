import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SQLConnect {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/subsakila";
        String user = "your_username";
        String password = "your_password";

        try {
            // 加载MySQL JDBC驱动
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 建立数据库连接
            Connection conn = DriverManager.getConnection(url, user, password);

            // 创建Statement对象
            Statement stmt = conn.createStatement();

            // 执行查询
            String sql = "SELECT title, length FROM film LIMIT 30";
            ResultSet rs = stmt.executeQuery(sql);

            // 处理查询结果
            while (rs.next()) {
                String title = rs.getString("title");
                int length = rs.getInt("length");
                System.out.println("电影名: " + title + ", 时长: " + length + " 分钟");
            }

            // 关闭资源
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

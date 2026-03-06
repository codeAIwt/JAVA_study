import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class end_sqlConn {
    public static void main(String[] args) {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306";
        String user = "root";
        String password = "*****";
        String filepath = "D:\\文档\\Baidu File\\browserecords_ds.txt";

        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, user, password);
            if (!conn.isClosed()) System.out.println("连接成功!");
            Statement statement = conn.createStatement();

            String createDaabase="CREATE DATABASE NewsRecord;";
            statement.executeUpdate(createDaabase);
            statement.executeUpdate("USE NewsRecord;");
            //创建用户信息表
            String createUserTable =
                    "CREATE TABLE Users ("
                            + "Userid BIGINT PRIMARY KEY, "
                            + "Username VARCHAR(255) NOT NULL, "
                            + "Gender CHAR(3) NOT NULL,"
                            + "Area CHAR(30),"
                            + "Age INT );";
            statement.executeUpdate(createUserTable);

            //创建新闻信息表
            String createNewsTable =
                    "CREATE TABLE News ("
                            + "Newsid BIGINT PRIMARY KEY, "
                            + "Title VARCHAR(255) NOT NULL, "
                            + "Content LONGTEXT NOT NULL, "
                            + "PublishTime CHAR(50) );";
            statement.executeUpdate(createNewsTable);

            //创建浏览记录表
            String createBrowsingRecordTable =
                    "CREATE TABLE BrowsingRecord ("
                            + "Userid BIGINT NOT NULL, "
                            + "Newsid BIGINT NOT NULL, "
                            + "Clicktime DATETIME,"
                            + "FOREIGN KEY (Userid) REFERENCES User(Userid), "
                            + "FOREIGN KEY (Newsid) REFERENCES News(Newsid)" + ");";
            statement.executeUpdate(createBrowsingRecordTable);

            BufferedReader reader = new BufferedReader(new FileReader(filepath));
            String line;
            line=reader.readLine();//跳过第一行
            while ((line = reader.readLine()) != null) {
                line = line.replaceAll("\"", "");
                String[] values = line.split("\t");

                // 插入用户数据
                String insertUserSql = "INSERT IGNORE INTO User" +//使用INSERT IGNORE避免重复数据
                        " (Userid, Username, Gender,Area,Age) VALUES (?,?,?,?,?);";

                try (PreparedStatement pstmtUser = conn.prepareStatement(insertUserSql)) {
                    pstmtUser.setInt(1, Integer.parseInt(values[0]));
                    pstmtUser.setString(2, values[1]);
                    pstmtUser.setString(3, values[2]);
                    pstmtUser.setString(4, values[3]);
                    pstmtUser.setInt(5, Integer.parseInt(values[4]));
                    pstmtUser.executeUpdate();
                }

                // 插入新闻数据
                String insertNewsSql = "INSERT IGNORE INTO News " +
                        "(Newsid, Title, Content, PublishTime) VALUES (?,?,?,?);";

                try (PreparedStatement pstmtNews = conn.prepareStatement(insertNewsSql)) {
                    pstmtNews.setInt(1, Integer.parseInt(values[6]));
                    pstmtNews.setString(2, values[7]);
                    pstmtNews.setString(3, values[8]);
                    pstmtNews.setString(4, values[9]);
                    pstmtNews.executeUpdate();
                }


                // 插入浏览记录数据
                String insertBrowsingRecordSql = "INSERT IGNORE INTO BrowsingRecord" +
                        " (Userid, Newsid, Clicktime) VALUES (?,?,?);";
                try (PreparedStatement pstmtBrowsingRecord = conn.prepareStatement(insertBrowsingRecordSql)) {
                    pstmtBrowsingRecord.setInt(1, Integer.parseInt(values[0]));
                    pstmtBrowsingRecord.setInt(2, Integer.parseInt(values[6]));
                    // 将字符串类型的Unix时间戳转换为java.sql.Timestamp
                    long browsingTimestamp = Long.parseLong(values[5]);
                    Date browsingDate = new Date(browsingTimestamp * 1000); // 转换为毫秒
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = sdf.format(browsingDate);
                    pstmtBrowsingRecord.setTimestamp(3, new java.sql.Timestamp(browsingDate.getTime()));
                    pstmtBrowsingRecord.executeUpdate();

                }
            }
            System.out.println("Data imported successfully.");

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

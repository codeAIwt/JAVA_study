import java.sql.*;

public class FilmDurationApp {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/subsakila";
        String user = "root";
        String password = "BHS1013%";
        
		try(Connection conn = DriverManager.getConnection(url, user, password)){
			Statement stmt = conn.createStatement();
			String sql = "SELECT title,length From film LIMIT 30";
        	ResultSet rs = stmt.executeQuery(sql);
        	
        	while(rs.next()){
        		String title = rs.getString("title");
        		int length = rs.getInt("length");
        		System.out.println("电影名"+title+"时长"+length);
        	}
        	rs.close();
        	stmt.close();
        	conn.close();
        	
        	
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
    }
}




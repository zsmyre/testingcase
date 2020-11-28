
import java.sql.*;
public class books {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Class.forName("com.mysql.jdbc.Driver");
	//	Connection connection=DriverManager.getConnection("jbdc:mysql://localhost/bookshelf" ,"root","root");
String query="insert into nameofbook values (1, 'Moby Dick', 'Hans');";

//String update="update nameofbook set title= "MobyDick" where ID=1";
// String delete="delete from nameofbook where ID=1";
		Statement statement=connection.createStatement();
		ResultSet rs=statement.executeQuery("Select*from nameofbook;");
		int rows=statement.executeUpdate(query);
		
		while(rs.next()) {System.out.println(rs.getInt(1)); }
	}

}
 
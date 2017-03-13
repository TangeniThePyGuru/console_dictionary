package classes;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnect {

	private Connection conn;
	private java.sql.Statement stmt;
	private ResultSet rs;
	
	public DBConnect(){
		try{
			Class.forName("com.sun.java.util.jar.pack.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/eng_dictionary","root","");
			stmt = conn.createStatement();
		}catch(Exception ex){
			System.out.println("Error: "+ex);
		}
	}
	
//	return the string of definitions to the server	
	public String getDefinition(String word) throws SQLException{
		String def = "";
		try{
			
			String query = "SELECT * FROM entries";
//			assign the result from the database
			rs = stmt.executeQuery(query);
			
			while (rs.next()){
				if (rs != null){
					String definition = rs.getString("definition");
//					store dictionary word
					String word_dic = rs.getString("word");

//					compare the word in the dictionary row with the current word searched 
					if (word.equals(word_dic)){
						def = " - "+ definition;
						break;
					}
				}
			}
			if (def == "")
//				show that word does not exist in the dictionary
				def = "- \"word not found\"";
	
		}catch(Exception ex){
//			catch the error
			System.out.println(ex);
		}
		
		return def;
		
	}
}

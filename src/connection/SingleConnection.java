package connection;
/**
 * Responsável por fazer a conexão com o banco de dados
 * @author huber
 *
 */

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnection {
    public static String url = "jdbc:postgresql://localhost:5432/curso-jsp?autoReconnect=true";
    public static String user = "postgres";
    public static String password = "admin";
    public static Connection connection = null;
    
    static {
    	doConnect();
    }
    
    public SingleConnection() {
    	doConnect();
    }
    
    public static void doConnect() {
    	try {
			if (connection == null) {
				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(url, user, password);
				connection.setAutoCommit(false);
				
			} 
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Conexão não efetivada. Erro desconhecido ao conectar-se!");
		}
    	
    }
    public static Connection getConnection() {
    	return connection;
    }
}

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.LoginUser;
import connection.SingleConnection;

public abstract class UserAuthentication {
	
	private static Connection connection;
	
	String usuario;
	String password;
	
	private LoginUser loginUser;

	static {
		connection = SingleConnection.getConnection();
	}

	public static LoginUser addUser(String usuario, String password) throws SQLException {
		  
		String sql = "select * from usuario where login <> 'admin' AND login = '" + usuario
				+ "' and senha = '" + password + "'";

		PreparedStatement declaracao = connection.prepareStatement(sql);
		ResultSet result = declaracao.executeQuery();
		if (result.next()) {
			return new LoginUser(usuario, password);	
		} else {
			return null;
		}

	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LoginUser getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(LoginUser loginUser) {
		this.loginUser = loginUser;
	}

}

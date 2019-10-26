package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.LoginUser;
import connection.SingleConnection;
import interfaces.DataManipulation;

public class UserDao implements DataManipulation {
	private static Connection connection;
	private LoginUser loginUser;

	static {
		connection = SingleConnection.getConnection();
	}

	public UserDao() {
	}

	public UserDao(LoginUser loginUser) {
		this.setLoginUser(loginUser);
	}

	@Override
	public void insert() {
		String sql = "insert into usuario (login, senha, nome, telefone) values (?,?,?,?)";
		try {
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, getLoginUser().getLogin());
			insert.setString(2, getLoginUser().getSenha());
			insert.setString(3, getLoginUser().getNome());
			insert.setString(4, getLoginUser().getTelefone());
			insert.execute();
			connection.commit();

		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	} 
 
	@Override
	public void update() {
		String sql = "update usuario SET login = ?, senha = ?, nome = ?, telefone = ? where id = " + getLoginUser().getId();

		try {
			PreparedStatement atualiza = connection.prepareStatement(sql);
			atualiza = connection.prepareStatement(sql);
			atualiza.setString(1, getLoginUser().getLogin());
			atualiza.setString(2, getLoginUser().getSenha());
			atualiza.setString(3, getLoginUser().getNome());
			atualiza.setString(4, getLoginUser().getTelefone());
			atualiza.execute();
			connection.commit(); 
			
		} catch (SQLException e) {
			try {
				e.printStackTrace();
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

	}

	@Override
	public void delet(Long id) {
		String sql = "delete from usuario where id = " + id;
		try {
			PreparedStatement declaration = connection.prepareStatement(sql);
			declaration.execute();
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

	}

	public boolean existeLoginDuplicado() throws SQLException {
		String sql;
		if (getLoginUser().getId() == null) {
			sql = "select count(1) as qtd from usuario where login = '" + getLoginUser().getLogin() + "'";
		} else {
			sql = "select count(1) as qtd from usuario where login = '" + getLoginUser().getLogin() + "' and id != " + getLoginUser().getId();
		}
		
		PreparedStatement declaracao = connection.prepareStatement(sql);
		ResultSet qtd = declaracao.executeQuery();
		if (qtd.next()) {
			return qtd.getInt("qtd") > 0 ? true : false;
		}
		return false;
	}
	
	public boolean existeSenhaDuplicada() throws SQLException {
		String sql;
		if (getLoginUser().getId() == null) {
			sql = "select count(1) as qtd from usuario where senha = '" + getLoginUser().getSenha() + "'";
		} else {
			sql = "select count(1) as qtd from usuario where senha = '" + getLoginUser().getSenha() + "' and id != " + getLoginUser().getId();
		}
		
		PreparedStatement declaracao = connection.prepareStatement(sql);
		ResultSet qtd = declaracao.executeQuery();
		if (qtd.next()) {
			return qtd.getInt("qtd") > 0 ? true : false;
		}
		return false;
	}

	public List<LoginUser> completeList() throws SQLException {
		List<LoginUser> lista = new ArrayList<LoginUser>();
		String sql = "select * from usuario where login <> 'admin'";
		PreparedStatement declaration = connection.prepareStatement(sql);

		ResultSet result = declaration.executeQuery();
		while (result.next()) {
			LoginUser loginUser = new LoginUser();
			loginUser.setId(Long.valueOf(result.getString("id")));
			loginUser.setLogin(result.getString("login"));
			loginUser.setSenha(result.getString("senha"));
			loginUser.setNome(result.getString("nome"));
			loginUser.setTelefone(result.getString("telefone"));
			lista.add(loginUser);
		}

		return lista;

	}

	public LoginUser consult(Long id) throws SQLException {
		String sql = "select * from usuario where login <> 'admin' AND id = " + id;
		LoginUser loginUser = new LoginUser();
		try {

			PreparedStatement declaration = connection.prepareStatement(sql);
			ResultSet result = declaration.executeQuery();
			if (result.next()) {
				loginUser.setId(Long.valueOf(result.getString("id")));
				loginUser.setLogin(result.getString("login"));
				loginUser.setSenha(result.getString("senha"));
				loginUser.setNome(result.getString("nome"));
				loginUser.setTelefone(result.getString("telefone"));
			}
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

		return loginUser;
	}

	public LoginUser getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(LoginUser loginUser) {
		this.loginUser = loginUser;
	}
}

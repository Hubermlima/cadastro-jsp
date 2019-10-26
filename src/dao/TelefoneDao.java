package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.Telefone;
import connection.SingleConnection;
import interfaces.DataManipulation;

public class TelefoneDao implements DataManipulation {
	private static Connection connection;
	private Telefone telefone;
	Long id;
	Long maxId;
 
	static {
		connection = SingleConnection.getConnection();
	}

	public TelefoneDao() {
	} 

	public TelefoneDao(Telefone telefone) {
		this.telefone = telefone;
	}

	@Override
	public void insert() {
		String sql = "INSERT INTO telefones(numero, tipo, origem, id_fornec_cli) VALUES (?, ?, ?, ?)";
		try {
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, telefone.getNumero());
			insert.setString(2, telefone.getTipo());
			insert.setInt(3, telefone.getOrigem());
			insert.setLong(4, telefone.getChaveEstrangeira());
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
		String sql = "UPDATE public.telefones " + "SET numero=?, tipo=?, origem=?, id_fornec_cli=? where id = "
				+ telefone.getId();

		try {
			PreparedStatement atualiza = connection.prepareStatement(sql);
			atualiza = connection.prepareStatement(sql);
			
			atualiza.setString(1, telefone.getNumero());
			atualiza.setString(2, telefone.getTipo());
			atualiza.setInt(3, telefone.getOrigem());
			atualiza.setLong(4, telefone.getChaveEstrangeira());
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
		String sql = "delete from telefones where id = " + id;
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

	public boolean telefoneDuplicado() throws SQLException {
		String sql;
		if (telefone.getId() == null) {
			sql = "select count(1) as qtd from telefones where numero = '" + telefone.getNumero() + "'";
		} else {
			sql = "select count(1) as qtd from telefones where numero = '" + telefone.getNumero() + "' and id != "
					+ telefone.getId();
		}

		PreparedStatement declaracao = connection.prepareStatement(sql);
		ResultSet qtd = declaracao.executeQuery();
		if (qtd.next()) {
			return qtd.getInt("qtd") > 0 ? true : false;
		}
		return false;
	}

	public Telefone consult(Long id) throws SQLException {
		String sql = "select * from telefones where id = " + id;
		Telefone telefone = new Telefone();
		try {

			PreparedStatement declaration = connection.prepareStatement(sql);
			ResultSet result = declaration.executeQuery();
			if (result.next()) {
				telefone.setId(result.getLong("id"));
				telefone.setNumero(result.getString("numero"));
				telefone.setTipo(result.getString("tipo"));
				telefone.setOrigem(result.getInt("origem"));
				telefone.setChaveEstrangeira(result.getLong("id_fornec_cli"));
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

		return telefone;
	}

	public Long getMaxId() throws SQLException {
		String sql = "SELECT MAX(id) as id FROM telefones";
		PreparedStatement declaracao;

		declaracao = connection.prepareStatement(sql);
		ResultSet id = declaracao.executeQuery();
		
		if (id.next()) {
			return id.getLong("id");
		}
		return 0L;

	}

	public static Connection getConnection() {
		return connection;
	}

	public static void setConnection(Connection connection) {
		TelefoneDao.connection = connection;
	}

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

}

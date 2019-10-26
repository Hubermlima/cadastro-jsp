package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List; 

import beans.Cliente;
import beans.Telefone;
import connection.SingleConnection;
import interfaces.DataManipulation;

public class ClienteDao implements DataManipulation {
	private static Connection connection;

	private Cliente cliente;

	static {
		connection = SingleConnection.getConnection();
	}

	public ClienteDao() { 
	}

	public ClienteDao(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public void insert() {
		
		String sql = "INSERT INTO clientes(nome, endereco, numero, complemento, bairro, cep, cidade, "
				+ "telefone1, telefone2, email, cpf_cnpj, rg_inscricao, "
				+ "info_adicional, estado_civil, titulo, genero, cnh, estado, tipo, data_nascimento, foto, contenttype, miniatura_imagem) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, getCliente().getNome());
			insert.setString(2, getCliente().getEndereco());
			insert.setString(3, getCliente().getNumero());
			insert.setString(4, getCliente().getComplemento());
			insert.setString(5, getCliente().getBairro());
			insert.setString(6, getCliente().getCep().replaceAll("[^0-9]", ""));
			insert.setString(7, getCliente().getCidade());
			insert.setString(8, getCliente().getTelefone1().replaceAll("[^0-9]", ""));
			insert.setString(9, getCliente().getTelefone2().replaceAll("[^0-9]", ""));
			insert.setString(10, getCliente().getEmail());
			insert.setString(11, getCliente().getCpfCnpj().replaceAll("[^0-9]", ""));
			insert.setString(12, getCliente().getRgInscricao());
			insert.setString(13, getCliente().getInfoAdicional());
			insert.setString(14, getCliente().getEstadoCivil());
			insert.setString(15, getCliente().getTitulo());
			insert.setString(16, getCliente().getGenero());
			insert.setString(17, getCliente().getCNH());
			insert.setString(18, getCliente().getEstado());
			insert.setInt(19, getCliente().getTipo());
			insert.setString(20, getCliente().getDataNascimento().replaceAll("[^0-9]", ""));
			insert.setString(21, getCliente().getFotoBase64());
			insert.setString(22, getCliente().getContentType());
			insert.setString(23, getCliente().getMiniaturaImagem().replaceAll("_", "").trim());

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
		String novaMiniaturaBase64 = getCliente().getMiniaturaImagem().split("_")[0];
		String tempMiniatura;
		try {
			/*
			 * Foi necessário o try porque estava dando erro se tempMiniatura estivesse vazio, pois
			 * não estava assim reconhecendo a posição 1 (estouro de array)
			 */
			tempMiniatura = getCliente().getMiniaturaImagem().split("_")[1];
		} catch (Exception e) {
			tempMiniatura = "";
		}
		
		String sql = "UPDATE clientes " + "SET nome=?, endereco=?, numero=?, complemento=?, bairro=?, "
				+ "cep=?, cidade=?, telefone1=?, telefone2=?, "
				+ "email=?, cpf_cnpj=?, rg_inscricao=?, info_adicional=?, estado_civil=?, "
				+ "titulo=?, genero=?, cnh=?, estado=?, tipo=?, data_nascimento=?, "
				+ "foto=?, contenttype=?, miniatura_imagem=? " + "where id = " + getCliente().getId();

		try {
			PreparedStatement atualiza = connection.prepareStatement(sql);
			atualiza = connection.prepareStatement(sql);

			atualiza.setString(1, getCliente().getNome());
			atualiza.setString(2, getCliente().getEndereco());
			atualiza.setString(3, getCliente().getNumero());
			atualiza.setString(4, getCliente().getComplemento());
			atualiza.setString(5, getCliente().getBairro());
			atualiza.setString(6, getCliente().getCep().replaceAll("[^0-9]", ""));
			atualiza.setString(7, getCliente().getCidade());
			atualiza.setString(8, getCliente().getTelefone1().replaceAll("[^0-9]", ""));
			atualiza.setString(9, getCliente().getTelefone2().replaceAll("[^0-9]", ""));
			atualiza.setString(10, getCliente().getEmail());
			atualiza.setString(11, getCliente().getCpfCnpj().replaceAll("[^0-9]", ""));
			atualiza.setString(12, getCliente().getRgInscricao());
			atualiza.setString(13, getCliente().getInfoAdicional());
			atualiza.setString(14, getCliente().getEstadoCivil());
			atualiza.setString(15, getCliente().getTitulo());
			atualiza.setString(16, getCliente().getGenero());
			atualiza.setString(17, getCliente().getCNH());
			atualiza.setString(18, getCliente().getEstado());
			atualiza.setInt(19, getCliente().getTipo());
			atualiza.setString(20, getCliente().getDataNascimento().replaceAll("[^0-9]", ""));

			// Se a foto foi mudada por outra, fazer a atualizacao
			if (!novaMiniaturaBase64.equalsIgnoreCase(tempMiniatura)) {
				atualiza.setString(21, getCliente().getFotoBase64());
				atualiza.setString(22, getCliente().getContentType());
				atualiza.setString(23, novaMiniaturaBase64);
			}
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
		String sql = "delete from clientes where id = " + id;
		String sql2 = "delete from telefones where id_fornec_cli = " + id;
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

		try {
			PreparedStatement declaration2 = connection.prepareStatement(sql2);
			declaration2.execute();
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

	public boolean existeClienteDuplicado() throws SQLException {
		String sql;
		if (getCliente().getId() == null) {
			sql = "select count(1) as qtd from clientes where nome = '" + getCliente().getNome() + "' and cpf_cnpj = '"
					+ getCliente().getCpfCnpj() + "'";
		} else {
			sql = "select count(1) as qtd from clientes where nome = '" + getCliente().getNome() + "' and cpf_cnpj = '"
					+ getCliente().getCpfCnpj() + "' and id != " + getCliente().getId();
		}

		PreparedStatement declaracao = connection.prepareStatement(sql);
		ResultSet qtd = declaracao.executeQuery();
		if (qtd.next()) {
			return qtd.getInt("qtd") > 0 ? true : false;
		}
		return false;
	}

	public List<Cliente> completeList() {
		String sql = "select * from clientes";
		return getListClientes(sql);

	}

	public List<Cliente> completeList(String pesquisar) throws SQLException {
		String sql = "SELECT * FROM usuario WHERE LOWER(nome) LIKE LOWER('%" + pesquisar + "%') ORDER BY nome";
		return getListClientes(sql);

	}

	public List<Cliente> getListClientes(String sql) {
		List<Cliente> lista = new ArrayList<Cliente>();

		try {
			PreparedStatement declaration = connection.prepareStatement(sql);
			ResultSet result = declaration.executeQuery();
			while (result.next()) {
				Cliente cliente = new Cliente();
				cliente.setId(result.getLong("id"));
				cliente.setTitulo(result.getString("titulo"));
				cliente.setNome(result.getString("nome"));
				cliente.setGenero(result.getString("genero"));
				cliente.setEstadoCivil(result.getString("estado_civil"));
				cliente.setDataNascimento(result.getString("data_nascimento"));
				cliente.setCep(result.getString("cep"));
				cliente.setEndereco(result.getString("endereco"));
				cliente.setNumero(result.getString("numero"));
				cliente.setComplemento(result.getString("complemento"));
				cliente.setBairro(result.getString("bairro"));
				cliente.setCidade(result.getString("cidade"));
				cliente.setEstado(result.getString("estado"));
				cliente.setTelefone1(result.getString("telefone1"));
				cliente.setTelefone2(result.getString("telefone2"));
				cliente.setEmail(result.getString("email"));
				cliente.setTipo(result.getInt("tipo"));
				cliente.setCpfCnpj(result.getString("cpf_cnpj"));
				cliente.setRgInscricao(result.getString("rg_inscricao"));
				cliente.setCNH(result.getString("cnh"));
				cliente.setInfoAdicional(result.getString("info_adicional"));
				// Incluir a lista de telefones
				cliente.setListaFone(listaTelefones(cliente.getId()));
				lista.add(cliente);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return lista;

	}

	public Cliente consult(Long id) {
		String sql = "select * from clientes where id = " + id;
		Cliente cliente = new Cliente();
		try {

			PreparedStatement declaration = connection.prepareStatement(sql);
			ResultSet result = declaration.executeQuery();
			if (result.next()) {
				cliente.setId(result.getLong("id"));
				cliente.setTitulo(result.getString("titulo"));
				cliente.setNome(result.getString("nome"));
				cliente.setGenero(result.getString("genero"));
				cliente.setEstadoCivil(result.getString("estado_civil"));
				cliente.setDataNascimento(result.getString("data_nascimento"));
				cliente.setCep(result.getString("cep"));
				cliente.setEndereco(result.getString("endereco"));
				cliente.setNumero(result.getString("numero"));
				cliente.setComplemento(result.getString("complemento"));
				cliente.setBairro(result.getString("bairro"));
				cliente.setCidade(result.getString("cidade"));
				cliente.setEstado(result.getString("estado"));
				cliente.setTelefone1(result.getString("telefone1"));
				cliente.setTelefone2(result.getString("telefone2"));
				cliente.setEmail(result.getString("email"));
				cliente.setTipo(result.getInt("tipo"));
				cliente.setCpfCnpj(result.getString("cpf_cnpj"));
				cliente.setRgInscricao(result.getString("rg_inscricao"));
				cliente.setCNH(result.getString("cnh"));
				cliente.setInfoAdicional(result.getString("info_adicional"));
				cliente.setMiniaturaImagem(result.getString("miniatura_imagem"));
				// Incluir a lista de telefones
				cliente.setListaFone(listaTelefones(cliente.getId()));
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

		return cliente;
	}

	public Cliente consultFoto(Long id) {
		String sql = "select foto, contenttype from clientes where id = " + id;
		Cliente cliente = new Cliente();
		try {

			PreparedStatement declaration = connection.prepareStatement(sql);
			ResultSet result = declaration.executeQuery();
			if (result.next()) {

				cliente.setFotoBase64(result.getString("foto"));
				cliente.setContentType(result.getString("contenttype"));
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

		return cliente;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Telefone> listaTelefones(Long id) throws SQLException {
		List<Telefone> listaTelefone = new ArrayList<Telefone>();
		String sql = "select * from telefones where id_fornec_cli = " + id;
		PreparedStatement declaration = connection.prepareStatement(sql);

		ResultSet result = declaration.executeQuery();
		while (result.next()) {
			Telefone telefone = new Telefone();
			telefone.setId(result.getLong("id"));
			telefone.setNumero(result.getString("numero"));
			telefone.setTipo(result.getString("tipo"));
			telefone.setOrigem(result.getInt("origem"));
			telefone.setChaveEstrangeira(result.getLong("id_fornec_cli"));
			listaTelefone.add(telefone);
		}

		return listaTelefone;

	}
}

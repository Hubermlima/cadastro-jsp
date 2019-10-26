package beans;

public class LoginUser {

	private Long id;
	private String login;
	private String senha;
	private String nome;
	private String telefone;

	public LoginUser() {
	}

	public LoginUser(String login, String password) {
		this.login = login;
		this.senha = password;
	}

	public LoginUser(String login, String password, String nome, String telefone) {
		this.login = login;
		this.senha = password;
		this.nome = nome;
		this.telefone = telefone;
	}

	public LoginUser(Long id, String login, String password, String nome, String telefone) {
		this.id = id;
		this.login = login;
		this.senha = password;
		this.nome = nome;
		this.telefone = telefone;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String password) {
		this.senha = password;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public boolean validation () {
		if (this.login == null || this.login.isEmpty()) {
	         	return false;
		}
		return true;
	}
}

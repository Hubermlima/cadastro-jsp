package beans;

import java.util.List;

public class Cliente {
	// Caracteristicas
	private Long id;
	private String titulo;
	private String nome;
	private String genero;
	private String estadoCivil;
	private String dataNascimento;
	// Localizacao
	private String cep;
	private String endereco;
	private String numero;
	private String complemento;
	private String bairro;
	private String cidade;
	private String estado;
	// Contato
	private String telefone1;
	private String telefone2;
	private String email;
	// Identificacao
	private int tipo;
	private String cpfCnpj;
	private String rgInscricao;
	private String CNH;
	// Info adicional
	private String infoAdicional;
	private List<Telefone> listaFone;
    
	private String fotoBase64;
	private String contentType;
	private String miniaturaImagem;
	
	public Cliente() {
	}

	public Cliente(Long id, String titulo, String nome, String genero, String estadoCivil, String dataNascimento,
			String cep, String endereco, String numero, String complemento, String bairro, String cidade, String estado,
			String telefone1, String telefone2, String email, int tipo, String cpfCnpj, String rgInscricao,
			String cNH, String infoAdicional, String fotoBase64, String contentType, String miniaturaImagem) {
		
		super();
		this.id = id;
		this.titulo = titulo;
		this.nome = nome;
		this.genero = genero;
		this.estadoCivil = estadoCivil;
		this.dataNascimento = dataNascimento;
		this.cep = cep;
		this.endereco = endereco;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.telefone1 = telefone1;
		this.telefone2 = telefone2;
		this.email = email;
		this.tipo = tipo;
		this.cpfCnpj = cpfCnpj;
		this.rgInscricao = rgInscricao;
		CNH = cNH;
		this.infoAdicional = infoAdicional;
		this.fotoBase64 = fotoBase64;
		this.contentType = contentType;
		this.miniaturaImagem = miniaturaImagem;
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	
	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getTelefone1() {
		return telefone1;
	}

	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}

	public String getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	
	public String getRgInscricao() {
		return rgInscricao;
	}

	public void setRgInscricao(String rgInscricao) {
		this.rgInscricao = rgInscricao;
	}

	public String getCNH() {
		return CNH;
	}

	public void setCNH(String cNH) {
		CNH = cNH;
	}

	public String getInfoAdicional() {
		return infoAdicional;
	}

	public void setInfoAdicional(String infoAdicional) {
		this.infoAdicional = infoAdicional;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public List<Telefone> getListaFone() {
		return listaFone;
	}

	public void setListaFone(List<Telefone> listaFone) {
		this.listaFone = listaFone;
	}

	public String getFotoBase64() {
		return fotoBase64;
	}

	public void setFotoBase64(String fotoBase64) {
		this.fotoBase64 = fotoBase64;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getMiniaturaImagem() {
		return miniaturaImagem;
	}

	public void setMiniaturaImagem(String miniaturaImagem) {
		this.miniaturaImagem = miniaturaImagem;
	}

	
}

package beans;

public class Telefone {
    Long id;
    String numero;
    String tipo;
    int origem;
    Long fK;
	
    public Telefone() {}
    
    public Telefone(Long id) {
    	this.id = id;
    }
    
    public Telefone(Long id, String numero, String tipo, int origem, Long fK) {
    	this.id = id;
    	this.numero = numero;
    	this.tipo = tipo;
    	this.origem = origem;
    	this.fK = fK;
    	
    }
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public int getOrigem() {
		return origem;
	}
	public void setOrigem(int origem) {
		this.origem = origem;
	}
	public Long getChaveEstrangeira() {
		return fK;
	}
	public void setChaveEstrangeira(Long fK) {
		this.fK = fK;
	}
    
}

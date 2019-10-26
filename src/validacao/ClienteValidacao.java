package validacao;
import dao.ClienteDao;

public abstract class ClienteValidacao {
	 
	 public static String validation(ClienteDao clienteDao) {
		 
		 String cadastroInvalido = "";
		  
		 if (clienteDao.getCliente().getNome() == null || clienteDao.getCliente().getNome().isEmpty()) {
			 cadastroInvalido = "Nome do cliente inválido (vazio)!";
		 }
		 
		 if (clienteDao.getCliente().getTitulo() == null || clienteDao.getCliente().getTitulo().isEmpty()) {
			 cadastroInvalido += "Título inválido (ou vazio)!";
		 }
		 if (clienteDao.getCliente().getGenero() == null || clienteDao.getCliente().getGenero().isEmpty()) {
			 cadastroInvalido += "Gênero inválido (ou vazio)!";
		 }
		 if (clienteDao.getCliente().getEstadoCivil() == null || clienteDao.getCliente().getEstadoCivil().isEmpty()) {
			 cadastroInvalido += "Estado civil inválido (ou vazio)!";
		 }
		 
		 if (!validateDate(clienteDao.getCliente().getDataNascimento())) {
			 cadastroInvalido += "Data de nascimento do cliente inválida (ou vazia)!";
		 }
		 if (clienteDao.getCliente().getCep() == null || clienteDao.getCliente().getCep().isEmpty()) {
			 cadastroInvalido += "Cep inválido (ou vazio)!";
		 }
		 if (clienteDao.getCliente().getEndereco() == null || clienteDao.getCliente().getEndereco().isEmpty()) {
			 cadastroInvalido += "Endereço inválido (vazio)!";
		 }
		 if (clienteDao.getCliente().getNumero() == null || clienteDao.getCliente().getNumero().isEmpty()) {
			 cadastroInvalido += "Número inválido (vazio)!";
		 }
		 if (clienteDao.getCliente().getBairro() == null || clienteDao.getCliente().getBairro().isEmpty()) {
			 cadastroInvalido += "Bairro inválido (vazio)!";
		 }
		 if (clienteDao.getCliente().getCidade() == null || clienteDao.getCliente().getCidade().isEmpty()) {
			 cadastroInvalido += "Cidade inválida (vazio)!";
		 }
		 if (clienteDao.getCliente().getEstado() == null || clienteDao.getCliente().getEstado().isEmpty()) {
			 cadastroInvalido += "Estado inválido (vazio)!";
		 }
		 if (clienteDao.getCliente().getTelefone1() == null || clienteDao.getCliente().getTelefone1().isEmpty()) {
			 cadastroInvalido += "Telefone inválido (vazio)!";
		 }
		  
		 if (clienteDao.getCliente().getCpfCnpj().length() == 11) {
			
		 }
		 else {
			 
		 }
		 return cadastroInvalido;
	}	 
		 
		 
	 public static boolean validateDate(String date) {
				
			int dia = Integer.valueOf(date.substring(0, 2));
			int mes = Integer.valueOf(date.substring(3, 5));
			int ano = Integer.valueOf(date.substring(6, 10));
			
			if (dia > 31 || mes > 12) {
				return false;
			}
			if ((mes == 4 || mes == 6 || mes == 9 || mes == 11)
					&& dia == 31) {
				return false;
			}
			if (mes == 2
					&& (dia > 29 || (dia == 29 && ano % 4 != 0))) {
				return false;
			}
			if (ano < 1900) {
				return false;
			}

			return true;

	 }

}

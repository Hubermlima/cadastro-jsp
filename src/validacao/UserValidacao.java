package validacao;
import java.sql.SQLException;
import dao.UserDao;

public abstract class UserValidacao {
	 
	 public static String validation(UserDao userDao) throws SQLException {
		 
		 String cadastroInvalido = "";
		  
		 if (userDao.getLoginUser().getNome() == null || userDao.getLoginUser().getNome().isEmpty()) {
			 cadastroInvalido = "Nome do usuário inválido (vazio)!";
		 }
		 
		 if (userDao.getLoginUser().getNome().length() > 200) {
			 cadastroInvalido += " Nome inválido (acima de 200 caracteres)!";
		 }
		 
		 if (userDao.getLoginUser().getTelefone() == null || userDao.getLoginUser().getTelefone().isEmpty()) {
			 cadastroInvalido += " Telefone inválido (vazio)!";
		 }
		  
		 if (userDao.getLoginUser().getLogin() == null || userDao.getLoginUser().getLogin().isEmpty()) {
			 cadastroInvalido += " Login inválido (vazio)!";
		 }
		 
		 if (userDao.getLoginUser().getLogin().length() > 50) {
			 cadastroInvalido += " Login inválido (acima de 50 caracteres)!";
		 }
		
		 if (userDao.getLoginUser().getSenha() == null || userDao.getLoginUser().getSenha().isEmpty()) {
			 cadastroInvalido += " Senha inválida (vazio)!";
		 }
		 
		 if (userDao.getLoginUser().getSenha().length() > 10) {
			 cadastroInvalido += " Senha inválida (acima de 10 caracteres)!";
		 }
		 
		 if (userDao.existeLoginDuplicado()) {
			 cadastroInvalido += " Login já cadastrado!";
		 }
		 if (userDao.existeSenhaDuplicada()) {
			 cadastroInvalido += " Senha já cadastrada!";
		 }
		
		return cadastroInvalido;	 
     }

		 
	 
}

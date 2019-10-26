package validacao;
import java.sql.SQLException;
import dao.UserDao;

public abstract class UserValidacao {
	 
	 public static String validation(UserDao userDao) throws SQLException {
		 
		 String cadastroInvalido = "";
		  
		 if (userDao.getLoginUser().getNome() == null || userDao.getLoginUser().getNome().isEmpty()) {
			 cadastroInvalido = "Nome do usu�rio inv�lido (vazio)!";
		 }
		 
		 if (userDao.getLoginUser().getNome().length() > 200) {
			 cadastroInvalido += " Nome inv�lido (acima de 200 caracteres)!";
		 }
		 
		 if (userDao.getLoginUser().getTelefone() == null || userDao.getLoginUser().getTelefone().isEmpty()) {
			 cadastroInvalido += " Telefone inv�lido (vazio)!";
		 }
		  
		 if (userDao.getLoginUser().getLogin() == null || userDao.getLoginUser().getLogin().isEmpty()) {
			 cadastroInvalido += " Login inv�lido (vazio)!";
		 }
		 
		 if (userDao.getLoginUser().getLogin().length() > 50) {
			 cadastroInvalido += " Login inv�lido (acima de 50 caracteres)!";
		 }
		
		 if (userDao.getLoginUser().getSenha() == null || userDao.getLoginUser().getSenha().isEmpty()) {
			 cadastroInvalido += " Senha inv�lida (vazio)!";
		 }
		 
		 if (userDao.getLoginUser().getSenha().length() > 10) {
			 cadastroInvalido += " Senha inv�lida (acima de 10 caracteres)!";
		 }
		 
		 if (userDao.existeLoginDuplicado()) {
			 cadastroInvalido += " Login j� cadastrado!";
		 }
		 if (userDao.existeSenhaDuplicada()) {
			 cadastroInvalido += " Senha j� cadastrada!";
		 }
		
		return cadastroInvalido;	 
     }

		 
	 
}

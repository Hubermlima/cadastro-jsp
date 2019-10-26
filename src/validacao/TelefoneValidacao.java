package validacao;
import java.sql.SQLException;
import dao.TelefoneDao;

public abstract class TelefoneValidacao {
	 
	 public static String validation(TelefoneDao telefoneDao) throws SQLException {
		 
		 String telefoneInvalido = "";
		  
		 if (telefoneDao.getTelefone().getNumero() == null || telefoneDao.getTelefone().getNumero().isEmpty()) {
			 telefoneInvalido = "N�mero telefone inv�lido (vazio)!";
		 }
		 
		 if (telefoneDao.telefoneDuplicado()) {
			 telefoneInvalido += "N�mero telefone duplicado!";
		 }
		 
		 return telefoneInvalido;
	}	 
		 
}

package validacao;

public abstract class LoginValidacao {
	 
	 public static boolean validation(String user, String password) {
		 
		 if (user.isEmpty() || password.isEmpty() ) {
			 return false;
		 }
		return true;
     }

		 
	 
}

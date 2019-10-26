package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.LoginUser;
import dao.UserAuthentication;
import validacao.LoginValidacao;

/**
 * Servlet implementation class login
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String user = request.getParameter("login");
		String password = request.getParameter("password");
		LoginUser usuarioLogado;

		if (LoginValidacao.validation(user, password)) {

			try { 
				
				usuarioLogado = UserAuthentication.addUser(user, password);	
				
				if (usuarioLogado == null) {

					response.sendRedirect("/cadastro-jsp");

				} else {
					
					request.getSession().setAttribute("usuarioLogado", usuarioLogado);
					response.sendRedirect("menu-principal.jsp");
				
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}


}

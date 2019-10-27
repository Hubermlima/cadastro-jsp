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
import mail.SendMailServiceGmail;
import mail.SendMailServiceInject;
import validacao.LoginValidacao;

/**
 * Servlet implementation class login
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	String from = "huber.produtos@gmail.com";
	String password = "1ATL389GUH";
	String to = "cerealistaportela@gmail.com";
	

	public LoginServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (Boolean.parseBoolean(request.getParameter("deslogar"))) {
			request.getSession().invalidate();
			response.sendRedirect("/cadastro-jsp");
		}

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
					/*
					 * Enviar email de confirmação que um novo usuário se logou no sistema
					*/
					confirmUserByEmail();

					response.sendRedirect("menu-principal.jsp");

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	} 

	private void confirmUserByEmail() {

		// Foi verificado que o Avast estava interferindo o envio do e-mail
		SendMailServiceInject sendEmailServiceInject = new SendMailServiceInject(new SendMailServiceGmail("huber.produtos@gmail.com", "1ATL389GUH", "cerrealistaportela@gmail.com", "Mail from java", "Hi!"));
		sendEmailServiceInject.getSendMailService().send();
		
	} 

}


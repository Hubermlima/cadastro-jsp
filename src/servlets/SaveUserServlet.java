package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.LoginUser;
import dao.UserDao;
import validacao.UserValidacao;

/**
 * Servlet implementation class SaveUserServlet
 */
@WebServlet("/SaveUserServlet")
public class SaveUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SaveUserServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("acao");
		String id = request.getParameter("user");

		LoginUser loginUser = new LoginUser();
		UserDao userDao = new UserDao();

		if (acao.equalsIgnoreCase("delete")) {
			userDao.delet(Long.valueOf(id));

			try {

				RequestDispatcher view = request.getRequestDispatcher("cadastro-usuario.jsp");
				request.setAttribute("usuarios", userDao.completeList());
				request.setAttribute("mensagem", "Usuário #" + id + " apagado com sucesso!");
				view.forward(request, response);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (acao.equalsIgnoreCase("editar")) {
			try {
				loginUser = userDao.consult(Long.valueOf(id));
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			RequestDispatcher view = request.getRequestDispatcher("cadastro-usuario.jsp");
			request.setAttribute("usuario", loginUser);
			view.forward(request, response);

		} else if (acao.equalsIgnoreCase("listarTodos")) {
			try {

				RequestDispatcher view = request.getRequestDispatcher("cadastro-usuario.jsp");
				request.setAttribute("usuarios", userDao.completeList());
				view.forward(request, response);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("acao");
		String id = request.getParameter("id");
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		String nome = request.getParameter("nome");
		String telefone = request.getParameter("telefone");

		String cadastroInvalido = null;

		LoginUser loginUser = new LoginUser(id.isEmpty() ? null : Long.valueOf(id), login, senha, nome, telefone);
		UserDao userDao = new UserDao(loginUser);
System.out.println("estou no servidor!");
		if (acao != null && acao.equalsIgnoreCase("reset")) { // Operação de cancelamento
			try {

				RequestDispatcher view = request.getRequestDispatcher("cadastro-usuario.jsp");
				request.setAttribute("usuarios", userDao.completeList());
				request.setAttribute("mensagem", "Operação cancelada!");
				view.forward(request, response);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			try {
				cadastroInvalido = UserValidacao.validation(userDao);
				if (!cadastroInvalido.isEmpty()) {
					request.setAttribute("mensagem", cadastroInvalido);
				} else if (loginUser.getId() == null) {
					userDao.insert();
					request.setAttribute("mensagem", "Inclusão realizada com sucesso!");
				} else {
					userDao.update();
					request.setAttribute("mensagem", "Atualização realizada com sucesso!");
				}

			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			if (!cadastroInvalido.isEmpty()) { // Cadastro com restrições
				request.setAttribute("usuario", loginUser);
			}

			try {

				RequestDispatcher view = request.getRequestDispatcher("cadastro-usuario.jsp");
				request.setAttribute("usuarios", userDao.completeList());
				view.forward(request, response);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	} // fim doPost
} // fim classe

package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import beans.Telefone;
import dao.TelefoneDao;
import validacao.TelefoneValidacao;


/**
 * Servlet implementation class login
 */
@WebServlet("/ListaTelefonesServlet")
public class ListaTelefonesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String codStatus = "0";
	private String status = "";

	public ListaTelefonesServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			Long id = Long.valueOf(request.getParameter("id").trim());
			TelefoneDao telefoneDao = new TelefoneDao();
			telefoneDao.delet(id);
			
			status = "Exclusão bem sucedida!";
            
			response.setStatus(200);
		    response.setContentType("status/plain");  
		    response.setCharacterEncoding("UTF-8"); 
		    response.getWriter().write(status);

		} catch (Exception e) {
			response.setStatus(500);
			response.getWriter().write(e.getMessage());  
		}
		
				
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String cadastroInvalido = null;
		codStatus = "0";
		status = "";
		
		String id = request.getParameter("id").trim();
		String numero = request.getParameter("telefoneName").trim();
		String tipo = request.getParameter("classification").trim();
		int origem = 0;
		Long fK = Long.valueOf(request.getParameter("foreignKey").trim());

		Telefone telefone = new Telefone(id.isEmpty()? null : Long.valueOf(id), numero, tipo, origem, fK);
		TelefoneDao telefoneDao = new TelefoneDao(telefone);
		
		try {
			cadastroInvalido = TelefoneValidacao.validation(telefoneDao);
			if (!cadastroInvalido.isEmpty()) {
				codStatus = "1";
				status = "Telefone inválido!";
			} else if (id.isEmpty()) {
				telefoneDao.insert();
				try {
				       telefone.setId(telefoneDao.getMaxId());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				status = "Inclusão realizada com sucesso!";
			} else {
				telefoneDao.update();
				status = "Atualização realizada com sucesso!";
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
		// Criando pacote de envio ->
		Map<String, Object> pacote = new HashMap<>();
		pacote.put("1", codStatus);
		pacote.put("2", status);
		pacote.put("3", telefone);
		
		String json = new Gson().toJson(pacote);
		response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(json);
	   
	}
	

	public String getCodStatus() {
		return codStatus;
	}

	public void setCodStatus(String codStatus) {
		this.codStatus = codStatus;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}

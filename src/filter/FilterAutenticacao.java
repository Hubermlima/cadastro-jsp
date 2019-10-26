package filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.LoginUser;
import connection.SingleConnection;

@WebFilter(urlPatterns={"/*"})
public class FilterAutenticacao implements Filter {

	private static Connection connection;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		try {
			
			HttpServletRequest req = (HttpServletRequest) request;
			HttpSession session = req.getSession();
			LoginUser usuarioLogado = (LoginUser) session.getAttribute("usuarioLogado");
			String url = req.getServletPath();
			
			if (usuarioLogado == null && !url.equalsIgnoreCase("/index.jsp") && !url.equalsIgnoreCase("/LoginServlet") ) {

				HttpServletResponse res = (HttpServletResponse) response;
				res.sendRedirect("/cadastro-jsp");
				return;
				
			}
			
			chain.doFilter(request, response);
			connection.commit();

		} catch (Exception e) {
			try {
				e.printStackTrace();
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		connection = SingleConnection.getConnection();

	}

}

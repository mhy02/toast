package basecamp.servlets;

import basecamp.regex.EmailValidator;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/message/add")
public class MessageAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doPost(
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection conn = null;
		PreparedStatement stmt = null;
		EmailValidator val = new EmailValidator();
		
		try {
			if(val.validate(request.getParameter("email"))) {
				ServletContext sc = this.getServletContext();
		
				conn = (Connection) sc.getAttribute("conn");
				
				stmt = conn.prepareStatement("INSERT INTO MESSAGE VALUE (NULL, ?, ?, ?, now(), now())");
				stmt.setString(1, request.getParameter("email"));			
				stmt.setString(2, request.getParameter("password"));
				stmt.setString(3, request.getParameter("content"));
				
				stmt.executeUpdate();
			}	
			
			response.sendRedirect("list");
			
		} catch (Exception e) {
			throw new ServletException(e);
		} finally {
			try {if (stmt != null) stmt.close();} catch(Exception e) {}
		}

	}
}

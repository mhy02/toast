package basecamp.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import basecamp.vo.Message;

@WebServlet("/message/list")
public class MessageListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			ServletContext sc = this.getServletContext();
	
			conn = (Connection) sc.getAttribute("conn");
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(
					"SELECT NO, EMAIL, CONTENT, CRE_DATE, MOD_DATE" + 
					" FROM MESSAGE" +
					" ORDER BY NO DESC");
			
			response.setContentType("text/html; charset=UTF-8");
			ArrayList<Message> messages = new ArrayList<Message>();
			
			while(rs.next()) {
				messages.add(new Message()
							.setNo(rs.getInt("no"))
							.setEmail(rs.getString("email"))
							.setContent(rs.getString("content"))
							.setCreatedDate(rs.getDate("cre_date"))
							.setModifiedDate(rs.getDate("mod_date"))
						);
				
			}
			request.setAttribute("messages", messages);
			
			RequestDispatcher rd = request.getRequestDispatcher("/messageList.jsp");
			rd.include(request, response);
			
		} catch (Exception e) {
			throw new ServletException(e);
		} finally {
			try {if (rs != null) rs.close();} catch(Exception e) {}
			try {if (stmt != null) stmt.close();} catch(Exception e) {}
		}

	}
}

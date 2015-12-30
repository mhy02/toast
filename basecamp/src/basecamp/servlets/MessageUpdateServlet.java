package basecamp.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import basecamp.vo.Message;

@WebServlet("/message/update")
public class MessageUpdateServlet extends HttpServlet {
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
					" WHERE NO=" + request.getParameter("no"));
			
			response.setContentType("text/html; charset=UTF-8");
			
			rs.next();
			
			Message message = new Message()
							.setNo(rs.getInt("no"))
							.setEmail(rs.getString("email"))
							.setContent(rs.getString("content"))
							.setCreatedDate(rs.getDate("cre_date"))
							.setModifiedDate(rs.getDate("mod_date"));
										

			request.setAttribute("message", message);
		
			RequestDispatcher rd = request.getRequestDispatcher("/messageUpdateForm.jsp");
			rd.forward(request, response);
			
		} catch (Exception e) {
			throw new ServletException(e);
		} finally {
			try {if (rs != null) rs.close();} catch(Exception e) {}
			try {if (stmt != null) stmt.close();} catch(Exception e) {}
		}
	}
	
	
	@Override
	public void doPost(
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			ServletContext sc = this.getServletContext();
	
			conn = (Connection) sc.getAttribute("conn");
			
			stmt = conn.prepareStatement("UPDATE MESSAGE SET CONTENT=?, MOD_DATE=NOW() WHERE NO=? AND PASSWORD=?");
			stmt.setString(1, request.getParameter("content"));
			stmt.setInt(2, Integer.parseInt(request.getParameter("no")));
			stmt.setString(3, request.getParameter("password"));
			stmt.executeUpdate();
			
			response.sendRedirect("list");
			
		} catch (Exception e) {
			throw new ServletException(e);
		} finally {
			try {if (stmt != null) stmt.close();} catch(Exception e) {}
		}

	}
}

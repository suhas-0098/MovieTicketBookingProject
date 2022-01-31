import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/updateticket")
public class UpdateTicketServlet extends HttpServlet{
	
	public void service(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException {
		
		res.setContentType("text/html");
		PrintWriter out =  res.getWriter();
		
		
		HttpSession session=req.getSession(false);
		String uname = (String)session.getAttribute("uname");
        String bookingid = req.getParameter("bookingid");
		session.setAttribute("bookingid", bookingid);
		
		out.println("<h1 align=center>Welcome "+uname+"</h1");
		out.println("<br>");
		out.println("<h1 align=center>Updating Tickets</h1");
		out.println("<br>");
		out.println("<h1 align=center>Booking id "+bookingid+"</h1");
		out.println("<br>");
		out.println("<center>");
		out.println("<form method=post action=updateticket1>");
		out.println("Number of tickets :<input type='number' name='ticketscount' required>");
		out.println("<br>");
		out.println("<input type='submit' value='update' required>");
		out.println("</form>");
		out.println("<center>");
		
String status="UPDATED";		
	
		
		
	}

}

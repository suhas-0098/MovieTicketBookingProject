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

@WebServlet("/cancelticket")
public class CancelTicketServlet extends HttpServlet{
	
	public void service(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException {
		
		res.setContentType("text/html");
		PrintWriter out =  res.getWriter();
		
		
		HttpSession session=req.getSession(false);
		String uname = (String)session.getAttribute("uname");
		
		out.println("<h1 align=right>Welcome "+uname+"</h1");
		
		String bookingid = req.getParameter("bookingid");
		
		String status = "CANCELLED";
		
		try
	    {
	      
	    	Connection con = null;
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("dr");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jfsdskill4","root","Asuhas@123");
			System.out.println("dr");
		 
	      PreparedStatement pstmt =con.prepareStatement("update ticketbooking set status=? where bookingid=?");
	      pstmt.setString(1, status);
	      pstmt.setString(2, bookingid);
	      
	      int i=pstmt.executeUpdate();
	       
	       if(i>0)
	       {
	        out.println("<b>Tickets Cancelled  Successfully</b><br>");
	        out.println("<a href='booktickets'>BACK</a>");
	       }
	       else {
	         out.println("Not Cancelled");
	       }
	    }
		catch(Exception e) {
			out.println(e);
		}
		
	}

}

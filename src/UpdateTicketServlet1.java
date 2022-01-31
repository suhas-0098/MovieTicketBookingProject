import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/updateticket1")
public class UpdateTicketServlet1 extends HttpServlet{
	
	public void service(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException {
		
		res.setContentType("text/html");
		PrintWriter out =  res.getWriter();
		
		
		HttpSession session=req.getSession(false);
		String uname = (String)session.getAttribute("uname");
		
		String bookingid = (String)session.getAttribute("bookingid");
		
		int ticketscount = Integer.parseInt(req.getParameter("ticketscount"));
		
		
		String status="UPDATED";
		double ticketprice=0.0;
		int theatreid=0;
		int capacity=0;
		
		try {
			
			Connection con = null;
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("dr");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jfsdskill4","root","Asuhas@123");
			System.out.println("dr");
			
			PreparedStatement pstmt = con.prepareStatement("select theatreid from ticketbooking where bookingid=?");
			pstmt.setString(1,bookingid);
			
			ResultSet rs = pstmt.executeQuery();
		
			
			if(rs.next()) {
			   theatreid=rs.getInt(1);
			}
		}
		catch(Exception e) {
			out.println(e);
		}
		
		
	
		
try {
	    	
	    	Connection con = null;
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("dr");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jfsdskill4","root","Asuhas@123");
			System.out.println("dr");
			
			PreparedStatement pstmt = con.prepareStatement("select ticketprice,capacity from theatre where theatreid=?");
			pstmt.setInt(1, theatreid);
			
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				capacity=rs.getInt(2);
				ticketprice=rs.getDouble(1);
			}
			
			
	    }
	    catch(Exception e) {
	    	out.println(e);
	    }
		
		
		
		
		
		double totalprice = ticketscount*ticketprice;
		
		
		
		
		try {
			Connection con = null;
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("dr");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jfsdskill4","root","Asuhas@123");
			System.out.println("dr");
		 
	      PreparedStatement pstmt =con.prepareStatement("update ticketbooking set status=?,ticketscount=? ,price=? where bookingid=?");
	      pstmt.setString(1,status);
	      pstmt.setInt(2, ticketscount);
	      pstmt.setDouble(3, totalprice);
	      pstmt.setString(4, bookingid);
	      
	      int i=pstmt.executeUpdate();
	       
	       if(i>0)
	       { 
	        out.println("<b>Tickets Updated Successfully</b><br>");
	        out.println("<a href='booktickets'>BACK</a>");
	       }
	       else {
	         out.println("Not Updated");
	       }
			
		}
		catch(Exception e) {
			out.println(e);
		}
		
		
	}

}

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

@WebServlet("/ticketbooking1")
public class BookTicketsServlet1 extends HttpServlet{
	
	public void service(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException {
		
		res.setContentType("text/html");
		PrintWriter out =  res.getWriter();
		
		
	    int value = (int)((Math.random()*999999))+1;
	    String bookingid = "TKT"+value;
	    
	    HttpSession session=req.getSession(false);
		String customeruname = (String)session.getAttribute("uname");
		
		int movieid = Integer.parseInt(req.getParameter("movieid"));
		
		int theatreid = Integer.parseInt(req.getParameter("theatreid"));
		
		String showdate = req.getParameter("showdate");
		
		String showslot = req.getParameter("showslot");
		
		int ticketscount = Integer.parseInt(req.getParameter("ticketscount"));
		
		int capacity=0;
		double ticketprice=0.0;
		
		int ticketbooked=0;
		String status="BOOKED";
      
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
			
			PreparedStatement pstmt = con.prepareStatement("select sum(ticketscount) from ticketbooking where theatreid=? and movieid=? and showslot=? and status!=? and showdate=?");
			pstmt.setInt(1, theatreid);
			pstmt.setInt(2, movieid);
			pstmt.setString(3, showslot);
			pstmt.setString(4, "CANCELLED");//staus!=cancelled
			pstmt.setString(5, showdate);
			
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				ticketbooked = rs.getInt(1);
			}
			
			
	    }
	    catch(Exception e) {
	    	out.println(e);
	    }
		
		if(ticketbooked+ticketscount<capacity) { 
			
	    try {
	      
	    	Connection con = null;
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("dr");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jfsdskill4","root","Asuhas@123");
			System.out.println("dr");
	      PreparedStatement psmt = con.prepareStatement("insert into ticketbooking(bookingid,customeruname, movieid,theatreid,showdate,showslot,ticketscount,price,status)  values(?,?,?,?,?,?,?,?,?) ");
	        psmt.setString(1,bookingid);
	        psmt.setString(2,customeruname);
	        psmt.setInt(3,movieid);
	        psmt.setInt(4,theatreid);
	        psmt.setString(5, showdate);
	        psmt.setString(6,showslot);
	        psmt.setInt(7,ticketscount);
	        psmt.setDouble(8,totalprice);
	        psmt.setString(9, status);
	        
	      
	        
	       
	       
	       
	       int i=psmt.executeUpdate();
	       
	       if(i>0)
	       {
	        out.println("<b>Tickets Booked  Successfully</b><br>");
	        out.println("<a href='booktickets'>BACK</a>");
	       }
	       else {
	         out.println("Not Added Theatre");
	       }
	    }
	    catch(Exception e)
	    {
	      out.println("Exception: "+e);
	    }
	    
		
			
		}
		else {
			out.println("<b>Unable to Book a Ticket as NO Tickets are available</b><br>");
	        out.println("<a href='booktickets'>BACK</a>");
	     
			
			
		}
		
	}

}

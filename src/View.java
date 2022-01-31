import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/viewticketsbyadmin")
public class View extends HttpServlet
{
  
  public void service(HttpServletRequest req,HttpServletResponse res) throws IOException
  {
    res.setContentType("text/html");
    PrintWriter out = res.getWriter();
    
   
    
    out.println("<h1 align=center>View  Tickets </h1>");
    
    out.println("<table border=1 align=center>");
    out.println("<tr>");
    out.println("<th>BOOKING ID</th>");
    out.println("<th>MOVIE ID</th>");
    out.println("<th>THEATER ID</th>");
    out.println("<th>SHOW DATE</th>");
    out.println("<th>SHOW SLOT</th>");
    out.println("<th>NO.OF TICKETS</th>");
    out.println("<th>PRICE</th>");
    out.println("<th>STATUS</th>");
    out.println("<th>TRANSACTION TIME</th>");
 
    
    
    out.println("</tr>");
    
    int totaltickets=0;
    Double totalmoney=0.0;
	
	
    try
    {
      
    	Connection con = null;
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("dr");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jfsdskill4","root","Asuhas@123");
		System.out.println("dr");
	 
      PreparedStatement pstmt =con.prepareStatement("select * from ticketbooking where status!=?");
      pstmt.setString(1, "CANCELLED");
      
      
      
      
      ResultSet rs = pstmt.executeQuery();
      while(rs.next()) {
        out.println("<tr>");
        out.println("<td>"+rs.getString(1)+"</td>");
        out.println("<td>"+rs.getInt(3)+"</td>");
        out.println("<td>"+rs.getInt(4)+"</td>");
        out.println("<td>"+rs.getString(5)+"</td>");
        out.println("<td>"+rs.getString(6)+"</td>");
        out.println("<td>"+rs.getInt(7)+"</td>");
        out.println("<td>"+rs.getDouble(8)+"</td>");
        out.println("<td>"+rs.getString(9)+"</td>");
        out.println("<td>"+rs.getString(10)+"</td>");
        
            
        out.println("</tr>");
        
      }
      
      
      
      
    }
    catch(Exception e)
    {
      out.println("exception:"+e);
    }
        
    out.println("</table>");
        
        
    
    try
    {
      
    	Connection con = null;
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("dr");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jfsdskill4","root","Asuhas@123");
		System.out.println("dr");
	 
		
		
		PreparedStatement pstmt = con.prepareStatement("select sum(ticketscount) from ticketbooking where status!=?");
		
		pstmt.setString(1, "CANCELLED");
		ResultSet rs = pstmt.executeQuery();
		
		if(rs.next()) {
			
			totaltickets = rs.getInt(1);
			
		}
		
      
      
      
    }
    catch(Exception e)
    {
      out.println("exception:"+e);
    }
        
        
    
    try
    {
      
    	Connection con = null;
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("dr");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jfsdskill4","root","Asuhas@123");
		System.out.println("dr");
	 
		
		
		PreparedStatement pstmt = con.prepareStatement("select sum(price) from ticketbooking where status!=?");
		
		pstmt.setString(1, "CANCELLED");
		ResultSet rs = pstmt.executeQuery();
		
		if(rs.next()) {
			
			totalmoney = rs.getDouble(1);
			
		}
		
      
      
      
    }
    catch(Exception e)
    {
      out.println("exception:"+e);
    }
        
    
    out.println("<h1>Total tickets sold="+totaltickets+"</h1><br>");
    out.println("<h1>Total money earned="+totalmoney+"</h1>");
        
  }
  
}
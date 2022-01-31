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


@WebServlet("/viewcustomertickets")
public class ViewCustomerTicketsServlet extends HttpServlet
{
  
  public void service(HttpServletRequest req,HttpServletResponse res) throws IOException
  {
    res.setContentType("text/html");
    PrintWriter out = res.getWriter();
    
    HttpSession session=req.getSession(false);
	String uname = (String)session.getAttribute("uname");
	 out.println("<h1 align=center>Welcome "+uname+ "</h1>");
	
    
    out.println("<h1 align=center>View Customers Tickets </h1>");
    
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
    out.println("<th>ACTION</th>");
    
    
    out.println("</tr>");
    try
    {
      
    	Connection con = null;
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("dr");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jfsdskill4","root","Asuhas@123");
		System.out.println("dr");
	 
      PreparedStatement pstmt =con.prepareStatement("select * from ticketbooking where customeruname=? and status!=?");
      pstmt.setString(1, uname);
      pstmt.setString(2, "CANCELLED");
      
      
      
      
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
        
        out.println("<td><a href='cancelticket?bookingid="+rs.getString(1)+"'>CANCEL</a>");
        out.println("&nbsp;&nbsp;");
        out.println("<a href='updateticket?bookingid="+rs.getString(1)+"'>UPDATE</a></td>");
        
        out.println("</tr>");
        
      }
      
      
      
      
    }
    catch(Exception e)
    {
      out.println("exception:"+e);
    }
        
    out.println("</table>");
        
        
        
  }
}
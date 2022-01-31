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


@WebServlet("/viewcustomer")
public class ViewCustomer extends HttpServlet
{
  
  public void service(HttpServletRequest req,HttpServletResponse res) throws IOException
  {
    res.setContentType("text/html");
    PrintWriter out = res.getWriter();
    
    out.println("<h1 align=center>View Customers</h1>");
    out.println("<table border=1 align=center>");
    out.println("<tr>");
    out.println("<th>ID</th>");
    out.println("<th>Name</th>");
    out.println("<th>Gender</th>");
    out.println("<th>Username</th>");
    out.println("<th>Email Id</th>");
    out.println("<th>Mobile No</th>");
    out.println("<th>Location</th>");
    
    
    out.println("</tr>");
    try
    {
      
    	Connection con = null;
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("dr");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jfsdskill4","root","Asuhas@123");
		System.out.println("dr");
	 
      PreparedStatement pstmt =con.prepareStatement("select * from customer");
      
      
      ResultSet rs = pstmt.executeQuery();
      while(rs.next()) {
        out.println("<tr>");
        out.println("<td>"+rs.getInt(1)+"</td>");
        out.println("<td>"+rs.getString(2)+"</td>");
        out.println("<td>"+rs.getString(3)+"</td>");
        out.println("<td>"+rs.getString(4)+"</td>");
        out.println("<td>"+rs.getString(5)+"</td>");
        out.println("<td>"+rs.getString(7)+"</td>");
        out.println("<td>"+rs.getString(8)+"</td>");
        
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
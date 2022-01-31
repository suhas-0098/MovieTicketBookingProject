import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/adddd")
public class AddTheaterServlet extends HttpServlet
{
  
  public void service(HttpServletRequest req,HttpServletResponse res) throws IOException
  {
    res.setContentType("text/html");
    PrintWriter out = res.getWriter();
    
    
    
    
    String tname=req.getParameter("tname");
    String year=req.getParameter("year");
    int year1=Integer.parseInt(year);
    String address=req.getParameter("address");
    
    String pincode=req.getParameter("pincode");
    String capacity=req.getParameter("capacity");
    String tprice=req.getParameter("tprice");
   
    
    try {
      
    	Connection con = null;
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("dr");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jfsdskill4","root","Asuhas@123");
		System.out.println("dr");
      PreparedStatement psmt = con.prepareStatement("insert into theatre(name, establishedyear,address,pincode,capacity,ticketprice)  values(?,?,?,?,?,?) ");
        psmt.setString(1,tname);
        psmt.setInt(2,year1);
        psmt.setString(3,address);
        psmt.setString(4, pincode);
        psmt.setString(5,capacity);
        psmt.setString(6,tprice);
      
        
       
       
       
       int i=psmt.executeUpdate();
       
       if(i>0)
       {
        out.println("<b>Theatre Record Added Successfully</b><br>");
        out.println("<a href='addtheatre.html'>Add Another Record ?</a>");
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
}
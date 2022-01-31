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


@WebServlet("/addmovie")
public class AddMovieServlet extends HttpServlet
{
  
  public void service(HttpServletRequest req,HttpServletResponse res) throws IOException
  {
    res.setContentType("text/html");
    PrintWriter out = res.getWriter();
    
    String name = req.getParameter("name");
    String language=req.getParameter("language");
    String genre = req.getParameter("genre");
    String director = req.getParameter("director");
    String producer = req.getParameter("producer");
    String cast = req.getParameter("cast");
    String rdate = req.getParameter("rdate");
    String cbrating = req.getParameter("cbrating");
    
    
    
    
    
    
  
   
    
    try {
      
    	Connection con = null;
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("dr");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jfsdskill4","root","Asuhas@123");
		System.out.println("dr");
   PreparedStatement psmt = con.prepareStatement("insert into movie(name,language,genre,director,producer,cast,releasedate,censorboardrating) values(?,?,?,?,?,?,?,?)");
   
        psmt.setString(1,name);
        psmt.setString(2,language);
        psmt.setString(3,genre);
        psmt.setString(4,director);
        psmt.setString(5,producer);
        psmt.setString(6,cast);
        psmt.setString(7,rdate);
        psmt.setString(8,cbrating);
       
       
       
       int i=psmt.executeUpdate();
       
       if(i>0)
       {
    	   out.println("<b>Movie Record Added Successfully</b><br>");
           out.println("<a href='addmovies.html'>Add Another Record ?</a>");
          
       }
       else {
       
    	   out.println("Not Added");
       }
    }
    catch(Exception e)
    {
      out.println("Exception: "+e);
    }
    
  }
}
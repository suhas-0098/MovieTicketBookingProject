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


@WebServlet("/addmanager")
public class AddManagerServlet extends HttpServlet{
	
	public void service(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException
	  {
	    res.setContentType("text/html");
	    PrintWriter out = res.getWriter();
	    
	    RequestDispatcher rd = req.getRequestDispatcher("superadminmenu.html");
	    rd.include(req,res);
	    
	    out.println("<h1 style='color:white;font-family:sans-serif; text-align:center;'>Add Theater Manager</h1>");
	    out.println("<center>");
	    out.println("<form method='post' action='addtmanager1'>");
	    out.println("<table>");
	    
	    
	    
	    RequestDispatcher rd1 = req.getRequestDispatcher("addmanager.html");
	    rd1.include(req,res);
	    
	    
	    try {
	        
	    	Connection con = null;
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("dr");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jfsdskill4","root","Asuhas@123");
			System.out.println("dr");
			
			PreparedStatement pstmt = con.prepareStatement("select * from theatre");
			
			ResultSet rs = pstmt.executeQuery();
			
			out.println("<tr>");
			out.println("<td> Theater");
			out.println("<select name='theatreid'>");
			out.println("<option value=' '>---select----</option>");
			
			while(rs.next()) {
			
		out.println("<option value="+rs.getString("theatreid")+">");
		 out.println(rs.getString("name"));
		
		out.println("</option>");
			}
			out.println("</select>");
			out.println("</td>");
			out.println("</tr>");
			
	    }
	    
	    catch(Exception e) {
	    	out.println(e);
	    	
	    }
	    
	    
	    
	   
	    
	    out.println("<tr><td align=center><input type='submit' value='Add'></td></tr>");
	    
	    out.println("</table>");
	    out.println("</form>");
	    out.println("</center>");
	    

	    
	    
	  }

}

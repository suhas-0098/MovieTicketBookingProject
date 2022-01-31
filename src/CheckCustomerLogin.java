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
import javax.servlet.http.HttpSession;


@WebServlet("/CheckCustomerLogin")
public class CheckCustomerLogin extends HttpServlet {
	
	public void service(HttpServletRequest req,HttpServletResponse res) throws IOException {
		
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		
		String uname = req.getParameter("uname");
		String pwd = req.getParameter("pwd");
		
		 try {
		    	
		    	Connection con = null;
				Class.forName("com.mysql.jdbc.Driver");
				System.out.println("dr");
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jfsdskill4","root","Asuhas@123");
				System.out.println("dr");
				
				PreparedStatement pstmt = con.prepareStatement("select * from customer where username=? and password=?");
				pstmt.setString(1, uname);
				pstmt.setString(2,pwd);
				
				ResultSet rs = pstmt.executeQuery();
				
				if(rs.next()) {
					
					HttpSession session = req.getSession();
					
					session.setAttribute("uname",uname);
					 
					 
					
					out.println("login in");
					RequestDispatcher rd = req.getRequestDispatcher("customerhome");
					rd.forward(req, res);
					
				}
				else {
					out.println("invalid login ");
					out.println("<a href=customerlogin.html> try agin </a>");
					
					
					
				}
				
				
		    }
		    catch(Exception e) {
		    	out.println(e);
		    }
	}

}

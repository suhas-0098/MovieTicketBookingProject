import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.sql.DriverManager;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/customerreg")
public class CustomerRegServlet extends HttpServlet{
	public void service(HttpServletRequest req,HttpServletResponse res) throws IOException {
		
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		
		String name = req.getParameter("name");
		String gender = req.getParameter("gender");
		String uname = req.getParameter("uname");
		String pwd = req.getParameter("pwd");
		String email = req.getParameter("email");
		String mobile = req.getParameter("mobile");
		String location = req.getParameter("location");
		
		
		try
		{
			Connection con = null;
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("dr");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jfsdskill4","root","Asuhas@123");
			System.out.println("dr");
		 
			
			PreparedStatement pstmt = con.prepareStatement("insert into customer(name,gender,username,email,password,mobileno,location) values(?,?,?,?,?,?,?)"); 
			pstmt.setString(1, name);
			pstmt.setString(2, gender);
			pstmt.setString(3,uname);
			pstmt.setString(4, email);
			pstmt.setString(5, pwd);
			pstmt.setString(6, mobile);
			pstmt.setString(7, location);
			
			int i=pstmt.executeUpdate();
			
			if(i>0) {
				res.sendRedirect("customerlogin.html");
			}
			
		}
		catch(Exception e) {
			out.println(e);
		}
		
	}
	
	
}
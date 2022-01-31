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

@WebServlet("/booktickets")
public class BookTicketsServlet  extends HttpServlet{
		
		public void service(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException {
			
			res.setContentType("text/html");
			PrintWriter out =  res.getWriter();
			
				
			
			  RequestDispatcher rd = req.getRequestDispatcher("customermenu.html");
			    rd.include(req,res);
			    
			    HttpSession session=req.getSession(false);
				String uname = (String)session.getAttribute("uname");
				
				out.println("<h1 align=right>Welcome "+uname+"</h1");
				out.println("<h1 style='color:white;font-family:sans-serif; text-align:center;'>Ticket Booking </h1>");
			
			    
			    out.println("<center>");
			    out.println("<form method='post' action='ticketbooking1'>");
			    out.println("<table>");
			    
			    out.println("<tr>");
			    out.println("<td> username</td>");
			    out.println("<td>"+uname+"</td>");
			    out.println("</tr>");
			    
			    
			    try {
			        
			    	Connection con = null;
					Class.forName("com.mysql.jdbc.Driver");
					System.out.println("dr");
					con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jfsdskill4","root","Asuhas@123");
					System.out.println("dr");
					
					PreparedStatement pstmt = con.prepareStatement("select * from movie");
					
					ResultSet rs = pstmt.executeQuery();
					
					out.println("<tr>");
					out.println("<td> Movie name</td>");
					out.println("<td><select name='movieid'>");
					out.println("<option value=' '>---select----</option>");
					
					while(rs.next()) {
					
				out.println("<option value="+rs.getString("movieid")+">");
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
			    
			    
			    try {
			        
			    	Connection con = null;
					Class.forName("com.mysql.jdbc.Driver");
					System.out.println("dr");
					con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jfsdskill4","root","Asuhas@123");
					System.out.println("dr");
					
					PreparedStatement pstmt = con.prepareStatement("select * from theatre");
					
					ResultSet rs = pstmt.executeQuery();
					
					out.println("<tr>");
					out.println("<td> Theater Name</td>");
					out.println("<td><select name='theatreid'>");
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
			    
			    out.println("<tr>");
			    out.println("<td>Show date</td>");
			    out.println("<td><input type='date' name='showdate' required></td>");
			    out.println("</tr>");
			    
			    out.println("<tr>");
			    out.println("<td> Show slot</td>");
			    out.println("<td><select name='showslot'>");
			    out.println("<option value=''> ---Select--</option>");
			    out.println("<option value='9AM'>9AM</option>");
			    out.println("<option value='1PM'>1PM</option>");
			    out.println("<option value='6:30PM'>6:30PM</option>");
			    out.println("<option value='9:30PM'>9:30PM</option>");
			    out.println("</td>");
			    out.println("</tr>");
			   
			    out.println("<tr>");
			    out.println("<td>Numbers of Tickets</td>");
			    out.println("<td><input type='number' name='ticketscount' required></td>");
			    out.println("</tr>");
			    
			    
			   
			    
			    
			    
			    out.println("<tr><td align=center><input type='submit' value='Proceed'></td></tr>");
			    
			    out.println("</table>"); 
			    out.println("</form>");
			    out.println("</center>");
			
			
			

		}
}

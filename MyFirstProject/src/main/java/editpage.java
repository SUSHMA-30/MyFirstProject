import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/editurl")

public class editpage extends HttpServlet {
	
	 private static final String QUERY = "SELECT Name, Email, Mobile, DOB, City, Gender FROM user WHERE id=?";

	    @Override
	    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	        
	        // Get PrintWriter
	        PrintWriter pw = res.getWriter();
	        
	        // Set the content type
	        res.setContentType("text/html");
	        
	        //get the id
	        int id=Integer.parseInt(req.getParameter("id"));
	        
	        //link bootstrap
			 
	        pw.println("<link rel='stylesheet' href='css/bootstrap.css'/>");
	        
	        // Load JDBC driver
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	        
	        // Generate the connection to JDBC
	        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/MyFirstProject", "root", "Sushma@30");
	             PreparedStatement ps = con.prepareStatement(QUERY);) {
	            
	        	
	        	
	        	//we have to set values
	        	ps.setInt(1,id);
	        	
	        	
	            // result set
	            ResultSet rs = ps.executeQuery();
	            rs.next();
	            pw.println("<div style='margin:auto;width:500px;margin-top:100px;'>");
	            pw.println("<form action='edit?id="+id+"' method='post'>");
	            pw.println("<table class='table table-hover table-striped'>");
	            pw.println("<table>");
	            pw.println("<tr>");
	            pw.println("<td>Name</td>");
	            pw.println("<td><input type='text' name='Name' value='"+rs.getString(1)+"'></td>");
	            pw.println("</tr>");
	            pw.println("<tr>");
	            pw.println("<td>Email</td>");
	            pw.println("<td><input type='email' name='Email' value='"+rs.getString(2)+"'></td>");
	            pw.println("</tr>");
	            pw.println("<tr>");
	            pw.println("<td>Mobile</td>");
	            pw.println("<td><input type='text' name='Mobile' value='"+rs.getString(3)+"'></td>");
	            pw.println("</tr>");
	            pw.println("<tr>");
	            pw.println("<td>DOB</td>");
	            pw.println("<td><input type='date' name='DOB' value='"+rs.getString(4)+"'></td>");
	            pw.println("</tr>");
	            pw.println("<tr>");
	            pw.println("<td>City</td>");
	            pw.println("<td><input type='text' name='City' value='"+rs.getString(5)+"'></td>");
	            pw.println("</tr>");
	            pw.println("<tr>");
	            pw.println("<td>Gender</td>");
	            pw.println("<td><input type='text' name='Gender' value='"+rs.getString(6)+"'></td>");
	            pw.println("</tr>");
	            pw.println("<tr>");
	            pw.println("<td><button type='submit' class='btn btn-outline-success'>Edit</td>");
	            pw.println("<td><button type='reset' class='btn btn-outline-danger'>Cancel</td>");
	            pw.println("</tr>");
	            pw.println("</table>");
	            pw.println("</form>");
	            
	       
	        } catch (SQLException se) {
	            pw.println("<h2>" + se.getMessage() + "</h2>");
	            se.printStackTrace();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        
	        pw.println("<a href='home.html'><button class='btn btn-outline-success'>Home</button></a>");
	        pw.println("</div>");
	        
	        
	        // Close the stream
	        pw.close();
	    }

	    @Override
	    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	        doGet(req, res);
	    }
	

}

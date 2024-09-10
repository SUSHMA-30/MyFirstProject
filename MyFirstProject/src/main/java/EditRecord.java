import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/edit")

public class EditRecord extends HttpServlet {
	
private final static String query="UPDATE user set Name=?,Email=?,Mobile=?,DOB=?,City=?,Gender=? WHERE id=?";
	@Override
	 protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		//Get printwriter
		 PrintWriter pw = res.getWriter();
		 
		 //set the contenttype
		 res.setContentType("text/html");	
		 
		 pw.println("<link rel='stylesheet' href='css/bootstrap.css'></link>");
		 
		 //get the values from index html 
		  int id=Integer.parseInt(req.getParameter("id"));
		  String Name=req.getParameter("Name");
		  String Email=req.getParameter("Email");
		  String Mobile=req.getParameter("Mobile");
		  String DOB=req.getParameter("DOB");
		  String City=req.getParameter("City");
		  String Gender=req.getParameter("Gender");
		  
		 
	        // add the JDBC driver to using try and catch blocks
		      //load the JDBC driver
		        try {
		            Class.forName("com.mysql.cj.jdbc.Driver");
		        }catch(Exception e) {
		            e.printStackTrace();
		        }
		        
		        //generate the connection to jdbc
		        try(Connection con = DriverManager.getConnection("jdbc:mysql:///MyFirstProject","root","Sushma@30");
		        		 PreparedStatement ps = con.prepareStatement(query);)
		        {
		        	
		        	//set the values what we have given database
		        	
		        	
		           ps.setNString(1,Name);
		           ps.setNString(2,Email);
		           ps.setNString(3,Mobile);
		           ps.setNString(4,DOB);
		           ps.setNString(5,City);
		           ps.setNString(6,Gender);
		           ps.setInt(7,id);
		            // here we  have to execute the query to update
		            int count = ps.executeUpdate();
		            pw.println("<div class='card' style='margin:auto;width:300px;margin-top:100px'>");
		            
		            if(count==1) {
		            	 pw.println("<h2 class='bg-danger text-light text-center'> Edited Successfully</h2>");
		            }else {
		                pw.println("<h2 class='bg-danger text-light text-center'> Not Edited Successfully</h2>");
		            }

		        }catch(SQLException se)
		        {
		        	pw.println("<h2+>"+se.getMessage()+"</h2>");
		        	se.fillInStackTrace();
		        }catch(Exception e) {
		        	e.fillInStackTrace();
		        }

		        pw.println("<a href='home.html'><button class='btn btn-outline-success'>Home</button></a>");
		        pw.println("&nbsp; &nbsp;");
		        pw.println("<a href='showdata'><button class='btn btn-outline-success'>Show User</button></a>");
		        pw.println("</div>");
		        
		        // and close the stream
		        pw.close();
		}
		@Override
		 protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			doGet(req,res);
		}
}

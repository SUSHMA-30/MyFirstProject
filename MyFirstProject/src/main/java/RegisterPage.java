import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.ServletException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/register")
public class RegisterPage extends HttpServlet {
	
	private final static String query="INSERT into user(Name,Email,Mobile,DOB,City,Gender) values(?,?,?,?,?,?)";
     
	@Override
	 protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		//Get printwriter
		 PrintWriter pw = res.getWriter();
		 
		 //set the contenttype
		 res.setContentType("text/html");	
		 
		 pw.println("<link rel='stylesheet' href='css/bootstrap.css'></link>");
		 
		 //get the values from index html 
		    String Name = req.getParameter("userName");
	        String Email = req.getParameter("Email");
	        String Mobile = req.getParameter("Mobile");
	        String DOB = req.getParameter("DOB");
	        String City = req.getParameter("City");
	        String Gender = req.getParameter("Gender");
	        
	        // add the JDBC driver to using try and catch blocks
		      //load the JDBC driver
		        try {
		            Class.forName("com.mysql.cj.jdbc.Driver");
		        }catch(Exception e) {
		            e.printStackTrace();
		        }
		        
		        //generate the connection to jdbc
		        try(Connection con = DriverManager.getConnection("jdbc:mysql:///myfirstproject","root","Sushma@30");
		        		 PreparedStatement ps = con.prepareStatement(query);)
		        {
		        	
		        	//set the values what we have given database
		        	
		            ps.setString(1, Name);
		            ps.setString(2, Email);
					ps.setString(3, Mobile);
		            ps.setString(4, DOB);
		            ps.setString(5, City);
		            ps.setString(6, Gender);
		            
		            // here we  have to execute the query to update
		            int count = ps.executeUpdate();
		            pw.println("<div class='card' style='margin:auto;width:300px;margin-top:100px'>");
		            
		            if(count==1) {
		            	 pw.println("<h2 class='bg-danger text-light text-center'> Registered Successfully</h2>");
		            }else {
		                pw.println("<h2 class='bg-danger text-light text-center'> Not Registered</h2>");
		            }

		        }catch(SQLException se)
		        {
		        	pw.println("<h2+>"+se.getMessage()+"</h2>");
		        	se.fillInStackTrace();
		        }catch(Exception e) {
		        	e.fillInStackTrace();
		        }

		        pw.println("<a href='home.html'><button class='btn btn-outline-success d-block'>Home</button></a>");
		        pw.println("</div>");
		        
		        // and close the stram
		        pw.close();
		}
		@Override
		 protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			doGet(req,res);
		}


	}
	


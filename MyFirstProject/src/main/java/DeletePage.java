import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/deleteurl")
public class DeletePage extends HttpServlet {
	
	private final static String query="DELETE from user where id= ? ";
    
	@Override
	 protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		//Get printwriter
		 PrintWriter pw = res.getWriter();
		 
		 //set the content type
		 res.setContentType("text/html");	
		 
		 pw.println("<link rel='stylesheet' href='css/bootstrap.css'></link>");
		 
		 //get the values from index html 
		  int id=Integer.parseInt(req.getParameter("id"));
		 
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
		           ps.setInt(1,id);
		           
		            // here we  have to execute the query to update
		            int count = ps.executeUpdate();
		            pw.println("<div class='card' style='margin:auto;width:300px;margin-top:100px'>");
		            
		            if(count==1) {
		            	 pw.println("<h2 class='bg-danger text-light text-center'> Deleted Successfully</h2>");
		            }else {
		                pw.println("<h2 class='bg-danger text-light text-center'> Not Deleted Successfully</h2>");
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

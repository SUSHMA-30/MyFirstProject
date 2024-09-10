import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.ResultSet;

@WebServlet("/showdata")
public class ShowUserServlet extends HttpServlet {
    
    private static final String QUERY = "SELECT id, Name, Email, Mobile, DOB, City, Gender FROM user";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        
        // Get PrintWriter
        PrintWriter pw = res.getWriter();
        
        // Set the content type
        res.setContentType("text/html");
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
            
            // Execute the query
        	//result set
            ResultSet rs = ps.executeQuery();
            
            pw.println("<div style='margin:auto;width:500px;margin-top:50px;'>");
            pw.println("<table class='table table-hover table-striped'>");
            pw.println("<tr>");
            pw.println("<th>ID</th>");
            pw.println("<th>Name</th>");
            pw.println("<th>Email</th>");
            pw.println("<th>Mobile</th>");
            pw.println("<th>DOB</th>");
            pw.println("<th>City</th>");
            pw.println("<th>Gender</th>");
            pw.println("<th>Edit</th>");
            pw.println("<th>Delete</th>");
            pw.println("</tr>");
            
            while (rs.next()) {
                pw.println("<tr>");
                pw.println("<td>"+rs.getInt(1)+"</td>");
                pw.println("<td>" + rs.getString(2) + "</td>");
                pw.println("<td>" + rs.getString(3) + "</td>");
                pw.println("<td>" + rs.getString(4) + "</td>");
                pw.println("<td>" + rs.getString(5) + "</td>");
                pw.println("<td>" + rs.getString(6) + "</td>");
                pw.println("<td>" + rs.getString(7) + "</td>");
                pw.println("<td><a href='editurl?id=" + rs.getInt(1) + "'>Edit</a></td>");
                pw.println("<td><a href='deleteurl?id=" + rs.getInt(1) + "'>Delete</a></td>");
                pw.println("</tr>");
            }
            
            pw.println("</table>");
          
            
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

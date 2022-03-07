import java.io.*;
import java.sql.*;
import java.util.*;
import jakarta.servlet.*;            // Tomcat 10
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/Welcome")   // Configure the request URL for this servlet (Tomcat 7/Servlet 3.0 upwards)
public class WelcomeServlet extends HttpServlet {

   @Override
   public void doGet(HttpServletRequest request, HttpServletResponse response)
               throws ServletException, IOException {

      response.setContentType("text/html");

      PrintWriter out = response.getWriter();

      out.println("<html>");

      out.println("<head><title>Response</title></head>");

      out.println("<body>");

      try (
         // Step 1: Allocate a database 'Connection' object
         Connection conn = DriverManager.getConnection(
               "jdbc:mysql://localhost:3306/MembershipDB?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
               "myuser", "1234");   // For MySQL
         // Step 2: Allocate a 'Statement' object in the Connection
         Statement stmt = conn.createStatement();
      ) {
         // Step 3 & 4: Execute a SQL SELECT query and Process the query result
         String Username = request.getParameter("username");
         String password = request.getParameter("password");
         //int phoneInt;
         ArrayList al = new ArrayList(); // invalid parameters
         if ((password == null) || (password.equals(""))) {
            al.add("PROVIDE YOUR PASSWORD");
         } 
         if ((Username == null) || (Username.equals(""))) {
            al.add("PROVIDE USERNAME");
         }
         
         if (al.size() != 0) {//got error
            out.println("<h2>Please go back and try again...</h2>");
            out.println(al);
         } 
         else { // no error

               String sqlStr;

               sqlStr = "SELECT * FROM UsersDetails WHERE Username = '" + Username + "'";

               out.println("<p class='debug'>" + sqlStr + "</p>");  // for debugging

               ResultSet rset = stmt.executeQuery(sqlStr);

               if(!rset.next()) {
                     out.println("<h2>No user found</h2>");
                     out.println("<h3>Please go back and try again...</h3>");                     
                  } else {
                     out.println(rset.getString("Username"));
                     if (password.equals(rset.getString("Password"))) {

                        out.println("</form>");
                        out.println("<form action = 'Home.html'>");
                        out.println("<button type='submit' >Continue</button></form>");
                        out.println("</form>");

                     } else {
                        out.println("<h2>Incorrect password</h2>");
                        out.println("<h3>Please go back and try again...</h3>"); 
                     }            
               }
         }
      } catch(Exception ex) {
         out.println("<p>Error: " + ex.getMessage() + "</p>");
         out.println("<p>Check Tomcat console for details.</p>");
         ex.printStackTrace();
      }  // Step 5: Close conn and stmt - Done automatically by try-with-resources (JDK 7)
      out.println("</body></html>");
      out.close();

   }

}   
import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.jar.Attributes.Name;

import javax.security.auth.Subject;

import jakarta.servlet.*;            // Tomcat 10
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
@WebServlet("/Feedback")   // Configure the request URL for this servlet (Tomcat 7/Servlet 3.0 upwards)
public class FeedbackServlet extends HttpServlet {
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
         String Name = request.getParameter("Name");
         String Email = request.getParameter("Email");
         String Subject = request.getParameter("Subject");
         String Feedback = request.getParameter("Feedback");
         //int phoneInt;
         ArrayList al = new ArrayList(); // invalid parameters
         if ((Name == null) || (Name.equals(""))) {
            al.add("PROVIDE NAME");
         } 
         if ((Email == null) || (Email.equals(""))) {
            al.add("PROVIDE EMAIL");
         }
         if ((Subject == null) || (Subject.equals(""))) {
            al.add("PROVIDE A SUBJECT TITLE");
         } 
         if ((Feedback == null) || (Feedback.equals(""))) {
            al.add("PROVIDE FEEDBACK");
         }
         
         if (al.size() != 0) {//got error
            out.println("<h2>Please go back and try again...</h2>");
            out.println(al);
         } 
         else { // no error

               String sqlStr;
               sqlStr = "insert into UserFeedback values (" + "'" + Name + "', '" + Email +
                "', '" + Subject + "', '" + Feedback + "');";

               out.println("<p class='debug'>" + sqlStr + "</p>");  // for debugging

               stmt.execute(sqlStr);

               out.println(Name);
               out.println(Email);
               out.println(Subject);
               out.println(Feedback);
               out.println("</form>");
               out.println("<form action = 'Home.html'>");
               out.println("<button type='submit' >Continue</button></form>");
               out.println("</form>");          
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
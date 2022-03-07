import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.jar.Attributes.Name;
import jakarta.servlet.*;            // Tomcat 10
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/Search")   // Configure the request URL for this servlet (Tomcat 7/Servlet 3.0 upwards)
public class SearchServlet extends HttpServlet {

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
               "jdbc:mysql://localhost:3306/MainDB?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
               "myuser", "1234");   // For MySQL    
         // Step 2: Allocate a 'Statement' object in the Connection
         Statement stmt = conn.createStatement();
      ) {
         // Step 3 & 4: Execute a SQL SELECT query and Process the query result
         String Name = request.getParameter("Stars");
         ArrayList al = new ArrayList(); // invalid parameters
         ArrayList al2 = new ArrayList();
         if ((Name == null) || (Name.equals(""))) {
            al.add("SEARCH SOMETHING!");
         }
         if (al.size() != 0) {//got error
            out.println("<h2>Please go back and try again...</h2>");
            out.println(al);
         } 
         else { // no error
               String sqlStr;
               String name;
               int castID;
               int moviesID;
               sqlStr = "SELECT * FROM MovieStars WHERE Name = '" + Name + "';";
               //out.println("<p class='debug'>" + sqlStr + "</p>");  // for debugging
               ResultSet rset = stmt.executeQuery(sqlStr);
               if(!rset.next()) {
                     out.println("<h2>Not found</h2>");
                     out.println("<h3>Try again</h3>");                     
                  } else {
                    //  out.println(rset.getString("Name"));
                    //  out.println(rset.getInt("CastID"));    
                    castID = rset.getInt("CastID");
                    String sqlStrStr;
                    sqlStrStr = "SELECT MoviesId FROM Movies_MoviesStars WHERE CastID = " + castID + ";";
                    //out.println("<p class='debug'>" + sqlStrStr + "</p>");  // for debugging
                    ResultSet rset2 = stmt.executeQuery(sqlStrStr);
                    while(rset2.next()){
                        //out.println(rset2.getInt("MoviesId"));
                        al2.add(rset2.getInt("MoviesId"));
                    }
                    out.println("Movies that this actor/actress");
                    out.println("<br>"); 
                    for (int i = 0; i < al2.size(); i++) {
                        String sqlStrStrStr;
                        sqlStrStrStr = "SELECT Name From Movies where MoviesId =" +al2.get(i)+";";
                        ResultSet rset3 = stmt.executeQuery(sqlStrStrStr);
                        rset3.next();//EXTREMELY IMPT CODE
                        String foundType = rset3.getString(1); //EXTREMELY IMPT CODE
                        out.println("<br>");
                        out.println(rset3.getString("Name"));
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
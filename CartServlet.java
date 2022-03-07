import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.jar.Attributes.Name;
import java.lang.Integer;
import javax.security.auth.Subject;

import jakarta.servlet.*;            // Tomcat 10
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/Cart")   // Configure the request URL for this servlet (Tomcat 7/Servlet 3.0 upwards)
public class CartServlet extends HttpServlet {
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
         String MinionsQuantity = request.getParameter("MinionsQuantity");
         String MulanQuantity = request.getParameter("MulanQuantity");
         String InceptionQuantity = request.getParameter("InceptionQuantity");
         String TheGodfatherQuantity = request.getParameter("TheGodfatherQuantity");
         String TheHangoverQuantity = request.getParameter("TheHangoverQuantity");
         String JawsQuantity = request.getParameter("JawsQuantity");
         String LiloAndStitchQuantity = request.getParameter("LiloAndStitchQuantity");
         String ReadyPlayerOneQuantity = request.getParameter("ReadyPlayerOneQuantity");

         int minions = Integer.parseInt(MinionsQuantity);
         int mulan = Integer.parseInt(MulanQuantity);
         int inception = Integer.parseInt(InceptionQuantity);
         int godfather = Integer.parseInt(TheGodfatherQuantity);
         int hangover = Integer.parseInt(TheHangoverQuantity);
         int jaws = Integer.parseInt(JawsQuantity);
         int liloandstitch  = Integer.parseInt(LiloAndStitchQuantity);
         int readyplayerone = Integer.parseInt(ReadyPlayerOneQuantity);

         int pmovies = godfather; //0.99
         int Wbmovies = hangover + readyplayerone + inception; //1.99
         int wdmovies = mulan + liloandstitch; //2.99
         int Upmovies = minions + jaws;//3.99

         float psum =  0.99f * (float)pmovies;
         float wbsum = Wbmovies * 1.99f;
         float wdsum = wdmovies * 2.99f;
         float Upsum = Upmovies * 3.99f;
         float totalsum = psum + wbsum + wdsum + Upsum;

         String sqlStr8; //update quantity in DB
         String sqlStr9;
         String sqlStr10;
         String sqlStr11;
         String sqlStr12;
         String sqlStr13;
         String sqlStr14;
         String sqlStr15;

         int inceptionAllowed = 0;
         int minionsAllowed = 0;
         int mulanAllowed = 0;
         int thegodfatherAllowed = 0;
         int jawsAllowed = 0;
         int readyplayeroneAllowed = 0;
         int hangoverAllowed = 0;
         int liloandstitchAllowed = 0;

         int receipt = 1;
         
         sqlStr8 = "Select AvailableQuantity from Movies where Name = 'Minions';";
         ResultSet rset0 = stmt.executeQuery(sqlStr8);
         while(rset0.next()){
            minionsAllowed = rset0.getInt("AvailableQuantity");
        }
        sqlStr9 = "Select AvailableQuantity from Movies where Name = 'Mulan';";
        ResultSet rset1 = stmt.executeQuery(sqlStr9);
         while(rset1.next()){
            mulanAllowed = rset1.getInt("AvailableQuantity");
        }
        
         sqlStr10 = "Select AvailableQuantity from Movies where Name = 'Inception';";
         ResultSet rset2 = stmt.executeQuery(sqlStr10);
         while(rset2.next()){
            inceptionAllowed = rset2.getInt("AvailableQuantity");
        }
        
         sqlStr11 = "Select AvailableQuantity from Movies where Name = 'The God Father';";
         ResultSet rset3 =  stmt.executeQuery(sqlStr11);
         while(rset3.next()){
            thegodfatherAllowed = rset3.getInt("AvailableQuantity");
        }
        
         sqlStr12 = "Select AvailableQuantity from Movies where Name = 'The Hangover';";
          ResultSet rset4 =  stmt.executeQuery(sqlStr12);
         while(rset4.next()){
            hangoverAllowed = rset4.getInt("AvailableQuantity");
        }
         
         sqlStr13 = "Select AvailableQuantity from Movies where Name = 'Jaws';";
         ResultSet rset5 = stmt.executeQuery(sqlStr13);
         while(rset5.next()){
            jawsAllowed = rset5.getInt("AvailableQuantity");
        }
         
         sqlStr14 = "Select AvailableQuantity from Movies where Name = 'Lilo and Stitch';";
         ResultSet rset6 = stmt.executeQuery(sqlStr14);
         while(rset6.next()){
            liloandstitchAllowed = rset6.getInt("AvailableQuantity");
        }
         
         sqlStr15 = "Select AvailableQuantity from Movies where Name = 'Ready Player One';";
         ResultSet rset7 = stmt.executeQuery(sqlStr15);
         while(rset7.next()){
            readyplayeroneAllowed = rset7.getInt("AvailableQuantity");
        }

         if(minionsAllowed<minions){
            out.println("Minions Insufficient Quantity Error");
            receipt = 0;
         }
         else{
            String sqlStr0; //update quantity
            sqlStr0 = "Update Movies Set AvailableQuantity = AvailableQuantity - " + minions + " where Name = 'Minions';";
            stmt.executeUpdate(sqlStr0);
         }
         if(mulanAllowed<mulan){
            out.println("Mulan Insufficient Quantity Error");
            receipt = 0;
         }
         else{
            String sqlStr0; //update quantity
            sqlStr0 = "Update Movies Set AvailableQuantity = AvailableQuantity - " + mulan + " where Name = 'Mulan';";
            stmt.executeUpdate(sqlStr0);
         }
         if(inceptionAllowed<inception){
            out.println("Inception Insufficient Quantity Error");
            receipt = 0;
         }
         else{
            String sqlStr0; //update quantity
            sqlStr0 = "Update Movies Set AvailableQuantity = AvailableQuantity - " + inception + " where Name = 'Inception';";
            stmt.executeUpdate(sqlStr0);
         }
         if(thegodfatherAllowed<godfather){
            out.println("Godfather Insufficient Quantity Error");
            receipt = 0;
         }
         else{
            String sqlStr0; //update quantity
            sqlStr0 = "Update Movies Set AvailableQuantity = AvailableQuantity - " + godfather + " where Name = 'The God Father';";
            stmt.executeUpdate(sqlStr0);
         }
         if(hangoverAllowed<hangover){
            out.println("Hangover Insufficient Quantity Error");
            receipt = 0;
         }
         else{
            String sqlStr0; //update quantity
            sqlStr0 = "Update Movies Set AvailableQuantity = AvailableQuantity - " + hangover + " where Name = 'The Hangover';";
            stmt.executeUpdate(sqlStr0);
         }
         if(jawsAllowed<jaws){
            out.println("Jaws Insufficient Quantity Error");
            receipt = 0;
         }
         else{
            String sqlStr0; //update quantity
            sqlStr0 = "Update Movies Set AvailableQuantity = AvailableQuantity - " + jaws + " where Name = 'Jaws';";
            stmt.executeUpdate(sqlStr0);
         }
         if(liloandstitchAllowed<liloandstitch){
            out.println("Lilo and Stitch Insufficient Quantity Error");
            receipt = 0;
         }
         else{
            String sqlStr0; //update quantity
            sqlStr0 = "Update Movies Set AvailableQuantity = AvailableQuantity - " + liloandstitch + " where Name = 'Lilo and Stitch';";
            stmt.executeUpdate(sqlStr0);
         }
         if(readyplayeroneAllowed<readyplayerone){
            out.println("Ready Player One Insufficient Quantity Error");
            receipt = 0;
         }
         else{
            String sqlStr0; //update quantity
            sqlStr0 = "Update Movies Set AvailableQuantity = AvailableQuantity - " + readyplayerone + " where Name = 'Ready Player One';";
            stmt.executeUpdate(sqlStr0);
         }
         if(receipt == 0){
            out.println("There is an error in the cart please go back and redo.");
         } else if (receipt == 1){
            out.println("INVOICE");
            out.println("<br>");
            out.println(pmovies + "*" + "0.99 = " + psum);
            out.println("<br>");
            out.println(Wbmovies + "*" + "1.99 = " + wbsum);
            out.println("<br>");
            out.println(wdmovies + "*" + "2.99 = " + wdsum);
            out.println("<br>");
            out.println(Upmovies + "*" + "3.99 = " + Upsum);
            out.println("<br>");
            out.println("Total: ");
            out.println(totalsum);
            out.println("Thanks for your purchase we will send you an invoice.");
            out.println("</form>");
            out.println("<form action = 'Home.html'>");
            out.println("<button type='submit' >Back to Main Page</button></form>");
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
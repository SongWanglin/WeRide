

import java.io.IOException;
import java.io.PrintWriter;

import com.mongodb.MongoClient;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

@WebServlet("/ViewReview")

public class ViewReview extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        Utilities utility = new Utilities(request, pw);
        review(request, response);
    }

    protected void review(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            response.setContentType("text/html");
            PrintWriter pw = response.getWriter();
            Utilities utility = new Utilities(request, pw);
            if (!utility.isLoggedin()) {
                HttpSession session = request.getSession(true);
                session.setAttribute("login_msg", "Please Login to view Review");
                response.sendRedirect("Login");
                return;
            }
            HttpSession session = request.getSession(true);
            String userName = (String) session.getAttribute("username");
            HashMap<String, ArrayList<Review>> hm = MongoDBDataStoreUtilities.selectReview();
            String orderId = "";
            String driverName = "";
            String rate = "";
            String text ="";
            String date = "";

            utility.printHtml("Header.html");
//            utility.printHtml("LeftNavigationBar.html");

            pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
            pw.print("<a style='font-size: 24px;'>Review</a>");
            pw.print("</h2><div class='entry'>");

            //if there are no reviews for product print no review else iterate over all the reviews using cursor and print the reviews in a table
            if (hm == null) {
                pw.println("<h2>Mongo Db server is not up and running</h2>");
            } else {
                if (!hm.containsKey(userName)) {
                    pw.println("<h2>There are no reviews for your trip.</h2>");
                } else {
                    for (Review r : hm.get(userName)) {
                        pw.print("<table border = '1' align='center' height ='300' width='400'>");
                        pw.print("<tr>");
                        pw.print("<th> userName: </th>");
                        userName = r.getUserName();
                        pw.print("<th>" +userName+ "</th>");
                        pw.print("</tr>");
                        pw.print("<tr>");
                        pw.print("<th> OrderId: </th>");
                        orderId = r.getOrderId();
                        pw.print("<th>" +orderId+ "</th>");
                        pw.print("</tr>");
                        pw.print("<tr>");
                        pw.print("<th> driver name: </th>");
                        driverName = r.getDriverName();
                        pw.print("<th>" + driverName + "</th>");
                        pw.print("</tr>");
                        pw.print("<tr>");
                        pw.print("<th> Review rating: </th>");
                        rate = r.getRate();
                        pw.print("<th>" + rate + "</th>");
                        pw.print("</tr>");
                        pw.print("<tr>");
                        pw.print("<th> Comments: </th>");
                        text = r.getText();
                        pw.print("<th>" + text + "</th>");
                        pw.print("</tr>");
                        pw.println("<tr>");
                        pw.println("<th> Date: </th>");
                        date = r.getDate().toString();
                        pw.print("<th>" + date + "</th>");
                        pw.print("</tr>");
                        pw.println("</table>");

                    }

                }
            }
            pw.print("</div></div></div>");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();

    }
}

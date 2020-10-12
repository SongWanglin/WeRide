

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

@WebServlet("/getRate1")

public class getRate1 extends HttpServlet {

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
//            if (!utility.isLoggedin()) {
//                HttpSession session = request.getSession(true);
//                session.setAttribute("login_msg", "Please Login to view Review");
//                response.sendRedirect("Login");
//                return;
//            }
//            HttpSession session = request.getSession(true);
//            String userName = (String) session.getAttribute("username");
//            String driverName = request.getParameter("driverName");
           String driverName = "Keith Moore";
//            String driverName = request.getParameter("driver1");
            HashMap<String, ArrayList<Review>> hm = MongoDBDataStoreUtilities.selectRate();
//            String orderId = "";
//            String driverName = "";
            String rate = "";
            double sum = 0;
//            String text ="";
//            String date = "";

//            utility.printHtml("Header.html");
//            utility.printHtml("LeftNavigationBar.html");

//            pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
//            pw.print("<a style='font-size: 24px;'>Review</a>");
//            pw.print("</h2><div class='entry'>");
            int size= hm.get(driverName).size();
            //if there are no reviews for product print no review else iterate over all the reviews using cursor and print the reviews in a table
            if (hm == null) {
                pw.println("<h2>Mongo Db server is not up and running</h2>");
            } else {
                if (!hm.containsKey(driverName)) {
                    pw.println("<h2>There are no reviews for this driver.</h2>");
                } else {
                    for (Review r : hm.get(driverName)) {
                        rate = r.getRate();
                        int i = Integer.parseInt(rate);
                        sum = sum + i;
                    }
                    pw.print("<table border = '1' align='center' height ='100' width='100'>");
                    pw.print("<tr>");
                    pw.print("<th> score:</th>");
                    pw.print("<th> ("+size+")reviews</th>");
                    pw.print("<th>" +String.format("%.2f", sum/size)+ "</th>");
                    pw.print("</tr></table>");

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



import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/WriteReview")
//once the user clicks writereview button from products page he will be directed
//to write review page where he can provide reqview for item rating reviewtext

public class WriteReview extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        Utilities utility = new Utilities(request, pw);
//        review(request, response);
//    }

//    protected void review(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//            response.setContentType("text/html");
//            PrintWriter pw = response.getWriter();
//            Utilities utility = new Utilities(request, pw);
        HttpSession session = request.getSession(true);
        if (!utility.isLoggedin()) {
            session = request.getSession(true);
            session.setAttribute("login_msg", "Please Login to Write a Review");
            response.sendRedirect("Login");
            return;
        }
        String username = (String) session.getAttribute("username");
		String orderId = request.getParameter("ID");
		String driver = request.getParameter("Drive");
        // on filling the form and clicking submit button user will be directed to submit review page
        utility.printHtml("Header.html");
        pw.print("<form name ='WriteReview' action='SubmitReview' method='post'>");
		pw.print("<form method='post' action='WriteReview'>"+"<input type='hidden' name='orderID' value='" + orderId + "'>");
		pw.print("<form method='post' action='WriteReview'>"+"<input type='hidden' name='driverName' value='" + driver + "'>");
        pw.print("<div id='content' align = 'center'><div class='post'><h2 class='title meta'>");
        pw.print("<br><br><br>");
        pw.print("<a style='font-size: 50px;'>Review</a>");
        pw.print("</h2><div class='entry'>");
        pw.print("<table class='gridtable'>");
        pw.print("<tr>");
        pw.print("<td > UserName: </td>");
        pw.print("<td>"+username+ "</td>");
        pw.print("</tr>");
		pw.print("<tr>");
		pw.print("<td > OrderId: </td>");
		pw.print("<td>"+orderId+ "</td>");
		pw.print("</tr>");
		pw.print("<tr>");
		pw.print("<td > Driver Name: </td>");
		pw.print("<td>"+driver+ "</td>");
		pw.print("</tr>");
        pw.print("<table><tr></tr><tr></tr><tr><td> Review Rating: </td>");
        pw.print("<td>");
        pw.print("<select name='Driver Rating'>");
        pw.print("<option value='1' selected>1</option>");
        pw.print("<option value='2'>2</option>");
        pw.print("<option value='3'>3</option>");
        pw.print("<option value='4'>4</option>");
        pw.print("<option value='5'>5</option>");
        pw.print("</td></tr>");
        pw.print("<br>");
//            pw.print("<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">");
//            pw.print("<ul class='ll'>" +
//                    "<li index='1'></li>" +
//                    "<li index='2'></li>" +
//                    "<li index='3'></li>" +
//                    "<li index='4'></li>" +
//                    "<li index='5'></li>" +
//                    "</ul>");

        pw.print("<tr>");
        pw.print("<td > Review Date: </td>");
        pw.print("<td> <input type='date' name='reviewdate'> </td>");
        pw.print("</tr>");
        pw.print("<br>");
        pw.print("<tr>");
        pw.print("<td > Review Text: </td>");
        pw.print("<td><textarea name='reviewtext' rows='4' cols='50'> </textarea></td></tr>");
        pw.print("<tr><td colspan='2'><div align = 'center'><input type='submit' class='btnbuy' name='SubmitReview' value='SubmitReview'></div></td></tr></table>");

        pw.print("</h2></div></div></div>");
//            utility.printHtml("Footer.html");

    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
    }
}
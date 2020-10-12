import com.sun.org.apache.xpath.internal.objects.XNumber;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

@WebServlet("/CheckOut")

//once the user clicks buy now button page is redirected to checkout page where user has to give checkout information

public class CheckOut extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        Utilities Utility = new Utilities(request, pw);
//		storeOrders(request, response);
//	}

//	public void storeOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//	    try
//        {

        response.setContentType("text/html");
//		HashMap<String, OrderItem> mm = new HashMap<>();

//		ArrayList<OrderItem> orderItems = OrdersHashMap.orders.get(username());
//		OrderItem order = new OrderItem(username, price,distance,drop);
//		mm.put(username,order);
//		OrderItem order1 = mm.get(username);
//		System.out.println(order1.getName());
//		PrintWriter pw = response.getWriter();
//        Utilities utility = new Utilities(request,pw);
        if (!Utility.isLoggedin()) {
            HttpSession session = request.getSession(true);
            session.setAttribute("login_msg", "Please Login to add items to cart");
            response.sendRedirect("Login");
            return;
        }
        HttpSession session = request.getSession();
//		HashMap<Integer, ArrayList<Users>> orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
        //get the order product details	on clicking submit the form will be passed to submitorder page
//		HashMap<String, User> hm=new HashMap<String, User>();
//		try
//		{
//			hm=MySqlDataStoreUtilities.selectUser();
//		}
//		catch(Exception e)
//		{
//
//		}
//		User user = hm.get(username);
//		System.out.println(user);

        String username = session.getAttribute("username").toString();
        String price = request.getParameter("price");
        String distance = request.getParameter("distance");
        String travelTime = request.getParameter("travelTime");
        String drop = request.getParameter("ArrivalTime");
        String start = request.getParameter("origin");
        String end = request.getParameter("arrive");
        String type = request.getParameter("Car");
        String tip = request.getParameter("tip");
        Double total = Double.valueOf(price)*(1+Double.valueOf(tip));
        SimpleDateFormat df = new SimpleDateFormat("HHmmss");
        int orderId = Integer.parseInt(df.format(new Date()));

        pw.print(
                "<div id='content'><div class='post'><h2 class='title meta'>" +
                        "<a style='font-size: 40px;' ></a>");
        String driver = null;
        String car = null;
        if (type.equals("Economy")) {
            driver = "Wade Johnson";
            car = "toyota yaris";
        }
        if (type.equals("Comfort")) {
            driver = "Keith Moore";
            car = "toyota camry";
        }
        if (type.equals("SUV")) {
            driver = "Stanley Haywood";
            car = "Land Rover LR4";
        }
        if (type.equals("Sports Car")) {
            driver = "Frances Evans";
            car = "maserati SQ4";
        }

        Utility.storePayment(driver, car, orderId, username, start, end, price, distance, drop);
//        Utility.printHtml("Header.html");
        pw.print("<div style='background-color:lightblue; height:100%'>");
        pw.print("<br>");
        pw.print("<form action='WriteReview'>");
        pw.print("<table border = '1' align='center' height ='400' width='400'>" +
                "<div align = 'center'><h1 >Trip History<h1></div>" +
                "<tr>" +
                "<th> Order ID</th>" +
                "<th name = 'ID'>" + orderId + "<th>" +
                "</tr>" +
                "<form method='post' action='WriteReview'>" + "<input type='hidden' name='ID' value='" + orderId + "'>" +
                "<form method='post' action='WriteReview'>" + "<input type='hidden' name='Drive' value='" + driver + "'>" +
                "<tr>" +
                "<th> User name</th>" +
                "<th>" + username + "<th>" +
                "</tr>" +
                "<tr>" +
                "<th> Driver</th>" +
                "<th>" + driver + "<th>" +
                "</tr>" +
                "<tr>" +
                "<th> Car Model</th>" +
                "<th>" + car + "<th>" +
                "</tr>" +
                "<tr>" +
                "<th> From</th>" +
                "<th>" + start + "<th>" +
                "</tr>" +
                "<tr>" +
                "<th> To</th>" +
                "<th>" + end + "<th>" +
                "</tr>" +
                "<tr>" +
                "<th> Total Cost</th>" +
                "<th>" + String.format("%.2f", total) + "<th>" +
                "</tr>" +
                "<tr>" +
                "<th> Distance</th>" +
                "<th>" + distance + "<th>" +
                "</tr>" +
                "<tr>" +
                "<th> Drop Off Time</th>" +
                "<th>" + drop + "<th>" +
                "</tr>" +
                "<tr>" +
                "<th> Travel Time</th>" +
                "<th>" + travelTime + "<th>" +
                "</tr>" +
                "</table>");
        pw.print("<br>" +
                "<br>");
        pw.print("<div align = 'center'><button type ='submit' style = 'font-size:35px; background-color:black; color:white;'>Write Review</button></div>");
        pw.print("</div>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
    }
}

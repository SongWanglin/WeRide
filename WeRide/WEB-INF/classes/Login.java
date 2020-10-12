import java.io.*;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Login")

public class Login extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();

		/* User Information(username,password,usertype) is obtained from HttpServletRequest,
		Based on the Type of user(customer,retailer,manager) respective hashmap is called and the username and 
		password are validated and added to session variable and display Login Function is called */

        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        //String repassword = request.getParameter("repassword");
        //String userType = request.getParameter("userType");

        HashMap<String, User> hm=new HashMap<String, User>();
		//user details are validated using a file 
		//if the file containts username and passoword user entered user will be directed to home page
		//else error message will be shown
		try
		{	
			hm=MySqlDataStoreUtilities.selectUser();
		}
		catch(Exception e)
		{
				
		}
        User user = hm.get(username);
        if (user != null) {  //存在该用户名，进行校验
            String user_password = user.getPassword();
            if (password.equals(user_password)) {
                HttpSession session = request.getSession(true);
                session.setAttribute("username", username);
                response.sendRedirect("Home.html");
                //session.setAttribute("userType", userType);
//                //if (userType.equalsIgnoreCase("Customer")) {
//                    response.sendRedirect("Home");
//                    return;
//                } else if (userType.equalsIgnoreCase("StoreManager")) {
//                    response.sendRedirect("StoreManagerHome");
//                    return;
//
//                } else if (userType.equalsIgnoreCase("Salesman")) {
//                    response.sendRedirect("SalesmanHome");
//                    return;
//                }
            }
        }
        //用户名不存在
        displayLogin(request, response, pw, true);
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        displayLogin(request, response, pw, false);
    }


    /*  Login Screen is Displayed, Registered User specifies credentials and logins into the Game Speed Application. */
    protected void displayLogin(HttpServletRequest request,
                                HttpServletResponse response, PrintWriter pw, boolean error)
            throws ServletException, IOException {

        Utilities utility = new Utilities(request, pw);
        utility.printHtml("Header.html");
        pw.print("<div class='post' style='float: none; width: 85%'>");
        pw.print("<h2 class='title meta'><a style='font-size: 24px;'>Login</a></h2>"
                + "<div class='entry'>"
                + "<div style='width:400px; margin:25px; margin-left: auto;margin-right: auto;'>");
        if (error)
            pw.print("<h4 style='color:red'>Please check your username, password and user type!</h4>");
        HttpSession session = request.getSession(true);
        if (session.getAttribute("login_msg") != null) {
            pw.print("<h4 style='color:red'>" + session.getAttribute("login_msg") + "</h4>");
            session.removeAttribute("login_msg");
        }
        pw.print("<form method='post' action='Login'>"
                + "<table style='width:100%'><tr><td>"
                + "<h3>Username</h3></td><td><input type='text' name='username' value='' class='input' required></input>"
                + "</td></tr><tr><td>"
                + "<h3>Password</h3></td><td><input type='password' name='password' value='' class='input' required></input>"
                + "</td></tr><tr><td>"
                + "<h3>Email Address</h3></td><td><input type='email' name='email' value='' class='input' required></input>"
                + "</td></tr><tr><td>"
                + "<input type='submit' class='btnbuy' value='Login' style='float: right;height: 20px margin: 20px; margin-right: 10px;'></input>"
                + "</td></tr><tr><td></td><td>"
                + "<strong><a class='' href='Registration' style='float: right;height: 20px margin: 20px;'>New User? Register here!</a></strong>"
                + "</td></tr></table>"
                + "</form>" + "</div></div></div>");
        //utility.printHtml("Footer.html");
    }
}
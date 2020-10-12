import java.sql.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Date;

                	
public class MySqlDataStoreUtilities
{
static Connection conn = null;

public static void getConnection()
{
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/WeRide?serverTimezone=UTC&useSSL=false", "root", "Angieawe911");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }





	public static boolean insertUser(String username,String password,String repassword,String email,String number,String trueName,String CVV, String date) {
		try
		{

			getConnection();
			String insertIntoCustomerRegisterQuery = "INSERT INTO Registration(username,password,repassword,email,number,trueName,CVV,date) "
					+ "VALUES (?,?,?,?,?,?,?,?);";

			PreparedStatement pst = conn.prepareStatement(insertIntoCustomerRegisterQuery);
			pst.setString(1,username);
			pst.setString(2,password);
			pst.setString(3,repassword);
			pst.setString(4,email);
			pst.setString(5,number);
			pst.setString(6,trueName);
			pst.setString(7,CVV);
			pst.setString(8,date);
			//pst.setString(4,usertype);
			pst.execute();
		}
		catch(Exception e)
		{

		}
		return true;
	}

	public static HashMap<String,User> selectUser()
	{
		HashMap<String,User> hm=new HashMap<String,User>();
		try
		{
			getConnection();
			Statement stmt=conn.createStatement();
			String selectCustomerQuery="select * from  Registration";
			ResultSet rs = stmt.executeQuery(selectCustomerQuery);
			while(rs.next())
			{ User user = new User(rs.getString("username"),rs.getString("password"),rs.getString("email"),rs.getString("number"));
				hm.put(rs.getString("username"), user);
			}
		}
		catch(Exception e)
		{
		}
		return hm;
	}
	public static void insertOrder(String driver,String car,int orderId,String userName,String pickUp_Location,String dropOff_Location,String orderPrice,String distance,String dropTime)
	{
		try
		{
			Date current_date = new Date();

			SimpleDateFormat SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

			getConnection();
			String insertIntoCustomerOrderQuery = "INSERT INTO orders(driver,car,orderId,userName,pickUp_Location,dropOff_Location,orderPrice,distance,dropTime) "
					+ "VALUES (?,?,?,?,?,?,?,?,?);";

			PreparedStatement pst = conn.prepareStatement(insertIntoCustomerOrderQuery);
			//set the parameter for each column and execute the prepared statement
			pst.setString(1,driver);
			pst.setString(2,car);
			pst.setInt(3,orderId);
			pst.setString(4,userName);
			pst.setString(5,pickUp_Location);
			pst.setString(6,dropOff_Location);
			pst.setString(7,orderPrice);
			pst.setString(8,distance);
			pst.setString(9,dropTime);
//			pst.setString(7, SimpleDateFormat.format(current_date.getTime()));
			pst.execute();
		}
		catch(Exception e)
		{

		}
	}

	public static HashMap<Integer, ArrayList<OrderPayment>> selectOrder()
	{

		HashMap<Integer, ArrayList<OrderPayment>> orderPayments=new HashMap<Integer, ArrayList<OrderPayment>>();

		try
		{

			getConnection();
			//select the table
			String selectOrderQuery ="select * from orders";
			PreparedStatement pst = conn.prepareStatement(selectOrderQuery);
			ResultSet rs = pst.executeQuery();
			ArrayList<OrderPayment> orderList=new ArrayList<OrderPayment>();
			while(rs.next())
			{
				if(!orderPayments.containsKey(rs.getInt("orderId")))
				{
					ArrayList<OrderPayment> arr = new ArrayList<OrderPayment>();
					orderPayments.put(rs.getInt("orderId"), arr);
				}
				ArrayList<OrderPayment> listOrderPayment = orderPayments.get(rs.getInt("orderId"));
				System.out.println("data is"+rs.getInt("orderId")+orderPayments.get(rs.getInt("orderId")));

				//add to orderpayment hashmap
				OrderPayment order= new OrderPayment(rs.getString("driver"),rs.getString("car"),rs.getInt("orderId"),rs.getString("userName"),rs.getString("pickUp_Location"),rs.getString("dropOff_Location"),rs.getString("orderPrice"),rs.getString("distance"),rs.getString("dropTime"));
				listOrderPayment.add(order);

			}


		}
		catch(Exception e)
		{

		}
		return orderPayments;
	}
	public static boolean deleteOrder(int orderId) {
		try {

			getConnection();
			String deleteOrderQuery = "Delete from orders where orderId=?";
			PreparedStatement pst = conn.prepareStatement(deleteOrderQuery);
			pst.setInt(1, orderId);
			pst.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}


}	
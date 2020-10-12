import java.io.IOException;
import java.io.*;
import java.util.Date;


/* 
	OrderPayment class contains class variables username,ordername,price,image,address,creditcardno.

	OrderPayment  class has a constructor with Arguments username,ordername,price,image,address,creditcardno
	  
	OrderPayment  class contains getters and setters for username,ordername,price,image,address,creditcardno
*/

public class OrderPayment implements Serializable {
    private int orderId;
    private String userName;
    private String orderName;
    private String orderPrice;
    private String distance;
    private String drop;
//    private String userAddress;
    private String number;
//    private int saleAmount;
    private Date orderTime;
    private String pickUp_Location;
    private String dropOff_Location;
    private String driver;
    private String car;


//    public OrderPayment(int orderId, String userName, String orderName, String orderPrice, String number) {
//        this.orderId = orderId;
//        this.userName = userName;
//        this.orderName = orderName;
//        this.orderPrice = orderPrice;
////        this.userAddress = userAddress;
//        this.number = number;
//    }

    public OrderPayment(String driver,String car,int orderId, String userName,String pickUp_Location,String dropOff_Location, String orderPrice ,String distance,String drop) {
        this.driver = driver;
        this.car = car;
        this.orderId = orderId;
        this.userName = userName;
        this.orderPrice = orderPrice;
        this.distance = distance;
        this.drop = drop;
        this.pickUp_Location = pickUp_Location;
        this.dropOff_Location = dropOff_Location;
    }
    public String getDriver() {
        return driver;
    }
    public String getCar(){return car;}
//    public OrderPayment(int orderId, String orderName, double orderPrice, int saleAmount) {
//        this.orderId = orderId;
//        this.orderName = orderName;
//        this.orderPrice = orderPrice;
////        this.saleAmount = saleAmount;
//    }

//    public OrderPayment(String orderName, double orderPrice, int saleAmount) {
//        this.orderName = orderName;
//        this.orderPrice = orderPrice;
////        this.saleAmount = saleAmount;
//    }
//
//    public OrderPayment(int saleAmount, Date orderTime) {
////        this.saleAmount = saleAmount;
//        this.orderTime = orderTime;
//    }
//
//    public Date getOrderTime() {
//        return orderTime;
//    }
//
//    public void setOrderTime(Date orderTime) {
//        this.orderTime = orderTime;
//    }

//    public int getSaleAmount() {
//        return saleAmount;
//    }

//    public void setSaleAmount(int saleAmount) {
//        this.saleAmount = saleAmount;
//    }

//    public String getUserAddress() {
//        return userAddress;
//    }

//    public void setUserAddress(String userAddress) {
//        this.userAddress = userAddress;
//    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getName() {
        return userName;
    }
    public void setName(String userName) {
        this.userName = userName;
    }

    public int getOrderId() {
        return orderId;
    }
    public String getDistance(){return distance;}
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    public String getDrop(){return drop;}
    public String getPickUp_Location(){return pickUp_Location;}
    public String getDropOff_Location(){return dropOff_Location;}
    public String getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(String orderPrice) {
        this.orderPrice = orderPrice;
    }


}

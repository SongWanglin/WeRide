import java.io.IOException;
import java.io.*;


/* 
	Review class contains class variables username,productname,reviewtext,reviewdate,reviewrating

	Review class has a constructor with Arguments username,productname,reviewtext,reviewdate,reviewrating
	  
	Review class contains getters and setters for username,productname,reviewtext,reviewdate,reviewrating
*/

public class Review implements Serializable {
    private String orderId;
    private String driverName;
    private String rate;
    private String text;
    private String date;
    private String userName;
    private int rate1;



    public Review(String userName,String orderId, String driverName, String rate, String date,String text) {
        this.orderId = orderId;
        this.driverName = driverName;
        this.date = date;
        this.rate = rate;
        this.text = text;
        this.userName = userName;
    }
    public Review(String driverName,String rate){
        this.driverName = driverName;
        this.rate = rate;
    }
//    public int getRate1(){return rate1;}
    public String getUserName(){return userName;}
    public void setUserName(){this.userName = userName;}
    public String getDate(){return date;}
    public void setDate(){this.date=date;}
    public String getOrderId(){return orderId;}
    public String getDriverName(){return driverName;}
    public String getRate(){return rate;}
    public String getText(){return text;}

    public void setOrderId(){
        this.orderId = orderId;
    }
    public void setDriverName(){
        this.driverName = driverName;
    }
    public void setRate(){
        this.rate = rate;
    }
    public void setText(){
        this.text = text;
    }

}

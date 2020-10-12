import java.io.IOException;
import java.io.PrintWriter;
import java.io.*;



/* 
	Users class contains class variables id,name,password,usertype.

	Users class has a constructor with Arguments name, String password, String usertype.
	  
	Users  class contains getters and setters for id,name,password,usertype.

*/

public class User implements Serializable{
	private int id;
	private String name;
	private String password;
	private String email;
	private String number;
	//private String usertype;
	
	public User(String name, String password,String email,String number) {
		this.name=name;
		this.password=password;
		this.email = email;
		this.number = number;
		//this.usertype=usertype;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
//	//public String getUsertype() {
//		return usertype;
//	}

//	public void setUsertype(String usertype) {
//		this.usertype = usertype;
//	}
}

import java.sql.*;
import java.util.Scanner;

public class KodnestBank implements Bank {
   Scanner scan=new Scanner(System.in);
	static  Connection con=null;
	private  KodnestBank() throws Exception {
		super();
		// TODO Auto-generated constructor stub
		String url="jdbc:mysql://localhost:3306/myjdbcdb";
		String username="root";
		String password="vish";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			 con=DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	static KodnestBank kb=null;
	
	public static KodnestBank getCon() throws Exception
	{
		if(kb==null)
		{
		 kb=new KodnestBank();	
		}
		return kb;
		
	}
	
	
	


	public void registration()  {
		try {
		String sql="insert into kodnestbank values(?,?,?,?,?,?,?)";
		System.out.println("enter Account number");
		String accno=scan.next();
		System.out.println("enter Name");
		String name=scan.next();
		System.out.println("enter Password");
		String password=scan.next();
		System.out.println("enter to Password to Confirm");
		String confPassword=scan.next();
		System.out.println("enter Age");
		int age=scan.nextInt();
		System.out.println("enter Amount");
		int amount=scan.nextInt();
		System.out.println("enter Email");
		String email=scan.next();
		System.out.println("enter Phone Number");
		long phone=scan.nextLong();
		if(accno.length()<8)
		{
			System.out.println("account number must be 9 digit");
			return;
		}
		if(name.length()<3 )
		{
			System.out.println("Name must be 3 Letters");
			return;
		}
		
		if(password.length()<=5 )
		{
			System.out.println("paassword  must be more than 5 digit");
			return;
		}
		if(password.equals(confPassword)!=true )
		{
			System.out.println("Password mismatch !!!!");
			return;
		}
		
		if(amount<=0 )
		{
			System.out.println("amount should be greater than Zero !");
			return;
		}
		if(age<0 )
		{
			System.out.println("age should be greater than 18 !");
			return;
		}
		if(email.length()<=8 )
		{
			System.out.println("letters must be more than 8 !");
			return;
		}
		if(String.valueOf(phone).length()!=8 )
		{
			System.out.println("Phone Number Must be 10 Digit !");
			return;
		}
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setString(1, accno);
		pst.setString(2,name);
		pst.setString(3, password);
		pst.setInt(4,age);
		pst.setInt(5,amount);
		pst.setString(6,email);
		pst.setLong(7,phone);
		int eff=pst.executeUpdate();
		
		if(eff==1) {
		System.out.println("Registration completed sucecfully");
		}else
		{
			System.out.println("some problem occured");
		}
	}
	catch(Exception e)
	{System.out.println("exception is handled");
		e.printStackTrace();
	}
		
	}


	public void login()  {
		try {
		boolean flag=false;
		String sql="select amount from kodnestbank where account_no=? and password=?";
		System.out.println("enter the Account no");
		String name=scan.next();
		System.out.println("enter the Paasword");
		String password=scan.next();
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setString(1, name);
		pst.setString(2, password);
		
			ResultSet res=	pst.executeQuery();
			
			while(res.next())
			{
				System.out.println("Login succeefully");
				
				flag=true;
			}
			
			
		if(flag==false)
		{
			System.out.println("wrong account number or password ");
		}
	}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}


	public void updatePassword()  {
		try {
		 String sql="select * from kodnestbank where account_no=? and password=?";
		
		boolean flag=false;
	   
		System.out.println("enter the Account no");
		String accno=scan.next();
		System.out.println("enter the Paasword");
		String password=scan.next();
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setString(1, accno);
		pst.setString(2, password);
		
			ResultSet res=	pst.executeQuery();
			
			while(res.next())
			{
				System.out.println("Login succeefully");
			
				flag=true;
			}
			
			
	
	if(flag==false) 
	{
		System.out.println("wrong account number or password ");
		return;
	}
	
	String sql1="update kodnestbank set password=? where account_no=?";
	System.out.println("enter new Password");
	String p1=scan.next();
	System.out.println("re eter the password for conformation");
	String p2=scan.next();
	if(p1.equals(p2))
	{
		PreparedStatement pst1 = con.prepareStatement(sql1);
		pst1.setString(1, p1);
		pst1.setString(2, accno);
		pst1.executeUpdate();
		System.out.println("password is updated......");
		
	}
	else{
		System.out.println("password mismatch");
		return;
	}
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	
	}
	
	public void transferAmount() {
	  try {
		String sql1="update kodnestbank set amount=amount-? where account_no=? and password=?";
		con.setAutoCommit(false);
		System.out.println("enter your account number and password");
		String accno=scan.next();
		String pass=scan.next();
		System.out.println("enter the amount to be transfered");
		int trans=scan.nextInt();
		
		PreparedStatement pst1 = con.prepareStatement(sql1);
		
		pst1.setInt(1, trans);
		pst1.setString(2, accno);
		pst1.setString(3, pass);
		int effected=pst1.executeUpdate();
		if(effected==1)
		{
			System.out.println("Login sucessfull");
		}
		else
		{
			System.out.println("Wrong acount number or password");
			return;
		}
		String sql2="update kodnestbank set amount=amount+"+trans+" where account_no=?";
		System.out.println("enter the account number where to be transfered");
		PreparedStatement pst2 = con.prepareStatement(sql2);
		String accno2=scan.next();
		
		pst2.setString(1, accno2);
		int effected1=pst2.executeUpdate();
		if(effected1==1)
		{
			System.out.println("Transfered completed");
		}
		else
		{
			System.out.println("Transfered is not completed");
			return;
		}
		if((effected1==1) && (effected==1)) 
			con.commit();
			else
			{
				con.rollback();
			}
	  }
	  catch(Exception e)
	  {
		  try {
			con.rollback();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		  System.out.println("Transefer amount is rolled back");
	  }
		
		
	}





	public void CheckBalance()  {
		// TODO Auto-generated method stub
		try {
		boolean flag=false;
		String sql="select amount from kodnestbank where account_no=? and password=?";
		System.out.println("enter the Account no");
		String name=scan.next();
		System.out.println("enter the Paasword");
		String password=scan.next();
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setString(1, name);
		pst.setString(2, password);
		
			ResultSet res=	pst.executeQuery();
			
			while(res.next())
			{
				System.out.println("Login succeefully");
				System.out.println("Your Balance is ="+res.getInt(5));
				flag=true;
			}
			
			
		if(flag==false)
		{
			System.out.println("wrong account number or password ");
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}





	public void CheckProfile()  {
		try {
		String sql="select * from kodnestbank where account_no=? and password=?";
		System.out.println("enter the Account no");
		String name=scan.next();
		System.out.println("enter the Paasword");
		String password=scan.next();
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setString(1, name);
		pst.setString(2, password);
		
			ResultSet res=	pst.executeQuery();
			if(res.next())
			{
				System.out.println("<<<<<<<<<<<<<<<<===========>>>>>>>>>>>>>>>>>");
				System.out.println("Account number>>>>>>>>>>>>"+res.getString(1));
				System.out.println("Account Holder Name>>>>>>>"+res.getString(2));
				System.out.println("Account Password>>>>>>>>>>"+res.getString(3));
				System.out.println("Account Holder Age>>>>>>>>"+res.getInt(4));
				System.out.println("Account Balance>>>>>>>>>>>"+res.getInt(5));
				System.out.println("Account Holder Email>>>>>>"+res.getString(6));
				System.out.println("Account Holder Phone>>>>>>"+res.getLong(7));
				System.out.println("<<<<<<<<<<<<<<<<=====THANK YOU======>>>>>>>>>>>>>>>>>");
			
			}
			else
				System.out.println("Wrong Account number or Password");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
	}





	public void deleteAccount()  {
		try {
		
		String sql="delete from kodnestbank where account_no=? and password=?";
		System.out.println("enter the Account no");
		String name=scan.next();
		System.out.println("enter the Paasword");
		String password=scan.next();
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setString(1, name);
		pst.setString(2, password);
		int res=	pst.executeUpdate();
		if(res==1)
		{
			System.out.println("ACCOUNT DELETED....");
		
		}
		else
			System.out.println("Wrong Account number or Password");	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}


	
	

}

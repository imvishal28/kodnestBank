import java.sql.*;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan=new Scanner(System.in);
		BankFactory bankf=new BankFactory();
		KodnestBank kb=(KodnestBank) bankf.getBank();
		int choice;
		Connection con=null;
		try {
			
		
			while(true) {
			System.out.println("WELCOME TO KODNEST BANK");
			System.out.println("1 FOR Registration");
			System.out.println("2 FOR Login");
			System.out.println("3 FOR update Password");
			System.out.println("4 FOR Transfer the amount");
			System.out.println("5 FOR Check Balance");
			System.out.println("6 FOR Check Profile");
			System.out.println("7 FOR Delete Account");
			System.out.println("8 FOR exist");
			
            choice=scan.nextInt();
            switch(choice)
            {
            case 1 :kb.registration();
                       System.out.println("THANK YOU");
                       System.out.println(">>>>>>>>>>>>>>>>>>>");
                       break;
            case 2 :kb.login();
            System.out.println("THANK YOU");
            System.out.println(">>>>>>>>>>>>>>>>>>>");
            break;
            case 3 :kb.updatePassword();
            break;
            case 4 :kb.transferAmount();
            break;
            case 5 : kb.CheckBalance();
            break;
            case 6 : kb.CheckProfile();
            break;
            case 7 : kb.deleteAccount();
            break;
            default : 
            	System.out.println("THANK YOU FOR USING THE KODNEST ONLINE BANK...");
            	System.exit(0);
            }
			}
		}
		
	
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}
	

}

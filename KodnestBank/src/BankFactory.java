
public class BankFactory {

	
	public Bank getBank()
		{
			try {
				return KodnestBank.getCon();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
	}



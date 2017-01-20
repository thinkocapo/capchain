package dao.evm;



public class MoneyTransfer
{
	public MoneyTransfer() {
		// TODO Auto-generated constructor stub
	}
	public void execute(ExternallyOwnedAccount sender, int usd, ExternallyOwnedAccount receiver) {
		System.out.println("> MoneyTransfer executing < ");
		//sender.updateBalance(usd, "subtraction");
		//receiver.updateBalance(usd,  "addition");
	}
	
}
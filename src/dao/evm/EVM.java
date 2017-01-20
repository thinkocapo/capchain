package dao.evm;
import java.util.HashSet;
import java.util.LinkedList;

public class EVM <T> { // ***
	private Blockchain blockchain;	
	private HashSet<Account> accounts;
	
	public EVM(Blockchain blockchain) {
		this.blockchain = blockchain;
		this.accounts = new HashSet<Account>();
	}
	
	public void registerAccount (Account account) {
		accounts.add(account);
	}

	public Blockchain blockchain() {
		return blockchain;
	}
	
	public HashSet<Account<T>> getAccountsByType (String type) {
		HashSet<Account<T>> accts = new HashSet<Account<T>>();
			if (type.equals("ContractAccount")) {
				for (Account acct: accounts) {
					if (acct.type() instanceof ContractAccount) {
						accts.add(acct);
					}
				}
			}
			if (type.equals("ExternallyOwnedAccount")) {
				for (Account acct: accounts) {
					if (acct.type() instanceof ExternallyOwnedAccount) {
						accts.add(acct);
					}
				}
			}
		return accts;
	}
	
	public void addTransactionToBlockchain(Transaction tx) throws BlockchainRejectedException {
		if (1 > 0) {
			blockchain.addTransaction(tx);
		} else {
			throw new BlockchainRejectedException();
		}
		
	}
}

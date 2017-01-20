package dao.evm;

import java.util.HashMap;
import java.util.HashSet;

public class ExternallyOwnedAccount extends Account<ExternallyOwnedAccount> {
	private int balance;
	private String username;
	private String password;
	private EVM evm; // warning because of EVM<T> in EVM.java ***
	
	public ExternallyOwnedAccount(String username, String password, int startingBalance) {
		this.balance = startingBalance;
		this.username = username;
		this.password = password;
		// "address" of the EOA *
	}
	
	public int updateBalance(int usd, String modifier) {
		if (modifier == "addition") {
			balance = balance + usd;
		}
		if (modifier == "subtraction") {
			balance = balance - usd;
		}
		return balance;
	}
	
	public void executeContract(String contractName, HashMap<String, Object> argMap) { // instance, ContractAccount, class
		
		HashSet<Account<ContractAccount>> accts = new HashSet<Account<ContractAccount>>();
		accts = evm.getAccountsByType("ContractAccount"); // capchain3 warning *
		
		Transaction tx = new Transaction();
		for (Account<ContractAccount> acct: accts) {
			if (acct.type().getContractAccountName() == contractName) {
				tx = acct.type().execute(this, argMap); // when executing a contract, is the executor (eoa) always needed? *
			}
		}
		
		try {
			evm.addTransactionToBlockchain(tx);
		} catch (BlockchainRejectedException e) {
			System.out.println("exception thrown when trying to add the transaction to the blockchain");
			// need to un-do the transaction at this point, if it failed *
		}
	}
	
	public void refreshMyEVM(EVM evm) {
		this.evm = evm;
	}
	
	public int getBalance() {
		return balance;
	}
	public void increaseBalance(int USD) {
		balance += USD;
	}
	public void decreaseBalance(int USD) {
		balance -= USD;
	}
	
	public String toString() {
		return username + "'s balance: " + balance;
	}

		
}

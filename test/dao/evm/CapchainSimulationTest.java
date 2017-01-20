package dao.evm;

import static org.junit.Assert.assertEquals;
import java.util.HashMap;
import java.util.HashSet;
import org.junit.Test;

public class CapchainSimulationTest {
	
	public CapchainSimulationTest() {
		
	}
	
	@Test
	public void singleEvmTest () {
		System.out.println("\n*** SingleEvmTest Running ***\n");
		/*
		 * EVM - Ethereum Virtual Machine - has two components to its state
		 *   
		 *   1. Blockchain - the digital ledger, transaction history,
		 *                   a linked list of blocks which contain transactions
		 *   
		 *   2. Accounts - there are 2 kinds of accounts, called EOA and CA
		 * 			 EOA - Externally Owned Account
		 * 			 CA - Contract Account
		 */
		
		// Create the blockchain (Will "Cap"chain)
		Blockchain blockchain = new Blockchain();
		
		// Create an empty block and add it to the blockchain
		Block block = new Block();
		
		assertEquals(0, block.transactionHistory());
		
		int expectedBlockChainSize = 0;
		blockchain.addBlock(block);
		expectedBlockChainSize++;
		
		assertEquals(expectedBlockChainSize, blockchain.blocksSize());
		
		
		// Create an EVM and record the blockchain on it's state
		EVM evm = new EVM(blockchain);
		
		assertEquals(blockchain, evm.blockchain());
		
		
	    /* 
	     * Now our EVM and Blockchain have been made. But it has no accounts yet.
		 */
		
		/*
		 * Setup ExternallyOwnedAccounts
		 */
		// Create 2 empty Account instances, which we will use for EOA's
		Account<ExternallyOwnedAccount> account = new Account<ExternallyOwnedAccount>();
		Account<ExternallyOwnedAccount> account2 = new Account<ExternallyOwnedAccount>();
		
		assertEquals(account.type(), null);
		assertEquals(account2.type(), null);
		
		// Prepare 2 EOA instances...
		int expectedExternallyOwnedAccounts = 0;
		ExternallyOwnedAccount eoaAlice = new ExternallyOwnedAccount("Alice", "pw123", 10);
		ExternallyOwnedAccount eoaBob = new ExternallyOwnedAccount("Bob", "pw123", 0);
		
		// Set the 2 EOA instances on the 2 empty Account's
		account.set(eoaAlice);
		expectedExternallyOwnedAccounts++;
		account2.set(eoaBob);
		expectedExternallyOwnedAccounts++;
		
		assertEquals(account.type(), eoaAlice);
		assertEquals(account2.type(), eoaBob);
		
		// Register these new Accounts (of type EOA) in the EVM
		evm.registerAccount(account);
		evm.registerAccount(account2);
		
		// * assertEquals(evm.getExternallyOwnedAccounts(), new HashSet<Account<ExternallyOwnedAccount>>().add(eoa).add(eoaBob).size()); // *
		assertEquals(evm.getAccountsByType("ExternallyOwnedAccount").size(), expectedExternallyOwnedAccounts);

		/*
		 * Setup Contract Accounts
		 */
		// Create 1 empty Account instance, which we will use for a CA
		Account<ContractAccount> account3 = new Account<ContractAccount>();
		assertEquals(null, account3.type());
		
		
		// Add the contract code to the CA
		int expectedContractAccounts = 0;
			
		ContractCode MoneyTransferContract = (ExternallyOwnedAccount sender, HashMap<String, Object> argMap) -> {
			System.out.println("\n> MoneyTransfer executing < \n");
			
			int USD = (int) argMap.get("USD");
			ExternallyOwnedAccount recipient = (ExternallyOwnedAccount) argMap.get("ExternallyOwnedAccount");
			
			// Sender pays the recipient
			// Can wrap these in a database transaction? ***
			sender.decreaseBalance(USD);
			recipient.increaseBalance(USD);
			
			// Somehow record the transaction details of what happened in a transaction instance ***
			Transaction tx = new Transaction();
			
			// 4. Return the 'transaction' so the next function on the callstack can receive this transaction, and send onward to the Blockchain *
			return tx;
			
			// Notes
			//http://ethdocs.org/en/latest/contracts-and-transactions/account-types-gas-and-transactions.html says the contract account has a balance too *
			// e.g. voting - store on the voter who they voted for, or store it somewhere in the blockchain *
			
		};
		
		ContractAccount ca = new ContractAccount("MoneyTransfer", MoneyTransferContract);
		
		assertEquals(ca.getContractAccountName(), "MoneyTransfer");

		account3.set(ca);
		expectedContractAccounts++;
		assertEquals(account3.type(), ca);
		
		// Register the account in the EVM
		evm.registerAccount(account3);
		assertEquals(evm.getAccountsByType("ContractAccount").size(), expectedContractAccounts);
	
		
		/*
		 * Get all Accounts by <T> Type
		 */	
		evm.getAccountsByType("ContractAccount");
		evm.getAccountsByType("ExternallyOwnedAccount");
		
		assertEquals(evm.getAccountsByType("ContractAccount").size(), expectedContractAccounts);
		assertEquals(evm.getAccountsByType("ExternallyOwnedAccount").size(), 2);
		
		/*
		 * Perform a Transaction - Alice pays $10 to Bob using the contract MoneyTransfer
		 */
		// 
		eoaAlice.refreshMyEVM(evm); // reverse ? *
		
		// create HashMap of arguments to pass to the invocation of the contract
		HashMap<String, Object> argMap = new HashMap<String, Object>();
		argMap.put("USD", 10);
		argMap.put("ExternallyOwnedAccount", eoaBob);
		
		// Print Alice and Bob's balances before executing the MoneyTransfer contract
		System.out.println(eoaAlice);
		System.out.println(eoaBob);
		
		eoaAlice.executeContract("MoneyTransfer", argMap); // should be evm.executeContract ***
		
		// Alice's balance should be $X.XXX
		assertEquals(eoaAlice.getBalance(), 0);
		// Bob's balance should be $10.00
		assertEquals(eoaBob.getBalance(), 10);
		
		// Print Alice and Bob's balances after executing the MoneyTransfer contract
		System.out.println(eoaAlice);
		System.out.println(eoaBob);
	}
	
	@Test
	public void multiEvmTest () {
		//System.out.println("\n*** MultiEvmTest Running ***\n");
	}
	
	/*
	 * Private helper methods & contractCode classes for things running in each test
	 */

}

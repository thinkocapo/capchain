package dao.evm;

import java.util.Date;
import java.util.HashSet;

public class Block {
	private int parentBlock;
	private int rootBlock;
	private HashSet<Transaction> transactions; // *
	private Date timestamp;
	
	// Constructor for initializing 1st and final instance of the Blockchain
	public Block() {
		this.parentBlock = 0; // null * parentBlock's header, according to YellowPaper
		this.rootBlock = this.hashCode();
		this.timestamp = new Date();
		this.transactions = new HashSet<Transaction>(); // * Merkle Tree *
	}
	// Constructor for initializing subsequence Blocks, for the Blockchain
	public Block(Block parentBLOCK) {
		this.parentBlock = parentBLOCK.hashCode();
		this.timestamp = new Date();
		//this.rootBlock = parentBLOCK.getRootBlock(); 
		this.transactions = new HashSet<Transaction>(); 
	}
	
	public int transactionHistory() {
		return transactions.size();
	}
	
	// Returns rootBlock, which is a hash
//	public int getRootBlock() {
//		return rootBlock;
//	}
	
	public void addTransaction(Transaction tx) {
		// **** try/catch here should trickle all the way back down the callstack to whatever called .execute ***
		transactions.add(tx);
	}
	public String toString() {
		return "BLock's hash: " + rootBlock;
	}

}


// logBloom - receipts of each transaction in transactions list
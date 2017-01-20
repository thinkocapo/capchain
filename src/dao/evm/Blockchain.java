package dao.evm;
import java.util.LinkedList;
import java.util.ArrayList;

public class Blockchain {
	
	//public static LinkedList blocks; // Static because there will only ever be 1 blockchain (instance) *
	public LinkedList<Block> blocks;
	//public ArrayList<EVM> connectedEVMs; *
	
	public Blockchain() {
		this.blocks = new LinkedList<Block>();
	}
	
	public void addBlock(Block block) {
		this.blocks.add(block);
	}
	
	public int blocksSize() {
		return blocks.size();
	}
	
	public LinkedList<Block> blocks () {
		return blocks;
	}
	
	// temporary *
	public void addTransaction(Transaction tx) {
		Block block = blocks.pop();
		block.addTransaction(tx);
	}
	
//	public String toString() { *
//		return blocks;
//	}

}

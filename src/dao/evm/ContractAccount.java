package dao.evm;
import java.util.HashMap;
public class ContractAccount {
	public String contractAccountName;
	public ContractCode contractCode;
	
	public ContractAccount (String contractAccountName, ContractCode contractCode) {
		this.contractAccountName = contractAccountName;
		this.contractCode = contractCode;
	}

	public String getContractAccountName() {
		return contractAccountName;
	}
	
	// executor is the eoa that's invoking the contract
	public Transaction execute(ExternallyOwnedAccount executor, HashMap<String, Object> argMap) { 
		Transaction tx = this.contractCode.doSomething(executor, argMap);
		// send the tx to the Blockchain ***
		return tx; 
	}

}

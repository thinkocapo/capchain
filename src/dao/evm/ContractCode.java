package dao.evm;

import java.util.HashMap;

public interface ContractCode {
	// capchain3 args were ExternallyOwnedAccount sender, int usd, ExternallyOwnedAccount receiver* 
	Transaction doSomething(ExternallyOwnedAccount executor, HashMap<String, Object> argMap);
}

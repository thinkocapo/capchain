# capchain
I was trying to understand Ethereum blockchain and kept getting confused every time I read through its wiki docs.
I figured, "What better and more exciting way to learn something than to try implementing it and coding it?".
And so the Capchain project was born. I was also looking for a side project to practice my java skills on while I was enrolled
in the Java & Hadoop course at Harvard Extension School.

About:
Capchain is a learning tool. Read the test CapchainSimulationTest.java https://github.com/thinkocapo/capchain/blob/master/test/dao/evm/CapchainSimulationTest.java to learn about how ethereum and blockchain works.
Capchain is a very high-level overview of how ethereum and blockchain work.
The example in the test is 1 user paying money to another user. Their names are Alice and Bob. Alice pays Bob.
A future example I'd like to incorporate is a voting system.
Capchain does not cover the Merkle Tree that's used in a real blockchain. Though there are java libraries available for working
with Merkle Trees for the future.

Hardest Challenge:
The Account class has an execute method for executing contracts. The problem is that different contracts take different amounts and types of arguments, and java is a static-typed language, so you can't pass an arbitrary amount of arguments of different types to the .execute method to execute any given contract. It will fail during compilation. I solved this by using Interfaces, Anonymous Functions and Java 8 Lambdas. Execute takes an account (user) as an argument, and then a HashMap as a second argument. This second argument holds the arguments that will go on to be passed to the Contract code for execution. To piece it together, ask me or see line 7 of ContractCode https://github.com/thinkocapo/capchain/blob/master/src/dao/evm/ContractCode.java and line 37 of ExternallyOwnedAccount https://github.com/thinkocapo/capchain/blob/master/src/dao/evm/ExternallyOwnedAccount.java and line 18 of ContractAccount https://github.com/thinkocapo/capchain/blob/master/src/dao/evm/ContractAccount.java 

Here are some basic java classes and vocab that get used in Blockchain. These were the concepts I really wanted to learn well.
These are my interpretations of what they do.

EVM - Ethereum Virtually Machine
EOA - Externally Owned Account
Contract
Accounts
MoneyTransferContract

Eventually I'd like to put full definitions in this README, so you don't even have to do your own research outside of this repo. Note - comments not professionaly written in source files.

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

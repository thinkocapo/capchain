# capchain
I was trying to understand Ethereum blockchain and kept getting confused every time I read through its wiki docs.
I figured, "What better and more exciting way to learn something than to try implementing it and coding it?".
And so the Capchain project was born. I was also looking for a side project to practice my java skills on while I was enrolled
in the Java & Hadoop course at Harvard Extension School.

About:
Capchain is a learning tool. Read the test CapchainSimulationTest.java to learn about how ethereum and blockchain works.
Capchain is a very high-level overview of how ethereum and blockchain work.
The example in the test is 1 user paying money to another user. Their names are Alice and Bob. Alice pays Bob.
A future example I'd like to incorporate is a voting system.
Capchain does not cover the Merkle Tree that's used in a real blockchain. Though there are java libraries available for working
with Merkle Trees for the future.

Challenge:
When users execute contracts, they amounts and types of arguments are arbitrary, because the contract could be for anything. This was difficult to code because Java is a static-typed language. So I designed Capchain so that any contract you create is an anonymous function. I used java 8 lambda functions to help accomplish this. E.g. the Contract for userA-pays-userB takes as arguments an ID of the user you want to pay, and the amount you want to pay them.

https://github.com/thinkocapo/capchain/blob/master/test/dao/evm/CapchainSimulationTest.java

Here are some basic java classes and vocab that get used in Blockchain. These were the concepts I really wanted to learn well.
These are my interpretations of what they do.

EVM - Ethereum Virtually Machine
EOA - Externally Owned Account
Contract
Accounts
MoneyTransferContract

Eventually I'd like to put full definitions in this README, so you don't even have to do your own research outside of this repo.

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

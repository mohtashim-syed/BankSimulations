# Bank Simulation – Java Multithreaded Project

Author: Mohtashim Syed  
Course: CS 4348 – Operating Systems  
Project Title: Bank Simulation with Threads and Semaphores
Language: Java

This project simulates a multithreaded bank environment where 3 tellers serve 50 customers performing deposit or withdrawal transactions. Access to the bank is limited to 2 customers at a time through a semaphore-controlled door. Customers must wait for the bank to open (when all tellers are ready), and then line up. Tellers interact with one customer at a time, and depending on the transaction type, they either go to the safe (at most 2 tellers can access at once), or also request manager permission (1 teller at a time).

To compile the program on cs1/cs2 or any standard terminal, navigate to the directory containing the files and run:  
`javac *.java`

This will compile all Java files in the project.

To run the program, execute:  
`java BankSimulation`

The project includes the following files:  
- BankSimulation.java: the main simulation controller that initializes and starts all threads.  
- Teller.java: defines the teller behavior, including transaction handling, manager interaction, and safe access.  
- Customer.java: defines the customer behavior, including waiting, entering, and interacting with tellers.  
- BankResources.java: static utility class holding shared semaphores and the customer queue.  
- devlog.md: detailed record of all development sessions, with timestamps matching commit history.  
- README.md: this file, containing documentation and instructions.

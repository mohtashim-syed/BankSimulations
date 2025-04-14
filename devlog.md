# Development Log: Bank Simulation Project

**Student:** Mohtashim Syed  
**Course:** CS 4348 – Operating Systems  
**Project:** Java Bank Simulation with Semaphores  
**Repository:** Initialized locally

---

## 2025-04-08 12:45 — Init Planning

### Thoughts So Far
Today I read through the entire project description. The simulation requires three tellers and 50 customers, with rules around access to the safe (max: two tellers), manager (one teller at a time), and the door (max two customers at a time). I also need to ensure that customers can't enter until all tellers are ready. Output must follow a very specific logging format.

I’ve decided to implement this in Java using only standard libraries. Synchronization will be handled using Semaphore, and shared data will be organized through a static utility class, called BankResources.java .

### Overall Plan
- Use a BlockingQueue to model the queue of customers.
- Tellers will repeatedly pull customers from this queue.
- Semaphore(2) will protect the safe.
- Semaphore(1) will protect the manager.
- Semaphore(2) for door control.
- Semaphore(0) to block customers from entering until all tellers are ready.
- Begin by building file structure: BankSimulation.java, Teller.java, Customer.java, and BankResources.java.

---

## 2025-04-09 20:45 — Core File Setup and Initial Class Definitions

### Thoughts So Far
I'm realizing the most difficult part of the project may be managing the inter-thread communication without missing signals. I need to keep careful track of which customer is talking to which teller.

### Session Plan
- Implement the BankResources.java with semaphores and a customer queue
- Teller.java and Customer.java threads
- In BankSimulation.java, initialize and start 3 tellers and 50 customers after a gate semaphore is released

### Notes
- Set up all semaphores and the queue in BankResources
- Each teller signals readiness, and the last one to do so releases the bank open gate
- Customers now wait for the bank to open and then enter the queue

Implemented: Initial version of BankResources, Teller, Customer, BankSimulation

---
## 2025-04-10 20:00 — Implement Transaction Behavior + Synchronization

### Thoughts So Far
I’ve been thinking about how to break down the transaction process. A customer will introduce itself, give its transaction type, then wait while the teller interacts with the manager and/or safe.

### Session Plan
- Add askTransaction(), giveTransaction(), and transactionDone() methods to Customer
- In Teller, based on transaction type, go to the manager (for withdrawals), then to the safe
- Simulate interaction with Thread.sleep() calls
- Add safe, manager access logs, and customer exit behavior

### Updates
- Encountered a potential deadlock when two tellers block on the safe, and one on the manager. Solved by sequencing locks properly
- Added poison pill (null in queue) to terminate teller threads after customers finish

Implemented: Teller and customer transaction logic

---

## 2025-04-11 20:35 — Testing, Logging, and Output Validation

### Thoughts So Far
I wanted to ensure the logs were readable and consistent. Originally, some lines were out of order because of thread timing. I added more logging before and after every block/sleep to maintain clarity. There are also some issues with the outputs. The logic seems fine, but it seems like the output is laid out wrong compared to the example. Also, I got a depreciation warning for overriding Thread.getId() in Customer.java.

### Session Plan
- Verify logging format matches required spec: THREAD_TYPE ID [THREAD_TYPE ID]: ACTION
- Refactor print statements in Customer.java, Teller.java, and BankSimulation.java
- Remove deprecated getId() override from Customer and replace it with getCustomerId()
- Test updated output with 50 customers and 3 tellers to confirm compliance
- Update BankResources.java for shared synchronization elementss
- Add teller shutdown messages and final closing message as per project spec

### Updates
- Rewrote Customer.java log messages to precisely match format
- Moved “wants to perform transaction” print into the constructor for consistency
- Renamed getId() to getCustomerId() to avoid deprecated method override
- Updated Teller.java to reflect full interaction: selection, transaction type, manager and safe usage, and customer handoff
- Ensured that both teller and customer messages include actor ID and related thread ID
- Manual verification confirms output sequence now matches professor’s example


### Notes
- Create a clean README.md with compilation/run instructions
- Prep files to upload onto git
- Ran full simulation — output looks correct

---

## 2025-04-13 23:05 — Final Cleanup and Submission Prep

### Thoughts So Far
Satisfied with how this turned out. The thread management is clean, and the output is readable and sequential. I’ve tested multiple times and I didn't find no race conditions or crashes.

### Session Plan
- Add inline comments and Javadoc for all classes
- Prepare README with compile/run instructions
- Ensure the working directory is clean
- Zip and prepare submission folder

Committed: Added all the files needed into git
Submitted: .zip archive with full repo

---
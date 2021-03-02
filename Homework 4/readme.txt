Homework #4 
Morgan Maness
010813680

	The first class in my program is my Data class. That is just an easy way for me to hold all of my variables such as my semaphores, mutex, and bounded buffer, which is the linked list named list. 
	The next class in my program is the consumer class. It goes through and checks if the full semaphore is empty. If it is empty, then the thread waits until something is put in the buffer. When that semaphore has something in it, the thread first takes the mutex. Then I print out the consumer and remove the item from the list. I then adjust the capacity in my bounded buffer and adjust the empty semaphore (the full semaphore gets adjusted if it is called true). Then it releases the mutex and sets the thread to sleep for a random time. 
	My next class is the producer class. It is laid out the exact same as the consumer, except opposite. It checks if the semaphore is full or not, and if it is not full then it takes the mutex, adds an item to the buffer, adjusts the semaphore, releases the mutex and puts the thread to sleep.
The final class in my program is the main class, the ProducerConsumer. First I take the arguments from the command line, then print out what was entered in the command line. I then create as many producers and consumers as dictated from the command line.
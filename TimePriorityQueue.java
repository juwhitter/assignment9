
package assignment9;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;

/**
 * @assignment   assignment9
 * @title        TimePriorItyQueue
 * @description  Timing class for the BST and heap queues.
 * @author       Julian Whitteron, Anastasia Cherkaev
 * @unid               u0593180 & u0345443
 * @version      July 14, 2013
 */

public class TimePriorityQueue 
{

	private static Random rand;

	public static void main(String[] args) throws FileNotFoundException
	{   


		ArrayList<ArrayList> listOfRandomNumbers = new ArrayList<ArrayList>();
		//ArrayList<HashSet> listOfLists = new ArrayList<HashSet>();

		int N = 5;
		while(N < 20006)
		{
			rand = new Random();
			int seed = 1;
			rand.setSeed(seed);


			for(int i = 0; i < 100; i++){
				ArrayList temp = new ArrayList(N);
				while (temp.size() < N){
					int random = rand.nextInt();
					if(!temp.contains(random))
						temp.add(random);
				}
				listOfRandomNumbers.add(temp);
			}



			//run the test code
			long startTime, midpointTime, stopTime;
		

			// First, spin computing stuff until one second has gone by.
			// This allows this thread to stabilize.
			startTime = System.nanoTime();

			while (System.nanoTime() - startTime < 1000000000) 
			{} // empty block   

			// Now, run the test.
			int timesToLoop = 100;
			//for ADD
//			PriorityQueueHEAP temp = new PriorityQueueHEAP();
//			PriorityQueueBST temp = new PriorityQueueBST();
			
			//For DeleteMin()
			ArrayList<PriorityQueueHEAP> listOfPQs = new ArrayList<PriorityQueueHEAP>();
	//		ArrayList<PriorityQueueBST> listOfBSTs = new ArrayList<PriorityQueueBST>();
			for (int i = 0; i < timesToLoop; i++) 
			{
				
				PriorityQueueHEAP temp = new PriorityQueueHEAP();
				ArrayList wordsToAdd = listOfRandomNumbers.get(i);
				for(int j = 0; j< N-2; j++)
					temp.add(wordsToAdd.get(j));
				listOfPQs.add(temp);
			}

				startTime = System.nanoTime();

				// Do test stuff
				for (int i = 0; i < timesToLoop; i++)
				{    
					//For findMin
//					PriorityQueueBST temp = listOfBSTs.get(i);
//					temp.findMin();
					
//					For DeleteMin
					PriorityQueueHEAP temp = listOfPQs.get(i);
					for(int j = 0; j < N-2; j++){
						temp.deleteMin();
					}
					
					
					//forADD
//					ArrayList wordsToAdd = listOfRandomNumbers.get(i);
//					for(int j = 0; j < N; j++)
//						temp.add(wordsToAdd.get(j));
//					temp.clear();

				}

				midpointTime = System.nanoTime();

				// Run an empty loop to capture the cost of running the loop.
				for (int i = 0; i < timesToLoop; i++) 
				{
//					PriorityQueueBST temp = listOfBSTs.get(i);
//					temp = listOfBSTs.get(i);
					//For DeleteMin
					PriorityQueueHEAP temp = listOfPQs.get(i);
					for(int j = 0; j < N-2; j++){
					}

//					 USE FOR ADD METHOD
//									ArrayList wordsToAdd = listOfRandomNumbers.get(i);
//									for(int j = 0; j< N-2; j++)
//										wordsToAdd.get(j);
//									temp.clear();

				} // empty block

				stopTime = System.nanoTime();

				// Compute the time, subtract the cost of running the loop
				// from the cost of running the loop and computing square roots.
				// Average it over the number of runs.
				double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / timesToLoop;


				//System.out.println("For N =" + N + ", It takes exactly " + averageTime
				//    + " with the contains method");
				//System.out.println(N + "\t" +averageTime);
				System.out.println(N + "\t" + averageTime);
				N+=500;

				listOfRandomNumbers.clear();
			}
		}

		public static Integer randomInt()
		{
			return new Integer(rand.nextInt());
		}

	}


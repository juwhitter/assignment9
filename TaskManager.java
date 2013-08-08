package assignment9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Represents a wrapper for running system tasks 
 * It reads a task file and extrcats infromation for each task
 * Then it uses a priority queue to present the tasks in order upon request
 * 
 * @author Paymon Saebi
 */
public class TaskManager 
{
	private File tasks;
	private PriorityQueueHEAP<SystemTask> PQ;	
	
	public TaskManager(File _tasks, Comparator<? super SystemTask> cmp)
	{
		this.tasks = _tasks;
		this.PQ = new PriorityQueueHEAP<SystemTask>(cmp);				
	}
	
	public boolean isDone() 
	{
		return this.PQ.size() == 0;
	}
	
	public String getTask() 
	{
		return this.PQ.findMin().getTaskName();
	}	
	
	public String nextTask() 
	{		
		return this.PQ.deleteMin().getTaskName();
	}	
	
	/**
	 * This method reads the file passed in to the constructor 
	 * Then it creates a SystemTask object with the info from the file
	 * First token in each line is a string for task name
	 * Second token in each line is a char for task priority group
	 * First token in each line is a string for task priority level
	 */
	public void populateTasks() 
	{
		try 
		{			
			//Java's Scanner class is a simple lexer for Strings and primitive types
			//(Please make sure to see the Java API, if you are unfamiliar).			
			Scanner fileInput = new Scanner(this.tasks);
			
			int level = 0;
			char group = 0;
			String name = "";
			String token = "";
			
			while (fileInput.hasNextLine()) 
			{		
				token = fileInput.next();					
					
				if (!token.equals("")) 
					name = token;
				
				token = fileInput.next();					
				
				if (!token.equals("")) 
					group = token.charAt(0);
				
				token = fileInput.next();					
				
				if (!token.equals("")) 
					level = Integer.parseInt(token);
									
				this.PQ.add(new SystemTask(name, group, level));
			}
			
			//this.PQ.generateDotFile("tasks.dot");
		} 
		catch (FileNotFoundException e) 
		{
			System.err.println("File " + tasks + " cannot be found.");
		}
	}
}

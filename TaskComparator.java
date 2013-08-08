package assignment9;
/**
 * @assignment   assignment9
 * @title        TaskComparator
 * @description  Comparator for managing min-heap tasks.
 * @author       Julian Whitteron, Anastasia Cherkaev
 * @unid               u0593180 & u0345443
 * @version      July 14, 2013
 */
import java.util.Comparator;

public class TaskComparator implements Comparator<SystemTask>
{
	public int compare(SystemTask task1, SystemTask task2) 
	{
		/*
		 * Sorts the tasks based on their priority group, in the event of a tie checks the individual priority levels of the two tasks.
		 */
		if(task1.getPriorityGroup() > task2.getPriorityGroup())
			return 1;
		else if(task1.getPriorityGroup() < task2.getPriorityGroup())
			return -1;
		else if(task1.getPriorityGroup() == task2.getPriorityGroup())
		{
			if(task1.getPriorityLevel() > task2.getPriorityLevel())
				return 1;
			else if (task1.getPriorityLevel() < task2.getPriorityLevel())
				return -1;
		}
		return 0;
	}

}

package assignment9;

/**
 * Represents the information to define a system task
 * Each task has name, a priority group A-Z, and a priority level 1-100
 * Lower values on group and level denote a higher priority
 * 
 * @author Paymon Saebi 
 */
public class SystemTask
{
	private String taskName;
	private char priorityGroup;
	private int priorityLevel;

	public SystemTask(String _taskName, char _priorityGroup, int _priorityLevel) 
	{
		this.taskName = _taskName;
		this.priorityGroup = _priorityGroup;
		this.priorityLevel = _priorityLevel;
	}

	public String toString() 
	{
		return "(Task: " + taskName + ", Group: " + priorityGroup + ", Level: " + priorityLevel + ")";
	}

	public String getTaskName() 
	{
		return this.taskName;
	}

	public char getPriorityGroup () 
	{
		return this.priorityGroup;
	}

	public int getPriorityLevel() 
	{
		return this.priorityLevel;
	}
}

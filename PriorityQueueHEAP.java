package assignment9;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.NoSuchElementException;
/**
 * @assignment   assignment9
 * @title        PriorityQueueHEAP
 * @description  Object class for creating and manipulating heap-based Priority queues.
 * @author       Julian Whitteron, Anastasia Cherkaev
 * @unid               u0593180 & u0345443
 * @version      July 14, 2013
 */
/**
 * Represents a priority queue of generically-typed items. 
 * The queue is implemented as a min heap. 
 * The min heap is implemented implicitly as an array.
 * 
 * @author Paymon Saebi & ??
 */
public class PriorityQueueHEAP<AnyType> 
{
	private int currentSize;
	private AnyType[] array;
	private Comparator<? super AnyType> cmp;

	/**
	 * Constructs an empty priority queue. Orders elements according
	 * to their natural ordering (i.e., AnyType is expected to be Comparable)
	 * AnyType is not forced to be Comparable.
	 */
	@SuppressWarnings("unchecked")
	public PriorityQueueHEAP() 
	{
		currentSize = 0;		
		array = (AnyType[]) new Object[10]; // safe to ignore warning
		cmp = null;
	}

	/**
	 * Construct an empty priority queue with a specified comparator.
	 * Orders elements according to the input Comparator 
	 * (i.e., AnyType need not be Comparable).
	 */
	@SuppressWarnings("unchecked")
	public PriorityQueueHEAP(Comparator<? super AnyType> c) 
	{
		currentSize = 0;		
		array = (AnyType[]) new Object[10]; // safe to ignore warning
		cmp = c;
	}

	/**
	 * @return the number of items in this priority queue.
	 */
	public int size() 
	{
		return currentSize;
	}

	/**
	 * Makes this priority queue empty.
	 */
	public void clear() 
	{
		currentSize = 0;
	}

	/**
	 * @return the minimum item in this priority queue.
	 * @throws NoSuchElementException if this priority queue is empty.
	 * 
	 * (Runs in constant time.)
	 */
	public AnyType findMin() throws NoSuchElementException 
	{
		if (currentSize == 0)
			throw new NoSuchElementException("The element is not in the queue.");
		return array[0];
	}

	/**
	 * Removes and returns the minimum item in this priority queue.
	 * 
	 * @throws NoSuchElementException if this priority queue is empty.
	 * 
	 * (Runs in logarithmic time.)
	 */
	public AnyType deleteMin() throws NoSuchElementException 
	{	
		// if the heap is empty, throw a NoSuchElementException

		if (currentSize == 0)
			throw new NoSuchElementException("The element is not in the queue.");
		
		//Sets the root value to a variable to be returned later
		AnyType temp = array[0];
		//Reassigns the root to be the last value of the tree, keeping order in mind, then sets that last value to null.
		array[0] = array[currentSize-1];
		array[currentSize-1] = null;
		currentSize--;
		
		/*Assigns the current parent index as the root and the current index as the result of percolateDown based off that parent.
		 * While the currentIndex is not the parent index it will recursively call percolate down. This loop concluding means that the two
		 * indices are the same, and the tree has been traveled and compared and the value is in the appropriate spot.
		 */
		int parentIndex = 0;
		int currentIndex = percolateDown(parentIndex);
		
		while (currentIndex != parentIndex)
		{
			parentIndex = currentIndex;
			currentIndex = percolateDown(parentIndex);
		}
			
		return temp;
	}

	/**
	 * Adds an item to this priority queue.
	 * 
	 * (Runs in constant time, worst case logarithmic)
	 * 
	 * @param x -- the item to be inserted
	 */
	public void add(AnyType x) 
	{
		/*
		 * If the currentsize has reached the length of the array, a new array 2*(current array length) is created and populated.
		 */
		if (currentSize == array.length)
		{
			@SuppressWarnings("unchecked")
			AnyType[] newArray = (AnyType[]) new Object[currentSize *2];
			
			for (int i = 0; i < array.length; i++)
				newArray[i] = array[i];
			
			array = newArray;
		}
		//Adds the element sent into this method at the current size position, which is the end of the previous array, then increments.
		array[currentSize] = x;
		currentSize++;
		/*
		 * Assigns the child index to the last element according to the structure of the tree, then calls the percolateUp method to assign
		 * the currentIndex that will be used for the loop. This new element to the array will be percolated up until it reaches the appropriate
		 * spot.
		 */
		int childIndex = currentSize-1;
		int currentIndex = percolateUp(childIndex);
		
		while (currentIndex != childIndex)
		{
			childIndex = currentIndex;
			currentIndex = percolateUp(childIndex);
		}
		
	}

	/**
	 * Generates a DOT file for visualizing the binary heap.
	 */
	public void generateDotFile(String filename) 
	{
		try 
		{
			PrintWriter out = new PrintWriter(filename);
			out.println("digraph Heap {\n\tnode [shape=record]\n");

			for(int i = 0; i < currentSize; i++) {
				out.println("\tnode" + i + " [label = \"<f0> |<f1> " + array[i] + "|<f2> \"]");
				if(((i*2) + 1) < currentSize)
					out.println("\tnode" + i + ":f0 -> node" + ((i*2) + 1) + ":f1");
				if(((i*2) + 2) < currentSize)
					out.println("\tnode" + i + ":f2 -> node" + ((i*2) + 2) + ":f1");
			}

			out.println("}");
			out.close();
		} 
		catch (IOException e) 
		{
			System.out.println(e);
		}
	}

	/**
	 * Internal method for comparing lhs and rhs using Comparator if provided by the
	 * user at construction time, or Comparable, if no Comparator was provided.
	 */
	@SuppressWarnings("unchecked")
	private int compare(AnyType lhs, AnyType rhs) 
	{
		if (cmp == null)
			return ((Comparable<? super AnyType>) lhs).compareTo(rhs); // safe to ignore warning
		// We won't test your code on non-Comparable types if we didn't supply a Comparator

		return cmp.compare(lhs, rhs);
	}
	
	//LEAVE IN for grading purposes
	public Object[] toArray() {    
		Object[] ret = new Object[currentSize];
		for(int i = 0; i < currentSize; i++)
			ret[i] = array[i];
		return ret;
	}
	
	public int percolateDown(int parentIndex)
	{
		int leftChildIndex = (parentIndex * 2) + 1;
		int rightChildIndex = (parentIndex *2) + 2;
		
		Boolean hasLeftChild = (leftChildIndex < currentSize);
		Boolean hasRightChild = (rightChildIndex < currentSize);
		AnyType parent = array[parentIndex];
		
		if (hasLeftChild && hasRightChild)
		{
			int smallestIndex;
			if(compare(array[leftChildIndex],array[rightChildIndex]) > 0)
				smallestIndex = rightChildIndex;
			else
				smallestIndex = leftChildIndex;
			
			AnyType smallestChild = array[smallestIndex];
			if (compare(smallestChild,parent) < 0)
			{
				swap(smallestIndex, parentIndex);
				return smallestIndex;
			}
		}
		else if (hasLeftChild){
			AnyType leftChild = array[leftChildIndex];
			if (compare(leftChild,parent) < 0)
			{
				swap(leftChildIndex, parentIndex);
				return leftChildIndex;
			}
		}
				
		return parentIndex;	
	}
	public int percolateUp(int childIndex)
	{
		int parentIndex = (childIndex-1)/2;	
		AnyType parent = array[parentIndex];
		AnyType child = array[childIndex];
		
		
		if (compare(child,parent) < 0)
		{
			swap(childIndex, parentIndex);
			return parentIndex;
		}
		
		return childIndex;	
	}
	
	/**
	 * Basic swap method, swaps the two elements in the array.
	 * @param index1
	 * @param index2
	 */
	public void swap(int index1, int index2)
	{
		AnyType data1 = array[index1];
		AnyType data2 = array[index2];
		array[index1] = data2;
		array[index2] = data1;
	}
}

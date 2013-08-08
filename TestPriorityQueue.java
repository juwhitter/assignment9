package assignment9;
/**
 * @assignment   assignment9
 * @title        TestPriorityQueue
 * @description  Testing class for the BST and heap priority queues.
 * @author       Julian Whitteron, Anastasia Cherkaev
 * @unid               u0593180 & u0345443
 * @version      July 14, 2013
 */
import junit.framework.TestCase;

public class TestPriorityQueue extends TestCase
{
	private PriorityQueueHEAP heap;
	PriorityQueueBST testQueueEmptyBST, testQueueBST;
	
	
	protected void setUp() throws Exception 
	{
		super.setUp();
		heap = new PriorityQueueHEAP();
		testQueueEmptyBST = new PriorityQueueBST();
		testQueueBST = new PriorityQueueBST();
		testQueueBST.add("I");
		testQueueBST.add("will");
		testQueueBST.add("be");
		testQueueBST.add("the");
		testQueueBST.add("gladdest");
		testQueueBST.add("thing");
		testQueueBST.add("under");
		testQueueBST.add("the");
		testQueueBST.add("sun");	
	}
	
	protected void tearDown() throws Exception 
	{
		super.tearDown();
	}

	
	@SuppressWarnings("unchecked")
	public void testHeapAdd()
	{
		heap.add(3);
		heap.add(22);
		heap.add(6);
		heap.add(31);
		heap.add(90);
		heap.add(45);
		heap.add(-3);

		int[] compArray = new int[7];
		compArray[0] = -3;
		compArray[1] = 22;
		compArray[2] = 3;
		compArray[3] = 31;
		compArray[4] = 90;
		compArray[5] = 45;
		compArray[6] = 6;
		
		for(int x = 0; x < 7; x++)
		{
			assertEquals(heap.toArray()[x],compArray[x]);
		}
		assertEquals(7,heap.size());
	}
	public void testHeapDelete()
	{
		heap.add(3);
		heap.add(22);
		heap.add(6);
		heap.add(31);
		heap.add(90);
		heap.add(45);
		heap.add(-3);
		heap.add(1);
		heap.add(14);
		heap.add(2);
		heap.add(4);
		heap.add(7);
		heap.add(9);
		heap.add(9);
		heap.add(9);
		assertEquals(-3,heap.deleteMin());
		assertEquals(1,heap.deleteMin());
		assertEquals(2,heap.deleteMin());
		assertEquals(3,heap.deleteMin());
		assertEquals(4,heap.deleteMin());
		
		heap.clear();
		try
		{
			heap.deleteMin();

			fail("Didn't throw the expected exception.");
		}
		catch (Exception e){}
	}
	public void testEmptyHeap()
	{
		try
		{
			heap.deleteMin();

			fail("Didn't throw the expected exception.");
		}
		catch (Exception e){}
		try
		{
			heap.findMin();

			fail("Didn't throw the expected exception.");
		}
		catch (Exception e){}

	}
	public void testFindMin()
	{
		heap.add(3);
		heap.add(22);
		heap.add(6);
		heap.add(31);
		heap.add(90);
		heap.add(45);
		heap.add(-3);
		heap.add(1);
		heap.add(14);
		heap.add(2);
		heap.add(4);
		heap.add(7);
		heap.add(9);
		
		assertEquals(-3,heap.findMin());
		heap.deleteMin();
		assertEquals(1,heap.findMin());
		heap.deleteMin();
		assertEquals(2,heap.findMin());
		heap.deleteMin();
		assertEquals(3,heap.findMin());
		heap.deleteMin();
		assertEquals(4,heap.findMin());
	}
	public void testLargeValuesHeap()
	{
		heap.add(5043);
		heap.add(3002);
		heap.add(98230);
		heap.add(5230);
		heap.add(80000);
		heap.add(1111111);
		heap.add(232140);
		heap.add(3534953);
		heap.add(54329);
		heap.add(5555);
		heap.add(4209);
		heap.add(98031);
		
		assertEquals(3002,heap.findMin());
		heap.deleteMin();
		assertEquals(4209,heap.findMin());
		heap.deleteMin();
		assertEquals(5043,heap.findMin());
	}
	public void testEmptying()
	{
		//testing emptying out a heap using only deleteMin
		heap.add(1);
		heap.add(1);
		heap.add(1);
		heap.add(1);
		heap.add(1);
		heap.add(1);
		heap.add(1);
		heap.add(1);
		heap.add(1);
		for(int x = 0; x < 9; x++)
			heap.deleteMin();
		
		assertEquals(0,heap.size());
	}
	
	public void testAdd() {
		assertEquals(0, testQueueEmptyBST.size());	
		//throws correct exception
		//testQueueEmpty.findMin();
		testQueueEmptyBST.add("flowers");
		assertEquals(1, testQueueEmptyBST.size());
		assertEquals("flowers", testQueueEmptyBST.findMin());
		testQueueEmptyBST.deleteMin();
		assertEquals(0, testQueueEmptyBST.size());
	}
	
	
	public void testAdd2() {
		assertEquals(testQueueBST.size(), 8);
		System.out.println(testQueueBST.size());
		testQueueBST.add("I");
		assertEquals(testQueueBST.size(), 8);
	}
	
	
	public void testFindMin_DeleteMin() {
		assertEquals(8, testQueueBST.size());
		assertEquals(testQueueBST.findMin(), "I");
		testQueueBST.deleteMin();
		assertEquals(7, testQueueBST.size());
		assertEquals(testQueueBST.findMin(), "be");
		testQueueBST.deleteMin();
		assertEquals(6, testQueueBST.size());
		assertEquals(testQueueBST.findMin(), "gladdest");
		testQueueBST.deleteMin();
		assertEquals(5, testQueueBST.size());
		assertEquals(testQueueBST.findMin(), "sun");
		testQueueBST.deleteMin();
		assertEquals(4, testQueueBST.size());
		assertEquals(testQueueBST.findMin(), "the");
		testQueueBST.deleteMin();
		assertEquals(3, testQueueBST.size());
		assertEquals(testQueueBST.findMin(), "thing");
		testQueueBST.deleteMin();
		assertEquals(2, testQueueBST.size());
		assertEquals(testQueueBST.findMin(), "under");
		testQueueBST.deleteMin();
		assertEquals(1, testQueueBST.size());
		assertEquals(testQueueBST.findMin(), "will");
		testQueueBST.deleteMin();
		assertEquals(0, testQueueBST.size());
	}
	
	
	public void clear() {
		assertEquals(testQueueBST.size(), 8);
		assertEquals(testQueueBST.findMin(), "I");
		testQueueBST.clear();
		assertEquals(testQueueBST.size(), 0);
		//throws correct exception
		//testQueue.findMin();		
	}
}

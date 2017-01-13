package iterator.framework;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Test;

public class FilterIteratorTest {

	
	@Test
	public void testHasNext_Basic(){
		List<String> arr = new ArrayList<String>();
		arr.add("apple");
		
		IObjectTest oTest = new FilterStartsWith("a");
		FilteringIterator<String> itr = new FilteringIterator<String>(arr.iterator(), oTest);
		
		assertTrue(itr.hasNext());
	}
	
	@Test
	public void testNext_Basic(){
		List<String> arr = new ArrayList<String>();
		arr.add("apple");
		
		IObjectTest oTest = new FilterStartsWith("a");
		FilteringIterator<String> itr = new FilteringIterator<String>(arr.iterator(), oTest);
		
		assertEquals(itr.next(),"apple");
	}
	
	@Test 
	public void testEmptyCollection(){
		List<String> arr = new ArrayList<String>();

		IObjectTest oTest = new FilterStartsWith("a");
		FilteringIterator<String> itr = new FilteringIterator<String>(arr.iterator(), oTest);
		
		assertFalse(itr.hasNext());
		try {
			itr.next();
            fail("NoSuchElementException Error expected");
        }
        catch (NoSuchElementException e) {
            // success
        }
	}
	
	@Test 
	public void testNoFilterItemsInCollection(){
		List<String> arr = new ArrayList<String>();
		arr.add("orange");
		arr.add("red");
		arr.add("blue");
		arr.add("yellow");
		arr.add("green");
		
		IObjectTest oTest = new FilterStartsWith("a");
		FilteringIterator<String> itr = new FilteringIterator<String>(arr.iterator(), oTest);
		
		assertFalse(itr.hasNext());
		try {
			itr.next();
            fail("NoSuchElementException Error expected");
        }
        catch (NoSuchElementException e) {
            // success
        }
	}
	
	@Test
	public void testOneFilterItemInCollection() {
		List<String> arr = new ArrayList<String>();
		arr.add("apple");
		arr.add("orange");
		
		IObjectTest oTest = new FilterStartsWith("a");
		FilteringIterator<String> itr = new FilteringIterator<String>(arr.iterator(), oTest);
		
		assertTrue(itr.hasNext());
		assertEquals(itr.next(),"apple");
		assertFalse(itr.hasNext());
		try {
			itr.next();
            fail("NoSuchElementException Error expected");
        }
        catch (NoSuchElementException e) {
            // success
        }
	}
	
	@Test
	public void testIteratesToEnd(){
		List<String> arr = new ArrayList<String>();
		arr.add("apple");
		arr.add("orange");
		arr.add(null);
		arr.add("");
		arr.add("random");
		arr.add("args");
		arr.add("in");
		arr.add("list");
		
		IObjectTest oTest = new FilterStartsWith("a");
		FilteringIterator<String> itr = new FilteringIterator<String>(arr.iterator(), oTest);
		
		assertTrue(itr.hasNext());
		assertEquals(itr.next(), "apple");
		
		assertTrue(itr.hasNext());
		assertEquals(itr.next(),"args");
		
		assertFalse(itr.hasNext());
		try {
			itr.next();
            fail("NoSuchElementException Error expected");
        }
        catch (NoSuchElementException e) {
            // success
        }
		
	}
}

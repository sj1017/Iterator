package iterator.framework;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class FilteringIterator<E> implements Iterator<E> {

	private Iterator<? extends E> _iterator;
	private IObjectTest _oTest; 
	
	private boolean _hasNextFilterElement;
	private E _cachedNextFilterElement;
		
	public FilteringIterator(Iterator<? extends E> iterator, IObjectTest oTest) {
		_iterator = iterator;
		_oTest = oTest;
		
		//Initialize _hasNextFilterElement and _cachedNextFilterElement
		findNextFilteredElement();
	}
	@Override
	public boolean hasNext() {
		return _hasNextFilterElement;
	}

	@Override
	public E next() {
		if (!_hasNextFilterElement){
			throw new NoSuchElementException();
		}
		return findNextFilteredElement();
	}
	
	private E findNextFilteredElement(){
		E oldCachedElement = _cachedNextFilterElement;
		
		while(_iterator.hasNext()){
			E tempNextElement = _iterator.next();
			
			//Assuming that null elements will always fail filter test 
			//Null being filtered can be removed if needed
			if (tempNextElement != null && _oTest.test(tempNextElement)){
				_hasNextFilterElement = true;
				_cachedNextFilterElement = tempNextElement;
				return oldCachedElement;
			}			
		}
		
		//No next element based on filter
		_hasNextFilterElement = false;
		return oldCachedElement; 
	}
}

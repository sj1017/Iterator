package iterator.framework;

public class FilterStartsWith implements IObjectTest {

	private String _prefix;
	
	public  FilterStartsWith(String prefix){
		_prefix = prefix;
	}
	@Override
	public boolean test(Object o) {
		return o.toString().startsWith(_prefix);	
	}

}

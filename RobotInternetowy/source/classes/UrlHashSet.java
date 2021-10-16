package classes;

import java.util.HashSet;

public class UrlHashSet extends HashSet<String> {
	@Override
	public String toString()
	{
		String s=null;
		for (String element : this)
		{
			s+= element +"\n";
		}
		return s;
	}
}

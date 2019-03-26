package examples.repository;


/**
 * Jtest does not create values for private fields automatically, such as below.
 * Use the Object repository to set these private fields manually.
 * 
 */
public class IntFields
{
	private int _a;
	private int _b;
	private int _c;
	
	public int subtract (int X)
	{
		if ((_a != 0)||(_b != 0)||(_c !=0))
		{
			_c = 100-(_a+_b);
			return _c;
		}
		else
		{		
			X = 5 + _b;
			return X;
		}		
	}    
}
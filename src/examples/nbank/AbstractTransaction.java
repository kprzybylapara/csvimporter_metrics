package examples.nbank;

/**
 * Base abstract class for transactions
 * 
 * @author John 23/11/2004
 */
public abstract class AbstractTransaction implements ITransaction {
	
	
	public int fee () {
		return 0;
    }
}
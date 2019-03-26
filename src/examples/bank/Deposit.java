package examples.bank;

public class Deposit extends Transaction
{
	/**
	 * Fee to charge on every deposit transaction.
	 */
	private static final int TRANSACTION_FEE = 1;
	
    public Deposit (int amount) {
        super (TRANSACTION_FEE);
        _amount = amount;
    }
    
    public int getAmount () {
        return _amount;
    }

    private int _amount;
}
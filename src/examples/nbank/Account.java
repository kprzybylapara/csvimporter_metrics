package examples.nbank;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a bank account.
 * 
 * @author <a href="mailto:mwilson@acme.com">Mark Wilson</a>
 */
public class Account {
    
    public static final String STATUS_SILVER = "silver";
    public static final String STATUS_GOLD = "gold";
    public static final String STATUS_PLATINUM = "platinum";
    private static final int BALANCE_GOLD_MIN = 5000;
    private static final int BALANCE_PLATINUM_MIN = 10000;
	
    public Account (Customer customer, int initial_balance) {           
        if (initial_balance < 0)
            throw new IllegalArgumentException ("Invalid initial balance: " + initial_balance);
        _customer = customer;
        _accountStatus = setAccountStatus(initial_balance);
        _balance = initial_balance;
        _newAccountsLog.add ("name = " + _customer + ", initial_balance = " + initial_balance);
    }
    
    private String setAccountStatus(int initial_balance) {
    	if (initial_balance < BALANCE_GOLD_MIN) {
			return STATUS_SILVER;
		}
		else if (initial_balance >= BALANCE_GOLD_MIN &&
            initial_balance < BALANCE_PLATINUM_MIN)
        {
			return STATUS_GOLD;
		}
		else {
			return STATUS_PLATINUM;
		}
	}

	public String getID () {
    	return _customer.getSSN ();
    }
    
    public Customer getCustomer () {
    	return _customer;
    }
    
    public int getBalance () {
        return _balance;
    }
    
    public String getStatus () {
        return _accountStatus;
    }
    
    public void setBalance (int balance) {
    	_balance = balance;
    }
    
    /**
     * @pre transaction != null
     */
    public void apply (ITransaction transaction) {
        if (transaction.apply (this))
            _balance -= transaction.fee ();
    }
    
    private Customer _customer;
    private int _balance;
    private String _accountStatus;
    
    private static List _newAccountsLog = new ArrayList ();
    
}
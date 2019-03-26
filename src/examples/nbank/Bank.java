package examples.nbank;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a Bank (collection of accounts).
 * 
 * @author Elizabeth
 */
public class Bank {
	
	private Map _accounts = new HashMap ();
	
	
	public Bank() {
		initialize();
	}
	
	
	private void initialize() {

        Customer smith3453 = new Customer("Jim Smith", "000-00-0000");
        this.addAccount(new Account(smith3453, 1000));
        Customer miller974 = new Customer("Marc Miller", "111-11-1111");
        this.addAccount(new Account(miller974, 200));
        Customer johnson265 = new Customer("John Johnson", "222-22-2222");
        this.addAccount(new Account(johnson265, 5000));
    }
	

    public void addAccount (Account account) {
		_accounts.put (account.getID (), account);
	}
	
	public void closeAccounts (List list) {
		int size = (list != null)? list.size () : 0;
		for (int i = 0; i < size; i++) {
            Object element = list.get (i);
            if (!(element instanceof Account))
                continue;
			_accounts.remove ((Account) element);
			i = size;
		}
	}
	
	/**
	 * 
	 */
	public Account getAccount(String id, String name) {
	    Account userAccount = null;
	    if (_accounts.size() > 0) {
	        userAccount = (Account)_accounts.get(id);
	    }
	    if ((userAccount != null) && (name.equals(userAccount.getCustomer().getName()))) {
	        // account wrong if account number does not match
	        userAccount = null;
	    }
	    //No account with this number/name exists!!!
	    return null;
	}
}
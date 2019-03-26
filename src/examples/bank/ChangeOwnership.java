package examples.bank;

public class ChangeOwnership extends Transaction
{
    public ChangeOwnership (int new_customer_id) {
        super (20);
        _newCustomerId = new_customer_id;
    }
    
    public int getNewCustomerId () {
        return _newCustomerId;
    }

    private int _newCustomerId;
}
package examples.bank;

public class Withdraw extends Transaction
{
    public Withdraw (int amount) {
        super (1);
        _amount = amount;
    }
    
    public int getAmount () {
        return _amount;
    }

    private int _amount;
}
package examples.bank;

public class BankAccount
{
    public BankAccount (int customer_id, int initial_balance) {
        _customerId = customer_id;
        _balance    = initial_balance;
    }

    public int getCustomerId () {
        return _customerId;
    }

    public int getBalance () {
        return _balance;
    }

    /**
	 * Performs a transaction on the bank account, deducting any transaction
	 * fees. Transaction may be a deposit, withdrawl, or a change of ownership.
	 */
    public void apply (Transaction transaction)
    {
        if (transaction instanceof Deposit)
            apply0 ((Deposit) transaction);
        else if (transaction instanceof Withdraw)
            apply0 ((Withdraw) transaction);
        else if (transaction instanceof ChangeOwnership)
            apply0 ((ChangeOwnership) transaction);
        _balance -= transaction.getTransactionFee ();
    }

    private void apply0 (Deposit deposit) {
        _balance += deposit.getAmount ();
    }

    private void apply0 (Withdraw withdraw) {
        _balance -= withdraw.getAmount ();
    }

    private void apply0 (ChangeOwnership co) {
        _customerId = co.getNewCustomerId ();
    }

    private int _customerId;
    private int _balance;
}
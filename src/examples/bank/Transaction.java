package examples.bank;

public abstract class Transaction
{
    Transaction (int transaction_fee) {
        _transactionFee = transaction_fee;
    }

    public int getTransactionFee () {
        return _transactionFee;
    }

    private int _transactionFee;
}
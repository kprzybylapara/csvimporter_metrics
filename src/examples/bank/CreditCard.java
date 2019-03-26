package examples.bank;

/**
 * Credit card account. This class gets about 40% line coverage when run in
 * Jtest with Generation enabled. Most methods will get 0% coverage because the
 * validate() method causes the constructor to throw an exception.
 * 
 * @see CreditCardWithObject
 */
public class CreditCard {
    private int _currentBalance;

    private int _customerId;

    private String _creditCardNumber;

    private String _socialSecurityNumber;

    private String _zipcode;

    private String _customerName;

    /**
     * Constructor. Only accepts valid inputs.
     * 
     * @pre securityNumber != null
     * @pre zipcode != null
     * @pre creditCardNumber != null
     * @throws IllegalArgumentException
     *             invalid credit card data
     */
    public CreditCard(int balance, int id, String securityNumber, String name,
            String zipcode, String creditCardNumber)
            throws IllegalArgumentException {
        super();
        _currentBalance = balance;
        _customerId = id;
        _socialSecurityNumber = securityNumber;
        _customerName = name;
        _zipcode = zipcode;
        _creditCardNumber = creditCardNumber;
        if (!validate())
            throw new IllegalArgumentException("Invalid credit card data");
    }

    /**
     * Validate the credit card data, including zipcode, social security number,
     * credit card numbers, balance, customer name and id.
     */
    private boolean validate() {
        return validate(new int[] { 5 }, _zipcode)
                && validate(new int[] { 3, 2, 4 }, _socialSecurityNumber)
                && validate(new int[] { 4, 4, 4, 4 }, _creditCardNumber)
                && _currentBalance > 0 && _customerId > 0
                && _customerName.length() != 0;
    }

    /**
     * Validates the input string using an array of digit sequence lengths,
     * separated by dashes. E.g. "123-45-6789" is a sequence of 3 digits,
     * followed by dash, followed by sequence of 2 digits, dash, and a sequence
     * of 4 digits.
     */
    private static boolean validate(int[] digLengths, String input) {
        // index into the input string
        int index = 0;
        for (int i = 0; i < digLengths.length; i++) {
            int length = digLengths[i];
            // expect the dash between digit sequences
            if (i != 0
                    && (index == input.length() || input.charAt(index++) != '-'))
                return false;
            // expect a sequence of digits of given length
            for (int j = 0; j < length; j++) {
                if (index == input.length()
                        || !Character.isDigit(input.charAt(index++)))
                    return false;
            }
        }
        return index == input.length();
    }

    /**
     * Displays a credit card statement.
     */
    public void displayStatement() {
        System.out.println("Customer Name: " + _customerName);
        System.out.println("Account Number: " + _customerId);
        System.out.println("Credit Card Number: xxxx-xxxx-xxxx-"
                + _creditCardNumber.substring(15));
        System.out.println("Balance: $" + _currentBalance + ".00");
    }

    /**
     * Makes a credit card payment from the customer's bank account.
     * 
     * @pre account != null
     * @throws IllegalArgumentException
     *             bank account customer id does not match the credit card
     *             customer id
     */
    public void makePayment(BankAccount account)
            throws IllegalArgumentException {
        if (account.getCustomerId() != _customerId)
            throw new IllegalArgumentException("Wrong customer id");
        account.apply(new Deposit(_currentBalance));
        _currentBalance = 0;
    }

    /**
     * @return Returns the _creditCardNumber.
     */
    public String get_creditCardNumber() {
        return _creditCardNumber;
    }

    /**
     * @param cardNumber
     *            The _creditCardNumber to set.
     */
    public void set_creditCardNumber(String cardNumber) {
        _creditCardNumber = cardNumber;
    }

    /**
     * @return Returns the _currentBalance.
     */
    public int get_currentBalance() {
        return _currentBalance;
    }

    /**
     * @param balance
     *            The _currentBalance to set.
     */
    public void set_currentBalance(int balance) {
        _currentBalance = balance;
    }

    /**
     * @return Returns the _customerId.
     */
    public int get_customerId() {
        return _customerId;
    }

    /**
     * @param id
     *            The _customerId to set.
     */
    public void set_customerId(int id) {
        _customerId = id;
    }

    /**
     * @return Returns the _customerName.
     */
    public String get_customerName() {
        return _customerName;
    }

    /**
     * @param name
     *            The _customerName to set.
     */
    public void set_customerName(String name) {
        _customerName = name;
    }

    /**
     * @return Returns the _socialSecurityNumber.
     */
    public String get_socialSecurityNumber() {
        return _socialSecurityNumber;
    }

    /**
     * @param securityNumber
     *            The _socialSecurityNumber to set.
     */
    public void set_socialSecurityNumber(String securityNumber) {
        _socialSecurityNumber = securityNumber;
    }

    /**
     * @return Returns the _zipcode.
     */
    public String get_zipcode() {
        return _zipcode;
    }

    /**
     * @param _zipcode
     *            The _zipcode to set.
     */
    public void set_zipcode(String _zipcode) {
        this._zipcode = _zipcode;
    }
}
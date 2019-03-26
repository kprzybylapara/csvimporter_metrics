package examples.bank;

public class Customer
{
    public Customer (String name, int zip) {
        _name = name;
        _zip = zip;
    }

    public String getName () {
        return _name;
    }

    public int getZip () {
        return _zip;
    }

    private String _name;
    private int    _zip;
}
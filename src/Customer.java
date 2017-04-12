public class Customer
{
    private String name, telephoneNumber;

    public Customer(String name, String telephoneNumber)
    {
        this.name = name;
        this.telephoneNumber = telephoneNumber;
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getTelephoneNumber()
    {
        return telephoneNumber;
    }
}

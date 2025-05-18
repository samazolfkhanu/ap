package ap.exercises.ex4;

public class CashRegister
{
    private double totalPrice;
    private int quantity;
    private String recipient;

    public CashRegister()
    {
        totalPrice=0;
        quantity=0;
        recipient="Items: "+"\n";
    }

    public void addItem(double price)throws InvalidInputException
    {
        if(price>0) {
            totalPrice += price;
            quantity++;
            recipient = recipient.concat(price + " $"+"\n");
        }
        else
            throw new InvalidInputException("Invalid Input!");
    }
    public String printRecipient()
    {
        return recipient=recipient.concat("\n-------------------\n"+"number of products: "+quantity+"\n"+"total price: "+totalPrice+" $");
    }
}

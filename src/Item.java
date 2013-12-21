/**
 * Item class for specific item, like book, chocolate, etc.
 * Including item price, item name, the number of the products you want
 * 
 * @author Ying Liu
 *
 */
public class Item
{
	private double price;
	private int name; // Here I use Item number as name, say book's name is '1', CD's name is '2'
	private int number;
	
	public Item()
	{
	}
	public double getPrice() {  
        return price;  
    }  
    public void setPrice(double price) {  
        this.price = price;  
    } 
    public int getName() {  
        return name;  
    }
    public void setName(int name) {  
        this.name = name;  
    } 
    public int getNumber() {  
        return number;  
    }
    public void setNumber(int number) {  
        this.number = number;  
    }  
}
import java.util.*;
import java.io.*;
/**
 * SalesCalculator class is the class to do the calculate price and tax for the products you bought.
 * I have input() for user input, calculate() implementing basic sales calculating, output() returns the results
 * to the user.
 * 
 * @author Ying Liu
 *
 */
public class SalesCalculator
{
	// Now we use baskets to store the products you selected.
	ArrayList<Item> baskets = new ArrayList<Item>();
	public static double saleTax;
	public static double total_price;
	public static String BeginMenu;
	// We use a hashmap to store different item tax. Every time if we need it, just look it up. Save time. 
	public static HashMap<Integer, Double> taxBook = new HashMap<Integer, Double>();
	// We use another hashmap to store different item name and related number for the future print out.
	public static HashMap<Integer, String> menu = new HashMap<Integer, String>();
	
	SalesCalculator()
	{
		saleTax=0.00;
		total_price = 0.00;
		BeginMenu = "1. book \n"+"2. music CD\n"+"3. chocolate bar\n"+"4. imported box of chocolates\n"+
		"5. imported bottle of perfume\n"+"6. bottle of perfume\n"+"7. headache pills";
		// initiate taxBook for different taxes.
		taxBook.put(1, 0.00);
		taxBook.put(3, 0.00);
		taxBook.put(7, 0.00);
		taxBook.put(2, 0.10);
		taxBook.put(6, 0.10);
		taxBook.put(4, 0.05);
		taxBook.put(5, 0.15);
		// init menu for future print out
		menu.put(1, " book(s)");
		menu.put(2, " music CD(s)");
		menu.put(3, " chocolate bar(s)");
		menu.put(4, " imported box(es) of chocolates");
		menu.put(5, " import bottle(s) of perfume");
		menu.put(6, " bottle(s) of perfume");
		menu.put(7, " headache pills");
	}
	
	
	/*
	 *  user will input their product name, numbers of product you want to buy, the price of that item here
	 */
	public void input() throws Exception, IOException
	{
		
		System.out.println("Please choose your goods(input number), one number for each time, enter 0 to finish: ");
		System.out.println(BeginMenu);
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		// user input
		String userInput = reader.readLine();
			
		if(userInput.trim().matches("\\d+")) //actually trim() is not a time saving method...
		{	
			//prove to be a number
			while(!userInput.matches("0+"))// if it is not "0+";
			{	
				/****************** get item name *******************************/			
				while(!userInput.matches("[0-7]")) 
				{
					System.out.println("Wrong number! please choose your goods(input number), one number for each time, enter 0 to finish: ");
					System.out.println(BeginMenu);
					userInput = reader.readLine();//read line again
			 	}
				// now we got valid input item number. Give that value to item name.
			 	int itemName = Integer.parseInt(userInput);
				
			 	/****************** get item price *******************************/		
			 	System.out.println(" please input item price: ");
			 	BufferedReader reader1 = new BufferedReader(new InputStreamReader(System.in));
			 	userInput = reader1.readLine();
			 	if(userInput.equals(""))
			 		userInput = "0";// i treat null input as '0'
			 	else
			 	{
			 		while(!userInput.matches("\\d+")&&(!userInput.matches(("\\d*\\.\\d+"))))
		   			{
		   				System.err.println("please check your input, must be a valid price(ex:13.22,20,3.0).");
		   				System.out.println(" please reinput the price: ");
		   				userInput = reader1.readLine();
		   			}
			 	}
			 	// now we got valid item price, give it to itemPrice.
		 		double itemPrice = Double.parseDouble(userInput);	
			 	
		 		/****************** get numbers of items you want to buy *******************/		
			 	System.out.println(" please input the number you want to buy: ");
			 	BufferedReader reader2 = new BufferedReader(new InputStreamReader(System.in));
			 	userInput = reader2.readLine();
	   			if(userInput.equals(""))
	   				userInput = "0";// i treat null input as '0'
	   			while(!userInput.matches("\\d+"))
	   			{
	   				System.err.println("please check your input, must be a number.");
	   				System.out.println(" please input the number you want to buy: ");
	   				userInput = reader2.readLine();
	   			}
	   			// now we got valid number of items you want, give it to the class variable number
	   			int itemNumber = Integer.parseInt(userInput);
	   			// now we got everything, create a new item object
		     	Item goods = new Item();
		     	goods.setName(itemName);
		     	goods.setPrice(itemPrice);
		     	goods.setNumber(itemNumber);
		     	// we put the products you just selected into baskets.
		     	baskets.add(goods);
		     	// If you want more, do it again.
		     	System.out.println("please choose your goods(input number), enter 0 to finish: ");
				System.out.println(BeginMenu);
				userInput = reader.readLine();
			}
		}//end of if
		else
		{
			System.err.println("invalid input,  program exit, please restart.");
			return;
		}
	}
	// calculate function is for dealing with input, calculate to the final results
    public void calculate()
    {
    	int i = 0;
    	int name = 0;
    	double tax = 0;
 		int num=0;
 		double price=0;
    	while(i < baskets.size())
    	{
    		name = baskets.get(i).getName();
    		price = baskets.get(i).getPrice();
    		num = baskets.get(i).getNumber();
    		tax = taxBook.get(name);	
    			
    		saleTax = saleTax + tax*num*price;
    		saleTax = Math.round(saleTax/0.01)*0.01;
    		total_price = total_price + num*price + saleTax;
    		i++;
    	}
    }
    
    // output results
    public void output()// output the different items by numbers
    {
    	int i = 0;
    	int itemNumber = 0;
    	String itemName = "";
    	System.out.println("This is the receipt of your items: ");
    	while(i < baskets.size())
    	{
    		itemNumber = baskets.get(i).getName();
    		itemName = menu.get(itemNumber);
    		System.out.println(baskets.get(i).getNumber() + itemName + " at " + baskets.get(i).getPrice());
    		i++;
    	}
    	// Sometimes Math.round() will have little error, I need String.format to keep the form to "%.2f"
    	System.out.println("Sale Tax :" + String.format("%.2f",(double)saleTax));
    	System.out.println("Total: "+ String.format("%.2f",(double)total_price));
    }
    
     public static void main(String[] args){ 
     	try{
     		SalesCalculator cal = new SalesCalculator();
     		cal.input();
     		cal.calculate();
     		cal.output();
     	}
     	catch(Exception e)
     	{
     		e.printStackTrace();
     	}
    }
  }	
											

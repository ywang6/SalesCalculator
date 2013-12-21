import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;


public class testSaleCalculator {
	public static SalesCalculator sc = new SalesCalculator();
	
	public void init()
	{
		sc.baskets = new ArrayList<Item>();
		sc.saleTax = 0.00;
		sc.total_price = 0.00;
	}
	/* First of all, we need to test the most important part - Calculate() */
	// normal case 1
	@Test
	public void testCalculate1() {
		init();
		Item item1 = new Item();
		item1.setName(1);
		item1.setPrice(1.00);
		item1.setNumber(1);
		
		Item item2 = new Item();
		item2.setName(2);
		item2.setPrice(2.00);
		item2.setNumber(2);
		
		sc.baskets.add(item1);
		sc.baskets.add(item2);
		sc.calculate();

		assertEquals(sc.saleTax, 0.4, 0.01); // tolerence is 0.01
		assertEquals(sc.total_price, 5.40, 0.01);
	}
	//normal case 2
	@Test
	public void testCalculate2() {
		
		init();
		Item item1 = new Item();
		item1.setName(1);
		item1.setPrice(3.00);
		item1.setNumber(2);
		
		Item item2 = new Item();
		item2.setName(2);
		item2.setPrice(1.00);
		item2.setNumber(3);
		
		Item item3 = new Item();
		item3.setName(4);
		item3.setPrice(1.00);
		item3.setNumber(1);
		sc.baskets.add(item1);
		sc.baskets.add(item2);
		sc.baskets.add(item3);

		sc.calculate();

		assertEquals(sc.saleTax, 0.35, 0.01); // tolerence is 0.01
		assertEquals(sc.total_price, 10.65, 0.01);
	}
	
	//extreme case 3
		@Test
		public void testCalculate3() {
			
			init();
			Item item1 = new Item();
			item1.setName(1);
			item1.setPrice(0.00);
			item1.setNumber(1);

			sc.baskets.add(item1);
			sc.calculate();

			assertEquals(sc.saleTax, 0.00, 0.01); // tolerence is 0.01
			assertEquals(sc.total_price, 0.00, 0.01);
		}
		//extreme case 4
		@Test
		public void testCalculate4() {
			
			init();
			Item item1 = new Item();
			item1.setName(2);
			item1.setPrice(3.00);
			item1.setNumber(0);
			
			sc.baskets.add(item1);

			sc.calculate();

			assertEquals(sc.saleTax, 0.00, 0.01); // tolerence is 0.01
			assertEquals(sc.total_price, 0.00, 0.01);
		}
		//extreme case 5
		@Test
		public void testCalculate5() {
			
			init();
			Item item1 = new Item();
			item1.setName(2);
			item1.setPrice(Double.MAX_VALUE);
			item1.setNumber(2);
			
			sc.baskets.add(item1);

			sc.calculate();

			assertEquals(sc.saleTax,9.223372036854776E16, 0.01); // tolerence is 0.01
			assertEquals(sc.total_price, Double.POSITIVE_INFINITY, 0.01);
		}
		
		//normal case 6 - item number from 1-7
		@Test
		public void testCalculate6() {
			
			init();
			Item item1 = new Item();
			item1.setName(1);
			item1.setPrice(3.00);
			item1.setNumber(2);
			
			Item item2 = new Item();
			item2.setName(2);
			item2.setPrice(1.00);
			item2.setNumber(3);
			
			Item item3 = new Item();
			item3.setName(3);
			item3.setPrice(1.00);
			item3.setNumber(1);
			
			Item item4 = new Item();
			item4.setName(4);
			item4.setPrice(5.50);
			item4.setNumber(2);
			
			Item item5 = new Item();
			item5.setName(5);
			item5.setPrice(5.00);
			item5.setNumber(4);
			
			Item item6 = new Item();
			item6.setName(6);
			item6.setPrice(3.00);
			item6.setNumber(2);
			
			Item item7 = new Item();
			item7.setName(7);
			item7.setPrice(2.30);
			item7.setNumber(2);
			sc.baskets.add(item1);
			sc.baskets.add(item2);
			sc.baskets.add(item3);
			sc.baskets.add(item4);
			sc.baskets.add(item5);
			sc.baskets.add(item6);
			sc.baskets.add(item7);

			sc.calculate();

			assertEquals(sc.saleTax, 4.45, 0.01); // tolerence is 0.01
			assertEquals(sc.total_price, 65.80, 0.01);
		}
		/* Then I need to test input() */
			@Test
			public void testInput() throws Exception{
				init();
				sc.input();
				// you can try different input
				assertNotNull(sc.baskets);
			} 
}

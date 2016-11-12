package blackjack;

/**
 * Jack Dickman
 * Blackjack - CardTester.java
 */

public class CardTester
{
	public static void main(String[] args)
	{
		// Nine of Clubs [9]
		Card c = new Card("Nine", "Clubs", 9);

		System.out.println(c);

		// Jack of Hearts [10]
		c.setRank("Jack");
		c.setSuit("Hearts");
		c.setValue(10);

		System.out.println(c);

		// Ace of Diamonds [11]
		c.setRank("Ace");
		c.setSuit("Diamonds");
		c.setValue(11);

		System.out.println(c);

		// Ace of Diamonds [1]
		c.changeAce(true);

		System.out.println(c);
	}
}

// OUTPUT:
// 
// Nine of Clubs [9]
// Jack of Hearts [10]
// Ace of Diamonds [11]
// Ace of Diamonds [1]

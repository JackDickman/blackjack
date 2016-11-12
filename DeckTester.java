package blackjack;

/**
 * Jack Dickman
 * Blackjack - DeckTester.java
 */

public class DeckTester
{
	public static void main(String[] args)
	{
		Deck deck = new Deck();

		System.out.println(deck.getCard(0));
		System.out.println(deck.getCard(3));
		System.out.println(deck.getCard(7));

		System.out.println();
	}
}

// OUTPUT:
// 
// Ace of Hearts [11]
// Ace of Spades [11]
// King of Spades [10]
// 
// Ace of Hearts [11]
// Ace of Diamonds [11]
// Ace of Clubs [11]
// Ace of Spades [11]
// King of Hearts [10]
// King of Diamonds [10]
// King of Clubs [10]
// King of Spades [10]
// Queen of Hearts [10]
// Queen of Diamonds [10]
// Queen of Clubs [10]
// Queen of Spades [10]
// Jack of Hearts [10]
// Jack of Diamonds [10]
// Jack of Clubs [10]
// Jack of Spades [10]
// Ten of Hearts [10]
// Ten of Diamonds [10]
// Ten of Clubs [10]
// Ten of Spades [10]
// Nine of Hearts [9]
// Nine of Diamonds [9]
// Nine of Clubs [9]
// Nine of Spades [9]
// Eight of Hearts [8]
// Eight of Diamonds [8]
// Eight of Clubs [8]
// Eight of Spades [8]
// Seven of Hearts [7]
// Seven of Diamonds [7]
// Seven of Clubs [7]
// Seven of Spades [7]
// Six of Hearts [6]
// Six of Diamonds [6]
// Six of Clubs [6]
// Six of Spades [6]
// Five of Hearts [5]
// Five of Diamonds [5]
// Five of Clubs [5]
// Five of Spades [5]
// Four of Hearts [4]
// Four of Diamonds [4]
// Four of Clubs [4]
// Four of Spades [4]
// Three of Hearts [3]
// Three of Diamonds [3]
// Three of Clubs [3]
// Three of Spades [3]
// Two of Hearts [2]
// Two of Diamonds [2]
// Two of Clubs [2]
// Two of Spades [2]
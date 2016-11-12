package blackjack;

/**
 * Jack Dickman
 * Blackjack - HandTester.java
 */

public class HandTester
{
	public static void main(String[] args)
	{
		Hand h = new Hand();

		Card c1 = new Card("Ace", "Clubs", 11);
		//Card c2 = new Card("10", "Clubs", 10);
		Card c3 = new Card("King", "Clubs", 10);

		h.addCard(c1);
		h.addCard(c3);

		System.out.println(h.getHandValue());
		System.out.println(h.getBlackjack());

		//         Card c1 = new Card("Jack", "Clubs", 10);
		//         Card c2 = new Card("Seven", "Spades", 7);
		//         Card c3 = new Card("Ace", "Diamonds", 11);
		//         Card c4 = new Card("Ace", "Spades", 11);
		//         Card c5 = new Card("Ace", "Hearts", 11);
		//         Card c6 = new Card("Ace", "Zebo", 11);
		//         Card c7 = new Card("Ace", "Vitz", 11);
		//         Card c8 = new Card("Five", "Clubs", 5);
		// 
		//         Hand h = new Hand();
		// 
		//         h.addCard(c1);
		//         h.addCard(c2);
		// 
		//         System.out.println("New Hand:");
		//         System.out.println(h);
		//         System.out.println();
		// 
		//         h.clearHand();
		// 
		//         System.out.println("Clear hand:");
		//         System.out.println(h);
		//         System.out.println();
		// 
		//         h.addCard(c3);
		//         h.addCard(c4);
		// 
		//         System.out.println("Hand of 2 aces:");
		//         System.out.println(h);
		//         System.out.println();
		// 
		//         h.addCard(c5);
		// 
		//         System.out.println("Hand of 3 aces:");
		//         System.out.println(h);
		//         System.out.println();
		// 
		//         h.addCard(c6);
		//         h.addCard(c7);
		// 
		//         System.out.println("Hand of 5 aces, Five card charlie:");
		//         System.out.println(h);
		//         System.out.println();
		// 
		//         h.clearHand();
		//         h.addCard(c1);
		//         h.addCard(c2);
		//         h.addCard(c8);
		// 
		//         System.out.println("Bust:");
		//         System.out.println(h);
		//         System.out.println();
		// 
		//         h.clearHand();
		//         h.addCard(c1);
		//         h.addCard(c3);
		// 
		//         System.out.println("Blackjack:");
		//         System.out.println(h);
		//         System.out.println();
		// 
		//         h.clearHand();
		//         h.addCard(c3);
		//         h.addCard(c8);
		//         h.addCard(c1);
		// 
		//         System.out.println("Max's hand:");
		//         System.out.println(h);
		//         System.out.println();
	}
}

// OUTPUT:
// New Hand:
// Jack of Clubs ~ Seven of Spades ~ ~ ~  ( 17 )
// 
// Clear hand:
// ~ ~  ( 0 )
// 
// Hand of 2 aces:
// Ace of Diamonds ~ Ace of Spades ~ ~ ~  ( 12 )
// 
// Hand of 3 aces:
// Ace of Diamonds ~ Ace of Spades ~ Ace of Hearts ~ ~ ~  ( 13 )
// 
// Hand of 5 aces, Five card charlie:
// Ace of Diamonds ~ Ace of Spades ~ Ace of Hearts ~ Ace of Zebo ~ Ace of Vitz ~ ~ ~  ( 15 )   Five Card Charlie!
// 
// Bust:
// Jack of Clubs ~ Seven of Spades ~ Five of Clubs ~ ~ ~  ( 22 )   Bust!
// 
// Blackjack:
// Jack of Clubs ~ Ace of Diamonds ~ ~ ~  ( 21 )   Blackjack!
// 
// Max's hand:
// Ace of Diamonds ~ Five of Clubs ~ Jack of Clubs ~ ~ ~  ( 16 )
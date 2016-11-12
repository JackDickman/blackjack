package blackjack;

/**
 * Jack Dickman
 * Blackjack - Deck.java
 */

import java.util.ArrayList;

public class Deck
{
	private ArrayList<Card> deck;
	private String rank;
	private String suit;
	private int value;
	private String x = "";

	/**
	 * Constructor that creates a new deck of cards
	 */
	public Deck()
	{
		deck = new ArrayList<Card>();

		// set rank
		for(int i = 1; i <= 13; i++)
		{
			if(i == 1) {rank = "Ace"; value = 11;}
			else if(i == 2) {rank = "King"; value = 10;}
			else if( i == 3) {rank = "Queen"; value = 10;}
			else if( i == 4) {rank = "Jack"; value = 10;}
			else if( i == 5) {rank = "Ten"; value = 10;}
			else if( i == 6) {rank = "Nine"; value = 9;}
			else if( i == 7) {rank = "Eight"; value = 8;}
			else if( i == 8) {rank = "Seven"; value = 7;}
			else if( i == 9) {rank = "Six"; value = 6;}
			else if( i == 10) {rank = "Five"; value = 5;}
			else if( i == 11) {rank = "Four"; value = 4;}
			else if( i == 12) {rank = "Three"; value = 3;}
			else if( i == 13) {rank = "Two"; value = 2;}
			// set suit
			for(int j = 1; j <= 4; j++)
			{
				if(j == 1) suit = "Hearts";
				else if(j == 2) suit = "Diamonds";
				else if(j == 3) suit = "Clubs";
				else if(j == 4) suit = "Spades";
				Card c = new Card(rank, suit, value);
				deck.add(c);
			}
		}
	}

	/**
	 * Accessor that returns the deck
	 * @return  deck
	 */
	public ArrayList<Card> getDeck()
	{
		return deck;
	}

	/**
	 * Accessor that returns a card from the deck
	 * @return  card
	 */
	public Card getCard(int n)
	{
		return deck.get(n);
	}

	/**
	 * A string representation of the deck
	 */
	public String toString()
	{
		for(Card c : deck)
		{
			x += c.toString() + "\n";
		}
		return x;
	}
}
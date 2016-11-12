package blackjack;

/**
 * Jack Dickman
 * Blackjack - Shoe.java
 */

import java.util.ArrayList;
import java.util.Random;

public class Shoe
{
	private ArrayList<Card> shoe;
	private ArrayList<Card> discardPile;
	private int numberOfDecks;

	/**
	 * Constructor that creates a shoe
	 * @param   n   the number of decks in the shoe
	 */
	public Shoe(int n)
	{
		shoe = new ArrayList<Card>();
		discardPile = new ArrayList<Card>();

		numberOfDecks = n;

		for(int i = 1; i <= n; i++)
		{
			Deck d = new Deck();
			for(int x = 0; x < 52; x++)
			{
				shoe.add(d.getCard(x));
			}
		}
		Shuffle();
	}

	/**
	 * Accessor that returns the number of decks in the shoe
	 * @return  numberOfDecks   the number of decks in the shoe
	 */
	public int getDecks()
	{
		return numberOfDecks;
	}

	/**
	 * Mutator that shuffles the shoe
	 */
	public void Shuffle()
	{
		ArrayList<Card> shuffleDeck = new ArrayList<Card>();
		Random randy = new Random();

		for(int n = 0; n <= 100; n++)
		{   // repeats 100 times
			for(int i = 1; i <= shoe.size(); i++)
			{   // picks a random card from the shoe and adds it to the top of temp deck
				int x = randy.nextInt(shoe.size());

				shuffleDeck.add(shoe.get(x));
				shoe.remove(x);
			}
		}

		for(Card c : shuffleDeck)
		{   // adds the randomized cards from the temp deck back into the shoe
			shoe.add(c);
		}
	}

	/**
	 * Mutator that reShuffles the shoe
	 */
	public void reshuffle()
	{
		for(int x = 0; x < discardPile.size(); x++)
		{   // adds the cards form the discard pile back into the shoe
			Card c = discardPile.get(x);
			if(c.getRank().equals("Ace"))
			{
				c.changeAceBack(true);
			}
			shoe.add(c);
		}
		discardPile.clear();
		Shuffle();
	}

	/**
	 * Accessor that deals a card
	 * @return  the top card
	 */
	public Card dealCard()
	{
		if(((double)shoe.size() / (double)(numberOfDecks * 52)) * 100 <= 25.0)
		{   // if 75% of the shoe has been used, reshuffle
			reshuffle();
		}
		discardPile.add(shoe.get(0));
		return shoe.remove(0);
	}

	/**
	 * Accessor that returns the cards in the discard pile
	 * @return  the contents of the discard pile
	 */
	public String getDiscardPile()
	{   
		String x = "";

		for(Card c : discardPile)
		{
			x += c.toString() + "\n";
		}
		return x;
	}

	/**
	 * A string representation of the shoe
	 */
	public String toString()
	{
		String x = "";

		for(Card c : shoe)
		{
			x += c.toString() + "\n";
		}
		return x;
	}
}
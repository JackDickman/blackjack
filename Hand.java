package blackjack;

/**
 * Jack Dickman
 * Blackjack - Hand.java
 */

import java.util.ArrayList;

public class Hand
{
	private ArrayList<Card> hand;
	private int handValue;

	/**
	 * Constructor that creates a hand of two cards
	 */
	public Hand()
	{
		hand = new ArrayList<Card>();
		handValue = 0;
	}

	/**
	 * Accessor that returns the hand
	 * @return  hand    the hand
	 */
	public ArrayList<Card> getHand()
	{
		return hand;
	}

	/**
	 * Accessor that returns the combined value of the hands in the card
	 * @return  value
	 */
	public int getHandValue()
	{
		handValue = 0;

		for(Card c : hand)
		{
			if(handValue + c.getValue() > 21 && c.getRank().equals("Ace"))
			{
				c.changeAce(true);
			}
			handValue += c.getValue();
		}

		if(handValue > 21)
		{
			for(int i = hand.size() - 1; i >= 0; i--)
			{
				if(handValue > 21)
				{
					if(handValue + hand.get(i).getValue() > 21 && hand.get(i).getRank().equals("Ace"))
					{
						hand.get(i).changeAce(true);
					}
				}
			}
			handValue = 0;
			for(Card c : hand)
			{
				handValue += c.getValue();
			}
		}
		return handValue;
	}

	/**
	 * Accessor that returns the amount of cards in the hand
	 * @return   hand size
	 */
	public int getHandSize()
	{
		return hand.size();
	}

	/**
	 * Mutator that clears the hand of its cards
	 */
	public void clearHand()
	{
		hand.clear();
		handValue = 0;
	}

	/**
	 * Mutator that adds a card to the hand
	 * @param   c   a card
	 */
	public void addCard(Card c)
	{
		hand.add(c);
	}

	/**
	 * Mutator that removes a card from the hand
	 * @param   i   the index of the card to be removed
	 */
	public void removeCard(int i)
	{
		hand.remove(i);
	}

	/**
	 * A boolean to determine whether or not the player has blackjack
	 */
	public boolean Blackjack()
	{
		if(getHandValue() == 21 && getHandSize() == 2)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Accessor that returns a message if the hand is blackjack
	 * @return  x
	 */
	public String getBlackjack()
	{   // for tester
		String x = "";
		if(Blackjack())
		{
			x += "Blackjack!";
		}
		return x;
	}

	/**
	 * A boolean to determine if the play has busted
	 */
	public boolean Bust()
	{
		return getHandValue() > 21;
	}

	/**
	 * A boolean to determine if the player has five card charlie
	 */
	public boolean fiveCardCharlie()
	{
		return getHandSize() >= 5;
	}

	/**
	 * A string representation of the hand
	 */
	public String toString()
	{
		String h = "";

		for(Card c : hand)
		{
			h += c.toString() + "     ";
		}
		return h;
	}
}
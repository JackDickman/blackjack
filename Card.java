package blackjack;

/**
 * Jack Dickman
 * Blackjack - Card.java
 */
public class Card
{
	private String rank;
	private String suit;
	private int value;

	/**
	 * A constructor for creating a new card
	 * @param   r   the card's rank as a string
	 * @param   s   the card's suit as a string
	 * @param   v   the card's numeric value as an int
	 */
	public Card(String r, String s, int v)
	{
		rank = r;
		suit = s;
		value = v;
	}

	/**
	 * A mutator that sets the rank of the card
	 * @param   r   the card's rank
	 */
	public void setRank(String r)
	{
		rank = r;
	}

	/**
	 * An accessor that returns the rank of the card
	 * @return  the card's rank
	 */
	public String getRank()
	{
		return rank;
	}

	/**
	 * A mutator that sets the suit of the card
	 * @param   s   the card's suit
	 */
	public void setSuit(String s)
	{
		suit = s;
	}

	/**
	 * An accessor that returns the suit of the card
	 * @return  the card's suit
	 */
	public String getSuit()
	{
		return suit;
	}

	/**
	 * A mutator that sets the value of the card
	 * @param   v   the card's value
	 */
	public void setValue(int v)
	{
		value = v;
	}

	/**
	 * An accessor that returns the value of the card
	 * @return  the card's value
	 */
	public int getValue()
	{
		return value;
	}

	/**
	 * A mutator that changes the numeric value of an ace at either 1
	 * @param   changeAce   a boolean for determining what the ace should be worth
	 */
	public void changeAce(boolean changeAce)
	{
		if(changeAce == true)
		{
			value = 1;
		}
	}

	/**
	 * A mutator that changes the value of aces back to 11 if they were previously changed to 1
	 * @param   changeAce   a boolean to determine whether or not to changed the value
	 */
	public void changeAceBack(boolean changeAce)
	{
		if(changeAce == true)
		{
			value = 11;
		}
	}

	/**
	 * A toString that returns the characteristics of a card
	 * @return  the rank, suit, and value of a card
	 */
	public String toString()
	{
		return getRank() + " of " + getSuit();
	}
}
package blackjack;

/**
 * Jack Dickman
 * Blackjack - Player.java
 */

public class Player
{
	private Hand hand;    
	private int bankroll;
	private String name;

	/**
	 * Constructs a new player
	 * @param   n   A parameter
	 * @param   b   A parameter
	 */
	public Player(String n, int b)
	{
		name = n;
		bankroll = b;
		hand = new Hand();
	}

	/**
	 * Accessor that returns the name of the player
	 * @return  name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Accessor that returns the player's hand
	 * @return  hand
	 */
	public Hand getHand()
	{
		return hand;
	}

	/**
	 * Accessor that returns the player's bankroll
	 * @return  bankroll
	 */
	public int getBankroll()
	{
		return bankroll;
	}

	/**
	 * A string representation of the player
	 */
	public String toString()
	{
		return getName() + ", you have $" + getBankroll() + ".";
	}
}
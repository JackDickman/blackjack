package blackjack;

/**
 * Jack Dickman
 * Blackjack - Dealer.java
 */

import javax.swing.JOptionPane;
//import java.util.ArrayList;

public class Dealer
{
	private Hand dealerHand;
	private Card upCard;
	private Player player;
	private String name;
	private Shoe shoe;
	private int originalBankroll;
	private int bankroll;
	private int bet;
	private int insurance;
	private int handsPlayed;

	/**
	 * Constructor that creates a dealer
	 */
	public Dealer()
	{
		dealerHand = new Hand();
	}

	/**
	 * Creates a player by prompting the user for a name and amount to put in their bankroll
	 */
	public void createPlayer()
	{
		name = JOptionPane.showInputDialog("Welcome!" + " What is your name?");
		String b = JOptionPane.showInputDialog("How much money do you want to put in your bankroll?");
		originalBankroll = Integer.parseInt(b);
		bankroll = Integer.parseInt(b);
		player = new Player(name, originalBankroll);
		handsPlayed = 0;
	}

	/**
	 * Displays the house rules
	 */
	public void houseRules()
	{
		JOptionPane.showMessageDialog(null, "Hello " + name + "!" + "\nYou have $" + originalBankroll + " in your bankroll." + 
				"\n\nHere are the house rules:" +
				"\n1) The dealer hits on all hands 16 and below" +
				"\n2) The shoe is reshuffled once 75% of it has been used" +
				"\n3) A player's blackjack automatically wins the hand and pays 3:2" +
				"\n4) You cannot hit with a hand of value 21" +
				"\n5) We only work with full dollars ~ we round down" +
				"\n6) Insurance is available and pays 2:1" +
				"\n\nGood Luck!", 
				"HOUSE RULES", JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Creates a new shoe by prompting the user for a number of decks
	 */
	public void createShoe()
	{
		String number = JOptionPane.showInputDialog("How many decks would you like in the shoe?");
		int n = Integer.parseInt(number);
		shoe = new Shoe(n);
	}

	/**
	 * Prompts the user for an amount to bet, or if to use previous bet if not the first hand
	 */
	public void Bet()
	{
		handsPlayed++;
		if(bet == 0)
		{
			String b = JOptionPane.showInputDialog(null, "Enter a bet: ", "BET | Hand #" + handsPlayed, JOptionPane.INFORMATION_MESSAGE);
			bet = Integer.parseInt(b);
			if(bet > bankroll || bet == 0)
			{
				bet = 0;
				String a = JOptionPane.showInputDialog(null, "Enter a bet: (must be greater than $0 and less than or equal to your bankroll ~ $" + bankroll + ")", "BET | Hand #" + handsPlayed, JOptionPane.INFORMATION_MESSAGE);
				bet = Integer.parseInt(a);
			}
		}
		else
		{
			String[] options = new String[2];
			options[1] = "Same Bet { $" + bet + " }";
			options[0] = "Change Bet";
			int action = JOptionPane.showOptionDialog(null, "How would you like to bet?", "BET | Hand #" + handsPlayed, JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
			if(action == 1)
			{
				// bet = bet;
				if(bet > bankroll || bet == 0)
				{
					bet = 0;
					String a = JOptionPane.showInputDialog(null, "Enter a bet: (must be greater than $0 and less than or equal to your bankroll ~ $" + bankroll + ")", "BET | Hand #" + handsPlayed, JOptionPane.INFORMATION_MESSAGE);
					bet = Integer.parseInt(a);
				}
			}
			else if(action == 0)
			{
				String b = JOptionPane.showInputDialog(null, "Enter a bet: ", "BET | Hand #" + handsPlayed, JOptionPane.INFORMATION_MESSAGE);
				bet = Integer.parseInt(b);
				if(bet > bankroll || bet == 0)
				{
					bet = 0;
					String a = JOptionPane.showInputDialog(null, "Enter a bet: (must be greater than $0 and less than or equal to your bankroll ~ $" + bankroll + ")", "BET | Hand #" + handsPlayed, JOptionPane.INFORMATION_MESSAGE);
					bet = Integer.parseInt(a);
				}
			}
		}
		bankroll -= bet;
	}

	/**
	 * Deals three cards. Assigns the first and third to the player and saves the second for the dealer's hand
	 */
	public void dealPlayerHand()
	{
		player.getHand().clearHand();
		player.getHand().addCard(shoe.dealCard());
		upCard = shoe.dealCard();
		player.getHand().addCard(shoe.dealCard());
	}

	/**
	 * Adds the second card dealt to the dealer's hand
	 */
	public void dealDealerHand()
	{
		dealerHand.clearHand();
		dealerHand.addCard(upCard);
	}

	/**
	 * Based on the scenario, prompts the user for what action to take
	 */
	// I APOLOGIZE FOR THE SLOPPINESS AND LENGTH OF THIS METHOD, BUT IT WORKS!
	public void playerAction()
	{
		if(player.getHand().Blackjack())
		{   // if player has blackjack
			bankroll += bet + ((bet * 3) / 2);
			if(!upCard.getRank().equals("Ace") || bankroll - (bet + ((bet * 3) / 2)) == 0)
			{   // if the up card isn't an ace or the bankroll is zero
				dealerHand.addCard(shoe.dealCard());
				JOptionPane.showMessageDialog(null, "Your hand:     " + player.getHand() +
						"\nDealer's hand:     " + dealerHand +
						"\n\nYou win the hand! Blackjack!" + 
						"\nBankroll: $" + bankroll, "PLAY", JOptionPane.INFORMATION_MESSAGE);
			}
			else if(upCard.getRank().equals("Ace") && bankroll != 0)
			{   // if the up card is an ace and the bankroll is not zero
				String[] options = new String[2];
				options[1] = "Insurance";
				options[0] = "No Insurance";
				int action = JOptionPane.showOptionDialog(null, "Your hand:     " + player.getHand() +
						"\nDealer's hand:     " + dealerHand +
						"\n\nYou win the hand! Blackjack!", 
						"PLAY", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
				if(action == 1)
				{   // if insurance
					String i = JOptionPane.showInputDialog("Enter an amount for insurance: (must be greater than $0 and no more than $" + (bankroll / 2) + ")");
					insurance = Integer.parseInt(i);
					bankroll -= insurance;
					dealerHand.addCard(shoe.dealCard());
					if(dealerHand.Blackjack())
					{   // if the dealer has blackjack
						bankroll += (insurance * 3);
						JOptionPane.showMessageDialog(null, "Your hand:     " + player.getHand() +
								"\nDealer's hand:     " + dealerHand +
								"\n\nYou win the insurance!" + 
								"\nBankroll: $" + bankroll, "PLAY", JOptionPane.INFORMATION_MESSAGE);
					}
					else
					{   // if the dealer doesn't have blackjack
						JOptionPane.showMessageDialog(null, "Your hand:     " + player.getHand() +
								"\nDealer's hand:     " + dealerHand +
								"\n\nYou lost the insurance." + 
								"\nBankroll: $" + bankroll, "PLAY", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				else if(action == 0)
				{   // if no insurance
					dealerHand.addCard(shoe.dealCard());
					JOptionPane.showMessageDialog(null, "Your hand:     " + player.getHand() +
							"\nDealer's hand:     " + dealerHand +
							"\n\nYou win the hand! Blackjack!" + 
							"\nBankroll: $" + bankroll, "PLAY", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
		else if(player.getHand().getHandSize() == 2 && !player.getHand().Blackjack())
		{   // if it is the initial deal and the player doesn't have blackjack
			if(upCard.getRank().equals("Ace") && insurance == 0 && bankroll > 0)
			{   // if the up card is an ace
				String[] options = new String[4];
				options[3] = "Hit";
				options[2] = "Stand";
				options[1] = "Double Down";
				options[0] = "Insurance";
				int action = JOptionPane.showOptionDialog(null, "Your hand:     " + player.getHand() +
						"\nDealer's hand:     " + dealerHand, 
						"PLAY", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[3]);
				if(action == 0)
				{
					String i = JOptionPane.showInputDialog("Enter an amount for insurance: (must be greater than $0 and no more than $" + (bankroll / 2) + ")");
					insurance = Integer.parseInt(i);
					bankroll -= insurance;
					playerAction();
				}
				else if(action == 1)
				{
					player.getHand().addCard(shoe.dealCard());
					doubleDownAction();
				}
				else if(action == 2)
				{
					dealerAction();
				}
				else if(action == 3)
				{
					player.getHand().addCard(shoe.dealCard());
					if(player.getHand().getHandValue() == 21)
					{
						dealerAction();
					}
					else if(player.getHand().Bust())
					{
						JOptionPane.showMessageDialog(null, "Your hand:     " + player.getHand() +
								"\n\nYou lose the hand. You busted." +
								"\nBankroll: $" + bankroll, "PLAY", JOptionPane.INFORMATION_MESSAGE);
					}
					else if(player.getHand().fiveCardCharlie())
					{
						bankroll += (bet * 2);
						JOptionPane.showMessageDialog(null, "Your hand:     " + player.getHand() +
								"\n\nYou win the hand! Five Card Charlie!" + 
								"\nBankroll: $" + bankroll, "PLAY", JOptionPane.INFORMATION_MESSAGE);
					}
					else
					{
						playerAction();
					}
				}
			}
			else
			{
				String[] options = new String[3];
				options[2] = "Hit";
				options[1] = "Stand";
				options[0] = "Double Down";
				int action = JOptionPane.showOptionDialog(null, "Your hand:     " + player.getHand() +
						"\nDealer's hand:     " + dealerHand, 
						"PLAY", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);
				if(action == 2)
				{
					player.getHand().addCard(shoe.dealCard());
					if(player.getHand().getHandValue() == 21)
					{
						dealerAction();
					}
					else if(player.getHand().Bust())
					{
						if(insurance == 0)
						{
							JOptionPane.showMessageDialog(null, "Your hand:     " + player.getHand() +
									"\n\nYou lose the hand. You busted." +
									"\nBankroll: $" + bankroll, "PLAY", JOptionPane.INFORMATION_MESSAGE);
						}
						else
						{
							dealerHand.addCard(shoe.dealCard());
							if(dealerHand.Blackjack())
							{
								bankroll += (insurance * 3);
								JOptionPane.showMessageDialog(null, "Your hand:     " + player.getHand() +
										"\nDealer's hand: " + dealerHand +
										"\n\nYou lose the hand. You busted." +
										"\nBankroll: $" + bankroll, "PLAY", JOptionPane.INFORMATION_MESSAGE);
							}
						}
					}
					else if(player.getHand().fiveCardCharlie())
					{
						if(insurance == 0)
						{
							bankroll += (bet * 2);
							JOptionPane.showMessageDialog(null, "Your hand:     " + player.getHand() +
									"\n\nYou win the hand! Five Card Charlie!" + 
									"\nBankroll: $" + bankroll, "PLAY", JOptionPane.INFORMATION_MESSAGE);
						}
						else
						{
							dealerHand.addCard(shoe.dealCard());
							if(dealerHand.Blackjack())
							{
								bankroll += (bet * 2);
								bankroll += (insurance * 3);
								JOptionPane.showMessageDialog(null, "Your hand:     " + player.getHand() +
										"\nDealer's hand: " + dealerHand +
										"\n\nYou win the hand! Five Card Charlie!" + 
										"\nBankroll: $" + bankroll, "PLAY", JOptionPane.INFORMATION_MESSAGE);
							}
						}
					}
					else
					{
						playerAction();
					}
				}
				else if(action == 1)
				{
					dealerAction();
				}
				else if(action == 0)
				{
					player.getHand().addCard(shoe.dealCard());
					doubleDownAction();
				}
			}
		}
		else if(player.getHand().getHandSize() > 2)
		{
			String[] options = new String[2];
			options[1] = "Hit";
			options[0] = "Stand";
			int action = JOptionPane.showOptionDialog(null, "Your hand:     " + player.getHand() +
					"\nDealer's hand:     " + dealerHand, 
					"PLAY", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
			if(action == 1)
			{
				player.getHand().addCard(shoe.dealCard());
				if(player.getHand().getHandValue() == 21)
				{
					dealerAction();
				}
				else if(player.getHand().Bust())
				{
					JOptionPane.showMessageDialog(null, "Your hand:     " + player.getHand() +
							"\n\nYou lose the hand. You busted." +
							"\nBankroll: $" + bankroll, "PLAY", JOptionPane.INFORMATION_MESSAGE);
				}
				else if(player.getHand().fiveCardCharlie())
				{
					bankroll += (bet * 2);
					JOptionPane.showMessageDialog(null, "Your hand:     " + player.getHand() +
							"\n\nYou win the hand! Five Card Charlie!" + 
							"\nBankroll: $" + bankroll, "PLAY", JOptionPane.INFORMATION_MESSAGE);
				}
				else
				{
					playerAction();
				}
			}
			else if(action == 0)
			{
				dealerAction();
			}
		}
	}

	/**
	 * Based on the scenario, carries out the dealer's appropriate action
	 */
	public void dealerAction()
	{
		if(insurance == 0)
		{
			if(dealerHand.getHandValue() < player.getHand().getHandValue() && !player.getHand().Bust() && !player.getHand().fiveCardCharlie() && !player.getHand().Blackjack())
			{   // if the player hasn't busted, doesn't have five card charlie or blackjack and the dealer's hand is less than the player's, deal a card to the dealer
				while(dealerHand.getHandValue() <= 16)
				{   // this makes sure that the dealer's hand is never less than 16
					dealerHand.addCard(shoe.dealCard());
				}
				if(dealerHand.Bust())
				{   // if the dealer busts, the player wins
					bankroll += (bet * 2);
					JOptionPane.showMessageDialog(null, "Your hand:     " + player.getHand() +
							"\nDealer's hand:     " + dealerHand +
							"\n\nYou win the hand! The dealer busted." +
							"\nBankroll: $" + bankroll, 
							"PLAY", JOptionPane.INFORMATION_MESSAGE);
				}
				else if(dealerHand.getHandValue() < player.getHand().getHandValue() && dealerHand.getHandValue() > 16)
				{   // if the dealer's hand is greater than 16 and less than the player's hand, dealer loses
					bankroll += (bet * 2);
					JOptionPane.showMessageDialog(null, "Your hand:     " + player.getHand() +
							"\nDealer's hand:     " + dealerHand +
							"\n\nYou win the hand!" +
							"\nBankroll: $" + bankroll, 
							"PLAY", JOptionPane.INFORMATION_MESSAGE);
				}
				else if(player.getHand().getHandValue() == dealerHand.getHandValue())
				{   // if their hands are equal, push
					bankroll += bet;
					JOptionPane.showMessageDialog(null, "Your hand:     " + player.getHand() +
							"\nDealer's hand:     " + dealerHand +
							"\n\nPush." +
							"\nBankroll: $" + bankroll, 
							"PLAY", JOptionPane.INFORMATION_MESSAGE);
				}
				else if(dealerHand.getHandValue() > player.getHand().getHandValue() && dealerHand.getHandValue() <= 21)
				{   // if the dealer's hand is greater than the player's and below 21, then dealer wins
					JOptionPane.showMessageDialog(null, "Your hand:     " + player.getHand() +
							"\nDealer's hand:     " + dealerHand +
							"\n\nYou lose the hand." +
							"\nBankroll: $" + bankroll, 
							"PLAY", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
		else if(insurance != 0)
		{
			if(dealerHand.getHandValue() < player.getHand().getHandValue() && !player.getHand().Bust() && !player.getHand().fiveCardCharlie() && !player.getHand().Blackjack())
			{   // if the player hasn't busted, doesn't have five card charlie or blackjack and the dealer's hand is less than the player's, deal a card to the dealer
				while(dealerHand.getHandValue() <= 16)
				{   // this makes sure that the dealer's hand is never less than 16
					dealerHand.addCard(shoe.dealCard());
					if(dealerHand.Blackjack())
					{
						bankroll += (insurance * 3);
					}
				}
				if(dealerHand.Bust())
				{   // if the dealer busts, the player wins
					bankroll += (bet * 2);
					JOptionPane.showMessageDialog(null, "Your hand:     " + player.getHand() +
							"\nDealer's hand:     " + dealerHand +
							"\n\nYou win the hand! The dealer busted." +
							"\nBankroll: $" + bankroll, 
							"PLAY", JOptionPane.INFORMATION_MESSAGE);
				}
				else if(dealerHand.getHandValue() < player.getHand().getHandValue() && dealerHand.getHandValue() > 16)
				{   // if the dealer's hand is greater than 16 and less than the player's hand, dealer loses
					bankroll += (bet * 2);
					JOptionPane.showMessageDialog(null, "Your hand:     " + player.getHand() +
							"\nDealer's hand:     " + dealerHand +
							"\n\nYou win the hand!" +
							"\nBankroll: $" + bankroll, 
							"PLAY", JOptionPane.INFORMATION_MESSAGE);
				}
				else if(player.getHand().getHandValue() == dealerHand.getHandValue())
				{   // if their hands are equal, push
					bankroll += bet;
					JOptionPane.showMessageDialog(null, "Your hand:     " + player.getHand() +
							"\nDealer's hand:     " + dealerHand +
							"\n\nPush." +
							"\nBankroll: $" + bankroll, 
							"PLAY", JOptionPane.INFORMATION_MESSAGE);
				}
				else if(dealerHand.getHandValue() > player.getHand().getHandValue() && dealerHand.getHandValue() <= 21)
				{   // if the dealer's hand is greater than the player's and below 21, then dealer wins
					JOptionPane.showMessageDialog(null, "Your hand:     " + player.getHand() +
							"\nDealer's hand:     " + dealerHand +
							"\n\nYou lose the hand." +
							"\nBankroll: $" + bankroll, 
							"PLAY", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
	}

	/**
	 * A method to carry out the appropriate action when the player doubles down
	 */
	public void doubleDownAction()
	{
		if((bet > bankroll))
		{   // if try to double down, but don't have enough money to pay if lose, reject
			JOptionPane.showMessageDialog(null, "You can't double down, your bet must be " +
					"no more than half your bankroll.", "PLAY", JOptionPane.INFORMATION_MESSAGE);
			player.getHand().removeCard(player.getHand().getHandSize() - 1);
			playerAction();
		}
		else if(player.getHand().Bust())
		{
			bankroll -= bet;
			JOptionPane.showMessageDialog(null, "Your hand:     " + player.getHand() +
					"\n\nYou lose the hand. You busted." +
					"\nBankroll: $" + bankroll, "PLAY", JOptionPane.INFORMATION_MESSAGE);
		}
		else if(dealerHand.getHandValue() < player.getHand().getHandValue() && !player.getHand().Bust() && !player.getHand().fiveCardCharlie() && !player.getHand().Blackjack())
		{   // if the player hasn't busted, doesn't have five card charlie or blackjack and the dealer's hand is less than the player's, deal a card to the dealer
			while(dealerHand.getHandValue() <= 16)
			{   // this makes sure that the dealer's hand is never less than 16
				dealerHand.addCard(shoe.dealCard());
			}
			if(dealerHand.Bust())
			{   // if the dealer busts, the player wins
				bankroll += (bet * 3);
				JOptionPane.showMessageDialog(null, "Your hand:     " + player.getHand() +
						"\nDealer's hand:     " + dealerHand +
						"\n\nYou win the hand! The dealer busted." +
						"\nBankroll: $" + bankroll, 
						"PLAY", JOptionPane.INFORMATION_MESSAGE);
			}
			else if(dealerHand.getHandValue() < player.getHand().getHandValue() && dealerHand.getHandValue() > 16)
			{   // if the dealer's hand is greater than 16 and less than the player's hand, dealer loses
				bankroll += (bet * 3);
				JOptionPane.showMessageDialog(null, "Your hand:     " + player.getHand() +
						"\nDealer's hand:     " + dealerHand +
						"\n\nYou win the hand!" +
						"\nBankroll: $" + bankroll, 
						"PLAY", JOptionPane.INFORMATION_MESSAGE);
			}
			else if(player.getHand().getHandValue() == dealerHand.getHandValue())
			{   // if their hands are equal, push
				bankroll += bet;
				JOptionPane.showMessageDialog(null, "Your hand:     " + player.getHand() +
						"\nDealer's hand:     " + dealerHand +
						"\n\nPush." +
						"\nBankroll: $" + bankroll, 
						"PLAY", JOptionPane.INFORMATION_MESSAGE);
			}
			else if(dealerHand.getHandValue() > player.getHand().getHandValue() && dealerHand.getHandValue() <= 21)
			{   // if the dealer's hand is greater than the player's and below or equal 21, then dealer wins
				bankroll -= bet;
				JOptionPane.showMessageDialog(null, "Your hand:     " + player.getHand() +
						"\nDealer's hand:     " + dealerHand +
						"\n\nYou lose the hand." +
						"\nBankroll: $" + bankroll, 
						"PLAY", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

	/**
	 * A boolean to check if the player's bankroll is less than or equal to zero
	 * @return  boolean
	 */
	public boolean checkBankroll()
	{
		if(bankroll <= 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * A method that is called when the game has ended. Prints a message including the number of hands played and money won or lost
	 */
	public void endGame()
	{
		if(originalBankroll > bankroll)
		{
			JOptionPane.showMessageDialog(null, "GAME OVER" + "\nYou played " + handsPlayed + " hands" + "\nYou lost $" + (originalBankroll - bankroll), "GAME OVER", JOptionPane.INFORMATION_MESSAGE);
		}
		else if(originalBankroll < bankroll)
		{
			JOptionPane.showMessageDialog(null, "GAME OVER" + "\nYou played " + handsPlayed + " hands" + "\nYou won $" + (bankroll - originalBankroll), "GAME OVER", JOptionPane.INFORMATION_MESSAGE);
		}
		else if(originalBankroll == bankroll)
		{
			JOptionPane.showMessageDialog(null, "GAME OVER" + "\nYou played " + handsPlayed + " hands" + "\nYou won $0", "GAME OVER", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	/**
	 * A method for testing that clears the player's hand
	 */
	public void clearPlayerHand()
	{   // for testing
		player.getHand().clearHand();
	}

	/**
	 * A method for testing that adds a card to the player's hand
	 * @param   c   a card
	 */
	public void playerAddCard(Card c)
	{   // for testing
		player.getHand().addCard(c);
	}

	/**
	 * A boolean method for testing that checks if the player has blackjack
	 * @return  boolean
	 */
	public boolean getPlayerBlackjack()
	{   // for testing
		if(player.getHand().Blackjack())
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * A method for testing that clears the dealer's hand
	 */
	public void clearDealerHand()
	{   // for testing
		dealerHand.clearHand();
	}

	/**
	 * A method for testing that adds a card to the dealer's hand
	 * @param   c   a card
	 */
	public void dealerAddCard(Card c)
	{   // for testing
		upCard = c;
		dealerHand.addCard(c);
	}

	/**
	 * An accessor that returns the dealer's up card
	 * @return  the dealer's up card
	 */
	public Card getUpCard()
	{   // for testing
		return upCard;
	}
}
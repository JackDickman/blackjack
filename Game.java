package blackjack;

import javax.swing.JOptionPane;

public class Game
{
	private static boolean play = true;

	public static void main(String[] args)
	{
		Dealer dealer = new Dealer();

		dealer.createPlayer();
		dealer.houseRules();
		dealer.createShoe();

		while(play)
		{
			dealer.Bet();
			dealer.dealPlayerHand();
			dealer.dealDealerHand();
			dealer.playerAction();
			dealer.checkBankroll();
			if(dealer.checkBankroll())
			{
				play = false;
			}
			else
			{
				Continue();
			}
		}
		dealer.endGame();
	}

	public static boolean Continue()
	{
		String[] cont = new String[2];
		cont[1] = "Play";
		cont[0] = "Leave";
		int blah = JOptionPane.showOptionDialog(null, "Would you like to continue?", "CONTINUE", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, cont, cont[1]);
		if(blah == 1)
		{
			return play = true;
		}
		else
		{
			return play = false;
		}
	}
}
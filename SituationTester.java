package blackjack;

import javax.swing.JOptionPane;

public class SituationTester
{
	public static void main(String[] args)
	{
		Dealer dealer = new Dealer();

		JOptionPane.showMessageDialog(null, "This is to test BLACKJACK", "TEST", JOptionPane.INFORMATION_MESSAGE);

		dealer.createPlayer();
		dealer.createShoe();
		dealer.Bet();
		dealer.dealPlayerHand();
		// while(!(dealer.getPlayerBlackjack() && dealer.getUpCard().getRank().equals("Ace")))
		while(!dealer.getUpCard().getRank().equals("Ace"))
		{
			dealer.clearPlayerHand();
			dealer.dealPlayerHand();
		}
		dealer.dealDealerHand();
		dealer.playerAction();
		dealer.endGame();

		//         Dealer dealer = new Dealer();
		// 
		//         Card c1 = new Card("Ace", "Clubs", 11);
		//         Card c2 = new Card("King", "Clubs", 10);
		//         Card c3 = new Card("Seven", "Clubs", 7);
		//         Card c4 = new Card("Jack", "Clubs", 10);
		//         Card c5 = new Card("Three", "Clubs", 3);
		// 
		//         JOptionPane.showMessageDialog(null, "This is to test INSURANCE", "TEST", JOptionPane.INFORMATION_MESSAGE);
		// 
		//         dealer.createPlayer();
		//         dealer.createShoe();
		//         dealer.Bet();
		//         dealer.dealPlayerHand();
		//         dealer.clearPlayerHand();
		//         dealer.playerAddCard(c1);
		//         dealer.playerAddCard(c3);
		//         dealer.dealDealerHand();
		//         dealer.clearDealerHand();
		//         dealer.dealerAddCard(c1);
		//         dealer.playerAction();
		//         dealer.endGame();
	}
}
// Output is in JOPtionPane
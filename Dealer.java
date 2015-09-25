import java.util.Scanner;


public class Dealer {
	public int stop = 0;
	public int handNum = 1;
	public int wins;
	public int losses;
	public int sum;
	public int sum2;
	public int dsum;
	public Card player1;
	public Card dealer1;
	public Card player2;
	public Card dealer2;
	public String dealerhand;
	public String playerhand;
	public int dAceCount = 0;
	public int pAceCount = 0;
	public int ps1AceCount = 0;
	public int ps2AceCount = 0;
	public String split1;
	public String split2;
	public int hand1in = 1;
	public int hand2in = 1;
	
	private Deck deck;
	public Dealer(String s){
		deck = new Deck(s);
	}
	public void choice(){
		
		System.out.println("Enter 1 to Hit, 2 to Stay, or 3 to Double Down");
		int input = 0;
		Scanner scan = new Scanner(System.in);
		try {
			input = scan.nextInt();
		}
		catch(Exception e) {
		    System.out.println("Please enter a valid number");
		    choice();
		}
		switch(input){
		case 1: Card player3 = deck.draw();
				sum += faceCheck(player3.getNum());
				playerhand += String.format(", %s", identify(player3));
				if(player3.getNum() == 1){
					pAceCount++;
				}
				if(sum > 21 && pAceCount > 0)
				{
					sum = sum - (10 * pAceCount);
					pAceCount = 0;
				}
				
				System.out.println(playerhand + " (" + sum +")");
				if(sum > 21){
					System.out.println();
					System.out.println("You busted, you lose this hand.");
					System.out.println();
					losses++;
				}
				choice();
				break;
		case 2: 
				System.out.println("You selected Stay");
				
				if(sum > 21){
					System.out.println(dealerhand);
					System.out.println();
					System.out.println("You busted, you lose this hand");
					System.out.println();
					losses++;
				}else if(dsum > 21){
					System.out.println(dealerhand);					
					System.out.println("Dealer busted, you win this hand");
					System.out.println();
					wins++;
				}else if(sum > dsum){
					System.out.println(dealerhand);					
					System.out.println("You win this hand");
					System.out.println();
					wins++;
				}else if (dsum > sum){
					System.out.println(dealerhand);				
					System.out.println("You lose this hand" );
					System.out.println();
					losses++;
				}else if(dsum == sum){
					System.out.println(dealerhand);					
					System.out.println("The hand is a Push");
					System.out.println();
				}
				break;
		case 3: System.out.println("You selected Double Down");
				Card player4 = deck.draw();
				if(player4.getNum() == 1){
					pAceCount++;
				}
				sum += faceCheck(player4.getNum());
				playerhand += String.format(", %s", identify(player4));
				if(sum > 21 && pAceCount > 0)
				{
					sum = sum - (10 * pAceCount);
					pAceCount = 0;
				}
				System.out.println(playerhand + " (" + sum +")");
				if(sum > 21){
					System.out.println();
					System.out.println("You busted, you lose this hand");
					System.out.println();
					losses++;
					losses++;
				}else if(dsum > 21){
					System.out.println(dealerhand);					
					System.out.println("Dealer busted, you win this hand");
					System.out.println();
					wins++;
					wins++;
				}else if(sum > dsum){
					System.out.println(dealerhand);					
					System.out.println("You win this hand");
					System.out.println();
					wins++;
					wins++;
				}else if (dsum > sum){
					System.out.println(dealerhand);				
					System.out.println("You lose this hand" );
					System.out.println();
					losses++;
					losses++;
				}else if(dsum == sum){
					System.out.println(dealerhand);					
					System.out.println("The hand is a Push");
					System.out.println();
				}
				break;
		default:pAceCount = 0;
				choice();
				break;
				
		}
	}
	public void play(){
		System.out.println();
		System.out.println();
		deck.shuffle();
		while (stop == 0 && deck.getDeck().size() > 12){
			System.out.println(deck.getDeck().size() + " Cards");
			System.out.println("Hand " + handNum +":   (Wins: " + wins + " --- Losses: " + losses + ")");
			handNum++;
			player1 = deck.draw();
			dealer1 = deck.draw();
			player2 = deck.draw();
			dealer2 = deck.draw();
			sum = faceCheck(player1.getNum()) + faceCheck(player2.getNum());
			dsum = faceCheck(dealer1.getNum()) + faceCheck(dealer2.getNum());
			System.out.println("Your Hand: " + identify(player1) + ", " + identify(player2) + " (" + sum + ")");
			System.out.println("Dealers Hand: " + identify(dealer1) + ", Hidden");
			dealerhand = String.format("Dealer Shows: " + identify(dealer1) + ", " + identify(dealer2));
			playerhand = String.format("Your Hand: "+ identify(player1) + ", " + identify(player2));
			if (player1.getNum() == 1){
				pAceCount++;
			}
			if (player2.getNum() == 1){
				pAceCount++;
			}
			if (dealer1.getNum() == 1){
				dAceCount++;
			}
			if (dealer2.getNum() == 1){
				dAceCount++;
			}
			System.out.println();
			
			
			
			
			while(dsum < 17){
				Card dealer3 = deck.draw();
				if (dealer3.getNum() == 1){
					dAceCount++;
				}
				dsum += faceCheck(dealer3.getNum());
				dealerhand += String.format(", %s", identify(dealer3));
				
				if(dsum > 21 && dAceCount > 0){
					dsum = dsum - (10 * dAceCount);
					dAceCount = 0;
				}
			}
			if(faceCheck(player1.getNum()) == faceCheck(player2.getNum())){
				System.out.println("Would you like to Split? (y/n)");
				if(splitQ() == 1){
					sum = sum / 2;
					sum2 = sum;
					split1 = String.format("Hand One: "+ identify(player1));
					split2 = String.format("Hand Two: "+ identify(player2));
					hand1in = 1;
					hand2in = 1;
					split();
				}else{pAceCount = 0;
					choice();}
			}else{
			pAceCount = 0;
			choice();
			System.out.println();
			stop = playoption();
			}
		}
		
	}
	public int faceCheck(int num){
		
		switch(num){
		case 1: return 11;
		case 11: return 10;
		case 12: return 10;
		case 13: return 10;
		default: return num;
		}
		
	}
	public String identify(Card c){
		int x;
		int y;
		String face;
		String suit;
		x = c.getNum();
		y = c.getSuit();
		switch(x){
		case 1: face = "Ace";
				break;
		case 11: face = "Jack";
				break;
		case 12: face = "Queen";
				break;
		case 13: face = "King";
				break;
		default: face = Integer.toString(x);
				break;
		}
		switch(y){
		case 1: suit = "Spades";
				break;
		case 2: suit = "Clubs";
				break;
		case 3: suit = "Diamonds";
				break;
		case 4: suit = "Hearts";
				break;
		default: suit = "error";
				break;
		}
		return String.format("%s of %s", face, suit);
		
	}
	public static void main(String[] args){
		System.out.println("Welcome to Blackjack");
		Dealer d = new Dealer("cards.txt");
		d.play();
		
		
		
	}
	public int playoption(){
		System.out.println("Would you like to play the next hand? (y/n)");

		Scanner scan = new Scanner(System.in);
		String response = scan.next();
		if(response.equals("y")){
			return 0;
		}else if(response.equals("n")){
			System.exit(0);
			return 1;
		}else{
			System.out.println("Please enter y/n");
			return playoption();
		}
	}
	public int splitQ(){
		Scanner scan = new Scanner(System.in);
		String response = scan.next();
		if(response.equals("y")){
			return 1;
		}else if(response.equals("n")){
			return 0;
		}else{
			System.out.println("Please enter y/n");
			return splitQ();
		}
	
	}
	public void split(){
		
		
		if(hand1in == 1){
			System.out.println("Hand One: Enter 1 to Hit, 2 to Stay, or 3 to Double Down");
			int input = 0;
			Scanner scan = new Scanner(System.in);
			try{
				input = scan.nextInt();
			}catch(Exception e){
				System.out.println("Please enter a valid option.");
				split();
			}
			switch (input){
			case 1: splitHit1();
					break;
			case 2:splitStay1();
					break;
			case 3:splitDouble1();
					break;
			default: split();
					break;
			}
		}
		if(hand2in == 1){
			System.out.println("Hand Two: Enter 1 to Hit, 2 to Stay, or 3 to Double Down");
			int input = 0;
			Scanner scan = new Scanner(System.in);
			try{
				input = scan.nextInt();
			}catch(Exception e){
				System.out.println("Please enter a valid option.");
				split();
			}
			switch (input){
			case 1: splitHit2();
					break;
			case 2:splitStay2();
					break;
			case 3:splitDouble2();
					break;
			default: split();
					break;
			}
		}
		System.out.println(split1);
		System.out.println(split2);
		if(hand1in == 3){
		if(sum > 21){
			System.out.println(dealerhand);
			System.out.println();
			System.out.println("You busted, you lose this hand");
			losses++;
		}else if(dsum > 21){
			System.out.println(dealerhand);					
			System.out.println("Dealer busted, you win both hands");
			wins++;
			wins++;
			System.out.println();
			System.out.println();
			if(playoption() == 0){
				play();
			}
		}else if(sum > dsum){
			System.out.println(dealerhand);					
			System.out.println("You win Hand One");
			wins++;
		}else if (dsum > sum){
			System.out.println(dealerhand);				
			System.out.println("You lose Hand One" );
			losses++;
		}else if(dsum == sum){
			System.out.println(dealerhand);					
			System.out.println("Hand One is a Push");
		}
		}
		if(hand2in ==3){
		if(sum2 > 21){
			System.out.println(dealerhand);
			System.out.println();
			System.out.println("You busted, you lose this hand");
			losses++;
		}else if(dsum > 21){
			System.out.println(dealerhand);					
			System.out.println("Dealer busted, you win both hands");
			wins++;
		}else if(sum2 > dsum){				
			System.out.println("You win Hand Two");
			wins++;
		}else if (dsum > sum2){			
			System.out.println("You lose Hand Two" );
			losses++;
		}else if(dsum == sum2){				
			System.out.println("Hand Two is a Push");
		}
		}
		if(playoption() == 0){
			play();
		}
		
	}
	public void splitHit2(){
		Card player3 = deck.draw();
		sum2 += faceCheck(player3.getNum());
		split2 += String.format(", %s", identify(player3));
		if(player3.getNum() == 1){
			ps2AceCount++;
		}
		if(sum > 21 && ps2AceCount > 0)
		{
			sum = sum - (10 * ps2AceCount);
			ps2AceCount = 0;
		}
		
		System.out.println(split2 + " (" + sum2 +")");
		if(sum2 > 21){
			System.out.println();
			System.out.println("You busted, you lose this hand.");
			hand2in = 0;
		}
		split();
	}
	private void splitStay2() {
System.out.println("You selected Stay");
		
		if(sum2 > 21){
			System.out.println();
			System.out.println("You busted, you lose this hand");
		}
		hand2in = 3;
		split();
	}
	private void splitDouble2() {
		Card player3 = deck.draw();
		sum2 += faceCheck(player3.getNum());
		split2 += String.format(", %s", identify(player3));
		if(player3.getNum() == 1){
			ps2AceCount++;
		}
		if(sum > 21 && ps2AceCount > 0)
		{
			sum = sum - (10 * ps2AceCount);
			ps2AceCount = 0;
		}
		
		System.out.println(split2 + " (" + sum +")");
		
		if(sum2 > 21){
			System.out.println();
			System.out.println("You busted, you lose this hand");
			hand2in = 0;
		}
		hand2in = 0;
		split();
	}
	
	private void splitDouble1() {
		Card player3 = deck.draw();
		sum += faceCheck(player3.getNum());
		split1 += String.format(", %s", identify(player3));
		if(player3.getNum() == 1){
			ps1AceCount++;
		}
		if(sum > 21 && ps1AceCount > 0)
		{
			sum = sum - (10 * ps1AceCount);
			ps1AceCount = 0;
		}
		
		System.out.println(split1 + " (" + sum +")");
		
		if(sum > 21){
			System.out.println();
			System.out.println("You busted, you lose this hand");
			hand1in = 0;
		}
		hand1in = 0;
		split();
	}
	private void splitStay1() {
		System.out.println("You selected Stay");
		
		if(sum > 21){
			System.out.println();
			System.out.println("You busted, you lose this hand");
		}
		hand1in = 3;
		split();
	}
	public void splitHit1(){
		Card player3 = deck.draw();
		sum += faceCheck(player3.getNum());
		split1 += String.format(", %s", identify(player3));
		if(player3.getNum() == 1){
			ps1AceCount++;
		}
		if(sum > 21 && ps1AceCount > 0)
		{
			sum = sum - (10 * ps1AceCount);
			ps1AceCount = 0;
		}
		
		System.out.println(split1 + " (" + sum +")");
		if(sum > 21){
			System.out.println();
			System.out.println("You busted, you lose this hand.");
			hand1in = 0;
		}
		split();
	}

	public void test(){
		int i = 0;
		while(i < 52){
		System.out.println(identify(deck.getDeck().get(i)));
		i++;
		}
	}
}

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class Deck {
	private ArrayList<Card> deck;
	public int i = 0;
	public Deck(String f)
	{
		deck = new ArrayList<Card>();
		File file = new File(f);
		try {
			int num;
			int suit;
			Scanner s = new Scanner(file);
			while(s.hasNextInt()){
				num = s.nextInt();
				suit = s.nextInt();
				deck.add(new Card(num, suit));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public ArrayList<Card> printDeck() {
		for(int i = 0; i < 52; i++){
			System.out.println(deck.get(i).getNum() + " " + deck.get(i).getSuit());
		}
		return null;
	}
	public void setDeck(ArrayList<Card> deck) {
		this.deck = deck;
	}
	public ArrayList<Card> getDeck(){
		return deck;
		
	}
	public void shuffle(){
		Collections.shuffle(deck);
	}
	public Card draw(){
		Card c = new Card(deck.get(i).getNum(),deck.get(i).getSuit());
		deck.remove(i);
		
		return c;
	}
	

}

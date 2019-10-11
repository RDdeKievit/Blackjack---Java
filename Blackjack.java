import java.util.ArrayList;
import java.util.Random;
class Blackjack {
	
	public static int[] dekSpeelkaarten = new int[] {2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11};
	
	public static void main (String args[]){
		deelStartHand();
	}
	
	public static void deelStartHand(){
		for(int i = 0; i < 2; i++){
			Bank.kaartenHand.add(getKaart(dekSpeelkaarten));
		}
		for(int i = 0; i < 2; i++){
			Gokker.kaartenHand.add(getKaart(dekSpeelkaarten));
		}
		System.out.println(Bank.kaartenHand);
		System.out.println(Gokker.kaartenHand);	
		blackjackCheck();
	}

	public static void blackjackCheck() {
		if((getSomHand(Gokker.kaartenHand) == 21) && (getSomHand(Bank.kaartenHand) != 21)){
			System.out.println("BLACKJACK!!");
		} else if((getSomHand(Gokker.kaartenHand) == 21) && (getSomHand(Bank.kaartenHand) == 21)){
			System.out.println("AAAHH GELIJKSPEL, de Bank heeft ook blackjack..");
		} else if((getSomHand(Gokker.kaartenHand) != 21) && (getSomHand(Bank.kaartenHand) == 21)){
			System.out.println("Doorgestoken kaart, de Bank heeft weer blackjack..");
		} else
			System.out.println("Still in it to win it!");
	}

	public static void winCheck() {
		if(getSomHand(Bank.kaartenHand) > getSomHand(Gokker.kaartenHand)) {
			System.out.println("De Bank wint..");
		} else if (getSomHand(Bank.kaartenHand) < getSomHand(Gokker.kaartenHand)) {
			System.out.println("De Gokker wint!");
		} else 
			System.out.println("Geen winnaars & geen verliezers.");
		
	}

	public static int getKaart(int[] array) {
   		int rnd = new Random().nextInt(array.length);
		return array[rnd];
	}
	
	public static int getSomHand(ArrayList<Integer> arraylist) {
		int total = 0;	
		for(int kaart : arraylist)
			total += kaart;
		
		return total;
	}
}

class Player {
	String name;
	ArrayList<Integer> kaartenHand;
}
class Bank extends Player {
	static final String name = new String("Bank");
	static ArrayList<Integer> kaartenHand = new ArrayList<Integer>();
	static int stopsAt = 17;
}
class Gokker extends Player {
	static final String name = new String("Gokker");
	static ArrayList<Integer> kaartenHand = new ArrayList<Integer>();
	static int stopsAt = 18;
}


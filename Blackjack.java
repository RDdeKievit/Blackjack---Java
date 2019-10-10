import java.util.ArrayList;
import java.util.Random;
class Blackjack {
	
	public static int[] dekSpeelkaarten = new int[] {2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11};
	
	public static void main (String args[]){
		System.out.println(Bank.name);
		System.out.println(Gokker.name);
		deelStartHand();
		System.out.println(Bank.kaartenHand);
		System.out.println(Gokker.kaartenHand);
	}
	
	public static void deelStartHand(){
		for(int i = 0; i < 2; i++){
			Bank.kaartenHand.add(getKaart(dekSpeelkaarten));
		}
		for(int i = 0; i < 2; i++){
			Gokker.kaartenHand.add(getKaart(dekSpeelkaarten));
		}		
	}

	public static int getKaart(int[] array) {
   		int rnd = new Random().nextInt(array.length);
		return array[rnd];
	}
}

class Player {
	String name;
	ArrayList<Integer> kaartenHand;
}
class Bank extends Player {
	static final String name = new String("Bank");
	static ArrayList<Integer> kaartenHand = new ArrayList<Integer>();
}
class Gokker extends Player {
	static final String name = new String("Gokker");
	static ArrayList<Integer> kaartenHand = new ArrayList<Integer>();
}

import java.util.Random;
class Blackjack {
	
	public static int[] dekSpeelkaarten = new int[] {2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11};
	
	public static void main (String args[]){
		System.out.println(getKaart(dekSpeelkaarten));
	}

	public static int getKaart(int[] array) {
   		int rnd = new Random().nextInt(array.length);
		return array[rnd];
	}

}
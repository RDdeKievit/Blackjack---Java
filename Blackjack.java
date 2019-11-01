import java.util.ArrayList;
import java.util.Random;
class Blackjack {
	
	//globale variabelen & globaal array
	public static double gokkerWallet = 10;
	public static int inzetHand = 1;

	public static String winnaar = null;

	public static int[] dekSpeelkaarten = new int[] {2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11};
	
	//start functie; roept inzetHand() aan 
	public static  void main (String args[]){
		inzetHand();
	}

	/* functie inzetHand bepaald of the Gokker nog een ronde kan spelen
	& wanneer dit zo is, wordt de inzet van de wallet afgetrokken, de values van de inzet en van de wallet geprint en deelStartHand() aangeroepen
	& wanneer dit niet zo is, wordt er een afscheidsbericht geprint
	*/
	public static void inzetHand(){
		if((gokkerWallet - 1) >= 0){
			gokkerWallet -= 1;

			System.out.println("Wallet: " + gokkerWallet);
			System.out.println("Inzet: " + inzetHand);

			deelStartHand();
		} else {
			System.out.println("Het geld is op voor vandaag, tijd om te gaan.");
		}
		
	}
	
	/* functie deelStartHand vult de ArrayList kaartenHand van zowel Gokker als Bank met 2 random kaarten
	& print beide ArrayLists
	& roept blackjackCheck() aan
	*/
	public static void deelStartHand(){
		for(int i = 0; i < 2; i++){
			Bank.kaartenHand.add(getKaart(dekSpeelkaarten));
		}
		for(int i = 0; i < 2; i++){
			Gokker.kaartenHand.add(getKaart(dekSpeelkaarten));
		}
		System.out.println("Hand bank:" + Bank.kaartenHand);
		System.out.println("Hand gokker:" + Gokker.kaartenHand);

		blackjackCheck();
	}
	/* functie blackjackCheck checkt of enkel de bank, enkel de gokker of beide blackjack hebben
	& wanneer een van deze waar is, wordt payOut() aangeroepen met de toepasselijke multiplier als argument
	& wanneer geen van deze waar is, wordt gokkerSpeelt() aangeroepen
	*/
	public static void blackjackCheck() {
		if((getSomHand(Gokker.kaartenHand) == 21) && (getSomHand(Bank.kaartenHand) != 21)){
			System.out.println("BLACKJACK!!");
			winnaar = Gokker.name;
			payOut(2.5);
		} else if((getSomHand(Gokker.kaartenHand) == 21) && (getSomHand(Bank.kaartenHand) == 21)){
			System.out.println("AAAHH GELIJKSPEL, de Bank heeft ook blackjack..");
			winnaar = "geen";
			payOut(1);
		} else if((getSomHand(Gokker.kaartenHand) != 21) && (getSomHand(Bank.kaartenHand) == 21)){
			System.out.println("Doorgestoken kaart, de Bank heeft weer blackjack..");
			winnaar = Bank.name;
			payOut(0);
		} else {
			bepaalCapGokker();
		}
	}

	/* functie bepaalCapGokker bepaald de waarde van de hand waarbij Gokker past (i.e. geen kaart meer vraagt)
	& wanneer de open (i.e. eerst gedeelde) kaart van de bank een waarde van 10 of 11 heeft stopt Gokker bij 18
	& in elk ander geval stopt Gokker bij 15
	& vervolgens wordt gokkerSpeelt() aangeroepen
	*/
	public static void bepaalCapGokker() {
		System.out.println("Open kaart bank:" + Bank.kaartenHand.get(0));
		Gokker.stopsAt = (Bank.kaartenHand.get(0) == 10 || Bank.kaartenHand.get(0) == 11)? 18 : 15;
		System.out.println("Gokker stopt bij:" + Gokker.stopsAt);

		gokkerSpeelt();
	}

	/* functie gokkerSpeelt voegt een extra random kaart toe aan ArrayList kaartenHand van Gokker zolang de waarde van de hand kleiner is dan de Cap van Gokker
	& wanneer de waarde van kaartenHand Gokker groter of gelijk is aan de Cap en kleiner of gelijk is aan 21 wordt bankSpeelt() aangeroepen
	& wanneer Gokker zich dood heeft gespeeld wordt een verliesbericht geprint en payOut() aangeroepen met de multiplier 0 als argument
	*/
	public static void gokkerSpeelt() {
		while (getSomHand(Gokker.kaartenHand) < Gokker.stopsAt){
			Gokker.kaartenHand.add(getKaart(dekSpeelkaarten));
			System.out.println("Hand gokker:" + Gokker.kaartenHand);
		}
		
		if(getSomHand(Gokker.kaartenHand) <= 21){
			bankSpeelt();
		} else {
			System.out.println("De Bank wint..");
			winnaar = Bank.name;
			payOut(0);
		}
	}

	/* functie bankSpeelt voegt een extra random kaart toe aan ArrayList kaartenHand van Bank zolang de waarde van de hand kleiner is dan de Cap van Bank
	& vervolgens wordt winCheck() aangeroepen
	*/
	public static void bankSpeelt() {
		while (getSomHand(Bank.kaartenHand) < Bank.stopsAt){
			Bank.kaartenHand.add(getKaart(dekSpeelkaarten));
			System.out.println("Hand bank:" + Bank.kaartenHand);
		}

		winCheck();
	}

	/* functie winCheck wordt bepaald of de waarde van kaartenHand van Bank groter is, kleiner is of gelijk is aan de waarde van kaartenHand Gokker
	& verder wordt payOut() aangeroepen met de toepasselijke multiplier meegegeven als argument
	*/
	public static void winCheck() {
		if(getSomHand(Bank.kaartenHand) > getSomHand(Gokker.kaartenHand)) {
			System.out.println("De Bank wint..");
			winnaar = Bank.name;
			payOut(0);
		} else if (getSomHand(Bank.kaartenHand) < getSomHand(Gokker.kaartenHand)) {
			System.out.println("De Gokker wint!");
			winnaar = Gokker.name;
			payOut(2);
		} else {
			System.out.println("Geen winnaars & geen verliezers.");
			winnaar = "geen";
			payOut(1);
		}
	}

	/* functie payOut() vermenigvuldigt de meegekregen multiplier met de waarde van inzetHand, telt dit getal op bij gokkerWallet en geeft gokkerWallet de waarde van deze som
	& vervolgens wordt verzamelKaarten() aangeroepen
	*/
	public static void payOut(double multiplier) {
		gokkerWallet += (inzetHand * multiplier);
		
		verzamelKaarten();
	}

	/* functie verzamelKaarten leegt ArrayList kaartenHand van zowel Gokker als Bank
	& roept inzetHand() aan
	*/
	public static void verzamelKaarten() {
		Gokker.kaartenHand.clear();
		Bank.kaartenHand.clear();

		System.out.println("---------------------------------");
		inzetHand();
	}

	// functie getKaart kiest een random waarde uit de meegekregen array en return deze waarde
	public static int getKaart(int[] array) {
   		int rnd = new Random().nextInt(array.length);
		return array[rnd];
	}
	
	// functie getSomHand berekent de som van de waarden in de meegekregem ArrayList en returnt de waarde van de som
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


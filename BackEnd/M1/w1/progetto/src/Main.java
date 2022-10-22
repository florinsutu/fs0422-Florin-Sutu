
import java.awt.print.Printable;
import java.util.Scanner;

import Classes.Audio;
import Classes.Image;
import Classes.Media;
import Classes.Video;

public class Main {

//	========= Proprietà ==========

	/*
	 * Tutta la classe è focalizzata sull'interazione con lo user, quindi anche per
	 * comodità ho pensato di aprire lo scanner sin dall'inizio e chiuderlo alla
	 * fine del main
	 */

	private static Media[] lista = new Media[5];
	private static Scanner scanner = new Scanner(System.in);

//	============ main =============

	public static void main(String[] args) {

		/*
		 * L'app parte e:
		 * 
		 * 1) viene chiesto all'utente di riempire il suo lettore di 5 elementi con
		 * media a sua scelta, dopodichè
		 * 
		 * 2) gli si chiede cosa vuole fare: scegliere di riprodurre un elemento,
		 * chiudere l'app oppure effettuare modifiche alla lista di elementi salvati,
		 * tutto ciò va avanti finchè
		 * 
		 * 3) l'utente non sceglie l'opzione di chiudere il lettore, a quel punto il
		 * programma finisce
		 * 
		 * Vedere le note sui metodi per capirne il funzionamento nel dettaglio, vedere
		 * anche i metodi di abbreviazione posti alla fine del programma per comprendere
		 * il funzionamento di ogni metodo
		 */

// 1)
		print("Carica 5 elementi nel tuo lettore multimediale");

		for (int i = 0; i < 5; i++) {
			createMedia(i);
		}

// 2)
		int choice = chooseOperation();
		while (choice != 0) {
			reproduce(choice - 1);
			choice = chooseOperation();
		}

// 3)
		print("Arrivederci");
		scanner.close();
		
	}

	
// ========= Metodi  ==========

// 1) Metodi di despaghettizzazione e lettura

	private static void createMedia(int i) {

		/*
		 * Questo metodo prende come parametro la posizione nell'array dei media dove
		 * andare ad inserire un nuovo elemento multimediale, a seconda della scelta
		 * dell'utente, creerà un tipo di elemento multimediale con titolo e
		 * caratteristiche consone
		 */

		print("Inserisci  la tipologia dell'elemento n° " + (i + 1) + " (1:img, 2:audio, 3:video)");

		int type = takeIntInRange(1, 3);

		print("inserisci il nome del file");

		String name = takeStr();

		switch (type) {

		case 1:
			lista[i] = new Image(name);
			break;

		case 2:
			print("inserisci durata della traccia (max 100 minuti)");
			int dAudio = takeIntInRange(1, 100);
			lista[i] = new Audio(name, dAudio);
			break;

		case 3:
			print("inserisci durata del video (max 100 minuti)");
			int dVideo = takeIntInRange(1, 100);
			lista[i] = new Video(name, dVideo);
			break;
		}

	}

	private static int chooseOperation() {

		/*
		 * Metodo che restituisce la scelta effettuata dall'utente, mediaList serve per
		 * formattare l'array di media e presentarlo allo user,s per non ripetere alcune
		 * stringhe all'interno di questo metodo. Nel caso l'utente scelga di modificare
		 * un elemento, viene lanciato il metodo modify che prenderà come parametri la
		 * posizione dell'elemento da modificare e la tipologia di modifica
		 */

		String mediaList = formatMediaList();

		String[] s = { "Scegli da 1 a 5 quale elemento riprodurre: ", mediaList,
				"Scegli 0 per chiudere il player, 9 per eseguire una modifica alla lista",
				"seleziona da 1 a 5 l'elemento da modificare: " };

		print(s[0] + s[1]);
		print(s[2]);

		int choice = takeValidOperation();
		while (choice == 9) {

			print(s[3] + s[1]);
			int toModify = takeIntInRange(1,5);
			print("seleziona il tipo di modifica, 1: rinomina, 2: sostituisci");
			int modType = takeIntInRange(1,2);

			modify(toModify, modType);

			mediaList = formatMediaList();
			s[1] = mediaList;

			print(s[0] + s[1]);
			print(s[2]);

			choice = takeValidOperation();
		}
		return choice;
	}

	private static String formatMediaList() {
		String mediaList = "";
		for (int i = 0; i < 5; i++) {
			mediaList += (i + 1) + ": " + lista[i].toString() + ", ";
		}
		return mediaList;
	}

	private static int takeValidOperation() {
		int choice = takeInt();

		while (!((choice < 6 && choice > -1) || choice == 9)) {
			print("Devi scegliere un'operazione valida");
			print("0: chiudi player; 1-5 riproduci elemento; 9: effettua modifica");
			choice = takeInt();
		}
		return choice;
	}

	private static void modify(int toModify, int modType) {

		if (modType == 1) {
			print("inserisci il nuovo nome per il file: " + lista[toModify - 1].getTitle());
			lista[toModify - 1].setTitle(takeStr());
			print("Rinomina avvenuta con successo");
		}
		if (modType == 2) {
			createMedia(toModify - 1);
			print("Sostituzione avvenuta con successo");
		}

	}

	private static void reproduce(int c) {

		/*
		 * Metodo che controlla che tipo di media l'utente ha selezionato e permette di
		 * riprodurlo con dei valori scelti sempre dall'utente
		 */

		if (lista[c] instanceof Audio) {

			print("Volume corrente = " + ((Audio) lista[c]).getVolume());
			print("premi 1 per confermare, 2 per aumentare di 1, 3 per diminuire di 1, 4 per impostarlo");	
			
			int sel = takeIntInRange(1,4);

			if(sel == 4) {
				print("Scegli il volume (min 1, max 10)");
				((Audio) lista[c]).setVolume(takeIntInRange(1, 10));
			}
			if(sel == 3) {
				((Audio) lista[c]).decreaseVolume();
			}
			if(sel == 2) {
				((Audio) lista[c]).increaseVolume();
			}
			
			lista[c].reproduce();
		}

		if (lista[c] instanceof Image) {
			
			print("Luminosità corrente = " + ((Image) lista[c]).getBrightness());
			print("premi 1 per confermare, 2 per aumentare di 1, 3 per diminuire di 1, 4 per impostarla");	
			
			int sel = takeIntInRange(1,4);

			if(sel == 4) {
				print("Scegli la luminosità (min 1, max 10)");
				((Image) lista[c]).setBrightness(takeIntInRange(1, 10));
			}
			if(sel == 3) {
				((Image) lista[c]).decreaseBrightness();
			}
			if(sel == 2) {
				((Image) lista[c]).increaseBrightness();
			}
			
			lista[c].reproduce();

		}

		if (lista[c] instanceof Video) {

			print("Luminosità corrente = " + ((Video) lista[c]).getBrightness());
			print("premi 1 per confermare, 2 per aumentare di 1, 3 per diminuire di 1, 4 per impostarla");	
			
			int sel1 = takeIntInRange(1,4);

			if(sel1 == 4) {
				print("Scegli la luminosità (min 1, max 10)");
				((Video) lista[c]).setBrightness(takeIntInRange(1, 10));
			}
			if(sel1 == 3) {
				((Video) lista[c]).decreaseBrightness();
			}
			if(sel1 == 2) {
				((Video) lista[c]).increaseBrightness();
			}
			
			print("Volume corrente = " + ((Video) lista[c]).getVolume());
			print("premi 1 per confermare, 2 per aumentare di 1, 3 per diminuire di 1, 4 per impostarlo");

			int sel2 = takeIntInRange(1,4);

			if(sel2 == 4) {
				print("Scegli il volume (min 1, max 10)");
				((Video) lista[c]).setVolume(takeIntInRange(1, 10));
			}
			if(sel2 == 3) {
				((Video) lista[c]).decreaseVolume();
			}
			if(sel2 == 2) {
				((Video) lista[c]).increaseVolume();
			}
			
			lista[c].reproduce();
		}

	}
	

// 2) Metodi per abbreviare alcune operazioni di base ricorrenti

	private static void print(Object a) {
		System.out.println(a);
	}

	private static int takeInt() {
		return Integer.parseInt(scanner.nextLine());
	}

	private static String takeStr() {

		/* Fa un semplice controllo di base e restituisce una stringa non vuota */

		String s = scanner.nextLine();
		while (s.length() < 1) {
			print("Deve avere almeno un carattere, riassegna");
			s = scanner.nextLine();
		}
		return s;
	}

	private static int takeIntInRange(int x1, int x2) {

		/*
		 * Breve metodo che assicura che il numero restituito rientri nel range che
		 * abbiamo stabilito
		 */

		int n = takeInt();
		while (n < x1 || n > x2) { // finchè non si inserisce un numero nel range non si va avanti
			print("Non hai immesso un numero nel range: " + x1 + '-' + x2 + ", riprova");
			n = takeInt();
		}
		return n;
	}

}

import java.util.Scanner;

import Classes.Audio;
import Classes.Image;
import Classes.Media;
import Classes.Video;

public class Main {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);

		System.out.println("Carica 5 elementi nel tuo lettore multimediale");

		Media[] lista = new Media[5];

		for (int i = 0; i < 5; i++) {

			System.out.println("Inserisci il primo elemento 0:img, 1:audio, 2:video");

			int type = Integer.parseInt(in.nextLine());

			System.out.println("inserisci nome del file");

			String name = in.nextLine();

			switch (type) {

			case 0:
				lista[i] = new Image(name);
				break;

			case 1:
				System.out.println("inserisci durata della traccia"); // int > 0
				int dAudio = Integer.parseInt(in.nextLine());
				lista[i] = new Audio(name, dAudio);
				break;

			case 2:
				System.out.println("inserisci durata del video");
				int dVideo = Integer.parseInt(in.nextLine());
				lista[i] = new Video(name, dVideo);
				break;

			default:
				System.out.println("Non hai selezionato un formato valido");

			}
		}
		System.out.println("Scegli cosa riprodurre");
		int op = Integer.parseInt(in.nextLine());

		while (op != 0) {
			

			if (lista[op - 1] instanceof Audio) {
				
				System.out.println("Scegli il volume");
				((Audio) lista[op - 1]).setVolume(Integer.parseInt(in.nextLine()));
				
				lista[op - 1].reproduce();
			}
			

			if (lista[op - 1] instanceof Image) {
				
				System.out.println("Scegli la luminosità");
				((Image) lista[op - 1]).setBrightness(Integer.parseInt(in.nextLine()));
				
				lista[op - 1].reproduce();
			}
			

			if (lista[op - 1] instanceof Video) {
				
				System.out.println("Scegli la luminosità");
				((Video) lista[op - 1]).setBrightness(Integer.parseInt(in.nextLine()));
				
				System.out.println("Scegli il volume");
				((Video) lista[op - 1]).setVolume(Integer.parseInt(in.nextLine()));
				
				lista[op -1].reproduce();
			}
			
			System.out.println("Scegli cosa riprodurre");
			op = Integer.parseInt(in.nextLine());

		}

		in.close();

	}

}

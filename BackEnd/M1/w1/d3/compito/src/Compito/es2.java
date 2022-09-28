package Compito;

import java.util.Scanner;

public class es2 {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);

		System.out.println("Inserisci un intero");
		int n = Integer.parseInt(in.nextLine());

		switch (n) {
		case 0:
			System.out.println("zero");
			break;
		case 1:
			System.out.println("uno");
			break;
		case 2:
			System.out.println("due");
			break;
		case 3:
			System.out.println("tre");
			break;
		default:
			System.out.println("Errore");
		}

		in.close();

	}

}

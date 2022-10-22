package Compito;

import java.util.Scanner;

public class Es3 {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);

		System.out.println("Inserisci una stringa");
		String s = in.nextLine();

		while (!(s.equals("q"))) {

			char[] c = s.toCharArray();

			for (int i = 0; i < c.length; i++) {

				System.out.print(c[i] + ",");

			}

			System.out.println("Inserisci una stringa");
			s = in.nextLine();
		}

		in.close();

	}

}

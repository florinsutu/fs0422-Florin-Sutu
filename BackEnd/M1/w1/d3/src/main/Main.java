package main;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);

		System.out.println("Quanti elementi vuoi nel'array?");
		int n = Integer.parseInt(in.nextLine());

		int[] arr = new int[n];

		for (int i = 0; i < n; i++) {
			System.out.println("inserisci elemento " + (i + 1));
			arr[i] = Integer.parseInt(in.nextLine());
		}

		System.out.println(Arrays.toString(arr));

		System.out.println("Effettua un'operazione");

		int o = Integer.parseInt(in.nextLine());

		while (o != 5) {

			op(o);
			
			System.out.println("Effettua un'operazione");
			 o = Integer.parseInt(in.nextLine());
		}

		in.close();
	}

	
	public static void op(int o) {
		
		switch (o) {

		case 1:
			System.out.println("push");
			break;
		case 2:
			System.out.println("shift");
			break;
		case 3:
			System.out.println("pop");
			break;
		case 4:
			System.out.println("unshift");
			break;
		case 5:
			return;
		}
		
	}

}

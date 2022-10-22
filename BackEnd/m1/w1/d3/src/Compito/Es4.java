package Compito;

import java.util.Scanner;

public class Es4 {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);

		System.out.println("Dammi un intero");

		int n = Integer.parseInt(in.nextLine());
		
		for(int i = n; i>=0 ; i--) {
			System.out.println(i);
		}
		in.close();
	}

}

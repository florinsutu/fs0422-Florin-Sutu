package it.epicode.be;

import java.util.Scanner;

public class Es3 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		System.out.println("come ti chiami");
		String name = in.nextLine();
		
		System.out.println("Dove sei nato?");
		String born = in.nextLine();
		
		System.out.println("Quanti anni hai?");
		String age = in.nextLine();
		
		System.out.printf("Nome: %s | Luogo di nascita: %s | Età: %s %n", name, born, age);
		
		System.out.printf("Nome: %s | Luogo di nascita: %s | Età: %s", age,born,name);
		
	
		in.close();
	}

}

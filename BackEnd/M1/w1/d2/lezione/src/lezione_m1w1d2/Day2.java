package lezione_m1w1d2;

public class Day2 {

	public static void main(String[] args) {

		// creazione di istanza

		Student s1 = new Student("mario","rossi",25,new byte[]{8,9,6,9});
		s1.name = "mario";
		s1.lastname = "rossi";
		s1.age = 25;
		s1.id = 1;
		s1.votes = new byte[] { 8, 9, 7, 10 };

		s1.sayHello();

		System.out.println(java.util.Arrays.toString(s1.votes));

	}

}

//classe

class Student {

	// attributi

	String name;
	String lastname;
	int age;
	int id;
	byte[] votes;
	
	// costruttori
	
	Student(String name, String lastname, int age, byte[] votes){
		
		if(checkStringLength(name, 2)) {
			this.name = name;
		}else System.out.println("error");
		
		if(checkStringLength(lastname, 2)) {
			this.lastname = lastname;
		}else System.out.println("error");
		
		
		this.age = age;
		this.votes = votes;
	}
	
	// metodi interni
	
	boolean checkStringLength(String s, int l) {
		return (s.length()>= l) ? true : false;
	}

	// metodi

	void sayHello() {
		System.out.println("ciao, mi chiamo " + this.name + " " + this.lastname + "e i miei voti sono: ");
		System.out.printf("ciao, mi chiamo %s %s e i miei voti sono: %n", this.name, this.lastname);
		for (int i = 0; i < this.votes.length; i++) {
			System.out.println("voto" + (i + 1) + ": " + this.votes[i]);
		}
	}

}

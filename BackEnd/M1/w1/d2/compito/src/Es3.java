
public class Es3 {

	public static void main(String[] args) {

		Article one = new Article(5, "carota", 3, 200);

		Client user1 = new Client(1, "mario", "rossi", "fs@mail.com", "12/09/22");

		Carrello carrello = new Carrello(user1,new Article[] {one}, 50);
		
		System.out.println(carrello.total);

	}

}

class Article {
	int codArticle;
	String description;
	int price;
	int units;

	Article(int cod, String description, int price, int units) {
		this.codArticle = cod;
		this.description = description;
		this.price = price;
		this.units = units;
	}

}

class Client {
	int codClient;
	String name;
	String lastname;
	String email;
	String data;

	Client(int cod, String name, String lastname, String email, String data) {
		this.codClient = cod;
		this.name = name;
		this.lastname = lastname;
		this.email = email;
		this.data = data;
	}

}

class Carrello {
	Client currentClient;
	Article[] articleList;
	int total;

	Carrello(Client client, Article[] list, int total) {
		this.currentClient = client;
		this.articleList = list;
		this.total = total;
	}

}
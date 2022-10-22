
public class Es3 {
 
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
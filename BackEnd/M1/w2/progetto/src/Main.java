import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Classes.Articolo;
import Classes.Libro;
import Classes.Rivista;
import Enums.Periodicita;

public class Main {

	public static final Logger logger = LoggerFactory.getLogger(Main.class);
	public static File catalogoFile = new File("docs/catalogo.txt");

	public static List<Articolo> catalogo = new ArrayList<>();

	public static void main(String[] args) {
		
		//Li ho messi fuori dal try/catch perchè sicuro della validità dei dati in questo caso

		Libro libro1 = new Libro(100001, "L'isola del tesoro", 1881, 300, "Stevenson", "avventura");
		Libro libro2 = new Libro(100002, "1984", 1949, 300, "Orwell", "distopico");
		Libro libro3 = new Libro(100003, "Al faro", 1987, 300, "Woolf", "narrativa");
		Libro libro4 = new Libro(100004, "David Copperfield", 1850, 300, "Dickens", "romanzo");
		
		aggiungiAlCatalogo(libro1);
		aggiungiAlCatalogo(libro2);
		aggiungiAlCatalogo(libro3);
		aggiungiAlCatalogo(libro4);
		
		Rivista rivista1 = new Rivista(200001, "rivista1", 2020, 30, Periodicita.MENSILE);
		Rivista rivista2 = new Rivista(200002, "rivista2", 2020, 30, Periodicita.SEMESTRALE);

		aggiungiAlCatalogo(rivista1);
		aggiungiAlCatalogo(rivista2);
		
		print("=======================================");

		//Prova della gestione delle varie exceptions

		
		try {
			Libro eccezione = new Libro(100001, "L'isola del tesoro", 1881, 300, "Stevenson", "avventura");
			aggiungiAlCatalogo(eccezione);
		} catch (RuntimeException e) {
			print(e);
		}
		
		try {
			Libro eccezione = new Libro(240001, "L'isola del tesoro", 1, 300, "Stevenson", "avventura");
			aggiungiAlCatalogo(eccezione);
		} catch (RuntimeException e) {
			print(e);
		}
		
		try {
			Libro eccezione = new Libro(240001, "L'isola del tesoro", 1881, 300, "S", "avventura");
			aggiungiAlCatalogo(eccezione);
		} catch (RuntimeException e) {
			print(e);
		}
		
		try {
			Libro eccezione = new Libro(240041, "L", 1881, 300, "Stevenson", "avventura");
			aggiungiAlCatalogo(eccezione);
		} catch (RuntimeException e) {
			print(e);
		}
		
		print(catalogo);


		print("=======================================");
		
		// Testing delle operazioni

		print("ricerca per autore: " + ricercaAutore("Stevenson"));

		print("ricerca per ISBN: " + ricercaISBN(100003));
		
		ricercaISBN(122345);

		print("ricerca per anno: " + ricercaAnno(2020));

		print("ricerca per anno: " + ricercaAnno(2010));
		
		rimuoviDalCatalogo(200002);
		
		print(catalogo);
			
		print("=======================================");
		
		// Lettura e scrittura del File

		salvaCatalogo();
		
		leggiCatalogo();
		
		
	}

	// --------------------------------------------------Metodi---------------------------------------------------------

	public static void print(Object a) {
		System.out.println(a);
	}

	public static void aggiungiAlCatalogo(Articolo articolo) {
		catalogo.add(articolo);
	}

	public static void rimuoviDalCatalogo(int ISBN) {
		catalogo = catalogo.stream().filter(n -> n.getISBN() != ISBN).collect(Collectors.toList());
	}

	public static Articolo ricercaISBN(int ISBN) {
		try {
			return catalogo.stream().filter(n -> n.getISBN() == (ISBN)).collect(Collectors.toList()).get(0);
		} catch (RuntimeException e) {
			print("Non c'è nessun risultato alla ricerca con questo ISBN");
		}
		return null;

	}

	public static List<Articolo> ricercaAnno(int anno) {

			return catalogo.stream().filter(n -> n.getAnno() == (anno)).collect(Collectors.toList());
	}

	public static List<Articolo> ricercaAutore(String autore) {
		
			return catalogo.stream().filter(n -> (n.getClass().toString()).equals("class Classes.Libro"))
					.filter(n -> ((Libro) n).getAutore().equals(autore)).collect(Collectors.toList());
	}

	// ------------------- Scrittura/Lettura File ---------------------

	public static void salvaCatalogo() {

		String catalogo = Main.catalogo.toString();

		try {
			FileUtils.writeStringToFile(catalogoFile, catalogo, "UTF-8");
		} catch (IOException e) {
			print("Errore durante la scrittura del file");
		}

	}

	public static void leggiCatalogo() {

		String readFileToString = "";
		try {
			readFileToString = FileUtils.readFileToString(catalogoFile, "UTF-8");
		} catch (IOException e) {
			print("Errore durante la lettura del file");
		}

		System.out.println("Catalogo: " + readFileToString);

	}

}

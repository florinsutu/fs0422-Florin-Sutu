package Classes;

import java.util.ArrayList;
import java.util.List;

import Exceptions.ISBNException;
import Exceptions.InvalidDateException;
import Exceptions.InvalidFormatException;
import Exceptions.InvalidNameException;

public abstract class Articolo {

	private static List<Integer> ISBNs = new ArrayList<>();

	private int ISBN;
	private String titolo;
	private int anno;
	private int pagine;

	public Articolo(int ISBN, String titolo, int anno, int pagine) {

		this.setISBN(ISBN);
		this.setTitolo(titolo);
		this.setAnno(anno);
		this.setPagine(pagine);
		
	}

	public int getISBN() {
		return ISBN;
	}
	
	public static List<Integer> getISBNs(){
		return ISBNs;
	}
	public String getTitolo() {
		return titolo;
	}

	public int getAnno() {
		return anno;
	}

	public int getPagine() {
		return pagine;
	}

	// I Setter sono protetti per non poter cambiare le proprietà all'esterno, è un
	// po come usare final, ma per poter avere un costruttore più snello ho dovuto
	// fare così

	protected void setISBN(int ISBN) {

		if (!ISBNs.contains(ISBN)) {
			if (ISBN > 100000 && ISBN < 999999) {
				this.ISBN = ISBN;
				ISBNs.add(ISBN);
			} else {
				throw new ISBNException("ISBN non valido");
			}
		} else {
			throw new ISBNException("ISBN già esistente");
		}

	}

	protected void setTitolo(String titolo) {

		if (titolo.length() >= 2) {
			this.titolo = titolo;
		} else {
			ISBNs.remove(new Integer(ISBN));
			throw new InvalidNameException("Il titolo non è valido");
		}

	}

	protected void setAnno(int anno) {

		if (anno < 2023 && anno > 1455) { // la Bibbia di Johann Gutenberg, primo libro stampato con una pressa moderna
			this.anno = anno;
		} else {
			ISBNs.remove(new Integer(ISBN));
			throw new InvalidDateException("La data inserita non è valida");
		}
	}

	protected void setPagine(int pagine) {

		if (pagine > 10) {
			this.pagine = pagine;
		} else {
			ISBNs.remove(new Integer(ISBN));
			throw new InvalidFormatException("Il numero di pagine non è sufficiente");
		}

	}

	@Override
	public String toString() {

		return "ISBN: " + getISBN() + ", Titolo: " + getTitolo();
	}

}

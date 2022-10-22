package Classes;

import Exceptions.InvalidNameException;

public class Libro extends Articolo {

	private String autore;
	private String genere;

	public Libro(int ISBN, String titolo, int anno, int pagine, String autore, String genere) {
		super(ISBN, titolo, anno, pagine);
		this.setAutore(autore);
		this.setGenere(genere);

	}

	public String getAutore() {
		return autore;
	}

	private void setAutore(String autore) {
		if (autore.length() >= 2) {
			this.autore = autore;
		} else {
			throw new InvalidNameException("Il nome non è valido");
		}

	}

	public String getGenere() {
		return genere;
	}

	private void setGenere(String genere) {
		this.genere = genere;
	}
	

	@Override
	public String toString() {
		return super.toString()+", Autore: "+ getAutore();
	}
		
	

}

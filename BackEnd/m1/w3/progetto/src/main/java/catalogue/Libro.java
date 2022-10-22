package catalogue;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import enums.Genere;
import exceptions.InvalidNameException;

@Entity
@DiscriminatorValue("Libro")
public class Libro extends Articolo {

	private String autore;
	
	@Enumerated(EnumType.STRING)
	private Genere genere;

	public Libro() {}
	
	public Libro( String titolo, int anno, int pagine, String autore, Genere genere) {
		super( titolo, anno, pagine);
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

	public Genere getGenere() {
		return genere;
	}

	private void setGenere(Genere genere2) {
		this.genere = genere2;
	}
	

	@Override
	public String toString() {
		return super.toString()+", Autore: "+ getAutore();
	}
		
	

}

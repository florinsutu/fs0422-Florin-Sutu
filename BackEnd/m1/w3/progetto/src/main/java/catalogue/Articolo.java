package catalogue;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import exceptions.InvalidDateException;
import exceptions.InvalidFormatException;
import exceptions.InvalidNameException;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="Tipologia", discriminatorType = DiscriminatorType.STRING)
public abstract class Articolo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ISBN;

	private String titolo;
	private int anno;
	private int pagine;

	public Articolo() {};

	public Articolo(String titolo, int anno, int pagine) {
		this.setTitolo(titolo);
		this.setAnno(anno);
		this.setPagine(pagine);

	}

	public int getISBN() {
		return ISBN;
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

	protected void setTitolo(String titolo) {

		if (titolo.length() >= 2) {
			this.titolo = titolo;
		} else {
			throw new InvalidNameException("Il titolo non è valido");
		}

	}

	protected void setAnno(int anno) {

		if (anno < 2023 && anno > 1455) { // la Bibbia di Johann Gutenberg, primo libro stampato con una pressa moderna
			this.anno = anno;
		} else {
			throw new InvalidDateException("La data inserita non è valida");
		}
	}

	protected void setPagine(int pagine) {

		if (pagine > 10) {
			this.pagine = pagine;
		} else {
			throw new InvalidFormatException("Il numero di pagine non è sufficiente");
		}

	}

	@Override
	public String toString() {

		return "ISBN: " + getISBN() + ", Titolo: " + getTitolo();
	}

}

package managment;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import catalogue.Articolo;

@Entity
public class Prestito {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToOne
	@JoinColumn(name = "id_articolo")
	private Articolo articolo;
	@OneToOne
	@JoinColumn(name = "id_utente")
	private Utente utente;
	private LocalDate prestato = LocalDate.now();
	private LocalDate scadenza = LocalDate.now().plusDays(30);
	private LocalDate restituito;

	public Prestito() {
	}

	public Prestito(Articolo articolo, Utente utente) {
		this.articolo = articolo;
		this.utente = utente;
	}

	public Articolo getArticolo() {
		return articolo;
	}

	public void setArticolo(Articolo articolo) {
		this.articolo = articolo;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public LocalDate getPrestato() {
		return prestato;
	}

	public void setPrestato(LocalDate prestato) {
		this.prestato = prestato;
	}

	public LocalDate getScadenza() {
		return scadenza;
	}

	public void setScadenza(LocalDate scadenza) {
		this.scadenza = scadenza;
	}

	public LocalDate getRestituito() {
		return restituito;
	}

	public void setRestituito(LocalDate restituito) {
		this.restituito = restituito;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Codice Prestito: " + getId() + ", Utente: " + getUtente().getId() + ", ISBN articolo: "
				+ getArticolo().getISBN() + ", Prestato il: " + getPrestato();
	}

}

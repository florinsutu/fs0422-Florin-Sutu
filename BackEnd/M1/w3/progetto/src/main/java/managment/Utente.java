package managment;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Utente {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id ;
	
	private String nome;
	private String cognome;
	private int tessera;
	private String data_di_nascita;
	
	public Utente() {}
	
	public Utente(String nome, String cognome, int tessera, String data_di_nascita) {
		this.nome = nome;
		this.cognome = cognome;
		this.tessera = tessera;
		this.data_di_nascita = data_di_nascita;
	}

	
	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	public int getTessera() {
		return tessera;
	}

	public String getData_di_nascita() {
		return data_di_nascita;
	}

	public int getId() {
		return id;
	}
	

}

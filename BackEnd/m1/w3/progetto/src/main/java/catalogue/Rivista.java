package catalogue;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import enums.Periodicita;
import exceptions.ISBNException;

@Entity
@DiscriminatorValue("Rivista")
public class Rivista extends Articolo {
	
	@Enumerated(EnumType.STRING)
	private Periodicita periodicita;
	
	public Rivista() {}

	public Rivista( String titolo, int anno, int pagine, Periodicita periodicita) throws ISBNException {
		super( titolo, anno, pagine);
		this.setPeriodicita(periodicita);
	}

	public Periodicita getPeriodicita() {
		return periodicita;
	}

	private void setPeriodicita(Periodicita periodicita) {
		this.periodicita = periodicita;
	}
	
	@Override
	public String toString() {
		return super.toString()+", Periodicità: "+ getPeriodicita();
	}

}

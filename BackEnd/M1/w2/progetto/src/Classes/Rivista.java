package Classes;

import Enums.Periodicita;import Exceptions.ISBNException;

public class Rivista extends Articolo {
	
	private Periodicita periodicita;

	public Rivista(int ISBN, String titolo, int anno, int pagine, Periodicita periodicita) throws ISBNException {
		super(ISBN, titolo, anno, pagine);
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

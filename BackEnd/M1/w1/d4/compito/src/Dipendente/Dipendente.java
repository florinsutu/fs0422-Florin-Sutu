package Dipendente;


public class Dipendente {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	private static double stipendioBase = 1000.0;
	private int matricola;
	private double stipendio;
	private double importoOrarioStraordinario;
	private role livello;
	private department dipartimento;
	
	public Dipendente(int matricola, department dipartimento) {
		this.matricola = matricola;
		this.dipartimento = dipartimento;
		this.stipendio = Dipendente.stipendioBase;
		this.setImportoOrarioStraordinario(30);
		this.setDipartimento(dipartimento);
	}
	
	public Dipendente() {
		
	}
	public static double getStipendioBase() {
		return stipendioBase;
	}

	public int getMatricola() {
		return matricola;
	}

	public double getStipendio() {
		return stipendio;
	}

	public role getLivello() {
		return livello;
	}


	public department getDipartimento() {
		return dipartimento;
	}

	public void setDipartimento(department dipartimento) {
		this.dipartimento = dipartimento;
	}

	public double getImportoOrarioStraordinario() {
		return importoOrarioStraordinario;
	}

	public void setImportoOrarioStraordinario(double importoOrarioStraordinario) {
		this.importoOrarioStraordinario = importoOrarioStraordinario;
	}

	@Override
	public String toString() {
		return "ciao";  //dipendente + cose etc.
	}
	
	public void stampaDatiDipendente(Dipendente dipendente) {
		System.err.println(dipendente);
	}
	
	
	public role promuovi() {
		
		
		switch(this.livello) {
		case OPERAIO:
			this.livello = role.IMPIEGATO;
			this.stipendio = Dipendente.stipendioBase*1.2;
			return this.livello;
		default:
			return this.livello;
			
			
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

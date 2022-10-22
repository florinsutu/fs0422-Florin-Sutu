
public class Es2 {

	public static void main(String[] args) {

		Sim s = new Sim("324999888");
		print(s);
	}
	
	public static void print(Sim s) {
		System.out.println("Sim: ");
	}

}

class Call{
	
	private String number;
	private double time;
	
	public Call(String number, double time) {
		this.number = number;
		this.time = time;
	}
}

class Sim {

	private String number;
	private double credit;
	private Call[] list;
	

	Sim(String number) {
		this.number = number;
		this.credit = 0;
		this.list = new Call[5];
	}
	
	public void stampa(){
		
	}
	
	public double getCredit() {
		return this.credit;
	}
	
	public String getNumber() {
		return this.number;
	}
	
	public Call[] getCall() {
		return this.list;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}

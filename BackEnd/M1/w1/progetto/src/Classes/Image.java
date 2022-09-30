package Classes;

import Enumerators.MediaType;
import Interfaces.Display;

public class Image extends Media implements Display {

	private int brightness = 5;  //luminosità di default

	public Image(String title) { // uso il costruttore privato per settare in automatico il tipo IMG se istanzio
									// un file Image, so che si può fare in modo molto più smeplice, ma volevo usare
									// questo utilizzo visto a lezione
		this(title, MediaType.IMG);
	}

	private Image(String title, MediaType extension) {
		super(title, extension);
	}

	@Override
	public void reproduce() {
		this.show(this.brightness);
	}

	private void show(int brightness) {

		String l = " ";
		for (int i = 0; i < brightness; i++) {
			l += "*";
		}

		System.out.println(this.title + '.' + this.extension.getExt() + l); //anche questo completamente più complicato di quel che serviva fare (mi riferisco all'assegnare l'estensione), però è potenzialmente modulare, inoltre se decido di cambiare il tipo di estensione non devo fare ricerche nel codice come invece avrei dovuto se avessi scelto di mettere la semplice stringa img
	}

	@Override
	public void increaseBrightness() {
		this.brightness++;
	}

	@Override
	public void decreaseBrightness() {
		this.brightness--;
	}

	@Override
	public void setBrightness(int n) {
		if (n > 0 && n <= 10)
			this.brightness = n;
	}

	@Override
	public int getBrightness() {
		return this.brightness;
	}

}

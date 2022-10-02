package Classes;

import Enumerators.MediaType;
import Interfaces.Display; //in questo caso potevo usare* ma ho preferito far vedere cosa esattamente importo
import Interfaces.Sound;

public class Video extends Media implements Display, Sound {

	private int length;
	private int brightness = 5;
	private int volume = 5;

	public Video(String title, int length) { // per essere istanziato, Video ha bisogno solo del titolo, in automatico
												// viene assegnato il tipo VID
		super(title, MediaType.VID);
		this.length = length;
	}

	@Override
	public void reproduce() {
		this.play(this.volume, this.length, this.brightness);
	}

	protected void play(int vol, int len, int bright) {

		String v = " ";
		for (int i = 0; i < vol; i++) {
			v += "!";
		}
		String b = " ";
		for (int i = 0; i < vol; i++) {
			b += "*";
		}

		for (int j = 0; j < len; j++) {
			System.out.println(this.title + v + b);
		}

	}

	@Override
	public void increaseBrightness() {
		if (this.brightness < 10) {
			this.brightness++;
		} else {
			System.out.println("La luminosità è già al massimo");
		}
	}

	@Override
	public void decreaseBrightness() {
		if (this.brightness > 1) {
			this.brightness--;
		} else {
			System.out.println("La luminosità è già al minimo");
		}
	}

	@Override
	public void increaseVolume() {
		if(this.volume < 10) {
			this.volume++;
		}else {
			System.out.println("Il volume è già al massimo");
		}
	}

	@Override
	public void decreaseVolume() {
		if(this.volume > 1) {
			this.volume--;
		}else {
			System.out.println("Il volume è già al minimo");
		}
	}

	@Override
	public void setVolume(int vol) {
		if (vol > 0 && vol <= 10)
			this.volume = vol;
	}

	@Override
	public int getVolume() {
		return volume;
	}

	@Override
	public void setBrightness(int brigth) {
		if (brigth > 0 && brigth <= 10)
			this.brightness = brigth;

	}

	@Override
	public int getBrightness() {
		return this.brightness;
	}

}

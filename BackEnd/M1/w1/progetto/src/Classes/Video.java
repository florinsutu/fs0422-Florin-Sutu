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
			System.out.println(this.title + '.' + this.extension.getExt() + v + b);
		}

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
	public void increaseVolume() {
		this.volume++;
	}

	@Override
	public void decreaseVolume() {
		this.volume--;
	}

	@Override
	public void setVolume(int vol) {
		this.volume = vol;
	}

	@Override
	public int getVolume() {
		return volume;
	}

	@Override
	public void setBrightness(int brigth) {
		this.brightness = brigth;

	}

	@Override
	public int getBrightness() {
		return this.brightness;
	}

}

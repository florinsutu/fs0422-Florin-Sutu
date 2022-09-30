package Classes;

import Enumerators.MediaType;
import Interfaces.Sound;

public class Audio extends Media implements Sound {

	private int length;  //non ho dato getter o setter perchè non ho necessità di richiamare questo valore all'esterno
	private int volume = 5; // default

	public Audio(String title, int length) {
		this(title, MediaType.AUD);
		this.length = length;
	}

	protected Audio(String title, MediaType extension) {
		super(title, extension);
	}

	@Override
	public void reproduce() {
		this.play(this.volume, this.length);

	}

	protected void play(int vol, int len) {

		String l = " ";
		for (int i = 0; i < vol; i++) {
			l += "!";
		}

		for (int j = 0; j < len; j++) {
			System.out.println(this.title + '.' + this.extension.getExt() + l);
		}

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
		return this.volume;
	}


}

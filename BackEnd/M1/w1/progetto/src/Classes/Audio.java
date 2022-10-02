package Classes;

import Enumerators.MediaType;
import Interfaces.Sound;

public class Audio extends Media implements Sound {

	private int length; // non ho dato getter o setter perchè non ho necessità di richiamare questo
						// valore all'esterno
	private int volume = 5;

	public Audio(String title, int length) {
		super(title, MediaType.AUD);
		this.length = length;
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
			System.out.println(this.title + l);
		}

	}

	@Override
	public void increaseVolume() {
		if (this.volume < 10) {
			this.volume++;
		} else {
			System.out.println("Il volume è già al massimo");
		}
	}

	@Override
	public void decreaseVolume() {
		if (this.volume > 1) {
			this.volume--;
		} else {
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
		return this.volume;
	}

}

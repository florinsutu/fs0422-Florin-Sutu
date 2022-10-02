package Classes;

import Enumerators.MediaType;
import Interfaces.Display;

public class Image extends Media implements Display {

	private int brightness = 5;

	public Image(String title) {
		super(title, MediaType.IMG);
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

		System.out.println(this.title + l);
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
	public void setBrightness(int brigth) {
		if (brigth > 0 && brigth <= 10)
			this.brightness = brigth;
	}

	@Override
	public int getBrightness() {
		return this.brightness;
	}

}

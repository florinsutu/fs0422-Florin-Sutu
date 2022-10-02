package Classes;

import Enumerators.MediaType;

public abstract class Media {

	protected String title;
	protected MediaType extension;

	public Media(String title, MediaType extension) { 

		this.extension = extension;
		setTitle(title);

	}

	public abstract void reproduce();

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title + "." + extension.getExt(); // il nome di qualunque media istanziato avrà estensione consona
		// al tipo di file
	}

	@Override
	public String toString() {
		return getTitle();
	}

}

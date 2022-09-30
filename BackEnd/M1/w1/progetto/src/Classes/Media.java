package Classes;

import Enumerators.MediaType;

public abstract class Media {

	
	protected String title;
	protected MediaType extension;
	
	public Media(String title, MediaType extension) {
		this.title = title;
		this.extension = extension;
	}
	
	public abstract void reproduce();

	
	
}

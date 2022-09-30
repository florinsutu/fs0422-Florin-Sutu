package Enumerators;

public enum MediaType {  //TODO magari provare ad implementare e modificare il nome del file aggiungendo l'estensione corrispondente
	IMG("img"),
	AUD("aud"),
	VID("vid");
	
	private String file_extension;

	MediaType(String string){
		file_extension = string;
	}
	
	public String getExt() {
		return file_extension;
	}
}

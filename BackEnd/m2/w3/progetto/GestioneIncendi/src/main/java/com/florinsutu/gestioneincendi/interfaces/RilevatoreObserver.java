package com.florinsutu.gestioneincendi.interfaces;

import com.florinsutu.gestioneincendi.models.Sonda;

public interface RilevatoreObserver {

	//method to update the observer, used by subject
	void update(String latitudine, String longitudine, int fumo);
	
	//attach with subject to observe
	public void addSonda(Sonda s);
	
}

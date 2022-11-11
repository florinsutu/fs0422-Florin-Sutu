package com.florinsutu.gestioneincendi.interfaces;

import com.florinsutu.gestioneincendi.models.Rilevatore;

public interface CentroDiControlloObserver {

	//method to update the observer, used by subject
	void update(String latitudine, String longitudine, int fumo);
		
		//attach with subject to observe
		public void addRilevatore(Rilevatore r);


}

package com.florinsutu.gestioneincendi.interfaces;

import com.florinsutu.gestioneincendi.models.CentroDiControllo;

public interface RilevatoreSubject {

	//methods to register and unregister observers
			public void register(CentroDiControllo r);
			public void unregister();
			
			//method to notify observers of change
			void notifyObservers(String latitudine, String longitudine, int fumo);
			
			//method to get updates from subject
			public Object getUpdate(CentroDiControllo r);

}

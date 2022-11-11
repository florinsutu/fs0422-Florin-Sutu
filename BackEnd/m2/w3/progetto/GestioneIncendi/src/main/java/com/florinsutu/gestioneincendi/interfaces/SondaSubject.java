package com.florinsutu.gestioneincendi.interfaces;

import com.florinsutu.gestioneincendi.models.Rilevatore;

public interface SondaSubject {
	
	//methods to register and unregister observers
		public void register(Rilevatore r);
		public void unregister();
		
		//method to notify observers of change
		public void notifyObservers();
		
		//method to get updates from subject
		public Object getUpdate(Rilevatore r);

}

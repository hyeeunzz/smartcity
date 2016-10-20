package org.gs1.smartcity.capturing.masterdata;

import org.gs1.smartcity.capturing.services.ServiceFactory;
import org.gs1.smartcity.capturing.services.bus.BusMasterDataManager;

public class MasterDataManagerFactory {
	
	public MasterDataManager getManager(String type) {
		
		MasterDataManager manager = null;
		
		if(type.equals(ServiceFactory.BUS)) {
			
			manager = new BusMasterDataManager();
		}
		
		return manager;
	}

}

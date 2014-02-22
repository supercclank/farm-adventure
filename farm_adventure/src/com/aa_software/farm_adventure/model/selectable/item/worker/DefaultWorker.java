package com.aa_software.farm_adventure.model.selectable.item.worker;

public class DefaultWorker extends AbstractWorker {
	
	public static final String WORKER_NAME = "Default Worker";
	public static int DEFAULT_COST = 30;
	public static int DEFAULT_VALUE = 15;
	
	public DefaultWorker() {
		this.cost = DEFAULT_COST;
		this.value = DEFAULT_VALUE;
		this.name = WORKER_NAME;
	}

}

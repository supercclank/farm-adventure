package com.aa_software.farm_adventure.model.item.tool;

import java.util.ArrayList;

import com.aa_software.farm_adventure.model.Inventory;
import com.aa_software.farm_adventure.model.item.AbstractItem;
import com.aa_software.farm_adventure.model.item.upgrade.AbstractUpgrade;
import com.aa_software.farm_adventure.model.plot.Plot;

public abstract class AbstractTool extends AbstractItem {

	protected float workTime;
	protected int workerIndex;
	protected ArrayList <AbstractUpgrade> upgrades = new ArrayList <AbstractUpgrade>();

	public float getWorkTime() {
		return workTime;
	}

	public void setWorkTime(float workTime) {
		this.workTime = workTime;
	}
	
	@Override
	public void update(Plot plot, Inventory inventory) {}

	public void setWorkerIndex(int selectedWorker) {
		this.workerIndex = selectedWorker;
	}
	
	public ArrayList <AbstractUpgrade> getUpgrades(){
		return this.upgrades;
	}
	
	public AbstractUpgrade peekUpgrade(){
		if (this.upgrades.size()>0){
			return this.upgrades.get(this.upgrades.size()-1);
		} else {
			return null;
		}
	}
	
	public AbstractUpgrade dequeueUpgrade(){
		if (this.upgrades.size()>0){
			AbstractUpgrade tempUpgrade = this.upgrades.get(this.upgrades.size()-1);
			this.upgrades.remove(this.upgrades.size()-1);
			return tempUpgrade;
		} else {
			return null;
		}
	}
}

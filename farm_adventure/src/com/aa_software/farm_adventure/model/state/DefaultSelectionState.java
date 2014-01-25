package com.aa_software.farm_adventure.model.state;

import com.aa_software.farm_adventure.model.item.spell.AbstractSpell;
import com.aa_software.farm_adventure.model.item.tool.AbstractTool;
import com.aa_software.farm_adventure.model.item.upgrade.AbstractUpgrade;
import com.aa_software.farm_adventure.model.item.worker.AbstractWorker;
import com.aa_software.farm_adventure.model.plot.AbstractPlot;

public class DefaultSelectionState extends AbstractSelectionState  {
	public DefaultSelectionState() {}
	
	public DefaultSelectionState update() {
		return new DefaultSelectionState();
	}
	public DefaultSelectionState update(AbstractPlot plot) {
		return new DefaultSelectionState();
	}
	public SpellSelectionState update(AbstractSpell spell) {
		return new SpellSelectionState(spell);
	}
	public UpgradeSelectionState update(AbstractUpgrade upgrade) {
		return new UpgradeSelectionState(upgrade);
	}
	public ToolSelectionState update(AbstractTool tool) {
		return new ToolSelectionState(tool);
	}
	public DefaultSelectionState update(AbstractWorker worker) {
		return new DefaultSelectionState();
	}
}

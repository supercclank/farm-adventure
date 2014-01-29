package com.aa_software.farm_adventure.presenter.state;

import com.aa_software.farm_adventure.model.item.crop.AbstractCrop;
import com.aa_software.farm_adventure.model.item.spell.AbstractSpell;
import com.aa_software.farm_adventure.model.item.tool.AbstractTool;
import com.aa_software.farm_adventure.model.item.upgrade.AbstractUpgrade;
import com.aa_software.farm_adventure.model.item.worker.AbstractWorker;
import com.aa_software.farm_adventure.model.plot.Plot;

public class ToolSelectionState extends AbstractSelectionState  {
	
	public ToolSelectionState(AbstractTool selection) {
		this.selection = selection;
	}
	public ToolSelectionState update(Plot plot) {
		return new ToolSelectionState((AbstractTool)selection);
	}
	public SpellSelectionState update(AbstractSpell spell) {
		return new SpellSelectionState((AbstractSpell)spell);
	}
	public UpgradeSelectionState update(AbstractUpgrade upgrade) {
		return new UpgradeSelectionState((AbstractUpgrade)upgrade);
	}
	public ToolSelectionState update(AbstractTool tool) {
		return new ToolSelectionState((AbstractTool)tool);
	}
	public ToolSelectionState update(AbstractWorker worker) {
		return new ToolSelectionState((AbstractTool)selection);
	}
	public ToolSelectionState update(AbstractCrop crop) {
		return new ToolSelectionState((AbstractTool)selection);
	}
}

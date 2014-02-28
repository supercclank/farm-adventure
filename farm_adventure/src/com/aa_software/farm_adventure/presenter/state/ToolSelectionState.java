package com.aa_software.farm_adventure.presenter.state;

import com.aa_software.farm_adventure.model.Inventory;
import com.aa_software.farm_adventure.model.selectable.item.crop.AbstractCrop;
import com.aa_software.farm_adventure.model.selectable.item.spell.AbstractSpell;
import com.aa_software.farm_adventure.model.selectable.item.tool.AbstractTool;
import com.aa_software.farm_adventure.model.selectable.item.tool.harvest.AbstractHarvestTool;
import com.aa_software.farm_adventure.model.selectable.item.tool.plant.AbstractPlantTool;
import com.aa_software.farm_adventure.model.selectable.item.upgrade.AbstractUpgrade;
import com.aa_software.farm_adventure.model.selectable.item.worker.AbstractWorker;
import com.aa_software.farm_adventure.model.selectable.plot.Plot;

public class ToolSelectionState extends AbstractSelectionState {

	public ToolSelectionState(AbstractTool selection) {
		this.selection = selection;
	}

	@Override
	public ToolSelectionState update(AbstractCrop crop) {
		return new ToolSelectionState((AbstractTool) selection);
	}

	@Override
	public SpellSelectionState update(AbstractSpell spell) {
		return new SpellSelectionState(spell);
	}

	@Override
	public ToolSelectionState update(AbstractTool tool) {
		return new ToolSelectionState(tool);
	}

	@Override
	public UpgradeSelectionState update(AbstractUpgrade upgrade) {
		return new UpgradeSelectionState(upgrade);
	}

	@Override
	public ToolSelectionState update(AbstractWorker worker) {
		return new ToolSelectionState((AbstractTool) selection);
	}

	@Override
	public ToolSelectionState update(Plot plot, Inventory inventory) {
		if ((selection instanceof AbstractHarvestTool)||(selection instanceof AbstractPlantTool)){
			selection.update(plot, inventory);
		} else {
			selection.update(plot);
		}
		return new ToolSelectionState((AbstractTool) selection);
	}
}

package com.aa_software.farm_adventure.presenter.selection_state;

import com.aa_software.farm_adventure.model.Inventory;
import com.aa_software.farm_adventure.model.item.crop.AbstractCrop;
import com.aa_software.farm_adventure.model.item.tool.AbstractTool;
import com.aa_software.farm_adventure.model.item.worker.DefaultWorker;
import com.aa_software.farm_adventure.model.plot.Plot;

public class ToolSelectionState extends AbstractSelectionState {

	public ToolSelectionState(AbstractTool selection) {
		this.selection = selection;
	}

	@Override
	public ToolSelectionState update(AbstractCrop crop) {
		return new ToolSelectionState((AbstractTool) selection);
	}

	/*
	 * @Override public SpellSelectionState update(AbstractSpell spell) { return
	 * new SpellSelectionState(spell); }
	 */
	@Override
	public ToolSelectionState update(AbstractTool tool) {
		return new ToolSelectionState(tool);
	}

	/*
	 * @Override public UpgradeSelectionState update(AbstractUpgrade upgrade) {
	 * return new UpgradeSelectionState(upgrade); }
	 */
	@Override
	public ToolSelectionState update(DefaultWorker worker) {
		return new ToolSelectionState((AbstractTool) selection);
	}

	@Override
	public ToolSelectionState update(Plot plot, Inventory inventory) {
		selection.update(plot, inventory);
		return new ToolSelectionState((AbstractTool) selection);
	}

}

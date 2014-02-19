package com.aa_software.farm_adventure.model;

import com.aa_software.farm_adventure.model.selectable.item.tool.AbstractTool;
import com.aa_software.farm_adventure.model.selectable.item.tool.harvest.ScytheTool;
import com.aa_software.farm_adventure.model.selectable.item.tool.irrigate.ShovelTool;
import com.aa_software.farm_adventure.model.selectable.item.tool.plant.AbstractPlantTool;
import com.aa_software.farm_adventure.model.selectable.item.tool.plant.TrowelTool;
import com.aa_software.farm_adventure.model.selectable.item.tool.plow.HandPlowTool;

public class ToolBar {
	public static final int COLUMNS = 5;
	public static final int ROWS = 1;
	private AbstractTool[][] tools2D;
	private Market market;
	private Inventory inventory;
	public static final int PLANT_TOOL_X = 2;
	public static final int PLANT_TOOL_Y = 0;

	public ToolBar() {
		initializeTools(COLUMNS, ROWS);
	}

	public ToolBar(int columns, int rows) {
		initializeTools(columns, rows);
	}

	public AbstractTool getTool(int x, int y) {
		return tools2D[x][y];
	}

	public final void initializeTools(int columns, int rows) {
		// TODO: hardcoded...also, column 5 should be Market/Inventory
		tools2D = new AbstractTool[columns][rows];
		tools2D[0][0] = new HandPlowTool();
		tools2D[1][0] = new ShovelTool();
		tools2D[2][0] = new TrowelTool();
		tools2D[3][0] = new ScytheTool();
	}

	public void setTool(int x, int y, AbstractTool tool) {
		tools2D[x][y] = tool;
	}
	
	public AbstractPlantTool getPlantTool() {
		return 	(AbstractPlantTool) tools2D[PLANT_TOOL_X][PLANT_TOOL_Y];
	}

}

package com.aa_software.farm_adventure.model;

import com.aa_software.farm_adventure.model.item.tool.AbstractTool;
import com.aa_software.farm_adventure.model.item.tool.harvest.ScytheTool;
import com.aa_software.farm_adventure.model.item.tool.irrigate.ShovelTool;
import com.aa_software.farm_adventure.model.item.tool.plant.AbstractPlantTool;
import com.aa_software.farm_adventure.model.item.tool.plant.TrowelTool;
import com.aa_software.farm_adventure.model.item.tool.plow.AbstractPlowTool;
import com.aa_software.farm_adventure.model.item.tool.plow.HandPlowTool;

public class ToolBar {
	public static final int COLUMNS = 5;
	public static final int ROWS = 1;
	private AbstractTool[][] tools2D;
	public static final int PLANT_TOOL_X = 2;
	public static final int PLANT_TOOL_Y = 0;



	public ToolBar() {
		tools2D = new AbstractTool[COLUMNS][ROWS];
	}

	public ToolBar(Inventory inventory) {
		tools2D = new AbstractTool[COLUMNS][ROWS];
		updateTools(inventory);
	}

	public AbstractPlantTool getPlantTool() {
		return (AbstractPlantTool) tools2D[PLANT_TOOL_X][PLANT_TOOL_Y];
	}

	public AbstractTool getTool(int x, int y) {
		return tools2D[x][y];
	}

	public final void updateTools(Inventory inventory) {
		// TODO: hardcoded...also, column 5 should be Market/Inventory	
		AbstractTool [] tools = {(AbstractTool) inventory.getItems().get("PLOW TOOLS").get(0),
				(AbstractTool) inventory.getItems().get("IRRIGATION TOOLS").get(0),
				(AbstractTool) inventory.getItems().get("PLANT TOOLS").get(0),
				(AbstractTool) inventory.getItems().get("HARVEST TOOLS").get(0)};
		
		for(int i = 0; i<COLUMNS-1; i++){
			if (tools2D[i][0] == null || (tools2D[i][0].compareTo(tools[i])!=0) ){
				tools2D[i][0] = tools[i];
			}			
		}
	}

	public void setTool(int x, int y, AbstractTool tool) {
		tools2D[x][y] = tool;
	}

}

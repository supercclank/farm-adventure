package com.aa_software.farm_adventure.presenter;

import java.util.Arrays;
import java.util.EnumSet;

import com.aa_software.farm_adventure.model.plot.Irrigation;
import com.aa_software.farm_adventure.model.plot.TaskType;

public class TextureHelper {
	
	public static String getIrrigationTextureFileName(EnumSet<Irrigation> irrigation) {
		String textureName = "textures/";
		if(irrigation.containsAll(Arrays.asList(Irrigation.values()))) {
			textureName += "TOP_LEFT_RIGHT_BOTTOM";
		} else if(irrigation.containsAll(Arrays.asList(Irrigation.TOP, 
				Irrigation.BOTTOM, Irrigation.LEFT))) {
			textureName += "TOP_LEFT_BOTTOM";
		} else if(irrigation.containsAll(Arrays.asList(Irrigation.TOP, 
				Irrigation.BOTTOM, Irrigation.RIGHT))) {
			textureName += "TOP_RIGHT_BOTTOM";
		} else if(irrigation.containsAll(Arrays.asList(Irrigation.TOP, 
				Irrigation.RIGHT, Irrigation.LEFT))) {
			textureName += "TOP_LEFT_RIGHT";
		} else if(irrigation.containsAll(Arrays.asList(Irrigation.RIGHT, 
				Irrigation.LEFT, Irrigation.BOTTOM))) {
			textureName += "LEFT_RIGHT_BOTTOM";
		} else if(irrigation.containsAll(Arrays.asList(Irrigation.RIGHT, 
				Irrigation.BOTTOM))) {
			textureName += "RIGHT_BOTTOM";
		} else if(irrigation.containsAll(Arrays.asList(Irrigation.BOTTOM, 
				Irrigation.LEFT))) {
			textureName += "LEFT_BOTTOM";
		} else if(irrigation.containsAll(Arrays.asList(Irrigation.RIGHT, 
				Irrigation.LEFT))) {
			textureName += "LEFT_RIGHT";
		} else if(irrigation.containsAll(Arrays.asList(Irrigation.TOP, 
				Irrigation.LEFT))) {
			textureName += "TOP_LEFT";
		} else if(irrigation.containsAll(Arrays.asList(Irrigation.TOP, 
				Irrigation.BOTTOM))) {
			textureName += "TOP_BOTTOM";
		} else if(irrigation.containsAll(Arrays.asList(Irrigation.TOP, 
				Irrigation.RIGHT))) {
			textureName += "TOP_RIGHT";
		} else if(irrigation.contains(Irrigation.TOP)) {
			textureName += "TOP";
		} else if(irrigation.contains(Irrigation.BOTTOM)) {
			textureName += "BOTTOM";
		} else if(irrigation.contains(Irrigation.LEFT)) {
			textureName += "LEFT";
		} else if(irrigation.contains(Irrigation.RIGHT)) {
			textureName += "RIGHT";
		}
		return textureName.toLowerCase() + ".png";
	}
	
	public static String getIrrigationTextureName(EnumSet<Irrigation> irrigation) {
		String textureName = null;
		if(irrigation.containsAll(Arrays.asList(Irrigation.values()))) {
			textureName = "TOP_LEFT_RIGHT_BOTTOM";
		} else if(irrigation.containsAll(Arrays.asList(Irrigation.TOP, 
				Irrigation.BOTTOM, Irrigation.LEFT))) {
			textureName = "TOP_LEFT_BOTTOM";
		} else if(irrigation.containsAll(Arrays.asList(Irrigation.TOP, 
				Irrigation.BOTTOM, Irrigation.RIGHT))) {
			textureName = "TOP_RIGHT_BOTTOM";
		} else if(irrigation.containsAll(Arrays.asList(Irrigation.TOP, 
				Irrigation.RIGHT, Irrigation.LEFT))) {
			textureName = "TOP_LEFT_RIGHT";
		} else if(irrigation.containsAll(Arrays.asList(Irrigation.RIGHT, 
				Irrigation.LEFT, Irrigation.BOTTOM))) {
			textureName = "LEFT_RIGHT_BOTTOM";
		} else if(irrigation.containsAll(Arrays.asList(Irrigation.RIGHT, 
				Irrigation.BOTTOM))) {
			textureName = "RIGHT_BOTTOM";
		} else if(irrigation.containsAll(Arrays.asList(Irrigation.BOTTOM, 
				Irrigation.LEFT))) {
			textureName = "LEFT_BOTTOM";
		} else if(irrigation.containsAll(Arrays.asList(Irrigation.RIGHT, 
				Irrigation.LEFT))) {
			textureName = "LEFT_RIGHT";
		} else if(irrigation.containsAll(Arrays.asList(Irrigation.TOP, 
				Irrigation.LEFT))) {
			textureName = "TOP_LEFT";
		} else if(irrigation.containsAll(Arrays.asList(Irrigation.TOP, 
				Irrigation.BOTTOM))) {
			textureName = "TOP_BOTTOM";
		} else if(irrigation.containsAll(Arrays.asList(Irrigation.TOP, 
				Irrigation.RIGHT))) {
			textureName = "TOP_RIGHT";
		} else if(irrigation.contains(Irrigation.TOP)) {
			textureName = "TOP";
		} else if(irrigation.contains(Irrigation.BOTTOM)) {
			textureName = "BOTTOM";
		} else if(irrigation.contains(Irrigation.LEFT)) {
			textureName = "LEFT";
		} else if(irrigation.contains(Irrigation.RIGHT)) {
			textureName = "RIGHT";
		}
		return textureName;
	}
	
	public static TaskType getTaskTypeValue(String textureName) {
		TaskType type = null;
		if(textureName.equals("plowedunwatered")) {
			type = TaskType.PLOW_UW;
		} else if(textureName.equals("plowedwatered")) {
			type = TaskType.PLOW_W;
		} else if(textureName.equals("pbananaCrop")) {
			type = TaskType.PBAN;
		} else if(textureName.equals("hbananaCrop")) {
			type = TaskType.HBAN;
		} else if (textureName.equals("pbeetCrop")) {
			type = TaskType.PBEET;
		} else if (textureName.equals("hbeetCrop")) {
			type = TaskType.HBEET;
		} else if (textureName.equals("pcarrotCrop")) {
			type = TaskType.PCAR;
		} else if (textureName.equals("hcarrotCrop")) {
			type = TaskType.HCAR;
		} else if (textureName.equals("priceCrop")) {
			type = TaskType.PRIC;
		} else if (textureName.equals("hriceCrop")) {
			type = TaskType.HRIC;
		} else if (textureName.equals("pbuddingCrop")) {
			type = TaskType.PBUD;
		} else if (textureName.equals("hbuddingCrop")) {
			type = TaskType.HBUD;
		}
		return type;
	}
	
	public static EnumSet<Irrigation> getIrrigationSet(String textureName) {
		EnumSet<Irrigation> irrigation = EnumSet.noneOf(Irrigation.class);
		if(textureName.equals("TOP_LEFT_RIGHT_BOTTOM")) {
			irrigation = EnumSet.allOf(Irrigation.class);
		} else if(textureName.equals("TOP_LEFT_BOTTOM")) {
			irrigation.addAll(Arrays.asList(Irrigation.TOP, 
					Irrigation.BOTTOM, Irrigation.LEFT));
		} else if(textureName.equals("TOP_RIGHT_BOTTOM")) {
			irrigation.addAll(Arrays.asList(Irrigation.TOP, 
					Irrigation.BOTTOM, Irrigation.RIGHT));
		} else if(textureName.equals("TOP_LEFT_RIGHT")) {
			irrigation.addAll(Arrays.asList(Irrigation.TOP, 
					Irrigation.RIGHT, Irrigation.LEFT));
		} else if(textureName.equals("LEFT_RIGHT_BOTTOM")) {
			irrigation.addAll(Arrays.asList(Irrigation.RIGHT, 
					Irrigation.LEFT, Irrigation.BOTTOM));
		} else if(textureName.equals("RIGHT_BOTTOM")) {
			irrigation.addAll(Arrays.asList(Irrigation.RIGHT, 
					Irrigation.BOTTOM));
		} else if(textureName.equals("LEFT_BOTTOM")) {
			irrigation.addAll(Arrays.asList(Irrigation.BOTTOM, 
					Irrigation.LEFT));
		} else if(textureName.equals("LEFT_RIGHT")) {
			irrigation.addAll(Arrays.asList(Irrigation.RIGHT, 
					Irrigation.LEFT));
		} else if(textureName.equals("TOP_LEFT")) {
			irrigation.addAll(Arrays.asList(Irrigation.TOP, 
					Irrigation.LEFT));
		} else if(textureName.equals("TOP_BOTTOM")) {
			irrigation.addAll(Arrays.asList(Irrigation.TOP, 
					Irrigation.BOTTOM));
		} else if(textureName.equals("TOP_RIGHT")) {
			irrigation.addAll(Arrays.asList(Irrigation.TOP, 
					Irrigation.RIGHT));
		} else if(textureName.equals("TOP")) {
			irrigation = EnumSet.of(Irrigation.TOP);
		} else if(textureName.equals("BOTTOM")) {
			irrigation = EnumSet.of(Irrigation.BOTTOM);
		} else if(textureName.equals("LEFT")) {
			irrigation = EnumSet.of(Irrigation.LEFT);
		} else if(textureName.equals("RIGHT")) {
			irrigation = EnumSet.of(Irrigation.RIGHT);
		}
		return irrigation;
	}
	
}

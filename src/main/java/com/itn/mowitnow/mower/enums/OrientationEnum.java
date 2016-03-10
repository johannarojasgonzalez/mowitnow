package com.itn.mowitnow.mower.enums;

/**
 * enumeration representant les points cardinaux
 */
public enum OrientationEnum {

	NORTH("N"),
	EAST("E"),
	WEST("W"),
	SOUTH("S");
	
	private String label;
		
	private OrientationEnum(String label) {
		this.label = label;
	}
	
	private String getLabel() {
		return label;
	}

	public static OrientationEnum getOrientationEnumByName(String label) {
		if(NORTH.getLabel().equals(label)) {
			return OrientationEnum.NORTH;
		} else if(EAST.getLabel().equals(label)) {
			return OrientationEnum.EAST;
		} else if(WEST.getLabel().equals(label)) {
			return OrientationEnum.WEST;
		} else if(SOUTH.getLabel().equals(label)) {
			return OrientationEnum.SOUTH;
		}
		throw new IllegalArgumentException(String.format("La valeur %s n'est pas definie", label)) ;		
	}
}

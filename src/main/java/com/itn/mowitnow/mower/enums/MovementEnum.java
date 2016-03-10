package com.itn.mowitnow.mower.enums;

/**
 * enumeration de mouvements possibles par la tondeuse
 *
 */
public enum MovementEnum {

	RIGHT("D"),
	LEFT("G"),
	FORWARD("A");
	
	private String label;
	
	private MovementEnum(String label) {
		this.label = label;
	}
	
	private String getLabel() {
		return label;
	}

	public static MovementEnum getMovementEnumByName(String label) {
		if(RIGHT.getLabel().equals(label)) {
			return MovementEnum.RIGHT;
		} else if(LEFT.getLabel().equals(label)) {
			return MovementEnum.LEFT;
		} else if(FORWARD.getLabel().equals(label)) {
			return MovementEnum.FORWARD;
		} 
		throw new IllegalArgumentException(String.format("La valeur %s n'est pas definie", label)) ;		
	}
}

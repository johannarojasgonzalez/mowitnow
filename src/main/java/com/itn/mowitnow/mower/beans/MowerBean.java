package com.itn.mowitnow.mower.beans;

import java.util.List;

import com.itn.mowitnow.mower.enums.MovementEnum;
import com.itn.mowitnow.mower.enums.OrientationEnum;

/**
 * represente la tondeuse
 * - position initiale
 * - mouvements à suivre
 */
public class MowerBean extends PositionBean {

	private OrientationEnum orientation;

	private List<MovementEnum> movements;

	public MowerBean(int posX, int posY, OrientationEnum orientation) {
		super(posX, posY);
		this.orientation = orientation;
	}

	public MowerBean(int posX, int posY, OrientationEnum orientation, List<MovementEnum> movements) {
		super(posX, posY);
		this.orientation = orientation;
		this.movements = movements;
	}

	public OrientationEnum getOrientation() {
		return orientation;
	}

	public void setOrientation(OrientationEnum orientation) {
		this.orientation = orientation;
	}

	public List<MovementEnum> getMovements() {
		return movements;
	}

	public void setMovements(List<MovementEnum> movements) {
		this.movements = movements;
	}
}

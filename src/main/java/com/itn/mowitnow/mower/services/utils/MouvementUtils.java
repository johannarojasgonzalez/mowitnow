package com.itn.mowitnow.mower.services.utils;

import static com.itn.mowitnow.mower.enums.OrientationEnum.getOrientationEnumByName;

import java.util.List;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.itn.mowitnow.mower.beans.MowerBean;
import com.itn.mowitnow.mower.beans.PositionBean;
import com.itn.mowitnow.mower.enums.MovementEnum;
import com.itn.mowitnow.mower.enums.OrientationEnum;

public final class MouvementUtils {

	private MouvementUtils() {
	}

	/**
	 * Calcul de l'orientation de la tondeuse quand elle bouge à droite
	 * @param orientation de la tondeuse
	 * @return
	 */
	public static OrientationEnum getOrientationWhenTurnToRight(OrientationEnum orientation) {
		switch (orientation) {
			case NORTH:
				return OrientationEnum.EAST;
			case EAST:
				return OrientationEnum.SOUTH;
			case SOUTH:
				return OrientationEnum.WEST;
			case WEST:
				return OrientationEnum.NORTH;
		}
		throw new RuntimeException("La valeur %s de l'orientation n'est pas valide " + orientation.name());
	}

	/**
	 * Calcul de l'orientation de la tondeuse quand elle bouge à gauche
	 * @param orientation de la tondeuse
	 * @return
	 */
	public static OrientationEnum getOrientationWhenTournToLeft(OrientationEnum orientation) {
		switch (orientation) {
			case NORTH:
				return OrientationEnum.WEST;
			case EAST:
				return OrientationEnum.NORTH;
			case SOUTH:
				return OrientationEnum.EAST;
			case WEST:
				return OrientationEnum.SOUTH;
		}
		throw new RuntimeException("La valeur %s de l'orientation n'est pas valide " + orientation.name());
	}

	/**
	 * Calcul de la position de la tondeuse dans la pelouse quand elle bouge à droite
	 * @param orientation de la tondeuse
	 * @return
	 */
	public static PositionBean forward(MowerBean mower, PositionBean ground) {
		int x = mower.getX();
		int y = mower.getY();
		OrientationEnum orientation = mower.getOrientation();
		if (OrientationEnum.NORTH.equals(orientation)) {
			y++;
		} else if (OrientationEnum.EAST.equals(orientation)) {
			x++;
		} else if (OrientationEnum.SOUTH.equals(orientation)) {
			y--;
		} else if (OrientationEnum.WEST.equals(orientation)) {
			x--;
		}
		return isInTheGround(x, y, ground) ? new PositionBean(x, y) : new PositionBean(mower.getX(),
				mower.getY());
	}
	
	/**
	 * Transforme une serie de caracteres en MovementEnum
	 * @param content : caracteres à transformer
	 * @return
	 */
	public static List<MovementEnum> getTransformedMovList(String content) {
		List<MovementEnum> transformedList = Lists.transform(Lists.charactersOf(content),
				new Function<Character, MovementEnum>() {
					@Override
					public MovementEnum apply(Character label) {
						return MovementEnum.getMovementEnumByName(label.toString());
					}
				});
		return transformedList;
	}

	/**
	 * Creation d'une position
	 * @param splitContent
	 * @return
	 */
	public static PositionBean getPositionFromContent(String[] splitContent) {
		int x = Integer.parseInt(splitContent[0]);
		int y = Integer.parseInt(splitContent[1]);
		return new PositionBean(x, y);
	}
	
	/**
	 * Creation d'une tondeuse
	 * @param splitContent
	 * @return
	 */
	public static MowerBean createMowerBean(String[] splitContent) {
		PositionBean positionMower = getPositionFromContent(splitContent);
		OrientationEnum orientation = getOrientationEnumByName(splitContent[2]);
		MowerBean mowerBean = new MowerBean(positionMower.getX(), positionMower.getY(), orientation);
		return mowerBean;
	}
	
	// valide si la tondeuse est dans la pelouse
	private static boolean isInTheGround(int posX, int posY, PositionBean ground) {
		return !(posX < 0 || posX > ground.getX()) && !(posY < 0 || posY > ground.getY());
	}
}

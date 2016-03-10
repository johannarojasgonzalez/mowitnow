package com.itn.mowitnow.mower.beans;

import java.util.List;

/**
 * represente les informations de lancement du jeu
 * - la taille de la pelouse
 * - la tondeuse avec ses mouvements
 */
public class InfoGameBean extends PositionBean {

	private List<MowerBean> tondeuses;

	public InfoGameBean(int x, int y, List<MowerBean> tondeuses) {
		super(x, y);
		this.tondeuses = tondeuses;
	}

	public List<MowerBean> getTondeuses() {
		return tondeuses;
	}

	public void setTondeuses(List<MowerBean> tondeuses) {
		this.tondeuses = tondeuses;
	}

}

package com.itn.mowitnow.mower.services;

import com.itn.mowitnow.mower.beans.InfoGameBean;
import com.itn.mowitnow.mower.beans.MowerBean;
import com.itn.mowitnow.mower.beans.PositionBean;

public interface IMowerMouvementService {

	/**
	 * Faire bouger la tondeuse
	 * @param ground : la pelouse
	 * @param mower : la tondeuse
	 */
	void moveMower(PositionBean ground, MowerBean mower);

	/**
	 * Chargement du fichier d'entree
	 * @return les informations pour demarrer le jeu
	 * @throws Exception
	 */
	InfoGameBean loadDataFromFile() throws Exception;
}

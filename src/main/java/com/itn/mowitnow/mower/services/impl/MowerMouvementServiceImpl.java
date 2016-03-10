package com.itn.mowitnow.mower.services.impl;

import static com.itn.mowitnow.mower.services.utils.MouvementUtils.createMowerBean;
import static com.itn.mowitnow.mower.services.utils.MouvementUtils.forward;
import static com.itn.mowitnow.mower.services.utils.MouvementUtils.getOrientationWhenTournToLeft;
import static com.itn.mowitnow.mower.services.utils.MouvementUtils.getOrientationWhenTurnToRight;
import static com.itn.mowitnow.mower.services.utils.MouvementUtils.getPositionFromContent;
import static com.itn.mowitnow.mower.services.utils.MouvementUtils.getTransformedMovList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.itn.mowitnow.mower.beans.InfoGameBean;
import com.itn.mowitnow.mower.beans.MowerBean;
import com.itn.mowitnow.mower.beans.PositionBean;
import com.itn.mowitnow.mower.enums.MovementEnum;
import com.itn.mowitnow.mower.services.IMowerMouvementService;

@Component
public class MowerMouvementServiceImpl implements IMowerMouvementService {

	@Override
	public void moveMower(PositionBean pelouseBean, MowerBean mowerBean) {
		// calculer la nouvelle orientation
		for (MovementEnum commande : mowerBean.getMovements()) {
			if (MovementEnum.FORWARD.equals(commande)) {
				PositionBean positionBean = forward(mowerBean, pelouseBean);
				mowerBean.setX(positionBean.getX());
				mowerBean.setY(positionBean.getY());
			} else if (MovementEnum.LEFT.equals(commande)) {
				mowerBean.setOrientation(getOrientationWhenTournToLeft(mowerBean.getOrientation()));
			} else if (MovementEnum.RIGHT.equals(commande)) {
				mowerBean.setOrientation(getOrientationWhenTurnToRight(mowerBean.getOrientation()));
			}
		}
	}

	@Override
	public InfoGameBean loadDataFromFile() throws Exception {
		ClassLoader classLoader = this.getClass().getClassLoader();
		InputStream inputStream = classLoader.getResourceAsStream("sequence.txt");
		// préparer la lecture du fichier d'entrée
		InputStreamReader isr = new InputStreamReader(inputStream);

		try {
			BufferedReader br = new BufferedReader(isr);
			int line = 0;
			String content;
			PositionBean ground = null;
			List<MowerBean> tondeuses = new ArrayList<MowerBean>();
			while (!StringUtils.isEmpty(content = br.readLine())) {
				String[] splitContent = content.trim().split(" ");
				if (splitContent.length > 0) {
					if (line == 0) {
						// creation de la pelouse
						ground = getPositionFromContent(splitContent);
					} else if (line % 2 != 0) { 
						// ligne impaire : coordonnees initiales de la pelouse + direction
						MowerBean mowerBean = createMowerBean(splitContent);
						// lecture de la ligne suivante pour recuperer les mouvements à appliquer 
						// à la pelouse
						content = br.readLine().trim();
						mowerBean.setMovements(getTransformedMovList(content));
						tondeuses.add(mowerBean);
						line++;
					}
					line++;
				}
			}

			// si la lecture du fichier est ok alors on cree les informations
			// pour initier le jeu
			if (ground != null && !tondeuses.isEmpty()) {
				return new InfoGameBean(ground.getX(), ground.getY(), tondeuses);
			}
		} catch (IOException e) {
			throw new Exception(e);
		}
		return null;
	}

}

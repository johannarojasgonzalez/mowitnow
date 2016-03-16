package com.itn.mowitnow.mower.ui.controller;

import static org.springframework.util.CollectionUtils.isEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itn.mowitnow.mower.beans.InfoGameBean;
import com.itn.mowitnow.mower.beans.MowerBean;
import com.itn.mowitnow.mower.beans.PositionBean;
import com.itn.mowitnow.mower.services.IMowerMouvementService;

@Controller
public class MowerController {

	@Autowired
	private IMowerMouvementService mouvementService;
	
	@RequestMapping("/rungame")
	public String resultgame(Model model) throws Exception {
		model.addAttribute("resultat", getResultRunGame());
		return "resultgame";
	}

	/**
	 * Appel au service pour la lecture du fichier et execution du jeu
	 * @return le résultat du jeu
	 */
	private String getResultRunGame() {
		StringBuilder sb = new StringBuilder();
		InfoGameBean infoGameBean;
		try {
			infoGameBean = mouvementService.loadDataFromFile();
			if (infoGameBean != null && !isEmpty(infoGameBean.getTondeuses())) {
				for (MowerBean mowerBean : infoGameBean.getTondeuses()) {
					mouvementService.moveMower(new PositionBean(infoGameBean.getX(), infoGameBean.getY()), mowerBean);
					sb.append(" X :").append(mowerBean.getX()).append(" Y :").append(mowerBean.getY())
							.append(" Orientation " + ":").append(mowerBean.getOrientation());
				}
			}
		} catch (Exception e) {
			sb.append("Une erreur est survenue. Vérifiez le format de votre fichier !!! ");
		}
		return sb.toString();
	}

}

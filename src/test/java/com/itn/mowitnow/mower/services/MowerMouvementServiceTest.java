package com.itn.mowitnow.mower.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.assertj.core.groups.Tuple;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import com.itn.mowitnow.mower.beans.PositionBean;
import com.itn.mowitnow.mower.beans.InfoGameBean;
import com.itn.mowitnow.mower.beans.MowerBean;
import com.itn.mowitnow.mower.enums.MovementEnum;
import com.itn.mowitnow.mower.enums.OrientationEnum;
import com.itn.mowitnow.mower.services.impl.MowerMouvementServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class MowerMouvementServiceTest {

	public static final int WIDHT_PELOUSE = 5;
	public static final int HEIGHT_PELOUSE = 5;

	public static final int POS_X_CAS1 = 1;
	public static final int POS_X_FINAL_CAS1 = 1;
	public static final int POS_Y_CAS1 = 2;
	public static final int POS_Y_FINAL_CAS1 = 3;

	public static final int POS_X_CAS2 = 3;
	public static final int POS_X_FINAL_CAS2 = 5;
	public static final int POS_Y_CAS2 = 3;
	public static final int POS_Y_FINAL_CAS2 = 1;

	public static final OrientationEnum ORIENTATION_NORTH = OrientationEnum.NORTH;
	public static final OrientationEnum ORIENTATION_EAST = OrientationEnum.EAST;

	@InjectMocks
	private MowerMouvementServiceImpl mowerMouvementService = new MowerMouvementServiceImpl();

	@Test
	public void testDeplacerTondeuseCas1() {
		// given
		MowerBean mowerBean = givenTondeuseBean(POS_X_CAS1, POS_Y_CAS1, ORIENTATION_NORTH,
				Arrays.asList(MovementEnum.LEFT, MovementEnum.FORWARD, MovementEnum.LEFT, MovementEnum.FORWARD,
						MovementEnum.LEFT, MovementEnum.FORWARD, MovementEnum.LEFT, MovementEnum.FORWARD,
						MovementEnum.FORWARD));
		// when
		mowerMouvementService.moveMower(givenPositionBean(WIDHT_PELOUSE, HEIGHT_PELOUSE), mowerBean);
		// then
		assertNotNull(mowerBean);
		assertEquals(mowerBean.getOrientation(), ORIENTATION_NORTH);
		assertEquals(mowerBean.getX(), POS_X_FINAL_CAS1);
		assertEquals(mowerBean.getY(), POS_Y_FINAL_CAS1);

	}

	@Test
	public void testDeplacerTondeuseCas2() {
		// given
		MowerBean mowerBean = givenTondeuseBean(POS_X_CAS2, POS_Y_CAS2, ORIENTATION_EAST,
				Arrays.asList(MovementEnum.FORWARD, MovementEnum.FORWARD, MovementEnum.RIGHT, MovementEnum.FORWARD,
						MovementEnum.FORWARD, MovementEnum.RIGHT, MovementEnum.FORWARD, MovementEnum.RIGHT,
						MovementEnum.RIGHT, MovementEnum.FORWARD));
		// when
		mowerMouvementService.moveMower(givenPositionBean(WIDHT_PELOUSE, HEIGHT_PELOUSE), mowerBean);
		
		// then
		assertNotNull(mowerBean);
		assertEquals(mowerBean.getOrientation(), ORIENTATION_EAST);
		assertEquals(mowerBean.getX(), POS_X_FINAL_CAS2);
		assertEquals(mowerBean.getY(), POS_Y_FINAL_CAS2);

	}

	@Test
	public void testLoadFile() throws Exception {

		InfoGameBean infoGameBean = mowerMouvementService.loadDataFromFile();

		assertNotNull(infoGameBean);
		assertEquals(infoGameBean.getX(), WIDHT_PELOUSE);
		assertEquals(infoGameBean.getY(), HEIGHT_PELOUSE);
		
		List<MowerBean> mowers = infoGameBean.getTondeuses();
		assertThat(mowers).isNotNull().isNotEmpty().hasSize(2);
		assertThat(mowers).extracting("x", "y", "orientation", "movements").contains(
				Tuple.tuple(3, 3, OrientationEnum.EAST,
						Arrays.asList(MovementEnum.FORWARD, MovementEnum.FORWARD, MovementEnum.RIGHT,
								MovementEnum.FORWARD, MovementEnum.FORWARD, MovementEnum.RIGHT, MovementEnum.FORWARD,
								MovementEnum.RIGHT, MovementEnum.RIGHT, MovementEnum.FORWARD)),
				Tuple.tuple(1, 2, OrientationEnum.NORTH,
						Arrays.asList(MovementEnum.LEFT, MovementEnum.FORWARD, MovementEnum.LEFT, MovementEnum.FORWARD,
								MovementEnum.LEFT, MovementEnum.FORWARD, MovementEnum.LEFT, MovementEnum.FORWARD,
								MovementEnum.FORWARD)));
	}

	private PositionBean givenPositionBean(int x, int y) {
		return new PositionBean(x, y);
	}

	private MowerBean givenTondeuseBean(int x, int y, OrientationEnum orientation, List<MovementEnum> movements) {
		return new MowerBean(x, y, orientation, movements);
	}

}

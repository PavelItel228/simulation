package x.logic.force;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import dao.ChildrenDao;
import dao.DAOFactory;
import dao.HumanDao;
import entity.Plants.FruitPlant;
import entity.Plants.Plant;
import entity.enums.landscape.WaterHeight;
import entity.humans.Children;
import entity.humans.Human;
import entity.humans.Man;
import entity.humans.Woman;
import entity.landscape.Water;
import x.data.ucf.XUcfCoder;
import x.data.ucf.XUcfCoderFactory;
import x.gui.info.cell.XCellInfoPanel;
import x.gui.info.events.XEventsInfoPanel;
import x.gui.main.XMainPanel;
import x.gui.map.XMapPanel;
import x.logic.random.XRandom;
import x.logic.statistic.XStatistic;
import x.logic.statistic.XStatisticFactory;

// Основной класс логики симуляции
public class XForce {
	
	private static int date = 1;
	private static Timer timer;
	private final XUcfCoder xUcfCoder = XUcfCoderFactory.getInstance();
	private final Water highWater = new Water(WaterHeight.HIGH);
	private final Water lowWater = new Water(WaterHeight.LOW);
	private final XStatistic xStatistic = XStatisticFactory.getInstance();
	private final HumanDao humanDao = DAOFactory.getInstance().getHumanDao();
	private final ChildrenDao childrenDao = DAOFactory.getInstance().getChildrenDao();
	private final Human human = new Human();
	private final Plant palt = new Plant();


	public void start() {
		if (timer == null) {
			int timerDelay = 100;
			timer = new Timer(timerDelay, e -> {
				XMapPanel map = XMainPanel.mapPanel;
				for (int y = 0; y < XMapPanel.MAP_SIZE; y++) {
					for (int x = 0; x < XMapPanel.MAP_SIZE; x++) {
						map.setActiveFlagHumanAt(1, y, x);
						map.setActiveFlagPlantAt(1, y, x);
					}
				}
				for (int y = 0; y < XMapPanel.MAP_SIZE; y++) {
					for (int x = 0; x < XMapPanel.MAP_SIZE; x++) {
						act(map.getRawDataAt(y, x), y, x);
					}
				}
				map.repaint();
				int y = map.getSelectedRow();
				int x = map.getSelectedColumn();
				XCellInfoPanel cellInfo = XMainPanel.cellInfoPanel;
				if (y != -1 && x != -1) {
					cellInfo.update(y, x);
				}
				else {
					cellInfo.reset();
				}
				xStatistic.update();
				XMainPanel.mapInfoPanel.update(++date);
			});
		}
		timer.start();
	}
	
	public static void pause() {
		if (timer != null) {
			timer.stop();	
		}
	}
	
	public static void stop() {
		if (timer != null) {
			timer.stop();	
		}
		date = 1;
	}
	
	// ACT
	private void act(long cellData, int y, int x) {
		if (xUcfCoder.decodeActiveFlagHuman(cellData) == 1 && xUcfCoder.decodeHumanType(cellData) != Human.HUMAN_TYPE_EMPTY) {
			human.actHuman(cellData, y, x, date);
		}
		if (xUcfCoder.decodeActiveFlagPlant(cellData) == 1 && xUcfCoder.decodePlantType(cellData) != Plant.PLANT_TYPE_EMPTY) {
			palt.actPlant(cellData, y, x, date);
		}
	}

	public static boolean isCellInMapRange(int y, int x) {
		return y >= 0 && y < XMapPanel.MAP_SIZE && x >= 0 && x < XMapPanel.MAP_SIZE;
	}
	
}

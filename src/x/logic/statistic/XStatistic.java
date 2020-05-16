package x.logic.statistic;

import dao.*;
import entity.Plants.FruitPlant;
import entity.Plants.Plant;
import entity.humans.Human;
import entity.humans.Man;
import entity.humans.Woman;
import entity.landscape.Landscape;
import x.gui.main.XMainPanel;
import x.gui.map.XMapPanel;

// Класс учета статистики
public class XStatistic {

	private final HumanDao humanDao = DAOFactory.getInstance().getHumanDao();
	private final WomanDao womanDao = DAOFactory.getInstance().getWomanDao();
	private final ManDao manDao = DAOFactory.getInstance().getManDao();
	private final ChildrenDao childrenDao = DAOFactory.getInstance().getChildrenDao();
	private final PlantDAO plantDAO = DAOFactory.getInstance().getPlantDao();
	private final FruitPlantDao fruitPlantDao = DAOFactory.getInstance().getFruitPlant();

	// DENSITY
	public float getPeopleLandDensity() {
		return (float) humanDao.findAll().size() / Landscape.CELLS_LAND;
	}
	
	public float getPlantsLandDensity() {
		return (float) plantDAO.findAll().size() / Landscape.CELLS_LAND;
	}
	
	// MEAN
	public float getPeopleAgeMean() {
		return humanDao.findAll().size() != 0 ? (float) humanDao.getAgeOfAll() / humanDao.findAll().size() : 0;
	}
	
	public  float getPlantsFruitsMean() {
		return plantDAO.findAll().size() != 0 ? (float) fruitPlantDao.findAll().size() / plantDAO.findAll().size() : 0;
	}
	
	// RATIO
	public float getPlantsFruitsPeopleRatio() {
		return humanDao.findAll().size() != 0 ? (float) fruitPlantDao.findAll().size() / humanDao.findAll().size() : fruitPlantDao.findAll().size();
	}
	// метод читает всю карту и из нее обновляет количество жителей деревьев и тд
	public void update() {
		XMapPanel map = XMainPanel.mapPanel;
		humanDao.deleteAll(false);
		manDao.deleteAll();
		womanDao.deleteAll();
		plantDAO.deleteAll();
		fruitPlantDao.deleteAll();
		for (int y = 0; y < XMapPanel.MAP_SIZE; y++) {
			for (int x = 0; x < XMapPanel.MAP_SIZE; x++) {
				Human human = new Human();
				if (map.getHumanTypeAt(y, x) != Human.HUMAN_TYPE_EMPTY) {
					human.setAge(map.getHumanAgeAt(y, x));
					humanDao.create(human);
					if (map.getHumanTypeAt(y, x) == Man.HUMAN_TYPE_MAN) {
						manDao.create(new Man());
					}
					else if (map.getHumanTypeAt(y, x) == Woman.HUMAN_TYPE_WOMAN) {
						Woman woman = new Woman();
						if (map.getHumanPregnancyAt(y, x) != 0) {
							woman.setPregnant(true);
						}
						womanDao.create(woman);
					}
				}
				if (map.getPlantTypeAt(y, x) != Plant.PLANT_TYPE_EMPTY) {
					plantDAO.create(new Plant());
					fruitPlantDao.create(new FruitPlant());
				}
			}
		}
	}
	
	public void reset() {
		humanDao.deleteAll(true);
		manDao.deleteAll();
		womanDao.deleteAll();
		childrenDao.deleteAll();
		plantDAO.deleteAll();
		fruitPlantDao.deleteAll();
	}

}

package x.gui.info.map;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import dao.*;
import entity.landscape.Landscape;
import x.csvWritter.CsvWritter;
import x.gui.format.XFormatter;
import x.logic.statistic.XStatistic;
import x.logic.statistic.XStatisticFactory;

@SuppressWarnings("serial")
//Класс для панели информации про всю карту
public class XMapInfoPanel extends JPanel {
	
	private JLabel info;
	private final XStatistic xStatistic = XStatisticFactory.getInstance();
	private final HumanDao humanDao = DAOFactory.getInstance().getHumanDao();
	private final WomanDao womanDao = DAOFactory.getInstance().getWomanDao();
	private final ManDao manDao = DAOFactory.getInstance().getManDao();
	private final ChildrenDao childrenDao = DAOFactory.getInstance().getChildrenDao();
	private final PlantDAO plantDAO = DAOFactory.getInstance().getPlantDao();
	private final FruitPlantDao fruitPlantDao = DAOFactory.getInstance().getFruitPlant();
	private boolean firstLine = true;
	private final CsvWritter csvWritter = new CsvWritter();

	public XMapInfoPanel() {

		setupView();
		setupInfo();
		setVisible(true);
	}
//	Обновление информации и запись ее в файл
	public final void update(int days) {
		String info = String.format("<html>Date: %s"
				+ "<br> "
				+ "<br> Common area: %s"
				+ "<br> &nbsp ┗ Water area: %s"
				+ "<br> &nbsp ┗ Land area: %s"
				+ "<br> People: %s"
				+ "<br> &nbsp ┗ Men: %s"
				+ "<br> &nbsp ┗ Women: %s"
				+ "<br> &nbsp&nbsp&nbsp&nbsp ┗ Pregnant: %s"
				+ "<br> Children were born: %s"
				+ "<br> Children died: %s"
				+ "<br> People died: %s"
				+ "<br> &nbsp ┗ Low Energy: %s"
				+ "<br> &nbsp ┗ Low Satiety: %s"
				+ "<br> &nbsp ┗ Age: %s"
				+ "<br> &nbsp ┗ Lost: %s"
				+ "<br> People Density (Land): %.2f"
				+ "<br> People Age Mean: %s"
				+ "<br> Plants: %s"
				+ "<br> Plants Fruits: %s"
				+ "<br> Plants Density (Land): %.2f"
				+ "<br> Plants Fruits Mean: %.2f"
				+ "<br> Plants Fruits / People Ratio: %.2f</html>",
				XFormatter.formatDateShort(days),
				Landscape.CELLS,
				Landscape.CELLS_WATER,
				Landscape.CELLS_LAND,
				humanDao.findAll().size(),
				manDao.findAll().size(),
				womanDao.findAll().size(),
				womanDao.getAllPregnant().size(),
				childrenDao.findAll().size(),
				childrenDao.getAllDead().size(),
				humanDao.allDead(),
				humanDao.getAllDiedByEnergy().size(),
				humanDao.getAllDiedBySatiety().size(),
				humanDao.getAllDiedByAge().size(),
				humanDao.getAllDiedByLost().size(),
				xStatistic.getPeopleLandDensity(),
				XFormatter.formatDate((int) xStatistic.getPeopleAgeMean()),
				plantDAO.findAll().size(),
				fruitPlantDao.findAll().size(),
				xStatistic.getPlantsLandDensity(),
				xStatistic.getPlantsFruitsMean(),
				xStatistic.getPlantsFruitsPeopleRatio());
		this.info.setText(info);

		if(firstLine) {
			String[] title = {"Date", "Humans", "Women", "Men", "Pregnant", "Children Born", "Children died",
					"People died", "Low energy", "Low Satiety", "Age", "Lost", "People Density (Land)", "People Age Mean",
					"Plants", "Plants Fruits", "Plants Density (Land)", "Plants Fruits Mean", "Plants Fruits / People Ratio"};
			csvWritter.writeLine(title);
			firstLine = false;
		}
		String[] line = {XFormatter.formatDateShort(days),
				String.valueOf(humanDao.findAll().size()),
				String.valueOf(manDao.findAll().size()),
				String.valueOf(womanDao.findAll().size()),
				String.valueOf(womanDao.getAllPregnant().size()),
				String.valueOf(childrenDao.findAll().size()),
				String.valueOf(childrenDao.getAllDead().size()),
				String.valueOf(humanDao.allDead()),
				String.valueOf(humanDao.getAllDiedByEnergy().size()),
				String.valueOf(humanDao.getAllDiedBySatiety().size()),
				String.valueOf(humanDao.getAllDiedByAge().size()),
				String.valueOf(humanDao.getAllDiedByLost().size()),
				String.valueOf(xStatistic.getPeopleLandDensity()),
				XFormatter.formatDate((int) xStatistic.getPeopleAgeMean()),
				String.valueOf(plantDAO.findAll().size()),
				String.valueOf(fruitPlantDao.findAll().size()),
				String.valueOf(xStatistic.getPlantsLandDensity()),
				String.valueOf(xStatistic.getPlantsFruitsMean()),
				String.valueOf(xStatistic.getPlantsFruitsPeopleRatio())};
		csvWritter.writeLine(line);

	}
	
	public void reset() {
		this.info.setText("-");
	}
//	Настройка положения
	private void setupView() {
		setLayout(new FlowLayout(FlowLayout.LEFT));
		setBorder(new TitledBorder("Map Info"));
		setPreferredSize(new Dimension(300, 0));
	}
	
	private void setupInfo() {
		this.info = new JLabel("-", SwingConstants.LEFT);
		add(this.info);
	}

}

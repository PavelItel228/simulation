package x.gui.map;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import entity.BaseEntity;
import entity.Plants.Plant;
import entity.enums.landscape.GrassHeight;
import entity.enums.landscape.GroundHeight;
import entity.enums.landscape.WaterHeight;
import entity.humans.Human;
import entity.humans.Man;
import entity.humans.Woman;
import entity.landscape.Grass;
import entity.landscape.Ground;
import entity.landscape.Water;
import x.data.ucf.XUcfCoder;
import x.data.ucf.XUcfCoderFactory;

@SuppressWarnings("serial")
public class XCellRenderer extends DefaultTableCellRenderer {

	private final static int TREE_TYPE_COLOR_APPLE            = 0x62B122;

	private final XUcfCoder xUcfCoder = XUcfCoderFactory.getInstance();
	private final Water highWater = new Water(WaterHeight.HIGH);
	private final Water lowWater = new Water(WaterHeight.LOW);
	private final Grass lowGrass = new Grass(GrassHeight.LOW);
	private final Grass highGrass = new Grass(GrassHeight.HIGH);
	private final Ground lowGround = new Ground(GroundHeight.LOW);
	private final Ground highGround = new Ground(GroundHeight.HIGH);


    @Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		super.getTableCellRendererComponent(table, "", isSelected, hasFocus, row, column);
		setHorizontalAlignment(SwingConstants.CENTER);
		setBackground(new Color(getBackgroundColor((Long) value)));
		setForeground(new Color(getForegroundColor((Long) value)));
		setValue(getUnit((Long) value));
		return this;
	}
	
	private int getBackgroundColor(Long cellData) {
		if(cellData == null){
			return BaseEntity.COMMON_COLOR_EMPTY;
		}
		int color;
		int landscapeType = xUcfCoder.decodeLandscapeType(cellData);
		if (landscapeType == lowWater.getWaterHeight().getTypeCode()) {
			color = lowWater.getWaterHeight().getColorCode();
		} else if (landscapeType == highWater.getWaterHeight().getTypeCode()) {
			color = highWater.getWaterHeight().getColorCode();
		} else if (landscapeType == lowGround.getGroundHeight().getTypeCode()) {
			color = lowGround.getGroundHeight().getColorCode();
		} else if (landscapeType == highGround.getGroundHeight().getTypeCode()) {
			color = highGround.getGroundHeight().getColorCode();
		} else if (landscapeType == lowGrass.getGrassHeight().getTypeCode()) {
			color = lowGrass.getGrassHeight().getColorCode();
		} else if (landscapeType == highGrass.getGrassHeight().getTypeCode()) {
			color = highGrass.getGrassHeight().getColorCode();
		} else {
			color = BaseEntity.COMMON_COLOR_UNDEFINED;
		}
		return color;
	}
	
	private int getForegroundColor(Long cellData) {
		if(cellData == null){
			return BaseEntity.COMMON_COLOR_EMPTY;
		}
		int color;
		int humanType = xUcfCoder.decodeHumanType(cellData);
		int plantType = xUcfCoder.decodePlantType(cellData);
		if (humanType != Human.HUMAN_TYPE_EMPTY) {
			switch (humanType) {
				case Man.HUMAN_TYPE_MAN: {
					color = Man.HUMAN_TYPE_COLOR_MAN;
					break;
				}
				case Woman.HUMAN_TYPE_WOMAN: {
					color = Woman.HUMAN_TYPE_COLOR_WOMAN;
					break;
				}
				default: {
					color = BaseEntity.COMMON_COLOR_UNDEFINED;
					break;
				}
			}
		}
		else if (plantType != Plant.PLANT_TYPE_EMPTY) {
			if (plantType == Plant.PLANT_TYPE_APPLE) {
				color = TREE_TYPE_COLOR_APPLE;
			} else {
				color = BaseEntity.COMMON_COLOR_UNDEFINED;
			}
		}
		else {
			color = BaseEntity.COMMON_COLOR_UNDEFINED;
		}
		return color;
	}
	
	private char getUnit(Long cellData) {
		if(cellData == null){
			return ' ';
		}
		char unit = ' ';
		int humanType = xUcfCoder.decodeHumanType(cellData);
		int plantType = xUcfCoder.decodePlantType(cellData);
		if (humanType != Human.HUMAN_TYPE_EMPTY) {
			unit = 'E';
		}
		if (plantType != Plant.PLANT_TYPE_EMPTY) {
			unit = 'F';
		}
		return unit;
	}
}

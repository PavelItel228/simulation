package x.gui.info.cell;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import x.data.ucf.XUcfCoder;
import x.data.ucf.XUcfCoderFactory;
import x.gui.format.XFormatter;
import x.gui.main.XMainPanel;

@SuppressWarnings("serial")
//Класс панели с информацией для клетки
public class XCellInfoPanel extends JPanel {
	
	private JLabel info;
	private final XUcfCoder xUcfCoder = XUcfCoderFactory.getInstance();
	
	public XCellInfoPanel() {
		setupView();
		setupInfo();
		setVisible(true);
	}
	// обовление информаци на панеле
	public void update(int y, int x) {
		long cellData = XMainPanel.mapPanel.getRawDataAt(y, x);		
		String info = String.format("<html>Landscape type: %s"
				+ "<br> "
				+ "<br> Human type: %s"
				+ "<br> Human age: %s"
				+ "<br> Human energy: %s"
				+ "<br> Human satiety: %s"
				+ "<br> Human pregnancy: %s"
				+ "<br> "
				+ "<br> Plant type: %s"
				+ "<br> Plant fruits: %s"
				+ "<br> "
				+ "<br> Active flag (Human): %s"
				+ "<br> Active flag (Plant): %s</html>",
				xUcfCoder.decodeLandscapeType(cellData),
				xUcfCoder.decodeHumanType(cellData),
				XFormatter.formatDate(xUcfCoder.decodeHumanAge(cellData)),
				xUcfCoder.decodeHumanEnergy(cellData),
				xUcfCoder.decodeHumanSatiety(cellData),
				XFormatter.formatDate(xUcfCoder.decodeHumanPregnancy(cellData)),
				xUcfCoder.decodePlantType(cellData),
				xUcfCoder.decodePlantFruits(cellData),
				xUcfCoder.decodeActiveFlagHuman(cellData),
				xUcfCoder.decodeActiveFlagPlant(cellData));
		this.info.setText(info);
		this.setToolTipText(XFormatter.formatRaw(cellData));
	}
	
	public void reset() {
		this.info.setText("-");
		this.setToolTipText(null);
	}
	//настройка положения
	private void setupView() {
		setLayout(new FlowLayout(FlowLayout.LEFT));
		setBorder(new TitledBorder("Cell Info"));
		setPreferredSize(new Dimension(400, 0));
	}
	
	private void setupInfo() {
		this.info = new JLabel("-");
		add(this.info);
	}

}

package x.gui.control;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import x.gui.main.XMainPanel;
import x.logic.force.XForce;
import x.logic.force.XForceFactory;
import x.logic.statistic.XStatistic;
import x.logic.statistic.XStatisticFactory;

@SuppressWarnings("serial")
// Класс настройки кнопок старт пауза и остановка
public class XControlPanel extends JPanel {
	
	private JButton startButton;
	private JButton pauseButton;
	private JButton stopButton;
	private final XForce xForce = XForceFactory.getInstance();
	private final XStatistic xStatistic = XStatisticFactory.getInstance();
	
	public XControlPanel() {
		setupView();
		setupButtons();
		setVisible(true);
	}
	// Настройка положения кнопок
	private void setupView() {
		setLayout(new FlowLayout(FlowLayout.LEFT));
	}

	// настройка функциональности кнопок
	private void setupButtons() {
		// START
		this.startButton = new JButton();
		startButton.setIcon(new ImageIcon("src\\resources\\gui\\icons\\control-start.png"));
		startButton.setPreferredSize(new Dimension(25, 25));
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				xForce.start();
				startButton.setEnabled(false);
				pauseButton.setEnabled(true);
				stopButton.setEnabled(true);
			}
		});
		add(startButton);
		// PAUSE
		this.pauseButton = new JButton();
		pauseButton.setIcon(new ImageIcon("src\\resources\\gui\\icons\\control-pause.png"));
		pauseButton.setPreferredSize(new Dimension(25, 25));
		pauseButton.setEnabled(false);
		pauseButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				XForce.pause();
				startButton.setEnabled(true);
				pauseButton.setEnabled(false);
				stopButton.setEnabled(true);
			}
		});
		add(pauseButton);
		// STOP
		this.stopButton = new JButton();
		stopButton.setIcon(new ImageIcon("src\\resources//gui//icons//control-stop.png"));
		stopButton.setPreferredSize(new Dimension(25, 25));
		stopButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				XForce.stop();
				xStatistic.reset();
				XMainPanel.mapPanel.reset();
				XMainPanel.mapInfoPanel.reset();
				XMainPanel.eventsInfoPanel.reset();
				XMainPanel.cellInfoPanel.reset();
				startButton.setEnabled(true);
				pauseButton.setEnabled(false);
				stopButton.setEnabled(false);
			}
		});
		add(stopButton);
	}

}

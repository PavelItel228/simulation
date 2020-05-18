package entity.humans;

import x.gui.main.XMainPanel;
import x.gui.map.XMapPanel;
import x.logic.random.XRandom;

import static x.logic.force.XForce.isCellInMapRange;

public class Man{
    public final static int HUMAN_TYPE_MAN = 0x1;
    public final static int HUMAN_TYPE_COLOR_MAN = 0x1D8EA3;

    public boolean tryToMakeChild(long cellData, int y, int x, int date) {
        XMapPanel map = XMainPanel.mapPanel;
        if (map.getHumanTypeAt(y, x) == Woman.HUMAN_TYPE_WOMAN && map.getHumanPregnancyAt(y, x) == 0) {
            for (int yShift = -1; yShift < 2; yShift++) {
                for (int xShift = -1; xShift < 2; xShift++) {
                    int yTarget = y + yShift;
                    int xTarget = x + xShift;
                    if (isCellInMapRange(yTarget, xTarget)
                            && map.getHumanTypeAt(yTarget, xTarget) == Man.HUMAN_TYPE_MAN
                            && map.getActiveFlagHumanAt(yTarget, xTarget) == 1) {
                        boolean decision = XRandom.generateBoolean(Woman.getPregnancyChance());
                        if (decision) {
                            map.setHumanPregnancyAt(1, y, x);
                            map.setHumanEnergyAt(map.getHumanEnergyAt(y, x) - 4, y, x);
                            map.setHumanSatietyAt(map.getHumanSatietyAt(y, x) - 4, y, x);
                            map.setHumanAgeAt(map.getHumanAgeAt(y, x) + 1, y, x);
                            map.setActiveFlagHumanAt(0, y, x);
                            map.setHumanEnergyAt(map.getHumanEnergyAt(yTarget, xTarget) - 4, yTarget, xTarget);
                            map.setHumanSatietyAt(map.getHumanSatietyAt(yTarget, xTarget) - 4, yTarget, xTarget);
                            map.setHumanAgeAt(map.getHumanAgeAt(yTarget, xTarget) + 1, yTarget, xTarget);
                            map.setActiveFlagHumanAt(0, yTarget, xTarget);
                            XMainPanel.eventsInfoPanel.update(date, "Woman got pregnant.");
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}

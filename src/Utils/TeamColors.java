package Utils;

import java.awt.*;

public class TeamColors {

    private static Color[] colors;

    public static void init(int maxNumOfTeams) {
        colors = new Color[maxNumOfTeams];
        for (int i = 0; i < maxNumOfTeams; i++) {
            colors[i] = new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
            System.out.println(colors[i].toString());
        }
    }

    public static Color getMyColor(int teamID) {
        return colors[teamID];
    }

}
package Model.UI.Entity_Formations;

import Utils.AssetManager;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import static Controlador.Controller.*;

/***
 * Gestiona el menu lateral con los diferentes modos de formacion dsiponibles.
 * Si se modifica el modo de formacion, Organizer es informado
 */

public class FormationVisualitzer {

    private static double y, w, h;

    public static void init() {

        //75 x 75 buttons 7.5 margin
        w = 82;
        //* 3 buttons and 5px margins
        h = 75 * 3 + 20;
        y = gameHeight / 2.0 - h / 2;
    }

    public static boolean mouseOnVisualitzer() {
        return mouseX >= 0 && mouseX < w && mouseY >= y && mouseY <= y + h;
    }

    private static void activateFormationMode(int i) {
        Organizer.setMode(i);
    }

    public static void mousePressed() {
        double x = 3.75;
        double ay = y + 5;
        for (int i = 0; i < 3; i++) {
            if (mouseX >= x && mouseX <= x + 75 && mouseY >= ay && mouseY <= ay + 75) {
                activateFormationMode(i);
                break;
            }
            ay += 85;
        }
    }

    public static void render(Graphics2D g) {
        g.setColor(Color.black);
        g.fill(new Rectangle2D.Double(0, y, w, h));
        g.setColor(Color.white);
        double x = 3.75;
        double ay = y + 5;
        for (int i = 0; i < 3; i++) {
            g.drawImage(AssetManager.getImage("formation_mode" + i + Organizer.isModeOn(i) + ".png", 75, 75), (int) x, (int) ay, null);
            ay += 80;
        }
    }

}

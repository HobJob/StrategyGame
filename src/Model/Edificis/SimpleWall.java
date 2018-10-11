package Model.Edificis;

import Model.Actions.Action;
import Model.Actions.DestroyMeAction;
import Model.DataContainers.ObjectInfo;
import Model.UI.Map.Minimap;
import Model.UI.Mouse_Area_Selection.MouseSelector;
import Model.Unitats.Unit_Training.Trainable;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;

public class SimpleWall extends Building {


    public static final int PRICE = 50;
    public static final int HP = 75;
    public static final int THICKNESS = 5;

    private double outerRad;

    public SimpleWall(double x, double y, int outerRad, int team, boolean createWithoutBuilingManager) {
        super(x, y, outerRad, outerRad, HP, PRICE, team);

        this.outerRad = outerRad;

        Action actions[] = new Action[1];
        actions[0] = new DestroyMeAction(this);
        if (createWithoutBuilingManager) {
            Minimap.add(this);
            MouseSelector.add(this);
        }

        objectInfo = new ObjectInfo(HP, HP, 0, 1, 0, 0, team, "prev_wall.png", actions);
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.red);

        Shape outerRect = new Rectangle2D.Double(x - outerRad / 2.0, y - outerRad / 2.0, outerRad, outerRad);
        double innerRad = outerRad - THICKNESS;
        Shape innerRect = new Rectangle2D.Double(x - innerRad / 2.0, y - innerRad / 2.0, innerRad, innerRad);
        Area a = new Area(outerRect);
        a.subtract(new Area(innerRect));
        g.fill(a);
    }

    @Override
    public void trainCompleted(Trainable t) {

    }

    @Override
    public ObjectInfo getInfo() {
        return objectInfo;
    }

}

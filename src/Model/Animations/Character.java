package Model.Animations;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/*
1. WEAPON
2. HANDS
3. HEAD
4. BELT
5. TORSO
6. LEGS
7. FEET
8. BODY
9. BEHIND
*/

public class Character {

    public static final int SIZE = 32;

    private Map<String, Animation> animations;
    private int actualAnimation;
    private String[] complementos;


    //TORSO_leather_armor_torso.png
    //TORSO_robe_shirt_brown.png
    private String animationNames[] = {
            "up_idle",
            "up_m",
            "left_idle",
            "left_m",
            "down_idle",
            "down_m",
            "right_idle",
            "right_m"
    };

    public Character(int idlePeriod, int movePeriod, String... complementos) {
        actualAnimation = 4;
        this.complementos = complementos;
        animations = new HashMap<>();

        for (String s : complementos) {

            animations.put("up_idle_" + s, new Animation(SheetCutter.obtainFrames(s, 0, 0, SIZE, SIZE, 1), idlePeriod));
            animations.put("up_m_" + s, new Animation(SheetCutter.obtainFrames(s, SIZE, 0, SIZE, SIZE, 8), movePeriod));

            animations.put("left_idle_" + s, new Animation(SheetCutter.obtainFrames(s, 0, SIZE, SIZE, SIZE, 1), idlePeriod));
            animations.put("left_m_" + s, new Animation(SheetCutter.obtainFrames(s, SIZE, SIZE, SIZE, SIZE, 8), movePeriod));

            animations.put("down_idle_" + s, new Animation(SheetCutter.obtainFrames(s, 0, SIZE * 2, SIZE, SIZE, 1), idlePeriod));
            animations.put("down_m_" + s, new Animation(SheetCutter.obtainFrames(s, SIZE, SIZE * 2, SIZE, SIZE, 8), movePeriod));

            animations.put("right_idle_" + s, new Animation(SheetCutter.obtainFrames(s, 0, SIZE * 2 + SIZE, SIZE, SIZE, 1), idlePeriod));
            animations.put("right_m_" + s, new Animation(SheetCutter.obtainFrames(s, SIZE, SIZE * 2 + SIZE, SIZE, SIZE, 8), movePeriod));
        }
    }

    public void changeAnimation(int i) {
        actualAnimation = i;
    }

    public int getSize() {
        return SIZE;
    }

    public Image[] getFrame() {
        Image[] images = new Image[complementos.length];
        int i = 0;
        for (String s : complementos) {
            images[i++] = animations.get(animationNames[actualAnimation] + "_" + s).getFrame();
        }
        return images;
    }

    public long getLastTime() {
        return animations.get(animationNames[actualAnimation] + "_" + complementos[0]).getLastTime();
    }

    public long getPeriod() {
        return animations.get(animationNames[actualAnimation] + "_" + complementos[0]).getPeriod();
    }

    public void nextFrame() {
        for (String c : complementos)
            animations.get(animationNames[actualAnimation] + "_" + c).nextFrame();
    }

    public void updateLastTime(long currentTimeMillis) {
        animations.get(animationNames[actualAnimation] + "_" + complementos[0]).updateLastTime(currentTimeMillis);
    }

    public void stop() {
        actualAnimation--;
    }

    public enum WEAR {
        BASIC("BODY_male_walkcycle_small.png", "FEET_shoes_brown_walkcycle_small.png", "LEGS_robe_skirt_walkcycle_small",
                "TORSO_robe_shirt_brown_walkcycle_small.png", "TORSO_leather_armor_bracers_walkcycle_small.png",
                "BELT_leather_walkcycle_small.png", "HEAD_chain_armor_hood_walkcycle_small.png", "HANDS_plate_armor_gloves_walkcycle_small.png"),
        HEAVY("");

        private String[] comp;

        WEAR(String... complementos) {
            comp = complementos;
        }

        public String[] getComp() {
            return comp;
        }

    }
}

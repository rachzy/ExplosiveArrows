package me.rachzy.explosivearrows.data;

import me.rachzy.explosivearrows.models.ExplosiveArrow;
import me.rachzy.explosivearrows.models.FiredExplosiveArrow;

import java.util.ArrayList;
import java.util.List;

public class FiredExplosiveArrows {
    private static List<FiredExplosiveArrow> firedArrows = new ArrayList<>();

    public static void createFiredArrow(FiredExplosiveArrow arrow) {
        firedArrows.add(arrow);
    }

    public static List<FiredExplosiveArrow> getFiredArrows() {
        return firedArrows;
    }

    public static FiredExplosiveArrow getFiredArrow(Integer uuid) {
        return firedArrows.stream().filter(arrow -> arrow.getUuid().equals(uuid)).findFirst().orElse(null);
    }
}

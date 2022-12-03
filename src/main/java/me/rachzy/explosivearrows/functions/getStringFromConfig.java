package me.rachzy.explosivearrows.functions;

import me.rachzy.explosivearrows.ExplosiveArrows;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.*;

public class getStringFromConfig {
    public static String byName(String key) {
        FileConfiguration config = ExplosiveArrows.getPlugin(ExplosiveArrows.class).getConfig();
        return ChatColor.translateAlternateColorCodes('&', config.getString(key));
    }

    public static List<String> byListName(String key) {
        FileConfiguration config = ExplosiveArrows.getPlugin(ExplosiveArrows.class).getConfig();
        List<String> loreWithColors = new ArrayList<>();

        for (String s : config.getStringList(key)) {
            loreWithColors.add(ChatColor.translateAlternateColorCodes('&', s));
        }

        return loreWithColors;
    }
}

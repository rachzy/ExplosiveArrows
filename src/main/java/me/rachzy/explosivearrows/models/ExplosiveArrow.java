package me.rachzy.explosivearrows.models;

import me.rachzy.explosivearrows.ExplosiveArrows;
import me.rachzy.explosivearrows.functions.getStringFromConfig;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ExplosiveArrow {
    FileConfiguration config = ExplosiveArrows.getPlugin(ExplosiveArrows.class).getConfig();
    ItemStack arrows;
    ItemMeta arrowMeta;
    public ExplosiveArrow(Integer amount) {
        this.arrows = new ItemStack(Material.ARROW, amount);

        this.arrowMeta = arrows.getItemMeta();
        this.arrowMeta.setDisplayName(getStringFromConfig.byName("explosivearrow_name"));
        this.arrowMeta.setLore(getStringFromConfig.byListName("explosivearrow_lore"));

        this.arrows.setItemMeta(arrowMeta);
    }

    public ItemStack getArrows() {
        return arrows;
    }
}

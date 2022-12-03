package me.rachzy.explosivearrows.models;

import me.rachzy.explosivearrows.ExplosiveArrows;
import me.rachzy.explosivearrows.functions.getStringFromConfig;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ExplosiveBow {
    FileConfiguration config = ExplosiveArrows.getPlugin(ExplosiveArrows.class).getConfig();
    ItemStack bow;
    ItemMeta bowMeta;
    public ExplosiveBow() {
        this.bow = new ItemStack(Material.BOW, 1);;

        this.bowMeta = bow.getItemMeta();
        this.bowMeta.setDisplayName(getStringFromConfig.byName("explosivebow_name"));
        this.bowMeta.setLore(getStringFromConfig.byListName("explosivebow_lore"));

        if(config.getBoolean("explosivebow_infinite_enchantment")) {
            this.bowMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, false);
        }

        this.bow.setItemMeta(bowMeta);
    }

    public ItemStack getBow() {
        return bow;
    }
}

package me.rachzy.explosivearrows.events;

import me.rachzy.explosivearrows.ExplosiveArrows;
import me.rachzy.explosivearrows.data.FiredExplosiveArrows;
import me.rachzy.explosivearrows.models.ExplosiveArrow;
import me.rachzy.explosivearrows.models.FiredExplosiveArrow;
import me.rachzy.explosivearrows.functions.getStringFromConfig;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class ShootBowListener implements Listener {
    FileConfiguration config = ExplosiveArrows.getPlugin(ExplosiveArrows.class).getConfig();
    @EventHandler
    public boolean onArrowShoot(EntityShootBowEvent e) {
        //Check if the shooter is a player
        if(!(e.getEntity() instanceof Player)) {
            return true;
        }

        Player player = (Player) e.getEntity();

        //Check if the bow is an Explosive Bow
        //If it is, stops the execution, since explosive arrows won't be necessary
        if(player.getItemInHand().hasItemMeta() && player.getItemInHand().getItemMeta().getDisplayName().equals(getStringFromConfig.byName("explosivebow_name"))) {
            //Check if the player has the right permission to use the bow
            if(!player.hasPermission("explosivearrows.usebow")) {
                player.sendMessage(getStringFromConfig.byName("no_permission_message"));
                e.setCancelled(true);
            }
            return true;
        }

        //THIS PART WILL CHECK IF THE ARROW THAT WAS FIRED IS AN EXPLOSIVE ARROW

        //If the player has at least one explosive arrow
        if(player.getInventory().containsAtLeast(new ExplosiveArrow(1).getArrows(), 1)) {
            //Check if the player doesn't have permission to use an explosive bow

            //Searches for arrows on player inventory
            ItemStack firstArrowType = null; //The first arrow in player's inventory is always the one that will be fired
            for(int i = 0; i <= 36; i++) {
                if(firstArrowType == null) {
                    ItemStack itemSlot = player.getInventory().getItem(i);
                    if(itemSlot != null && itemSlot.getType() == Material.ARROW) {
                        firstArrowType = itemSlot;
                    }
                }
            }

            //If the arrow that will be fired is not an explosive arrow, stop the execution
            if(firstArrowType == null || !firstArrowType.hasItemMeta() || !firstArrowType.getItemMeta().getDisplayName().equals(getStringFromConfig.byName("explosivearrow_name"))) {
                return true;
            }

            Arrow arrow = (Arrow) e.getProjectile();
            FiredExplosiveArrows.createFiredArrow(new FiredExplosiveArrow(arrow.getEntityId()));
        }

        return true;
    }
}

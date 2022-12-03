package me.rachzy.explosivearrows.events;

import me.rachzy.explosivearrows.ExplosiveArrows;
import me.rachzy.explosivearrows.data.FiredExplosiveArrows;
import me.rachzy.explosivearrows.functions.getStringFromConfig;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;

public class ProjectileHitListener implements Listener {
    FileConfiguration config = ExplosiveArrows.getPlugin(ExplosiveArrows.class).getConfig();
    public void createExplosion(Player player, Arrow arrow, Location location) {
        if (player.hasPermission("explosivearrows.usearrows")) {
            location.getWorld().createExplosion(location,
                    config.getInt("arrow_explosion_power"),
                    config.getBoolean("arrow_fire_on_explosion_enabled"));
            arrow.remove();
        }
    }

    @EventHandler
    public boolean onProjectileHit(ProjectileHitEvent e) {
        //Check if the entity is not an arrow
        if (e.getEntityType() != EntityType.ARROW) {
            return true;
        }

        //Check if the sender is not a player
        if (!(e.getEntity().getShooter() instanceof Player)) {
            return true;
        }

        Arrow arrow = (Arrow) e.getEntity();
        Player player = (Player) e.getEntity().getShooter();
        ItemStack playerBow = player.getItemInHand();
        Location blockLocation = arrow.getLocation();

        //Check if the bow is an Explosive Bow
        if (playerBow.hasItemMeta() && playerBow.getItemMeta().getDisplayName().equals(getStringFromConfig.byName("explosivebow_name"))) {
            createExplosion(player, arrow, blockLocation);
            return true;
        }

        //Check if the arrow id is insert in the list of Fired ExplosiveArrows
        if (FiredExplosiveArrows.getFiredArrow(arrow.getEntityId()) != null) {
            createExplosion(player, arrow, blockLocation);
            return true;
        }

        //Check if the arrow doesn't have a display name metadata
        if (!arrow.hasMetadata("name")) {
            return true;
        }

        //Check if the arrow is an Explosive Arrow
        if (arrow.getMetadata("name").toString().equals(getStringFromConfig.byName("explosivearrow_name"))) {
            createExplosion(player, arrow, blockLocation);
            return true;
        }
        return true;
    }
}

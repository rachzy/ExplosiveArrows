package me.rachzy.explosivearrows;

import me.rachzy.explosivearrows.commands.ExplosiveArrowsCommand;
import me.rachzy.explosivearrows.commands.ExplosiveBowCommand;
import me.rachzy.explosivearrows.events.ProjectileHitListener;
import me.rachzy.explosivearrows.events.ShootBowListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class ExplosiveArrows extends JavaPlugin {
    @Override
    public void onEnable() {
        System.out.println("ExplosiveArrows plugin enabled...");

        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        getCommand("explosivebow").setExecutor(new ExplosiveBowCommand());
        getCommand("explosivearrows").setExecutor(new ExplosiveArrowsCommand());

        getServer().getPluginManager().registerEvents(new ProjectileHitListener(), this);
        getServer().getPluginManager().registerEvents(new ShootBowListener(), this);
    }

    @Override
    public void onDisable() {
        System.out.println("Disabling ExplosiveArrows plugin...");
    }
}

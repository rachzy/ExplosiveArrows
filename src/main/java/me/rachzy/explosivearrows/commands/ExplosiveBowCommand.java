package me.rachzy.explosivearrows.commands;

import me.rachzy.explosivearrows.ExplosiveArrows;
import me.rachzy.explosivearrows.models.ExplosiveBow;
import me.rachzy.explosivearrows.functions.getStringFromConfig;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.*;

public class ExplosiveBowCommand implements CommandExecutor {
    FileConfiguration config = ExplosiveArrows.getPlugin(ExplosiveArrows.class).getConfig();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //Check if the sender is not a player
        if(!(sender instanceof Player)) {
            sender.sendMessage(getStringFromConfig.byName("not_a_player_message"));
            return true;
        }

        //Gets the player
        Player player = (Player) sender;

        //Check if the player doesn't have permission to use the command
        if(!player.hasPermission("explosivearrows.getbow")) {
            player.sendMessage(getStringFromConfig.byName("no_permission_message"));
            return true;
        }

        //Creates the bow
        ExplosiveBow bow = new ExplosiveBow();

        //Gives the bow to the player
        player.getInventory().addItem(bow.getBow());
        player.sendMessage(getStringFromConfig.byName("getbow_success_message"));

        return true;
    }


}

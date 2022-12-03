package me.rachzy.explosivearrows.commands;

import me.rachzy.explosivearrows.ExplosiveArrows;
import me.rachzy.explosivearrows.models.ExplosiveArrow;
import me.rachzy.explosivearrows.functions.getStringFromConfig;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class ExplosiveArrowsCommand implements CommandExecutor {
    FileConfiguration config = ExplosiveArrows.getPlugin(ExplosiveArrows.class).getConfig();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //Check if the commands sender is not a player
        if(!(sender instanceof Player)) {
            sender.sendMessage(getStringFromConfig.byName("not_a_player_message"));
            return true;
        }

        //Gets the player
        Player player = (Player) sender;

        //Check if the player doesn't have permission to use the command
        if(!player.hasPermission("explosivearrows.getarrows")) {
            player.sendMessage(getStringFromConfig.byName("no_permission_message"));
            return true;
        }

        //Check if the command doesn't contain args
        if(args.length == 0) {
            player.sendMessage(getStringFromConfig.byName("getarrows_no_args_message"));
            return true;
        }

        try {
            int amount = Integer.parseInt(args[0]);

            if(amount < 1 || amount > 64) {
                player.sendMessage(getStringFromConfig.byName("getarrows_invalid_amount_message"));
                return true;
            }

            //Create the arrows
            ExplosiveArrow arrows = new ExplosiveArrow(amount);

            //Gives the arrows to the player
            player.getInventory().addItem(arrows.getArrows());
            player.sendMessage(String.format(getStringFromConfig.byName("getarrows_success_message"), amount));
        }catch (NumberFormatException e) {
            player.sendMessage(getStringFromConfig.byName("getarrows_must_be_integer_message"));
            return true;
        }

        return true;
    }
}

package com.softwaremongers.voidtotem.commands.subcommands;

import com.softwaremongers.voidtotem.ItemManager;
import com.softwaremongers.voidtotem.Main;
import com.softwaremongers.voidtotem.commands.CommandManager;
import com.softwaremongers.voidtotem.commands.SubCommand;
import com.softwaremongers.voidtotem.recipes.VoidTotem;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.List;

public class HelpCommand extends SubCommand {

    @Override
    public String getName(){
        return "help";
    }

    @Override
    public String getDescription(){
        return "Get a little help.";
    }

    @Override
    public String getSyntax(){
        return "/totem help";
    }

    @Override
    public void perform(Player player, String[] args){
        if(args.length == 1){
            player.sendMessage("Void Totem Help:");
            player.sendMessage("Green arguments are " + ChatColor.GREEN + "required");
            player.sendMessage("Yellow arguments are " + ChatColor.YELLOW + "optional");
            player.sendMessage("==============================");
            player.sendMessage("/totem setworld " + ChatColor.GREEN + "worldname " + ChatColor.YELLOW + "<x,y,z>");
            player.sendMessage("Set the world that players in the void teleport to when holding a totem. Default uses players current x and z coordinates.");
            player.sendMessage("Simply use /totem setworld " + ChatColor.GREEN + "worldname "+ ChatColor.WHITE + "to stop using custom coordinates.");
            player.sendMessage("==============================");
            player.sendMessage("/totem givetotem");
            player.sendMessage("Give yourself a totem. :)");
        }
    }

    @Override
    public List<String> getSubcommandArguments(Player player, String[] args) {
        return null;
    }

}

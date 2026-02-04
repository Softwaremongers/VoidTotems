package com.softwaremongers.voidtotem.commands.subcommands;

import com.softwaremongers.voidtotem.ItemManager;
import com.softwaremongers.voidtotem.commands.SubCommand;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.List;

public class ToggleSetLocationCommand extends SubCommand {

    //TODO:COPIED FROM GIVETOTEMCOMMAND.JAVA, PLEASE FINISH

    @Override
    public String getName(){
        return "givetotem";
    }

    @Override
    public String getDescription(){
        return "Give yourself a totem.";
    }

    @Override
    public String getSyntax(){
        return "/totem givetotem";
    }

    @Override
    public void perform(Player player, String[] args){
        if(args.length > 0){
            if(player.hasPermission("voidtotem.give")){
                if(args.length == 1){
                    //give player totem
                    player.getInventory().addItem(new ItemManager().VoidTotem());

                }
            }else{
                player.sendMessage(ChatColor.RED + "You do not have permission to do that.");
            }
        }
    }

    @Override
    public List<String> getSubcommandArguments(Player player, String[] args) {
        return null;
    }

}

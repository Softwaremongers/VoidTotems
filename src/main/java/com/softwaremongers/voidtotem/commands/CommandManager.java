package com.softwaremongers.voidtotem.commands;

import com.softwaremongers.voidtotem.Main;
import com.softwaremongers.voidtotem.commands.subcommands.GiveTotemCommand;
import com.softwaremongers.voidtotem.commands.subcommands.HelpCommand;
import com.softwaremongers.voidtotem.commands.subcommands.SetWorldCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CommandManager implements TabExecutor {

    private ArrayList<SubCommand> subCommands = new ArrayList<>();

    public CommandManager(){
        subCommands.add(new SetWorldCommand());
        subCommands.add(new GiveTotemCommand());
        subCommands.add(new HelpCommand());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){


        if (sender instanceof Player){
            Player p = (Player) sender;

            //p.sendMessage(ChatColor.GREEN + "arg count is: " + args.length);
            if (args.length == 0){ return false; }
            else if(args.length > 0){
                for(int i = 0; i < this.getSubCommands().size(); i++){
                    if(args[0].equalsIgnoreCase(this.getSubCommands().get(i).getName())){
                        getSubCommands().get(i).perform(p, args);
                    }
                }
            }


        }
        return true;
    }

    public ArrayList<SubCommand> getSubCommands(){
        return subCommands;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args){
        if(args.length == 1){
            ArrayList<String> subcommandArgs = new ArrayList<>();

            for(int i = 0; i < getSubCommands().size(); i++){
                subcommandArgs.add(getSubCommands().get(i).getName());
            }
            return subcommandArgs;
        }else if(args.length >= 2){
            for(int i = 0; i < this.getSubCommands().size(); i++){
                if(args[0].equalsIgnoreCase(this.getSubCommands().get(i).getName())){
                    return getSubCommands().get(i).getSubcommandArguments((Player) sender, args);
                }
            }
        }
        return  null;
    }
}


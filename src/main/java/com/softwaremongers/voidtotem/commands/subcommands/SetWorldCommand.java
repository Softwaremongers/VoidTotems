package com.softwaremongers.voidtotem.commands.subcommands;

import com.softwaremongers.voidtotem.Main;
import com.softwaremongers.voidtotem.commands.SubCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class SetWorldCommand extends SubCommand {

    JavaPlugin plugin = Main.getPlugin(Main.class);
    Logger logger = plugin.getLogger();
    FileConfiguration config = plugin.getConfig();

    @Override
    public String getName(){
        return "setworld";
    }

    @Override
    public String getDescription(){
        return "Set the spawn world. set specific coordinates if you feel so inclined.";
    }

    @Override
    public String getSyntax(){
        return "/totem setworld worldname <x,y,z>";
    }

    @Override
    public void perform(Player player, String[] args){
        if(args.length > 1){
            if(player.hasPermission("voidtotem.setworld")){
                if(args.length == 2){
                    //TODO: TRY CATCH THIS
                    //setworld with dynamic coords
                    config.set("useSetLocation", false);
                    config.set("totemworld", args[1]);
                    //TODO: lang file
                    player.sendMessage(ChatColor.GREEN + "The world for totems to teleport players to has been set to: " + args[1]);
                }else if(args.length == 3){
                    //TODO: TRY CATCH THIS
                    //setworld with "here" arg
                    if(args[2].compareToIgnoreCase("here") == 0){
                        config.set("useSetLocation", true);
                        config.set("totemworld", args[1]);
                        config.set("spawnX", player.getLocation().getX());
                        config.set("spawnY", player.getLocation().getY());
                        config.set("spawnZ", player.getLocation().getZ());
                        config.set("spawnR", player.getLocation().getYaw());
                        //TODO: lang file
                        player.sendMessage(ChatColor.GREEN + "The world for totems to teleport players to has been set to: XXX");
                    }
                }else if(args.length == 5){
                    //TODO: TRY CATCH THIS
                    //setworld with given coords
                    config.set("useSetLocation", true);
                    config.set("totemworld", args[1]);
                    config.set("spawnX", parseDouble(args[2]));
                    config.set("spawnY", parseDouble(args[3]));
                    config.set("spawnZ", parseDouble(args[4]));
                    //TODO: lang file
                    player.sendMessage(ChatColor.GREEN + "The world for totems to teleport players to has been set to: " + args[1] + " at: " + args[2] + ", " + args[3] + ", " + args[4]);
                }else{
                    //TODO: ERROR MESSAGE, NO CHANGES TO CONFIG
                }
                plugin.saveConfig();
            }else{
                //TODO: lang file
                player.sendMessage(ChatColor.RED + "You do not have permission to do that.");
            }
        }
    }

    @Override
    public List<String> getSubcommandArguments(Player player, String[] args) {

        //Autofill the args
        if(args.length == 2){
            List<String> worldNames = new ArrayList<>();
            World[] worlds = new World[Bukkit.getServer().getWorlds().size()];
            Bukkit.getServer().getWorlds().toArray(worlds);
            for (int i = 0; i < worlds.length; i++){
                worldNames.add(worlds[i].getName());
            }
            return  worldNames;
        }else if(args.length == 3){
            List<String> coords = new ArrayList<>();
            String x = String.valueOf(player.getLocation().getX());
            coords.add(x);
            coords.add("here");
            return  coords;
        }else if(args.length == 4){
            List<String> coords = new ArrayList<>();
            String y = String.valueOf(player.getLocation().getY());
            coords.add(y);
            return  coords;
        }else if(args.length == 5){
            List<String> coords = new ArrayList<>();
            String z = String.valueOf(player.getLocation().getZ());
            coords.add(z);
            return  coords;
        }

        return null;
    }

}

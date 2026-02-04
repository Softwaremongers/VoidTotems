package com.softwaremongers.voidtotem.events;

import com.softwaremongers.voidtotem.ItemManager;
import com.softwaremongers.voidtotem.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityResurrectEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class VoidResurrect {

    //TODO: read from lang file
    String teleMessage = "You have been returned to the mortal realm by the dark power of the Void Totem!";
    String errMessage = "Error teleporting player. Is the world set in the config?";

    JavaPlugin plugin = Main.getPlugin(Main.class);
    Logger logger = plugin.getLogger();

    Location teleLocation;
    ItemStack totem = new ItemManager().VoidTotem();
    Player player;

    //get config settings
    FileConfiguration config = plugin.getConfig();
    boolean useCoords = config.getBoolean("useSetLocation");
    String world = config.getString("totemworld");

    public VoidResurrect(EntityResurrectEvent e){
        //set entity as player
        player = (Player) e.getEntity();

        if (player.getInventory().contains(totem)){
            double spawnX, spawnY, spawnZ;
            if(useCoords){
                spawnX = config.getDouble("spawnX");
                spawnY = config.getDouble("spawnY");
                spawnZ = config.getDouble("spawnZ");
                //TODO: lang file
                //player.sendMessage("Set coords are " + spawnX + ", " + spawnY + ", " + spawnZ);
            }
            else{
                spawnX = player.getLocation().getX();
                spawnY = 320;
                spawnZ = player.getLocation().getZ();
                //TODO: lang file
                //player.sendMessage("Current pos is " + spawnX + ", " + spawnY + ", " + spawnZ);
            }
            //create location to TP player to. Set Yaw if also using coords.
            if(!useCoords)
                teleLocation = new Location(Bukkit.getWorld(world), spawnX, spawnY, spawnZ, player.getLocation().getYaw(), player.getLocation().getPitch());
            else
                teleLocation = new Location(Bukkit.getWorld(world), spawnX, spawnY, spawnZ, (float)config.getDouble("spawnR"), 0f);

            // Try to teleport player, catch error and log.
            try{
                player.teleport(teleLocation);
                e.setCancelled(true);
                player.getInventory().removeItem(totem);
                player.sendMessage(ChatColor.DARK_GRAY + teleMessage);
            }catch (IllegalArgumentException err){
                player.sendMessage(ChatColor.BLUE + errMessage);
                logger.info(err.toString());
                logger.info(errMessage);
            }
        }

    }

}

package com.softwaremongers.voidtotem.events;

import com.softwaremongers.voidtotem.Main;
import com.softwaremongers.voidtotem.utils.UpdateChecker;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityResurrectEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class EventManager implements Listener {

    JavaPlugin plugin = Main.getPlugin(Main.class);
    Logger logger = plugin.getLogger();
    boolean easyMode = true;
    //boolean easyMode = plugin.getConfig().getBoolean("easyMode");

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        if(!e.getPlayer().isOp())
            return;

        new UpdateChecker(plugin, 93938).getVersion(version -> {
            if (!plugin.getDescription().getVersion().equalsIgnoreCase(version)) {
                e.getPlayer().sendMessage(ChatColor.YELLOW + "A new version of void totems has been released. (New: " + version + ", Installed: " + plugin.getDescription().getVersion());
            }
        });
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent e){
        //do nothing if easy mode disabled
        if(!easyMode)
            return;

        if (e.getEntity() instanceof Player) {
            if(e.getCause() == EntityDamageEvent.DamageCause.VOID)
                new VoidDamage(e);
            else if(e.getCause() == EntityDamageEvent.DamageCause.FIRE)
                return;
        }
    }

    //TODO: Add hard mode
    @EventHandler
    public void onResurrect(EntityResurrectEvent e){
        //do nothing if easy mode enabled
        if(easyMode)
            return;

        if (e.getEntity() instanceof Player) {
            return;
        }
    }

}

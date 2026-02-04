package com.softwaremongers.voidtotem;

import com.softwaremongers.voidtotem.bstats.Metrics;
import com.softwaremongers.voidtotem.commands.CommandManager;
import com.softwaremongers.voidtotem.events.EventManager;
import com.softwaremongers.voidtotem.recipes.RecipesManager;
import com.softwaremongers.voidtotem.utils.UpdateChecker;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class Main extends JavaPlugin {
    private final Logger logger = this.getLogger();

    @Override
    public void onEnable() {
        // Check For Updates
        new UpdateChecker(this, 93938).getVersion(version -> {
            if (!this.getDescription().getVersion().equalsIgnoreCase(version)) {
                logger.info("New version of void totems is available! New version: " + version + ", Installed version: " + this.getDescription().getVersion());
            }
        });

        //load config
        this.saveDefaultConfig();
        FileConfiguration config = getConfig();

        //load recipes
        new RecipesManager(this);

        //startups
        this.getServer().getPluginManager().registerEvents(new EventManager(), this); //register event manager
        //TODO: add alias "vt" to totem command
        getCommand("totem").setExecutor(new CommandManager()); //register command manager
        new Metrics(this, 11930); //bstats

        logger.info("Success!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        saveConfig();
        getLogger().info("Plugin Disabling, Goodbye! :)");
    }

}

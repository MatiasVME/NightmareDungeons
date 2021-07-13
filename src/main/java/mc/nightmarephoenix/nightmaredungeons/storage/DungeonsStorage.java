package mc.nightmarephoenix.nightmaredungeons.storage;

import mc.nightmarephoenix.nightmaredungeons.util.Global;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;

public class DungeonsStorage {

    private String defaultDungeons[] = {"dungeon1.yml", "dungeon2.yml"};

    public DungeonsStorage() {
        saveDefaultConfig();
    }

    /**
     * Reloads the general config file with the all anchors.
     */
    public void reloadConfig() {
        if(this.configFile == null) {
            this.configFile = new File(
                    Global.plugin.getDataFolder() + File.separator + "dungeons", "dungeons.yml"
            );
        }
        this.dataConfig = YamlConfiguration.loadConfiguration(this.configFile);
        InputStream defaultStream = Global.plugin.getResource( "dungeons.yml");

        if(defaultStream != null) {
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
            this.dataConfig.setDefaults(defaultConfig);
        }
    }

    /**
     * Returns the config.
     * @return
     */
    public FileConfiguration getConfig() {
        if(this.dataConfig == null) {
            reloadConfig();
        }
        return this.dataConfig;
    }

    /**
     * Saves the config.
     */
    public void saveConfig() {
        if(this.dataConfig == null || this.configFile == null) {
            return;
        }
        try {
            this.getConfig().save(this.configFile);
        } catch (IOException e) {
            Global.plugin.getLogger().log(Level.SEVERE, "Could not save config to " + this.configFile, e);
        }
    }

    /**
     * Saves a default config.
     */
    public void saveDefaultConfig() {
        for(int i = 0; i < defaultDungeons.length; i++) {
            if(this.configFile == null) {
                this.configFile = new File(
                        Global.plugin.getDataFolder() + File.separator + "dungeons",
                        defaultDungeons[i]
                );
            }
            if(!this.configFile.exists()) {
                if (!configFile.exists()) {
                    configFile.getParentFile().mkdirs();
                    Global.plugin.saveResource(
                            "dungeons" + File.separator + defaultDungeons[i],
                            false
                    );
                }

                dataConfig = new YamlConfiguration();
                try {
                    dataConfig.load(configFile);
                } catch (IOException | InvalidConfigurationException e) {
                    e.printStackTrace();
                }
            }
            configFile = null;
        }

    }

    private FileConfiguration dataConfig = null;
    private File configFile = null;
}

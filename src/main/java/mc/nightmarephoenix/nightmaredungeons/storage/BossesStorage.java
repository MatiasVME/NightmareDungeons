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

public class BossesStorage {

    private String defaultBosses[] = {"boss1.yml"};

    public BossesStorage() {
        saveDefaultConfig();
    }

    /**
     * Reloads a user config.
     */
    public void reloadConfig() {
        if(this.configFile == null) {
            this.configFile = new File(
                    Global.plugin.getDataFolder() + File.separator + "bosses", "enemies.yml"
            );
        }
        this.dataConfig = YamlConfiguration.loadConfiguration(this.configFile);
        InputStream defaultStream = Global.plugin.getResource("enemies.yml");

        if(defaultStream != null) {
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
            this.dataConfig.setDefaults(defaultConfig);
        }
    }

    public FileConfiguration getConfig() {
        if(this.dataConfig == null) {
            reloadConfig();
        }
        return this.dataConfig;
    }

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

    public void saveDefaultConfig() {
        for(int i = 0; i < defaultBosses.length; i++) {
            if(this.configFile == null) {
                this.configFile = new File(
                        Global.plugin.getDataFolder() + File.separator + "bosses",
                        defaultBosses[i]
                );
            }
            if(!this.configFile.exists()) {
                if (!configFile.exists()) {
                    configFile.getParentFile().mkdirs();
                    Global.plugin.saveResource(
                            "bosses" + File.separator + defaultBosses[i],
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

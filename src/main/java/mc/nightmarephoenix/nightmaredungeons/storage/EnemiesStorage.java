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

public class EnemiesStorage {

    private String defaultEnemies[] = {"enemy1.yml", "enemy2.yml"};

    public EnemiesStorage() {
        saveDefaultConfig();
    }

    /**
     * Reloads a user config.
     */
    public void reloadConfig() {
        if(this.configFile == null) {
            this.configFile = new File(
                    Global.plugin.getDataFolder() + File.separator + "enemies", "enemies.yml"
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
        for(int i = 0; i < defaultEnemies.length; i++) {
            if(this.configFile == null) {
                this.configFile = new File(
                        Global.plugin.getDataFolder() + File.separator + "enemies",
                        defaultEnemies[i]
                );
            }
            if(!this.configFile.exists()) {
                if (!configFile.exists()) {
                    configFile.getParentFile().mkdirs();
                    Global.plugin.saveResource(
                            "enemies" + File.separator + defaultEnemies[i],
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

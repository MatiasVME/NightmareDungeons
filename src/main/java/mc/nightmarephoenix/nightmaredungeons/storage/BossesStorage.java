package mc.nightmarephoenix.nightmaredungeons.storage;

import mc.nightmarephoenix.nightmaredungeons.util.Global;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;

public class BossesStorage {

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
        if(this.configFile == null) {
            this.configFile = new File(
                    Global.plugin.getDataFolder() + File.separator + "bosses",
                    "boss1.yml"
            );
        }
        if(!this.configFile.exists()) {
            try {
                new File(Global.plugin.getDataFolder() + File.separator + "bosses").mkdir();
                new File(Global.plugin.getDataFolder() + File.separator + "bosses", "boss1.yml").createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private FileConfiguration dataConfig = null;
    private File configFile = null;

}

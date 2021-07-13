package mc.nightmarephoenix.nightmaredungeons.storage;

import mc.nightmarephoenix.nightmaredungeons.util.Global;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.*;

public class Messages {

    private static final String[] defaultLang = {"en_US.yml", "es_ES.yml"};

    /**
     * Reloads a user config.
     */
    public static void reloadConfig() {

        if(configFolder.exists()) {
            InputStream defaultStream = null;
            try {
                defaultStream = new FileInputStream(
                        Global.plugin.getDataFolder() + File.separator + "lang" + File.separator + Global.plugin.getConfig().getString("lang")
                );
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            if(defaultStream != null) {
                YamlConfiguration conf = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
                dataConfig = conf;
                configFile = new File(Global.plugin.getDataFolder() + File.separator + "lang" + Global.plugin.getConfig().getString("lang"));
            }
        }
    }

    public static FileConfiguration getConfig() {
        if(dataConfig == null) {
            reloadConfig();
        }
        return dataConfig;
    }

    public static void saveConfig() {
        try {
            dataConfig.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveDefaultConfig() {
        configFolder = new File(
                Global.plugin.getDataFolder() + File.separator + "lang"
        );
        if(!configFolder.exists()) {
            configFolder.getParentFile().mkdirs();
            for(int i = 0; i < defaultLang.length; i++) {
                Global.plugin.saveResource(
                        "lang" + File.separator + defaultLang[i],
                        false
                );
            }
        }
    }

    private static FileConfiguration dataConfig = null;
    private static File configFile = null;
    private static File configFolder = null;

}

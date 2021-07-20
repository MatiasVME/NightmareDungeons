package mc.nightmarephoenix.nightmaredungeons.storage;

import mc.nightmarephoenix.nightmaredungeons.util.Global;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.*;
import java.util.ArrayList;

public class EnemiesStorage {

    private static final String defaultEnemies[] = {"Generic_Zombie.yml", "Generic_Skeleton.yml"};

    public EnemiesStorage() {
        saveDefaultConfig();
    }

    /**
     * Reloads a user config.
     */
    public static void reloadConfig() {

        dataConfig.removeAll(dataConfig);

        if(configFolder.exists()) {
            for(File f: configFolder.listFiles()) {
                InputStream defaultStream = null;
                try {
                    defaultStream = new FileInputStream(f);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                if(defaultStream != null) {
                    YamlConfiguration conf = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
                    dataConfig.add(conf);
                    configFiles.add(f);
                }
            }
        }
    }

    public static ArrayList<FileConfiguration> getConfigs() {
        if(dataConfig.isEmpty()) {
            reloadConfig();
        }
        return dataConfig;
    }

    public static void saveConfig(FileConfiguration config) {
        for(FileConfiguration f : getConfigs()) {
            if(f.equals(config)) {
                try {
                    f.save(new File(String.valueOf(configFiles.get(dataConfig.indexOf(f)))));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    public static void saveDefaultConfig() {
        configFolder = new File(
                    Global.plugin.getDataFolder() + File.separator + "enemies"
        );
        if(!configFolder.exists()) { // load default enemies
            configFolder.getParentFile().mkdirs();
            for(int i = 0; i < defaultEnemies.length; i++) {
                Global.plugin.saveResource(
                        "enemies" + File.separator + defaultEnemies[i],
                        false
                );
            }
        }
    }

    // Linked lists
    private static ArrayList<FileConfiguration> dataConfig = new ArrayList<>();
    private static ArrayList<File> configFiles = new ArrayList<>();
    //

    private static File configFolder = null;
}

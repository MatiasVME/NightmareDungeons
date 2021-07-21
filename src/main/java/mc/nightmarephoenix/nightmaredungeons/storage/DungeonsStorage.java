package mc.nightmarephoenix.nightmaredungeons.storage;

import com.tchristofferson.configupdater.ConfigUpdater;
import mc.nightmarephoenix.nightmaredungeons.util.Global;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DungeonsStorage {

    private static final String defaultDungeons[] = {"dungeon1.yml", "dungeon2.yml"};

    public DungeonsStorage() {
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
                Global.plugin.getDataFolder() + File.separator + "dungeons"
        );
        if(!configFolder.exists()) { // load default dungeons
            configFolder.getParentFile().mkdirs();
            for(int i = 0; i < defaultDungeons.length; i++) {
                Global.plugin.saveResource(
                        "dungeons" + File.separator + defaultDungeons[i],
                        false
                );
            }
        }
    }

    public static void checkUpdate() {
        File folder = new File(Global.plugin.getDataFolder() + File.separator + "dungeons");
        for(File dungeonFile : folder.listFiles()) {
            File configFile = new File(folder, dungeonFile.getName());
            try {
                List<String> ignoredFields = new ArrayList<>();
                ignoredFields.addAll(
                        Arrays.asList(
                                "mobs-spawn"
                        ));

                ConfigUpdater.update(
                        Global.plugin,
                        "dungeons" + File.separator + "dungeon1.yml",
                        configFile,
                        ignoredFields
                );
            } catch (IOException e) {
                e.printStackTrace();
            }
            reloadConfig();
        }
    }

    // Linked lists
    private static ArrayList<FileConfiguration> dataConfig = new ArrayList<>();
    private static ArrayList<File> configFiles = new ArrayList<>();
    //

    private static File configFolder = null;
}

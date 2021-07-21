package mc.nightmarephoenix.nightmaredungeons.storage;

import com.tchristofferson.configupdater.ConfigUpdater;
import mc.nightmarephoenix.nightmaredungeons.util.Global;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public static void checkUpdate() {
        File folder = new File(Global.plugin.getDataFolder() + File.separator + "lang");
        for(File langFile : folder.listFiles()) {
            File configFile = new File(folder, langFile.getName());
            try {
                List<String> ignoredFields = new ArrayList<>();
                ignoredFields.addAll(
                        Arrays.asList(

                                ));

                ConfigUpdater.update(
                        Global.plugin,
                        "lang" + File.separator + "en_US.yml",
                        configFile,
                        ignoredFields
                );
            } catch (IOException e) {
                e.printStackTrace();
            }
            reloadConfig();
        }
    }

    private static FileConfiguration dataConfig = null;
    private static File configFile = null;
    private static File configFolder = null;

}

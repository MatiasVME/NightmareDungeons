package mc.nightmarephoenix.nightmaredungeons.enemies;

import mc.nightmarephoenix.nightmaredungeons.storage.EnemiesStorage;
import mc.nightmarephoenix.nightmaredungeons.util.ArmorSet;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import java.util.ArrayList;
import java.util.Set;

public class EnemiesManager {

    public static ArrayList<Enemy> getAllEnemies() {
        ArrayList<Enemy> enemies = new ArrayList<>();
        EnemiesStorage.getConfigs().forEach((enemyFile) -> {
            ItemStack helmet = null,
                      chestplate = null,
                      leggings = null,
                      boots = null;
            if(enemyFile.contains("armor")) {
                if(enemyFile.contains("armor.helmet")) {
                    helmet = new ItemStack(Material.getMaterial(enemyFile.getString("armor.helmet.item")));
                    if(enemyFile.contains("armor.helmet.enchants")) {
                        Set<String> helmetEnchants = enemyFile.getConfigurationSection("armor.helmet.enchants").getKeys(false);
                        for(String enchant: helmetEnchants) {
                            helmet.addEnchantment(Enchantment.getByKey(NamespacedKey.minecraft(enchant.toLowerCase())), enemyFile.getInt("armor.helmet.enchants." + enchant));
                        }
                    }
                }

                if(enemyFile.contains("armor.chestplate")) {
                    chestplate = new ItemStack(Material.getMaterial(enemyFile.getString("armor.chestplate.item")));
                    if(enemyFile.contains("armor.chestplate.enchants")) {
                        Set<String> chestplateEnchants = enemyFile.getConfigurationSection("armor.chestplate.enchants").getKeys(false);
                        for(String enchant: chestplateEnchants) {
                            chestplate.addEnchantment(Enchantment.getByKey(NamespacedKey.minecraft(enchant.toLowerCase())), enemyFile.getInt("armor.chestplate.enchants." + enchant));
                        }
                    }
                }

                if(enemyFile.contains("armor.leggings")) {
                    leggings = new ItemStack(Material.getMaterial(enemyFile.getString("armor.leggings.item")));
                    if(enemyFile.contains("armor.leggings.enchants")) {
                        Set<String> leggingsEnchants = enemyFile.getConfigurationSection("armor.leggings.enchants").getKeys(false);
                        for(String enchant: leggingsEnchants) {
                            leggings.addEnchantment(Enchantment.getByKey(NamespacedKey.minecraft(enchant.toLowerCase())), enemyFile.getInt("armor.leggings.enchants." + enchant));
                        }
                    }
                }

                if(enemyFile.contains("armor.boots")) {
                    boots = new ItemStack(Material.getMaterial(enemyFile.getString("armor.boots.item")));
                    if(enemyFile.contains("armor.boots.enchants")) {
                        Set<String> bootsEnchants = enemyFile.getConfigurationSection("armor.boots.enchants").getKeys(false);
                        for(String enchant: bootsEnchants) {
                            boots.addEnchantment(Enchantment.getByKey(NamespacedKey.minecraft(enchant.toLowerCase())), enemyFile.getInt("armor.boots.enchants." + enchant));
                        }
                    }
                }
            }

            ArmorSet armor = new ArmorSet(
                    helmet,
                    chestplate,
                    leggings,
                    boots
            );
            enemies.add(new Enemy(
                    enemyFile.getString("name"),
                    EntityType.valueOf(enemyFile.getString("base-mob")),
                    enemyFile.getDouble("health"),
                    enemyFile.getDouble("damage"),
                    enemyFile.getDouble("speed"),
                    armor
            ));
        });
        return enemies;
    }

}

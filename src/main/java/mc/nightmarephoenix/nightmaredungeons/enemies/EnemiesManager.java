package mc.nightmarephoenix.nightmaredungeons.enemies;

import mc.nightmarephoenix.nightmaredungeons.storage.EnemiesStorage;
import mc.nightmarephoenix.nightmaredungeons.util.ArmorSet;
import mc.nightmarephoenix.nightmaredungeons.util.Global;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

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

            ArrayList<PotionEffectType> potionEffects = new ArrayList<>();
            ArrayList<Integer> potionEffectsDuration = new ArrayList<>();
            if(enemyFile.contains("potion-effects")) {
                Set<String> effects = enemyFile.getConfigurationSection("potion-effects").getKeys(false);
                for(String effect: effects) {
                    potionEffects.add(PotionEffectType.getByName(effect));
                    potionEffectsDuration.add(enemyFile.getInt("potion-effects." + effect));
                }
            }

            ArrayList<ItemStack> drops = new ArrayList<>();
            if(enemyFile.contains("drops")) {
                if(!enemyFile.getString("drops").equalsIgnoreCase("MC-DEFAULT")) {
                    Set<String> dropsString = enemyFile.getConfigurationSection("drops").getKeys(false);
                    for(String drop : dropsString) {
                        drops.add(new ItemStack(Material.getMaterial(drop), enemyFile.getInt("drops." + drop)));
                    }
                }
            }

            enemies.add(new Enemy(
                    enemyFile.getString("name"),
                    EntityType.valueOf(enemyFile.getString("base-mob")),
                    enemyFile.getDouble("health"),
                    enemyFile.getDouble("damage"),
                    armor,
                    potionEffects,
                    potionEffectsDuration,
                    null,
                    drops
                ));
            });
                return enemies;
            }

    public static void spawnEnemy(Enemy enemy1, Location loc) {

        Enemy newEnemy = new Enemy(enemy1);

        LivingEntity entity = (LivingEntity) loc.getWorld().spawnEntity(loc, newEnemy.getBaseMob());

        entity.getEquipment().setHelmet(newEnemy.getArmor().getHelmet());
        entity.getEquipment().setChestplate(newEnemy.getArmor().getChestplate());
        entity.getEquipment().setLeggings(newEnemy.getArmor().getLeggings());
        entity.getEquipment().setBoots(newEnemy.getArmor().getBoots());

        entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(newEnemy.getHealth());
        entity.setHealth(entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
        entity.damage(newEnemy.getDamage());

        entity.setCanPickupItems(false);

        for(PotionEffectType effect: newEnemy.getPotionEffects()) {
            entity.addPotionEffect(effect.createEffect(10000, newEnemy.getPotionEffectsDuration().get(newEnemy.getPotionEffects().indexOf(effect))));
        }

        entity.setRemoveWhenFarAway(false);

        newEnemy.setEntity(entity);

        Global.spawnedEnemies.add(newEnemy);

    }

    public static void spawnEnemies(ArrayList<Enemy> enemies) {
        for(Enemy enemy : enemies) {
            spawnEnemy(enemy, enemy.getSpawnLocation());
        }
    }

}

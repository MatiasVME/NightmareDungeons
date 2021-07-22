package mc.nightmarephoenix.nightmaredungeons.bosses;

import mc.nightmarephoenix.nightmaredungeons.storage.BossesStorage;
import mc.nightmarephoenix.nightmaredungeons.util.ArmorSet;
import mc.nightmarephoenix.nightmaredungeons.util.Global;
import mc.nightmarephoenix.nightmaredungeons.util.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BossBar;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.Set;

public class BossManager {

    public static ArrayList<Boss> getAllBosses() {
        ArrayList<Boss> bosses = new ArrayList<>();

        BossesStorage.getConfigs().forEach((bossFile) -> {
            ItemStack helmet = null,
                    chestplate = null,
                    leggings = null,
                    boots = null;
            if(bossFile.contains("armor")) {
                if(bossFile.contains("armor.helmet")) {
                    helmet = new ItemStack(Material.getMaterial(bossFile.getString("armor.helmet.item")));
                    if(bossFile.contains("armor.helmet.enchants")) {
                        Set<String> helmetEnchants = bossFile.getConfigurationSection("armor.helmet.enchants").getKeys(false);
                        for(String enchant: helmetEnchants) {
                            helmet.addEnchantment(Enchantment.getByKey(NamespacedKey.minecraft(enchant.toLowerCase())), bossFile.getInt("armor.helmet.enchants." + enchant));
                        }
                    }
                }

                if(bossFile.contains("armor.chestplate")) {
                    chestplate = new ItemStack(Material.getMaterial(bossFile.getString("armor.chestplate.item")));
                    if(bossFile.contains("armor.chestplate.enchants")) {
                        Set<String> chestplateEnchants = bossFile.getConfigurationSection("armor.chestplate.enchants").getKeys(false);
                        for(String enchant: chestplateEnchants) {
                            chestplate.addEnchantment(Enchantment.getByKey(NamespacedKey.minecraft(enchant.toLowerCase())), bossFile.getInt("armor.chestplate.enchants." + enchant));
                        }
                    }
                }

                if(bossFile.contains("armor.leggings")) {
                    leggings = new ItemStack(Material.getMaterial(bossFile.getString("armor.leggings.item")));
                    if(bossFile.contains("armor.leggings.enchants")) {
                        Set<String> leggingsEnchants = bossFile.getConfigurationSection("armor.leggings.enchants").getKeys(false);
                        for(String enchant: leggingsEnchants) {
                            leggings.addEnchantment(Enchantment.getByKey(NamespacedKey.minecraft(enchant.toLowerCase())), bossFile.getInt("armor.leggings.enchants." + enchant));
                        }
                    }
                }

                if(bossFile.contains("armor.boots")) {
                    boots = new ItemStack(Material.getMaterial(bossFile.getString("armor.boots.item")));
                    if(bossFile.contains("armor.boots.enchants")) {
                        Set<String> bootsEnchants = bossFile.getConfigurationSection("armor.boots.enchants").getKeys(false);
                        for(String enchant: bootsEnchants) {
                            boots.addEnchantment(Enchantment.getByKey(NamespacedKey.minecraft(enchant.toLowerCase())), bossFile.getInt("armor.boots.enchants." + enchant));
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

            ArrayList<BossImmunities> immunities = new ArrayList<>();
            if(bossFile.contains("defence.immunities.explosion")) {
                if(bossFile.getBoolean("defence.immunities.explosion")) immunities.add(BossImmunities.EXPLOSION);
            }
            if(bossFile.contains("defence.immunities.fire")) {
                if(bossFile.getBoolean("defence.immunities.fire")) immunities.add(BossImmunities.FIRE);
            }
            if(bossFile.contains("defence.immunities.drowning")) {
                if(bossFile.getBoolean("defence.immunities.drowning")) immunities.add(BossImmunities.DROWNING);
            }
            if(bossFile.contains("defence.immunities.projectiles")) {
                if(bossFile.getBoolean("defence.immunities.projectiles")) immunities.add(BossImmunities.PROJECTILES);
            }
            if(bossFile.contains("defence.immunities.suffocation")) {
                if(bossFile.getBoolean("defence.immunities.suffocation")) immunities.add(BossImmunities.SUFFOCATION);
            }

            ArrayList<ItemStack> drops = new ArrayList<>();
            if(bossFile.contains("drops")) {
                if(!bossFile.getString("drops").equalsIgnoreCase("MC-DEFAULT")) {
                    Set<String> itemDrops = bossFile.getConfigurationSection("drops").getKeys(false);
                    for(String drop: itemDrops) {
                        drops.add(new ItemStack(Material.valueOf(drop), bossFile.getInt("drops." + drop)));
                    }
                }
            }

            ArrayList<PotionEffectType> potionEffects = new ArrayList<>();
            ArrayList<Integer> potionEffectsDuration = new ArrayList<>();
            if(bossFile.contains("potion-effects")) {
                Set<String> effects = bossFile.getConfigurationSection("potion-effects").getKeys(false);
                for(String effect: effects) {
                    potionEffects.add(PotionEffectType.getByName(effect));
                    potionEffectsDuration.add(bossFile.getInt("potion-effects." + effect));
                }
            }

            bosses.add(new Boss(
                    bossFile.getBoolean("enabled"),
                    EntityType.valueOf(bossFile.getString("base-mob")),
                    bossFile.getString("name"),
                    bossFile.getDouble("health"),
                    bossFile.getDouble("damage"),
                    bossFile.getDouble("speed"),
                    bossFile.getBoolean("bossbar.enabled"),
                    BarColor.valueOf(bossFile.getString("bossbar.color")),
                    bossFile.getStringList("broadcast.spawn"),
                    bossFile.getStringList("broadcast.death"),
                    immunities,
                    drops,
                    bossFile.getStringList("death-commands"),
                    armor,
                    potionEffects,
                    potionEffectsDuration,
                    bossFile.getString("name-tag"),
                    null
            ));
        });
        return bosses;
    }

    public static void spawnBoss(Boss boss1, Location loc) {

        Boss newBoss = new Boss(boss1);

        LivingEntity entity = (LivingEntity) loc.getWorld().spawnEntity(loc, newBoss.getBaseMob());

        entity.getEquipment().setHelmet(newBoss.getArmor().getHelmet());
        entity.getEquipment().setChestplate(newBoss.getArmor().getChestplate());
        entity.getEquipment().setLeggings(newBoss.getArmor().getLeggings());
        entity.getEquipment().setBoots(newBoss.getArmor().getBoots());

        entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(newBoss.getHealth());
        entity.setHealth(entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
        entity.damage(newBoss.getDamage());

        entity.setCustomNameVisible(true);
        entity.setCustomName(Utils.Color(newBoss.getNametag()));

        entity.setCanPickupItems(false);

        for(PotionEffectType effect: newBoss.getPotionEffects()) {
            entity.addPotionEffect(effect.createEffect(10000, newBoss.getPotionEffectsDuration().get(newBoss.getPotionEffects().indexOf(effect))));
        }

        BossBar bb = Utils.bossbar(entity, Utils.Color(newBoss.getNametag()), newBoss.getBossBarColor());

        // TODO: change to only the dungeons players can see the bossbar
        for(Player p : Bukkit.getOnlinePlayers()) {
            bb.addPlayer(p);
        }

        for(String line : newBoss.getSpawnMessage()) {
            Bukkit.getServer().broadcastMessage(Utils.Color(line));
        }

        newBoss.setEntity(entity);

        Global.spawnedBosses.add(newBoss);
        Global.bossBars.add(bb);

    }

}

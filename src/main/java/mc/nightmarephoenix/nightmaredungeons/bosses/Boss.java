package mc.nightmarephoenix.nightmaredungeons.bosses;

import mc.nightmarephoenix.nightmaredungeons.util.ArmorSet;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class Boss {

    public Boss(boolean enabled, EntityType baseMob, String name, double health, double damage, double speed, boolean bossBarEnabled, List<String> spawnMessage, List<String> deathMessage, ArrayList<BossImmunities> immunities, ArrayList<ItemStack> drops, ArmorSet armor, ArrayList<PotionEffectType> potionEffects, ArrayList<Integer> potionEffectsDuration) {
        this.enabled = enabled;
        this.baseMob = baseMob;
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.speed = speed;
        this.bossBarEnabled = bossBarEnabled;
        this.spawnMessage = spawnMessage;
        this.deathMessage = deathMessage;
        this.immunities = immunities;
        this.drops = drops;
        this.armor = armor;
        this.potionEffects = potionEffects;
        this.potionEffectsDuration = potionEffectsDuration;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getName() {
        return name;
    }

    public double getHealth() {
        return health;
    }

    public double getDamage() {
        return damage;
    }

    public double getSpeed() {
        return speed;
    }

    public boolean getBossBar() {
        return bossBarEnabled;
    }

    public List<String> getSpawnMessage() {
        return spawnMessage;
    }

    public List<String> getDeathMessage() {
        return deathMessage;
    }

    public ArrayList<BossImmunities> getImmunities() {
        return immunities;
    }

    public ArrayList<ItemStack> getDrops() {
        return drops;
    }

    public ArmorSet getArmor() {
        return armor;
    }

    public EntityType getBaseMob() {
        return baseMob;
    }

    public ArrayList<PotionEffectType> getPotionEffects() {
        return potionEffects;
    }

    public ArrayList<Integer> getPotionEffectsDuration() {
        return potionEffectsDuration;
    }

    private boolean                   enabled;
    private String                    name;
    private EntityType                baseMob;
    private double                    health;
    private double                    damage;
    private double                    speed;
    private boolean                   bossBarEnabled;
    private List<String>              spawnMessage;
    private List<String>              deathMessage;
    private ArrayList<BossImmunities> immunities;
    private ArrayList<ItemStack>      drops;
    private ArmorSet                  armor;
    private ArrayList<PotionEffectType> potionEffects;
    private ArrayList<Integer> potionEffectsDuration;
    // TODO: DEFENCE EFFECTS

}

package mc.nightmarephoenix.nightmaredungeons.enemies;

import mc.nightmarephoenix.nightmaredungeons.util.ArmorSet;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;

public class Enemy {

    public Enemy(String name, EntityType baseMob, double health, double damage, ArmorSet armor, ArrayList<PotionEffectType> potionEffects, ArrayList<Integer> potionEffectsDuration, Location spawnLocation, ArrayList<ItemStack> drops) {
        this.name    = name;
        this.baseMob = baseMob;
        this.health  = health;
        this.damage  = damage;
        this.armor   = armor;
        this.potionEffects = potionEffects;
        this.potionEffectsDuration = potionEffectsDuration;
        this.spawnLocation = spawnLocation;
        this.drops = drops;
    }

    public Enemy(Enemy enemy) {
        this.name    = enemy.getName();
        this.baseMob = enemy.getBaseMob();
        this.health  = enemy.getHealth();
        this.damage  = enemy.getDamage();
        this.armor   = enemy.getArmor();
        this.potionEffects = enemy.getPotionEffects();
        this.potionEffectsDuration = enemy.getPotionEffectsDuration();
        this.spawnLocation = enemy.getSpawnLocation();
        this.drops = enemy.getDrops();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EntityType getBaseMob() {
        return baseMob;
    }

    public void setBaseMob(EntityType baseMob) {
        this.baseMob = baseMob;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public ArmorSet getArmor() {
        return armor;
    }

    public ArrayList<PotionEffectType> getPotionEffects() {
        return potionEffects;
    }

    public ArrayList<Integer> getPotionEffectsDuration() {
        return potionEffectsDuration;
    }

    public Location getSpawnLocation() {
        return spawnLocation;
    }

    public void setSpawnLocation(Location spawnLocation) {
        this.spawnLocation = spawnLocation;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public ArrayList<ItemStack> getDrops() {
        return this.drops;
    }

    private String                      name;
    private EntityType                  baseMob;
    private double                      health;
    private double                      damage;
    private ArmorSet                    armor;
    private ArrayList<PotionEffectType> potionEffects;
    private ArrayList<Integer>          potionEffectsDuration;
    private Location                    spawnLocation;
    private Entity                      entity;
    private ArrayList<ItemStack>        drops;

}

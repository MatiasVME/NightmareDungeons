package mc.nightmarephoenix.nightmaredungeons.enemies;

import mc.nightmarephoenix.nightmaredungeons.util.ArmorSet;
import org.bukkit.entity.EntityType;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;

public class Enemy {

    public Enemy(String name, EntityType baseMob, double health, double damage, ArmorSet armor, ArrayList<PotionEffectType> potionEffects, ArrayList<Integer> potionEffectsDuration) {
        this.name    = name;
        this.baseMob = baseMob;
        this.health  = health;
        this.damage  = damage;
        this.armor   = armor;
        this.potionEffects = potionEffects;
        this.potionEffectsDuration = potionEffectsDuration;
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

    private String     name;
    private EntityType baseMob;
    private double     health;
    private double     damage;
    private ArmorSet   armor;
    private ArrayList<PotionEffectType> potionEffects;
    private ArrayList<Integer> potionEffectsDuration;
}

package mc.nightmarephoenix.nightmaredungeons.enemies;

import org.bukkit.entity.EntityType;

public class Enemy {

    public Enemy(String name, EntityType baseMob, double health, double damage, double speed) {

        this.name    = name;
        this.baseMob = baseMob;
        this.health  = health;
        this.damage  = damage;
        this.speed   = speed;

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

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    private String     name;
    private EntityType baseMob;
    private double     health;
    private double     damage;
    private double     speed;

}

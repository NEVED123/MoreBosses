package net.fabricmc.example.entities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.GiantEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;
import org.lwjgl.system.Pointer;

public class GiantBossEntity extends GiantEntity {

    private ServerBossBar bossBar;
    public GiantBossEntity(EntityType<? extends GiantEntity> entityType, World world) {
        super(entityType, world);
        bossBar = (ServerBossBar)(new ServerBossBar(this.getDisplayName(), BossBar.Color.PURPLE, BossBar.Style.PROGRESS)).setDarkenSky(true);
    }

    protected void initGoals(){
        this.goalSelector.add(8, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(3, new LookAroundGoal(this));
        this.targetSelector.add(8, new ActiveTargetGoal(this, PlayerEntity.class, true));
        this.goalSelector.add(8, new MeleeAttackGoal(this, 1.0, true));
        this.targetSelector.add(3, new WanderAroundGoal(this, 0.5));
    }

    public static DefaultAttributeContainer.Builder createGiantEntityAttributes() {
        return DefaultAttributeContainer.builder()
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 30.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 100.0)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 2)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 5)
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 200)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 1)
                .add(EntityAttributes.GENERIC_ARMOR)
                .add(EntityAttributes.GENERIC_ARMOR_TOUGHNESS)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 0.5);
    }

    public void onStartedTrackingBy(ServerPlayerEntity player){
        super.onStartedTrackingBy(player);
        bossBar.addPlayer(player);
    }

    public void mobTick(){
        bossBar.setPercent(this.getHealth()/this.getMaxHealth());
    }

    public void onDeath(DamageSource source){
        for(ServerPlayerEntity player : bossBar.getPlayers()){
            bossBar.removePlayer(player);
        }
    }

}

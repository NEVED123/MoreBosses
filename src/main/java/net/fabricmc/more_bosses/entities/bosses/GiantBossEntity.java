package net.fabricmc.more_bosses.entities.bosses;

import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.tag.TagKey;
import net.minecraft.text.LiteralText;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public abstract class GiantBossEntity extends HostileEntity {

    protected ServerBossBar bossBar;

    protected GiantBossEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
        this.setPersistent();
        this.getNavigation().setCanSwim(true);
    }

    protected void initGoals(){
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(8, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(3, new LookAroundGoal(this));
        this.targetSelector.add(8, new ActiveTargetGoal(this, PlayerEntity.class, false));
        this.goalSelector.add(8, new MeleeAttackGoal(this, 1.0, false));
        this.goalSelector.add(3, new WanderAroundGoal(this, 0.5));
        this.targetSelector.add(1, new RevengeGoal(this, new Class[0]));
    }

    protected float getActiveEyeHeight(EntityPose pose, EntityDimensions dimensions){
        return 10.440001F;
    }

    public static DefaultAttributeContainer.Builder createGiantBossEntityAttributes() {
        //THE ONLY VALUES THAT HAVE NOT BEEN INSTANTIATED ARE MAX HEALTH, ATTACK
        //DAMAGE, and ATTACK SPEED
        return DefaultAttributeContainer.builder()
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 100.0)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 5)
                .add(EntityAttributes.GENERIC_MAX_HEALTH)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 1)
                .add(EntityAttributes.GENERIC_ARMOR)
                .add(EntityAttributes.GENERIC_ARMOR_TOUGHNESS)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 0.5);
    }

    public void onStartedTrackingBy(ServerPlayerEntity player){
        super.onStartedTrackingBy(player);
        this.bossBar.addPlayer(player);
    }

    public void onStoppedTrackingBy(ServerPlayerEntity player){
        super.onStoppedTrackingBy(player);
        this.bossBar.removePlayer(player);
    }

    public void mobTick(){
        this.bossBar.setPercent(this.getHealth()/this.getMaxHealth());
    }

    public double getSwimHeight(){
        return 6;
    }

    protected void swimUpward(TagKey<Fluid> fluid){
        //allows Giant to get onto land from water
        if(this.horizontalCollision){
            Vec3d initialVelocity = this.getVelocity();
            this.setVelocity(initialVelocity.x, 2, initialVelocity.y);
        }
        else{
            super.swimUpward(fluid);
        }
    }

}

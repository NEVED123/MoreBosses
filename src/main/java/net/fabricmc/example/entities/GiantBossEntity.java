package net.fabricmc.example.entities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.GiantEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class GiantBossEntity extends GiantEntity {
    public GiantBossEntity(EntityType<? extends GiantEntity> entityType, World world) {
        super(entityType, world);
    }

    protected void initGoals(){
        this.goalSelector.add(8, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(8, new LookAroundGoal(this));
        this.targetSelector.add(2, new ActiveTargetGoal(this, PlayerEntity.class, true));
        this.goalSelector.add(8, new MeleeAttackGoal(this, 1.0, true));
    }



}

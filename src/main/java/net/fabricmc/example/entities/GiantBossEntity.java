package net.fabricmc.example.entities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.mob.GiantEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class GiantBossEntity extends GiantEntity {
    public GiantBossEntity(EntityType<? extends GiantEntity> entityType, World world) {
        super(entityType, world);
    }

    protected void initGoals(){
        this.goalSelector.add(8, new ActiveTargetGoal(this, PlayerEntity.class, false));
    }
}

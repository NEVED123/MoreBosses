package net.fabricmc.more_bosses.entities.bosses;

import net.fabricmc.more_bosses.entities.bosses.GiantBossEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class GiantZombieBossEntity extends GiantBossEntity {

    public GiantZombieBossEntity(EntityType<? extends GiantBossEntity> entityType, World world) {
        super(entityType, world);
        this.initializeBossBar("Giant Zombie", BossBar.Color.PURPLE, BossBar.Style.PROGRESS, true);
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

    public static DefaultAttributeContainer.Builder createGiantZombieBossEntityAttributes() {
        return createGiantBossEntityAttributes()
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 30.0)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 2)
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 200);
    }

}

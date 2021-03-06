package net.fabricmc.more_bosses.entities.bosses;

import net.fabricmc.more_bosses.entities.bosses.GiantBossEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

public class GiantZombieBossEntity extends GiantBossEntity {

    public GiantZombieBossEntity(EntityType<? extends GiantBossEntity> entityType, World world) {
        super(entityType, world);
        bossBar = (ServerBossBar)(new ServerBossBar(this.getDisplayName(), BossBar.Color.PURPLE, BossBar.Style.PROGRESS)).setDarkenSky(true);
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

    @Override
    public SoundEvent getAmbientSound(){
        return SoundEvents.ENTITY_ZOMBIE_AMBIENT;
    }

    protected float getSoundVolume(){
        return 1.0F;
    }

    public float getSoundPitch(){
        return .5F;
    }

    public SoundCategory getSoundCategory(){
        return SoundCategory.HOSTILE;
    }

    public SoundEvent getHurtSound(DamageSource source){
        return SoundEvents.ENTITY_ZOMBIE_HURT;
    }

    public void onDeath(DamageSource source){
        this.world.playSound((PlayerEntity)null, this.getX(), this.getY(), this.getZ(), SoundEvents.ENTITY_ZOMBIE_DEATH, SoundCategory.HOSTILE, 10F, .5F);
        super.onDeath(source);
    }

    public int getXpToDrop() {
        return 7500;
    }
}

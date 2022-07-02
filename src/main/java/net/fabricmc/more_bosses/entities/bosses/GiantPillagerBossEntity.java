package net.fabricmc.more_bosses.entities.bosses;

import net.fabricmc.more_bosses.entities.bosses.GiantBossEntity;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.RavagerEntity;
import net.minecraft.entity.mob.VexEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.World;
import java.util.Random;

import static net.fabricmc.more_bosses.MoreBosses.SLEEPING_PILLAGER_SOUL;
import static net.fabricmc.more_bosses.MoreBosses.ZOMBIE_SCARECROW_ITEM;

public class GiantPillagerBossEntity extends GiantBossEntity{

    Random random = new Random();

    public GiantPillagerBossEntity(EntityType<? extends GiantBossEntity> entityType, World world) {
        super(entityType, world);
        this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_AXE));
        bossBar = (ServerBossBar)(new ServerBossBar(this.getDisplayName(), BossBar.Color.RED, BossBar.Style.PROGRESS)).setDarkenSky(false);
    }

    public static DefaultAttributeContainer.Builder createGiantPillagerBossEntityAttributes() {
        return createGiantBossEntityAttributes()
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 30.0)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 2)
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 200);
    }

    public void mobTick(){
        super.mobTick();
        if(this.getTarget() instanceof PlayerEntity){
            Vec3d playerPos = this.getTarget().getPos();
            Vec3d bossPos = this.getPos();
            float playerDistance = distance(playerPos, bossPos);
            if(playerDistance < 15 && !world.isClient()){
                ServerWorld serverWorld = (ServerWorld)world;
                if(random.nextFloat() < .1){
                    BlockPos spawnLocation = reinforcementSpawnLocation(this.getBlockPos(), 10, Reinforcement.VEX);
                    VexEntity vex = EntityType.VEX.create(serverWorld, null, null, null, spawnLocation, SpawnReason.REINFORCEMENT, true, false);
                    serverWorld.spawnEntity(vex);
                }
                if(random.nextFloat() < .002){
                    BlockPos spawnLocation = reinforcementSpawnLocation(this.getBlockPos(), 10, Reinforcement.RAVAGER);
                    RavagerEntity ravager = EntityType.RAVAGER.create(serverWorld, null, null, null, spawnLocation, SpawnReason.REINFORCEMENT, true, false);
                    serverWorld.spawnEntity(ravager);
                }
            }
        }
    }

    private float distance(Vec3d i, Vec3d j){
        return (float)Math.sqrt(Math.pow(i.x - j.x, 2) + Math.pow(i.y - j.y, 2) + Math.pow(i.z - j.z, 2));
    }

    private BlockPos reinforcementSpawnLocation(BlockPos source, float range, Reinforcement mob){
        Random spawnDistance = new Random();
        Random isNegative = new Random();
        float spawnX = 0;
        float spawnY = 0;
        float spawnZ = 0;
        if(mob.equals(Reinforcement.VEX)){
            spawnX = isNegative.nextBoolean() ? source.getX() + spawnDistance.nextFloat() * -range : source.getX() + spawnDistance.nextFloat() * range;
            spawnY = source.getY() + spawnDistance.nextFloat() * range;
            spawnZ = isNegative.nextBoolean() ? source.getZ() + spawnDistance.nextFloat() * -range : source.getZ() + spawnDistance.nextFloat() * range;
        }
        if(mob.equals(Reinforcement.RAVAGER)){
            spawnX = isNegative.nextBoolean() ? source.getX() + spawnDistance.nextFloat() * -range : source.getX() + spawnDistance.nextFloat() * range;
            spawnY = source.getY();
            spawnZ = isNegative.nextBoolean() ? source.getZ() + spawnDistance.nextFloat() * -range : source.getZ() + spawnDistance.nextFloat() * range;
        }

        return new BlockPos(spawnX, spawnY, spawnZ);
    }

    @Override
    public SoundEvent getAmbientSound(){
        return SoundEvents.ENTITY_PILLAGER_AMBIENT;
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
        return SoundEvents.ENTITY_PILLAGER_HURT;
    }

    public void onDeath(DamageSource source){
        this.world.playSound((PlayerEntity)null, this.getX(), this.getY(), this.getZ(), SoundEvents.ENTITY_PILLAGER_DEATH, SoundCategory.HOSTILE, 10F, .5F);
        super.onDeath(source);
    }

    public ItemStack getPickBlockStack(){
        return new ItemStack(SLEEPING_PILLAGER_SOUL);
    }

    @Override
    public int getXpToDrop() {
        return 10000;
    }

    private enum Reinforcement{
        VEX, RAVAGER
    }




}

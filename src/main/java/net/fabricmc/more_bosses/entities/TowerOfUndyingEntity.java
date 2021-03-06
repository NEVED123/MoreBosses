package net.fabricmc.more_bosses.entities;

import net.fabricmc.more_bosses.MoreBosses;
import net.minecraft.entity.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.raid.RaiderEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Arm;
import net.minecraft.util.Hand;
import net.minecraft.village.raid.Raid;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

import java.util.ArrayList;
import java.util.UUID;

import static net.fabricmc.more_bosses.MoreBosses.TOWER_OF_UNDYING_ITEM;

public class TowerOfUndyingEntity extends LivingEntity{

    //Sounds:
    //place: block.anvil.land
    //rotating: none?
    //dying: xp
    private UUID ownerUuid;
    private int towerAge;
    private int health = 5;

    public TowerOfUndyingEntity(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
        this.setNoGravity(true);
        towerAge = 0;
        this.setHealth(health);
    }

    @Override
    public Iterable<ItemStack> getArmorItems() {
        return new ArrayList<ItemStack>();
    }

    @Override
    public ItemStack getEquippedStack(EquipmentSlot slot) {
        return ItemStack.EMPTY;
    }

    @Override
    public void equipStack(EquipmentSlot slot, ItemStack stack) {

    }

    @Override
    public Arm getMainArm() {
        return null;
    }

    @Override
    public boolean damage(DamageSource source, float amount){
        if(source == DamageSource.OUT_OF_WORLD)
            return super.damage(source, amount);
        if(source.getAttacker() instanceof RaiderEntity){
            return super.damage(source, amount);
        }

        return false;
    }

    public void tick(){
        super.tick();
        towerAge++;
        if(!world.isClient()){
            if(towerAge < 160){
                this.setRotation(this.getYaw() + (float)towerAge/4, getPitch());
                //this.setPosition(this.getX(), this.getY() + Math.sin(towerAge/1000), this.getZ()); get the wavy thing to work
            }
            else if(towerAge < 220){
                this.setRotation(this.getYaw() + 40, getPitch());
                this.setVelocity(0, (float)(towerAge-160)/100, 0);
            }
            else{
                ServerWorld serverWorld = (ServerWorld)world;
                Raid raid = serverWorld.getRaidAt(this.getBlockPos());
                if(raid != null && raid.isActive()){
                    raid.invalidate();
                    for(RaiderEntity raider : raid.getAllRaiders()){
                        raider.kill();
                    }
                    if(this.getOwner() != null){
                        PlayerEntity owner = this.getOwner();
                        double x = owner.getBlockX();
                        double y = owner.getBlockY();
                        double z = owner.getBlockZ();
                        owner.addStatusEffect(new StatusEffectInstance(StatusEffects.HERO_OF_THE_VILLAGE, 48000, 1, false, false, true));
                        owner.incrementStat(Stats.RAID_WIN);
                        this.world.playSound((PlayerEntity)null, this.getX(), this.getY(), this.getZ(), SoundEvents.ENTITY_PLAYER_LEVELUP, SoundCategory.PLAYERS, 10F, 1.0F);
                    }

                }
                world.createExplosion(this, this.getX(), this.getY(), this.getZ(), 10, Explosion.DestructionType.NONE);
                this.remove(RemovalReason.DISCARDED);
            }
        }
    }

    public PlayerEntity getOwner(){
        return (PlayerEntity)((ServerWorld)this.world).getEntity(this.ownerUuid);
    }
    public void setOwnerUuid(UUID ownerUuid){
        this.ownerUuid = ownerUuid;
    }

    public UUID getOwnerUuid(){
        return this.ownerUuid;
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt){
        super.writeCustomDataToNbt(nbt);
        if(this.ownerUuid != null){
            nbt.putUuid("Owner", this.ownerUuid);
        }
        nbt.putInt("towerAge", this.towerAge);
        System.out.println("write custom nbt data");
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt){
        if(nbt.containsUuid("Owner")){
            this.ownerUuid = nbt.getUuid("Owner");
        }
        this.towerAge = nbt.getInt("towerAge");
        System.out.println("read custom data");
    }

    public ItemStack getPickBlockStack(){
        return new ItemStack(TOWER_OF_UNDYING_ITEM);
    }

    public boolean isPushable() {
        return false;
    }

    protected void pushAway(Entity entity) {
    }

}

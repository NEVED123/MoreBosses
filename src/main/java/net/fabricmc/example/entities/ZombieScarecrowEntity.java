package net.fabricmc.example.entities;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.s2c.play.EntityS2CPacket;
import net.minecraft.network.packet.s2c.play.MobSpawnS2CPacket;
import net.minecraft.util.Arm;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

import java.util.ArrayList;

public class ZombieScarecrowEntity extends LivingEntity {

    public long lastHitTime;

    public ZombieScarecrowEntity(EntityType<? extends LivingEntity> entityType, World world){
        super(entityType, world);
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

    public boolean damage(DamageSource source, float amount) {
        if (!this.world.isClient && !this.isRemoved()) {
            if (DamageSource.OUT_OF_WORLD.equals(source)) {
                this.kill();
                return false;
            } else if (!this.isInvulnerableTo(source)/*&& !this.invisible && !this.isMarker()*/) {
                if (source.isExplosive()) {
                    //this.onBreak(source);
                    this.kill();
                    return false;
                } else if (DamageSource.IN_FIRE.equals(source)) {
                    if (this.isOnFire()) {
                        this.updateHealth(source, 0.15F);
                    } else {
                        this.setOnFireFor(5);
                    }

                    return false;
                } else if (DamageSource.ON_FIRE.equals(source) && this.getHealth() > 0.5F) {
                    this.updateHealth(source, 4.0F);
                    return false;
                } else {
                    boolean bl = source.getSource() instanceof PersistentProjectileEntity;
                    boolean bl2 = bl && ((PersistentProjectileEntity)source.getSource()).getPierceLevel() > 0;
                    boolean bl3 = "player".equals(source.getName());
                    if (!bl3 && !bl) {
                        return false;
                    } else if (source.getAttacker() instanceof PlayerEntity && !((PlayerEntity)source.getAttacker()).getAbilities().allowModifyWorld) {
                        return false;
                    } else if (source.isSourceCreativePlayer()) {
                        //this.playBreakSound();
                        //this.spawnBreakParticles();
                        this.kill();
                        return bl2;
                    } else {
                        long l = this.world.getTime();
                        if (l - this.lastHitTime > 5L && !bl) {
                            this.world.sendEntityStatus(this, (byte)32);
                            this.emitGameEvent(GameEvent.ENTITY_DAMAGED, source.getAttacker());
                            this.lastHitTime = l;
                        } else {
                            //this.breakAndDropItem(source);
                            //this.spawnBreakParticles();
                            this.kill();
                        }

                        return true;
                    }
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public void kill() {
        this.remove(RemovalReason.KILLED);
    }

    /*private void onBreak(DamageSource damageSource) {
        //this.playBreakSound();
        this.drop(damageSource);
    }

    private void breakAndDropItem(DamageSource damageSource) {
        Block.dropStack(this.world, this.getBlockPos(), new ItemStack(Items.ZOMBIE_SCARECROW));
        this.onBreak(damageSource);
    }*/

    private void updateHealth(DamageSource damageSource, float amount) {
        float f = this.getHealth();
        f -= amount;
        if (f <= 0.5F) {
            //this.onBreak(damageSource);
            this.kill();
        } else {
            this.setHealth(f);
            this.emitGameEvent(GameEvent.ENTITY_DAMAGED, damageSource.getAttacker());
        }

    }



}



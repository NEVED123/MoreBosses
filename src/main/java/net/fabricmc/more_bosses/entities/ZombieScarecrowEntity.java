package net.fabricmc.more_bosses.entities;

import net.fabricmc.more_bosses.MoreBosses;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Arm;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

import java.util.ArrayList;

import static net.fabricmc.more_bosses.MoreBosses.ZOMBIE_SCARECROW_ITEM;

public class ZombieScarecrowEntity extends LivingEntity {

    public long lastHitTime;
    public final static float FLEE_DISTANCE = 50.0F;
    public ZombieScarecrowEntity(EntityType<? extends LivingEntity> entityType, World world){
        super(entityType, world);
    }

    public boolean damage(DamageSource source, float amount) {
        if (!this.world.isClient && !this.isRemoved()) {
            if (DamageSource.OUT_OF_WORLD.equals(source)) {
                this.remove(RemovalReason.DISCARDED);
                return false;
            } else if (!this.isInvulnerableTo(source)) {
                if (source.isExplosive()) {
                    this.onBreak(source);
                    this.remove(RemovalReason.DISCARDED);
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
                        this.playBreakSound();
                        this.spawnBreakParticles();
                        this.remove(RemovalReason.DISCARDED);
                        return bl2;
                    } else {
                        long l = this.world.getTime();
                        if (l - this.lastHitTime > 5L && !bl) {
                            this.world.sendEntityStatus(this, (byte)32);
                            this.emitGameEvent(GameEvent.ENTITY_DAMAGED, source.getAttacker());
                            this.lastHitTime = l;
                        } else {
                            this.breakAndDropItem(source);
                            this.spawnBreakParticles();
                            this.remove(RemovalReason.DISCARDED);
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

    private void breakAndDropItem(DamageSource damageSource) {
        Block.dropStack(this.world, this.getBlockPos(), new ItemStack(ZOMBIE_SCARECROW_ITEM));
    }

    private void spawnBreakParticles() {
        if (this.world instanceof ServerWorld) {
            ((ServerWorld)this.world).spawnParticles(new BlockStateParticleEffect(ParticleTypes.BLOCK, Blocks.OAK_PLANKS.getDefaultState()), this.getX(), this.getBodyY(0.6666666666666666), this.getZ(), 10, (double)(this.getWidth() / 4.0F), (double)(this.getHeight() / 4.0F), (double)(this.getWidth() / 4.0F), 0.05);
        }

    }

    private void updateHealth(DamageSource damageSource, float amount) {
        float f = this.getHealth();
        f -= amount;
        if (f <= 0.5F) {
            this.onBreak(damageSource);
            this.remove(RemovalReason.DISCARDED);
        } else {
            this.setHealth(f);
            this.emitGameEvent(GameEvent.ENTITY_DAMAGED, damageSource.getAttacker());
        }

    }

    private void onBreak(DamageSource damageSource) {
        this.playBreakSound();
        this.drop(damageSource);

        Block.dropStack(this.world, this.getBlockPos(), new ItemStack(ZOMBIE_SCARECROW_ITEM));
    }

    private void playBreakSound() {
        this.world.playSound((PlayerEntity)null, this.getX(), this.getY(), this.getZ(), SoundEvents.ENTITY_ARMOR_STAND_BREAK, this.getSoundCategory(), 1.0F, 1.0F);
    }

    public void handleStatus(byte status) {
        if (status == 32) {
            if (this.world.isClient) {
                this.world.playSound(this.getX(), this.getY(), this.getZ(), SoundEvents.ENTITY_ARMOR_STAND_HIT, this.getSoundCategory(), 0.3F, 1.0F, false);
                this.lastHitTime = this.world.getTime();
            }
        } else {
            super.handleStatus(status);
        }
    }

    public boolean isPushable() {
        return false;
    }

    protected void pushAway(Entity entity) {
    }

    public ItemStack getPickBlockStack(){
        return new ItemStack(ZOMBIE_SCARECROW_ITEM);
    }

}


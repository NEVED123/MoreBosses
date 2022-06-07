package net.fabricmc.example.entities;

import com.mojang.authlib.yggdrasil.response.User;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.FireworkRocketEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Arm;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class TowerOfUndyingEntity extends LivingEntity{


    public TowerOfUndyingEntity(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
        this.setNoGravity(true);
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
        else
            return false;
    }

    public void tick(){
        super.tick();
        if(!world.isClient()){
            if(this.age < 160){
                this.setRotation(this.getYaw() + (float)this.age/4, getPitch());
            }
            else if(this.age < 220){
                this.setRotation(this.getYaw() + 40, getPitch());
                this.setVelocity(0, (float)(this.age-160)/100, 0);
            }
            else{
                this.remove(RemovalReason.DISCARDED);
            }

        }
    }



}

package net.fabricmc.example.entities;

import net.minecraft.entity.*;
import net.minecraft.entity.projectile.FireworkRocketEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Arm;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.ArrayList;

public class TowerOfUndyingEntity extends FireworkRocketEntity {

    public TowerOfUndyingEntity(EntityType<? extends FireworkRocketEntity> entityType, World world) {
        super(entityType, world);
    }







   /*public void tick(){
        if(!world.isClient()){
            BlockPos pos = this.getBlockPos();
            pos.add(.01, .01, .01);
        }
   }*/
}

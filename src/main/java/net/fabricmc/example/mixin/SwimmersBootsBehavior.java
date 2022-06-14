package net.fabricmc.example.mixin;

import net.fabricmc.example.MoreBosses;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.system.CallbackI;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//net/minecraft/util/math/Vec3d.class

@Mixin(PlayerEntity.class)
public class SwimmersBootsBehavior {
    @Inject(at = @At("TAIL"), method = "travel(Lnet/minecraft/util/math/Vec3d;)V")
    public void travel(Vec3d movementInput, CallbackInfo info){
        if (((PlayerEntity)(Object)this).isSwimming() ) {
            Iterable<ItemStack> armorItems = ((PlayerEntity)(Object)this).getArmorItems();
            boolean hasBoots = false;
            Iterator<ItemStack> iterator = armorItems.iterator();

            while(iterator.hasNext() && !hasBoots){
                if(iterator.next().isItemEqual(new ItemStack(MoreBosses.SWIMMERS_BOOTS))){
                    hasBoots = true;
                }
            }
            if(hasBoots){
                double g = ((PlayerEntity)(Object)this).getVelocity().y;
                float i = ((PlayerEntity)(Object)this).airStrafingSpeed;
                ((PlayerEntity)(Object)this).airStrafingSpeed =
                        ((PlayerEntity)(Object)this).getAbilities().getFlySpeed() * (float)(((PlayerEntity)(Object)this).isSprinting() ? 2 : 1);
                ((LivingEntity)((PlayerEntity)(Object)this)).travel(movementInput);
                Vec3d vec3d2 = ((PlayerEntity)(Object)this).getVelocity();
                ((PlayerEntity)(Object)this).setVelocity(vec3d2.x, g * 0.6, vec3d2.z);
                ((PlayerEntity)(Object)this).airStrafingSpeed = i;
                ((PlayerEntity)(Object)this).onLanding();
                //this.setFlag(7, false);
            }
        } else {
            ((LivingEntity)((PlayerEntity)(Object)this)).travel(movementInput);
        }
    }



}

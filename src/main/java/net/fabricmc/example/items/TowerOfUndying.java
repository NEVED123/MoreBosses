package net.fabricmc.example.items;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.raid.RaiderEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.village.raid.Raid;
import net.minecraft.world.World;

public class TowerOfUndying extends Item {

    public TowerOfUndying(Settings settings) {
        super(settings);
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand){
        ItemStack itemStack = user.getStackInHand(hand);
        if(!world.isClient()){
            ServerWorld serverWorld = (ServerWorld)world;
            Raid raid = serverWorld.getRaidAt(user.getBlockPos());
            if(raid.isActive()){
                raid.invalidate();
                if(user.isCreative()){
                    itemStack.decrement(1);
                }
            }
            for(RaiderEntity raider : raid.getAllRaiders()){
                raider.kill();
            }
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.HERO_OF_THE_VILLAGE, 48000, 1, false, false, true));
            user.incrementStat(Stats.RAID_WIN);
            return TypedActionResult.success(itemStack, true);
        }

        return TypedActionResult.fail(itemStack);

    }

}

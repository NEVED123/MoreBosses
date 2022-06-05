package net.fabricmc.example.items;

import net.fabricmc.example.MoreBosses;
import net.fabricmc.example.entities.TowerOfUndyingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireworkRocketEntity;
import net.minecraft.entity.raid.RaiderEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
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
            TowerOfUndyingEntity towerOfUndyingEntity = MoreBosses.TOWER_OF_UNDYING_ENTITY.create(serverWorld, null, null, user, user.getBlockPos(), SpawnReason.SPAWN_EGG, true, false);
            serverWorld.spawnEntity(towerOfUndyingEntity);
            Raid raid = serverWorld.getRaidAt(user.getBlockPos());
            if(raid != null && raid.isActive()){
                raid.invalidate();
                for(RaiderEntity raider : raid.getAllRaiders()){
                    raider.kill();
                }
                user.addStatusEffect(new StatusEffectInstance(StatusEffects.HERO_OF_THE_VILLAGE, 48000, 1, false, false, true));
                user.incrementStat(Stats.RAID_WIN);
            }
            return TypedActionResult.success(itemStack, true);
        }

        return TypedActionResult.fail(itemStack);

    }

}

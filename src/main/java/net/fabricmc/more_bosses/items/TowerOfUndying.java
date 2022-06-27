package net.fabricmc.more_bosses.items;

import net.fabricmc.more_bosses.MoreBosses;
import net.fabricmc.more_bosses.entities.TowerOfUndyingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
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
            towerOfUndyingEntity.setOwnerUuid(user.getUuid()); //so it knows who to give hero potion effect to
            serverWorld.spawnEntity(towerOfUndyingEntity);
            itemStack.decrement(1);
            return TypedActionResult.success(itemStack, true);
        }

        return TypedActionResult.fail(itemStack);

    }

}

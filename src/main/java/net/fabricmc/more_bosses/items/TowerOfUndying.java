package net.fabricmc.more_bosses.items;

import net.fabricmc.more_bosses.MoreBosses;
import net.fabricmc.more_bosses.entities.TowerOfUndyingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class TowerOfUndying extends Item {

    public TowerOfUndying(Settings settings) {
        super(settings);
    }

    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        if(!world.isClient && context.getSide() == Direction.UP){
            PlayerEntity player = context.getPlayer();
            BlockPos placedPos = context.getBlockPos();
            ServerWorld serverWorld = (ServerWorld)world;
            TowerOfUndyingEntity towerOfUndyingEntity = MoreBosses.TOWER_OF_UNDYING_ENTITY
                    .create(serverWorld, null, null, null,
                            placedPos, SpawnReason.SPAWN_EGG, true, false);
            towerOfUndyingEntity.setOwnerUuid(player.getUuid());
            serverWorld.spawnEntity(towerOfUndyingEntity);
            world.playSound((PlayerEntity)null, placedPos.getX(), placedPos.getY(), placedPos.getZ(), SoundEvents.BLOCK_ANVIL_LAND, SoundCategory.BLOCKS, 1.0F, 1.0F);
            if(!player.isCreative()){
                ItemStack stack = context.getStack();
                stack.decrement(1);
            }
            return ActionResult.SUCCESS;
        }

        return ActionResult.FAIL;
    }

    public boolean hasGlint(ItemStack stack) {
        return true;
    }
}

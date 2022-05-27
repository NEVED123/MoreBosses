package net.fabricmc.example.items;

import net.fabricmc.example.entities.ZombieScarecrowEntity;
import net.minecraft.block.Block;
import net.minecraft.entity.*;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.*;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import java.util.Random;

import static net.fabricmc.example.MoreBosses.ZOMBIE_SCARECROW_ENTITY;

public class ZombieScarecrowItem extends Item {

    public ZombieScarecrowItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        Direction direction = context.getSide();
        if (direction == Direction.DOWN) {
            return ActionResult.FAIL;
        } else {
            World world = context.getWorld();
            ItemPlacementContext itemPlacementContext = new ItemPlacementContext(context);
            BlockPos blockPos = itemPlacementContext.getBlockPos();
            ItemStack itemStack = context.getStack();
            Vec3d vec3d = Vec3d.ofBottomCenter(blockPos);
            Box box = EntityType.ARMOR_STAND.getDimensions().getBoxAt(vec3d.getX(), vec3d.getY(), vec3d.getZ());
            if (world.isSpaceEmpty((Entity)null, box) && world.getOtherEntities((Entity)null, box).isEmpty()) {
                if (world instanceof ServerWorld) {
                    ServerWorld serverWorld = (ServerWorld)world;
                    ZombieScarecrowEntity zombieScarecrowEntity = (ZombieScarecrowEntity)ZOMBIE_SCARECROW_ENTITY.create(serverWorld, itemStack.getNbt(), (Text)null, context.getPlayer(), blockPos, SpawnReason.SPAWN_EGG, true, true);
                    if (zombieScarecrowEntity == null) {
                        return ActionResult.FAIL;
                    }

                    float f = (float)MathHelper.floor((MathHelper.wrapDegrees(context.getPlayerYaw() - 180.0F) + 22.5F) / 45.0F) * 45.0F; //makes scarecrow face player
                    zombieScarecrowEntity.refreshPositionAndAngles(zombieScarecrowEntity.getX(), zombieScarecrowEntity.getY(), zombieScarecrowEntity.getZ(), f, 0.0F);
                    serverWorld.spawnEntityAndPassengers(zombieScarecrowEntity);
                    world.playSound((PlayerEntity)null, zombieScarecrowEntity.getX(), zombieScarecrowEntity.getY(), zombieScarecrowEntity.getZ(), SoundEvents.ENTITY_ARMOR_STAND_PLACE, SoundCategory.BLOCKS, 0.75F, 0.8F);
                    world.emitGameEvent(context.getPlayer(), GameEvent.ENTITY_PLACE, zombieScarecrowEntity);
                }

                itemStack.decrement(1);
                return ActionResult.success(world.isClient);
            } else {
                return ActionResult.FAIL;
            }
        }
    }



}

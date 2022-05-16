package net.fabricmc.example;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.example.blocks.ZombieScarecrow;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MoreBosses implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("MoreMobs");

	public static final ZombieScarecrow ZOMBIE_SCARECROW = new ZombieScarecrow(
			FabricBlockSettings.of(Material.STONE).hardness(4.0f));

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Fabric world!");
		Registry.register(Registry.BLOCK, new Identifier(
				"more_bosses", "zombie_scarecrow"), ZOMBIE_SCARECROW);
		Registry.register(Registry.ITEM, new Identifier("more_bosses", "zombie_scarecrow"),
				new BlockItem(ZOMBIE_SCARECROW, new Item.Settings().group(ItemGroup.MISC)));
	}
}

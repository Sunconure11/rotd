package com.TheRPGAdventurer.ROTD.client.handler;

import com.TheRPGAdventurer.ROTD.RealmOfTheDragons;
import com.TheRPGAdventurer.ROTD.client.blocks.BlockDragonBreedEgg;
import com.TheRPGAdventurer.ROTD.client.init.ModArmour;
import com.TheRPGAdventurer.ROTD.client.init.ModBlocks;
import com.TheRPGAdventurer.ROTD.client.init.ModItems;
import com.TheRPGAdventurer.ROTD.client.init.ModTools;
import com.TheRPGAdventurer.ROTD.client.items.ItemDragonBreedEgg;
import com.TheRPGAdventurer.ROTD.client.render.DragonRenderer;
import com.TheRPGAdventurer.ROTD.server.entity.EntityTameableDragon;
import com.TheRPGAdventurer.ROTD.server.entity.breeds.EnumDragonBreed;
import com.TheRPGAdventurer.ROTD.util.Utils;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod.EventBusSubscriber
public class RegistryEventHandler {
	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {	
		event.getRegistry().registerAll(ModBlocks.BLOCKS);
		Utils.getLogger().info("Registered blocks");
	}
	
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {	
		event.getRegistry().registerAll(ModItems.ITEMS);
		event.getRegistry().registerAll(ModTools.TOOLS);
		event.getRegistry().registerAll(ModArmour.ARMOR);
		
		for (Block block : ModBlocks.BLOCKS) {
			event.getRegistry().register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
		}
		
		Utils.getLogger().info("Registered items");
	}
	
	@SubscribeEvent
	public static void registerDragonEggItem(RegistryEvent.Register<Item> event) {
		event.getRegistry().register(ItemDragonBreedEgg.DRAGON_BREED_EGG);
	}
	
	@SubscribeEvent
	public static void registerDragonnEggBlock(RegistryEvent.Register<Block> event) {
		event.getRegistry().register(BlockDragonBreedEgg.DRAGON_BREED_EGG);
	}

	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event) {
		for (Block block: ModBlocks.BLOCKS) {
			ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
		}
		
		for (Block block: BlockDragonBreedEgg.BLOCK_EGG) {
			// register item renderer for dragon egg block variants
	        ResourceLocation eggModelItemLoc = new ResourceLocation(RealmOfTheDragons.MODID, "dragon_egg");
	        Item itemBlockDragonEgg = Item.REGISTRY.getObject(eggModelItemLoc);
	        EnumDragonBreed.META_MAPPING.forEach((breed, meta) -> {
	            ModelResourceLocation eggModelLoc = new ModelResourceLocation(RealmOfTheDragons.MODID + ":dragon_egg", "breed=" + breed.getName());
	            ModelLoader.setCustomModelResourceLocation(itemBlockDragonEgg, meta, eggModelLoc);
	        });
		}
		
		for (Item item: ItemDragonBreedEgg.ITEM_EGG) {
			// register item renderer for dragon egg block variants
	        ResourceLocation eggModelItemLoc = new ResourceLocation(RealmOfTheDragons.MODID, "dragon_egg");
	        Item itemBlockDragonEgg = Item.REGISTRY.getObject(eggModelItemLoc);
	        EnumDragonBreed.META_MAPPING.forEach((breed, meta) -> {
	            ModelResourceLocation eggModelLoc = new ModelResourceLocation(RealmOfTheDragons.MODID + ":dragon_egg", "breed=" + breed.getName());
	            ModelLoader.setCustomModelResourceLocation(itemBlockDragonEgg, meta, eggModelLoc);
	        });
		}
		
		for (Item item: ModItems.ITEMS) {
			ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
		}
		
		for (Item item: ModTools.TOOLS) {
			ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
		}
		
		for (Item item: ModArmour.ARMOR) {
		    ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName().toString(), "inventory"));
		}
		
    	Utils.getLogger().info("Registered models");
	}
	
}
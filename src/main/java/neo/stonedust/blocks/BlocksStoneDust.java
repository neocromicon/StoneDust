package neo.stonedust.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import cpw.mods.fml.common.registry.GameRegistry;

public class BlocksStoneDust {
	
	public static Block cleanedStone;

	public static void init() {
		
		cleanedStone = new BlockCleanedStone(Material.rock);
		GameRegistry.registerBlock(cleanedStone, "cleanedStone");			
	}
}

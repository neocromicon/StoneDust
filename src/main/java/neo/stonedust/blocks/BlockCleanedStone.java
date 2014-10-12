package neo.stonedust.blocks;

import neo.stonedust.init.InitStoneDust;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockCleanedStone extends Block {

	protected BlockCleanedStone(Material material) {
		super(material);
		this.setBlockTextureName(InitStoneDust.MODID + ":" + "cleanedStone");
		this.setBlockName(InitStoneDust.MODID + ":" + "cleanedStone");
		this.setCreativeTab(CreativeTabs.tabBlock);
	}

}

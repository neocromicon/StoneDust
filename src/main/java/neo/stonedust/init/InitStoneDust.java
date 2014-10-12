package neo.stonedust.init;

import java.lang.reflect.Method;

import thermalexpansion.util.crafting.PulverizerManager;
import ic2.api.item.IC2Items;
import ic2.api.recipe.RecipeInputItemStack;
import ic2.api.recipe.Recipes;
import mekanism.api.RecipeHelper;
import neo.stonedust.blocks.BlocksStoneDust;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = InitStoneDust.MODID, name = InitStoneDust.NAME, version = InitStoneDust.VERSION, dependencies = "after:IndustrialCraft2")
public class InitStoneDust {

	public static boolean isDebug = true;
	public static final String MODID = "stonedust";
	public static final String NAME = "StoneDust";
	public static final String VERSION = "1.7.10-1.0";

	@Instance
	public static InitStoneDust modInstance = new InitStoneDust();

	@EventHandler
	public void preinit(FMLPreInitializationEvent event) {
		BlocksStoneDust.init();
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		if (validadeIC2()) {
			ItemStack stoneDust = IC2Items.getItem("stoneDust").copy();

			NBTTagCompound oreWashData = new NBTTagCompound();
			oreWashData.setInteger("amount", 1000);

			/** Stone to Cleaned Stone */
			Recipes.oreWashing.addRecipe(new RecipeInputItemStack(new ItemStack(Blocks.stone)), oreWashData, new ItemStack(BlocksStoneDust.cleanedStone));

			/** Cleaned Stone to StoneDust */
			Recipes.macerator.addRecipe(new RecipeInputItemStack(new ItemStack(BlocksStoneDust.cleanedStone)), null, stoneDust);
			System.out.println("[StoneDust] IC2 Experimental found");

			if (validadeTE4()) {
				RecipeHelper.addCrusherRecipe(new ItemStack(BlocksStoneDust.cleanedStone), stoneDust);
				System.out.println("[StoneDust] Thermal Expansion found");
			}
			if (validadeMekanism()) {
				PulverizerManager.addRecipe(1250, new ItemStack(BlocksStoneDust.cleanedStone), stoneDust);
				System.out.println("[StoneDust] Mekanism found");
			}
		}
	}

	private boolean validadeIC2() {
		try {
			Class recipeClass = Class.forName("ic2.api.info.Info");
			return true;
		} catch (Exception e) {
			System.err.println("[StoneDust] IndustrialCraft 2 Experimental not found, you can not use my Mod");
		}
		return false;
	}

	private boolean validadeTE4() {
		try {
			Class recipeClass = Class.forName("thermalexpansion.ThermalExpansion");
			return true;
		} catch (Exception e) {
			System.err.println("[StoneDust] Thermal Expansion not found");
		}
		return false;
	}

	private boolean validadeMekanism() {
		try {
			Class recipeClass = Class.forName("mekanism.api.MekanismAPI");
			return true;
		} catch (Exception e) {
			System.err.println("[StoneDust] Mekanism not found");
		}
		return false;
	}
}
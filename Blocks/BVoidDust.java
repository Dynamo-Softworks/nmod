package com.ncd1998.nmod.Blocks;
 
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.ncd1998.nmod.nmod;
import com.ncd1998.nmod.Init.NBlocks;
import com.ncd1998.nmod.Init.NItems;


public class BVoidDust extends NBlock
{
	private final String name = "BVoidDust";
	public BVoidDust()
	{
		super(Material.circuits);
		GameRegistry.registerBlock(this, name);
		setUnlocalizedName(nmod.MODID + "_" + name);
		setHardness(.1F);
		setStepSound(Block.soundTypeStone);
		setResistance(.1F);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.0625F, 1.0F);
	}
	public String getName()
	{
		return name;
	}
	@Override
	public boolean isOpaqueCube()
    {
        return false;
    }
	@Override
    public boolean isFullCube()
    {
        return false;
    }
	@Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        return World.doesBlockHaveSolidTopSurface(worldIn, pos.down()) || worldIn.getBlockState(pos.down()).getBlock() == Blocks.glowstone;
    }
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return NItems.VoidDust;
    }
	@Override
	@SideOnly(Side.CLIENT)
    public EnumWorldBlockLayer getBlockLayer()
    {
        return EnumWorldBlockLayer.CUTOUT;
    }
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ)
    {
		//Crossing Rune
		if(worldIn.getBlockState(pos.north()).equals(NBlocks.BVoidDust.getDefaultState())){
			if(worldIn.getBlockState(pos.east()).equals(NBlocks.BVoidDust.getDefaultState())){
				if(worldIn.getBlockState(pos.south()).equals(NBlocks.BVoidDust.getDefaultState())){
					if(worldIn.getBlockState(pos.west()).equals(NBlocks.BVoidDust.getDefaultState())){
						if(worldIn.getBlockState(pos.north().east()).equals(Blocks.air.getDefaultState()) && worldIn.getBlockState(pos.north().west()).equals(Blocks.air.getDefaultState())&& worldIn.getBlockState(pos.south().west()).equals(Blocks.air.getDefaultState())&& worldIn.getBlockState(pos.south().east()).equals(Blocks.air.getDefaultState())){
							worldIn.setBlockToAir(pos);
							worldIn.setBlockToAir(pos.north());
							worldIn.setBlockToAir(pos.east());
							worldIn.setBlockToAir(pos.south());
							worldIn.setBlockToAir(pos.west());
							playerIn.inventory.addItemStackToInventory(new ItemStack(NBlocks.VoidCrosshairRune));
						}
						
					}
				}
			}
		}
        return false;
    }

}
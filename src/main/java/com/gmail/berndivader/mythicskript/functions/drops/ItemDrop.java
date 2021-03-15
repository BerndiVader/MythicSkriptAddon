package com.gmail.berndivader.mythicskript.functions.drops;

import org.bukkit.inventory.ItemStack;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.function.Function;
import io.lumine.xikage.mythicmobs.adapters.AbstractItemStack;
import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
import io.lumine.xikage.mythicmobs.drops.Drop;
import io.lumine.xikage.mythicmobs.drops.DropMetadata;
import io.lumine.xikage.mythicmobs.drops.IItemDrop;
import io.lumine.xikage.mythicmobs.io.MythicLineConfig;

public class ItemDrop extends Drop implements IItemDrop {
	
	Function<?>function;
	Object[][]parameters;

	public ItemDrop(String line, MythicLineConfig mlc, Function<?>f) {
		super(line, mlc);
		
		function=f;
		parameters=new Object[1][];
		
	}

	@Override
	public AbstractItemStack getDrop(DropMetadata data) {
		parameters[0]=new DropMetadata[] {data};
		Object[]result=function.execute(parameters);
		if(result!=null&&(result[0] instanceof ItemStack)) {
			return BukkitAdapter.adapt((ItemStack)result[0]);
		}
		Skript.warning("The return for "+function.getName()+" expects an itemstack but got "+result==null?"NULL":result[0].getClass().getName());
		return null;
	}

}

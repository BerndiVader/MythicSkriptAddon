package com.gmail.berndivader.mythicskript.functions.drops;

import javax.annotation.Nullable;

import org.bukkit.inventory.ItemStack;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.function.Function;
import io.lumine.mythic.api.adapters.AbstractItemStack;
import io.lumine.mythic.api.config.MythicLineConfig;
import io.lumine.mythic.api.drops.DropMetadata;
import io.lumine.mythic.api.drops.IItemDrop;
import io.lumine.mythic.bukkit.BukkitAdapter;
import io.lumine.mythic.core.drops.Drop;

public class ItemDrop extends Drop implements IItemDrop {
	
	Function<?>function;
	Object[][]parameters;

	public ItemDrop(String line, MythicLineConfig mlc, Function<?>f) {
		super(line, mlc);
		
		function=f;
		parameters=new Object[2][];
		
	}

	@Override
	@Nullable
	public AbstractItemStack getDrop(DropMetadata data, double amount) {
		parameters[0]=new DropMetadata[] {data};
		parameters[0]=new Number[] {amount};
		Object[]result=function.execute(parameters);
		if(result!=null&&(result[0] instanceof ItemStack)) {
			return BukkitAdapter.adapt((ItemStack)result[0]);
		}
		Skript.warning("The return for "+function.getName()+" expects an itemstack but got "+result==null?"NULL":result[0].getClass().getName());
		return null;
	}

}

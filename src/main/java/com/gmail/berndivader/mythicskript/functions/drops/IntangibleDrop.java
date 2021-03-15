package com.gmail.berndivader.mythicskript.functions.drops;

import org.bukkit.entity.Player;

import ch.njol.skript.lang.function.Function;
import io.lumine.xikage.mythicmobs.adapters.AbstractPlayer;
import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
import io.lumine.xikage.mythicmobs.drops.Drop;
import io.lumine.xikage.mythicmobs.drops.DropMetadata;
import io.lumine.xikage.mythicmobs.drops.IIntangibleDrop;
import io.lumine.xikage.mythicmobs.io.MythicLineConfig;

public class IntangibleDrop extends Drop implements IIntangibleDrop {
	
	Function<?>function;
	Object[][]parameters;

	public IntangibleDrop(String line, MythicLineConfig mlc, Function<?>f) {
		super(line,mlc);
		
		function=f;
		parameters=new Object[2][];
		
	}

	@Override
	public void giveDrop(AbstractPlayer aPlayer, DropMetadata data) {
		parameters[0]=new DropMetadata[] {data};
		parameters[1]=new Player[] {BukkitAdapter.adapt(aPlayer)};
		function.execute(parameters);
	}

}

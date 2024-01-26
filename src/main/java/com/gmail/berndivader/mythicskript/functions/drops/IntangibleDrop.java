package com.gmail.berndivader.mythicskript.functions.drops;

import org.bukkit.entity.Player;

import ch.njol.skript.lang.function.Function;
import io.lumine.mythic.api.adapters.AbstractPlayer;
import io.lumine.mythic.api.config.MythicLineConfig;
import io.lumine.mythic.api.drops.DropMetadata;
import io.lumine.mythic.api.drops.IIntangibleDrop;
import io.lumine.mythic.bukkit.BukkitAdapter;
import io.lumine.mythic.core.drops.Drop;

public class IntangibleDrop extends Drop implements IIntangibleDrop {
	
	Function<?>function;
	Object[][]parameters;

	public IntangibleDrop(String line, MythicLineConfig mlc, Function<?>f) {
		super(line,mlc);
		
		function=f;
		parameters=new Object[3][];
	}

	@Override
	public void giveDrop(AbstractPlayer player, DropMetadata meta, double amount) {
		parameters[0]=new DropMetadata[] {meta};
		parameters[1]=new Player[] {BukkitAdapter.adapt(player)};
		parameters[2]=new Number[] {amount};
		function.execute(parameters);
	}

}

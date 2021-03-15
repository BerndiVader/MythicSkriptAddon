package com.gmail.berndivader.mythicskript.effects.dropmetadata;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import io.lumine.xikage.mythicmobs.drops.DropMetadata;

public class SetAmount extends Effect {
	
	Expression<DropMetadata>expData;
	Expression<Float>amount;

	@Override
	public boolean init(Expression<?>[] expr, int arg1, Kleenean arg2, ParseResult arg3) {
		
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void execute(Event arg0) {
		// TODO Auto-generated method stub
		
	}

}

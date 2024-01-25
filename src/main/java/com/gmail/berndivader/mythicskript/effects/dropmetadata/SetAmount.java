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

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int arg1, Kleenean arg2, ParseResult arg3) {
		expData=(Expression<DropMetadata>)expr[0];
		amount=(Expression<Float>)expr[1];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean arg1) {
		return getClass().getSimpleName()+e!=null?"@"+e.getEventName():"";
	}

	@Override
	protected void execute(Event e) {
		DropMetadata meta=expData.getSingle(e);
		meta.setAmount(amount.getSingle(e).floatValue());
	}

}

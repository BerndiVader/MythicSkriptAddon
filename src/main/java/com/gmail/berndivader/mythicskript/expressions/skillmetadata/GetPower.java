package com.gmail.berndivader.mythicskript.expressions.skillmetadata;

import org.jetbrains.annotations.Nullable;

import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import io.lumine.mythic.api.skills.SkillMetadata;

public class GetPower extends SimpleExpression<Float> {
	
	Expression<SkillMetadata>data;

	@Override
	public Class<? extends Float> getReturnType() {
		return Float.class;
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expression, int var1, Kleenean kleenean, ParseResult result) {
		return (data=(Expression<SkillMetadata>)expression[0])!=null;
	}

	@Override
	public String toString(@Nullable Event e, boolean bool) {
		return getClass().getSimpleName()+e!=null?"@"+e.getEventName():"";
	}

	@Override
	protected Float[] get(Event event) {
		SkillMetadata meta=data.getSingle(event);
		if(meta!=null) {
			return new Float[] {meta.getPower()};
		}
		return new Float[0];
	}

}

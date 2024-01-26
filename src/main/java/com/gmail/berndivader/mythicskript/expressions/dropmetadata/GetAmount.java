package com.gmail.berndivader.mythicskript.expressions.dropmetadata;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import io.lumine.mythic.api.drops.DropMetadata;

public class GetAmount extends SimpleExpression<Float> {
	
	Expression<DropMetadata>expr;

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
	public boolean init(Expression<?>[] expression, int arg1, Kleenean kleenean, ParseResult result) {
		return (expr=(Expression<DropMetadata>)expression[0])!=null;
	}

	@Override
	public String toString(@Nullable Event e, boolean arg1) {
		return getClass().getSimpleName()+e!=null?"@"+e.getEventName():"";
	}

	@Override
	protected Float[] get(Event event) {
		DropMetadata data=expr.getSingle(event);
		return new Float[] {data.getAmount()};
	}

}

package com.gmail.berndivader.mythicskript.expressions.dropmetadata;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import io.lumine.xikage.mythicmobs.drops.DropMetadata;

public class GetGenerations extends SimpleExpression<Integer> {
	
	Expression<DropMetadata>expr;

	@Override
	public Class<? extends Integer> getReturnType() {
		return Integer.class;
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
	public String toString(@Nullable Event event, boolean arg1) {
		return "GetGenerations@"+event.getEventName();
	}

	@Override
	@Nullable
	protected Integer[] get(Event event) {
		DropMetadata data=expr.getSingle(event);
		return new Integer[] {data.getGenerations()};
	}

}

package com.gmail.berndivader.mythicskript.expressions.dropmetadata;

import javax.annotation.Nullable;

import org.bukkit.entity.Entity;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import io.lumine.mythic.api.drops.DropMetadata;

public class GetCause extends SimpleExpression<Entity> {
	
	Expression<DropMetadata>expr;

	@Override
	public Class<? extends Entity> getReturnType() {
		return Entity.class;
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
	@Nullable
	protected Entity[] get(Event event) {
		DropMetadata data=expr.getSingle(event);
		if(data.getCause().isPresent()) {
			return new Entity[] {data.getCause().get().getBukkitEntity()};
		}
		return null;
	}


}

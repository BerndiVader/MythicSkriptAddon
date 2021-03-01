package com.gmail.berndivader.mythicskript.expressions.skillmetadata;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import io.lumine.xikage.mythicmobs.skills.SkillMetadata;

public class GetCause extends SimpleExpression<String> {
	
	Expression<SkillMetadata>data;

	@Override
	public Class<? extends String> getReturnType() {
		return String.class;
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
	public String toString(@Nullable Event event, boolean bool) {
		return "GetCause@"+event.getEventName();
	}

	@Override
	@Nullable
	protected String[] get(Event event) {
		SkillMetadata meta=data.getSingle(event);
		if(meta!=null&&meta.getCause()!=null) {
			return new String[] {meta.getCause().toString()};
		}
		return new String[0];
	}

}

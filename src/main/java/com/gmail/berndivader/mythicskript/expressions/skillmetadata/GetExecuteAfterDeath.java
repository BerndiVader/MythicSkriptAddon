package com.gmail.berndivader.mythicskript.expressions.skillmetadata;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import io.lumine.xikage.mythicmobs.skills.SkillMetadata;

public class GetExecuteAfterDeath extends SimpleExpression<Boolean> {
	
	Expression<SkillMetadata>data;

	@Override
	public Class<? extends Boolean> getReturnType() {
		return Boolean.class;
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
		return "ExecuteAfterDeath@"+event.getEventName();
	}

	@Override
	@Nullable
	protected Boolean[] get(Event event) {
		SkillMetadata meta=data.getSingle(event);
		if(meta!=null&&Skript.methodExists(SkillMetadata.class,"getExecuteAfterDeath")) {
			return new Boolean[] {meta.getExecuteAfterDeath()};
		}
		return new Boolean[0];
	}

}

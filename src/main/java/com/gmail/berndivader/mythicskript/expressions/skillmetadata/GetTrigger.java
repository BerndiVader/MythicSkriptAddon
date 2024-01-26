package com.gmail.berndivader.mythicskript.expressions.skillmetadata;

import javax.annotation.Nullable;

import org.bukkit.entity.Entity;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import io.lumine.mythic.api.skills.SkillMetadata;

public class GetTrigger extends SimpleExpression<Entity> {
	
	Expression<SkillMetadata>data;

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
	public boolean init(Expression<?>[] expression, int var1, Kleenean kleenean, ParseResult result) {
		return (data=(Expression<SkillMetadata>)expression[0])!=null;
	}

	@Override
	public String toString(@Nullable Event e, boolean bool) {
		return getClass().getSimpleName()+e!=null?"@"+e.getEventName():"";
	}

	@Override
	protected Entity[] get(Event event) {
		SkillMetadata meta=data.getSingle(event);
		if(meta!=null&&meta.getTrigger()!=null) {
			return new Entity[] {meta.getTrigger().getBukkitEntity()};
		}
		return new Entity[0];
	}

}

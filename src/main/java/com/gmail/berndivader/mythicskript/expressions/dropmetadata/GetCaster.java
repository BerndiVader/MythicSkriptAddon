package com.gmail.berndivader.mythicskript.expressions.dropmetadata;

import org.jetbrains.annotations.Nullable;

import org.bukkit.entity.Entity;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import io.lumine.mythic.api.drops.DropMetadata;
import io.lumine.mythic.api.skills.SkillCaster;

public class GetCaster extends SimpleExpression<Entity> {
	
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
		SkillCaster caster=data.getCaster();
		if(caster!=null) {
			return new Entity[] {caster.getEntity().getBukkitEntity()};
		}
		return null;
	}

}

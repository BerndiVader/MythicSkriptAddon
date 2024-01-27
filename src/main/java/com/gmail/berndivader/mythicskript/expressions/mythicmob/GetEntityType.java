package com.gmail.berndivader.mythicskript.expressions.mythicmob;

import org.jetbrains.annotations.Nullable;

import org.bukkit.entity.EntityType;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import io.lumine.mythic.api.mobs.MythicMob;

public class GetEntityType extends SimpleExpression<EntityType>{
	private Expression<MythicMob> skMythicMob; 

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public Class<? extends EntityType> getReturnType() {
		return EntityType.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matched, Kleenean var3, ParseResult var4) {
		this.skMythicMob = (Expression<MythicMob>) expr[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean var2) {
		return getClass().getSimpleName()+e!=null?"@"+e.getEventName():"";
	}

	@Override
	protected EntityType[] get(Event e) {
		EntityType et = null;
		MythicMob mm = this.skMythicMob.getSingle(e);
		String t = mm.getEntityType().name().toUpperCase();
		try {
			et = EntityType.valueOf(t);
		} catch (Exception ex) {
			et = EntityType.UNKNOWN;
		}
		return new EntityType[]{et};
	}
}

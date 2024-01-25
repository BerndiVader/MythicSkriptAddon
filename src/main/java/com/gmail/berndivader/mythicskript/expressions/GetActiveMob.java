package com.gmail.berndivader.mythicskript.expressions;

import org.bukkit.entity.Entity;
import org.bukkit.event.Event;

import com.gmail.berndivader.mythicskript.Utils;

import javax.annotation.Nullable;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import io.lumine.xikage.mythicmobs.mobs.ActiveMob;

public class GetActiveMob extends SimpleExpression<ActiveMob>{
	private Expression<Entity> bukkitEntity;

	@Override
	public Class<? extends ActiveMob> getReturnType() {
		return ActiveMob.class;
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean arg2, ParseResult arg3) {
		bukkitEntity = (Expression<Entity>) expr[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean arg1) {
		return getClass().getSimpleName()+e!=null?"@"+e.getEventName():"";
	}
	@Override
	@Nullable
	protected ActiveMob[] get(Event e) {
		ActiveMob am = Utils.mobManager.getMythicMobInstance(bukkitEntity.getSingle(e));
		if (am!=null) return new ActiveMob[] {am};
		return null;
	}
}

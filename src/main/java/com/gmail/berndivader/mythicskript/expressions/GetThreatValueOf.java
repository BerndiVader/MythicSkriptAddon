package com.gmail.berndivader.mythicskript.expressions;

import javax.annotation.Nullable;

import org.bukkit.entity.Entity;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import io.lumine.mythic.bukkit.BukkitAdapter;
import io.lumine.mythic.core.mobs.ActiveMob;

public class GetThreatValueOf extends SimpleExpression<Number>{
	private Expression<ActiveMob> activemob;
	private Expression<Entity> skriptEntity;

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public Class<? extends Number> getReturnType() {
		return Number.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int i, Kleenean var3, ParseResult var4) {
		this.skriptEntity = (Expression<Entity>) expr[0];
		this.activemob = (Expression<ActiveMob>) expr[1];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean var2) {
		return getClass().getSimpleName()+e!=null?"@"+e.getEventName():"";
	}

	@Override
	@Nullable
	protected Number[] get(Event e) {
		ActiveMob am = this.activemob.getSingle(e);
		Entity entity = this.skriptEntity.getSingle(e);
		return am.hasThreatTable()?new Number[]{am.getThreatTable().getThreat(BukkitAdapter.adapt(entity))}:null;
	}
}

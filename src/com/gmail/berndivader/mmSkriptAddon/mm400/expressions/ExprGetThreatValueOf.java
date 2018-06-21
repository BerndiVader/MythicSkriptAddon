package com.gmail.berndivader.mmSkriptAddon.mm400.expressions;

import javax.annotation.Nullable;

import org.bukkit.entity.Entity;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
import io.lumine.xikage.mythicmobs.mobs.ActiveMob;

public class ExprGetThreatValueOf extends SimpleExpression<Number>{
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
	public String toString(@Nullable Event var1, boolean var2) {
		return null;
	}

	@Override
	@Nullable
	protected Number[] get(Event e) {
		ActiveMob am = this.activemob.getSingle(e);
		Entity entity = this.skriptEntity.getSingle(e);
		return am.hasThreatTable()?new Number[]{am.getThreatTable().getThreat(BukkitAdapter.adapt(entity))}:null;
	}
}

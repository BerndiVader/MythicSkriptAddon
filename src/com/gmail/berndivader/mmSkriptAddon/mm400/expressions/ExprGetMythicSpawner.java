package com.gmail.berndivader.mmSkriptAddon.mm400.expressions;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
import io.lumine.xikage.mythicmobs.spawning.spawners.MythicSpawner;

public class ExprGetMythicSpawner extends SimpleExpression<MythicSpawner> {
	private Expression<ActiveMob> activeMob;

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public Class<? extends MythicSpawner> getReturnType() {
		return MythicSpawner.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int i, Kleenean var3, ParseResult var4) {
		activeMob = (Expression<ActiveMob>) expr[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event var1, boolean var2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Nullable
	protected MythicSpawner[] get(Event e) {
		ActiveMob am = activeMob.getSingle(e);
		if (am.getSpawner()==null) return null;
		return new MythicSpawner[]{am.getSpawner()};
	}
}

package com.gmail.berndivader.mythicskript.expressions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Nullable;

import org.bukkit.entity.Entity;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import io.lumine.mythic.api.adapters.AbstractEntity;
import io.lumine.mythic.core.mobs.ActiveMob;

public class GetThreatTable extends SimpleExpression<Entity> {
	private Expression<ActiveMob> activemob;

	@Override
	public boolean isSingle() {
		return false;
	}

	@Override
	public Class<? extends Entity> getReturnType() {
		return Entity.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int i, Kleenean var3, ParseResult var4) {
		activemob = (Expression<ActiveMob>) expr[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean var2) {
		return getClass().getSimpleName()+e!=null?"@"+e.getEventName():"";
	}

	@Override
	@Nullable
	protected Entity[] get(Event e) {
		ActiveMob am = activemob.getSingle(e);
		if (am.hasThreatTable()) {
			List<Entity> ttes = new ArrayList<Entity>();
			Iterator<AbstractEntity>it = am.getThreatTable().getAllThreatTargets().iterator();
			while (it.hasNext()) {
				ttes.add(it.next().getBukkitEntity());
			}
			return ttes.toArray(new Entity[0]);
		}
		return null;
	}
}

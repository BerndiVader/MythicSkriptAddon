package com.gmail.berndivader.mythicskript.expressions.event;

import javax.annotation.Nullable;

import org.bukkit.entity.Entity;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import io.lumine.mythic.bukkit.events.MythicMobDeathEvent;
import io.lumine.mythic.bukkit.events.MythicMobLootDropEvent;

public class DeathEventAttacker extends SimpleExpression<Entity>{
	
	//event-killer for MyhicMobDeathEvent & MythicMobLootDropEvent

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public Class<? extends Entity> getReturnType() {
		return Entity.class;
	}

	@Override
	public boolean init(Expression<?>[] e, int var2, Kleenean var3, ParseResult var4) {
		return getParser().isCurrentEvent(MythicMobDeathEvent.class, MythicMobLootDropEvent.class);
	}

	@Override
	public String toString(@Nullable Event e, boolean var2) {
		return getClass().getSimpleName()+e!=null?"@"+e.getEventName():"";
	}

	@Override
	protected Entity[] get(Event e) {
		return e instanceof MythicMobDeathEvent?new Entity[]{((MythicMobDeathEvent)e).getKiller()}:new Entity[]{((MythicMobLootDropEvent)e).getKiller()};
	}
}

package com.gmail.berndivader.mmSkriptAddon.mm400.expressions.event;

import javax.annotation.Nullable;

import org.bukkit.entity.Entity;
import org.bukkit.event.Event;

import ch.njol.skript.ScriptLoader;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobDeathEvent;

public class DeathEventAttacker extends SimpleExpression<Entity>{
	
	//deathevent-killer

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
		if (!ScriptLoader.isCurrentEvent(MythicMobDeathEvent.class)) {
			Skript.error("Only allowed in MythicMobDeath Event!");
			return false;
		}
		return true;
	}

	@Override
	public String toString(@Nullable Event var1, boolean var2) {
		return "event-killer";
	}

	@Override
	@Nullable
	protected Entity[] get(Event e) {
		return new Entity[]{((MythicMobDeathEvent)e).getKiller()};
	}
}

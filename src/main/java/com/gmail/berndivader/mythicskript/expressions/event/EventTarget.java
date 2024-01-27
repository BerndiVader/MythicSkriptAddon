package com.gmail.berndivader.mythicskript.expressions.event;

import org.jetbrains.annotations.Nullable;

import org.bukkit.entity.Entity;
import org.bukkit.event.Event;

import com.gmail.berndivader.mythicskript.events.skript.MythicSkriptSkillEvent;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class EventTarget extends SimpleExpression<Entity>{
	
	//event-target

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public Class<? extends Entity> getReturnType() {
		return Entity.class;
	}

	@Override
	public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelay, ParseResult parser) {
		if (!getParser().isCurrentEvent(MythicSkriptSkillEvent.class)) {
			Skript.error("Only allowed in SkriptSkill Event!");
			return false;
		}
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean var2) {
		return getClass().getSimpleName()+e!=null?"@"+e.getEventName():"";
	}

	@Override
	protected Entity[] get(Event e) {
		return new Entity[]{((MythicSkriptSkillEvent)e).getTargetEntity()};
	}
}

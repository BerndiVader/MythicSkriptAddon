package com.gmail.berndivader.mythicskript.effects.mythicspawner;

import org.jetbrains.annotations.Nullable;

import org.bukkit.event.Event;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import io.lumine.mythic.core.spawning.spawners.MythicSpawner;

public class SetMobTypeOfSpawner extends Effect {
	private Expression<MythicSpawner> skriptSpawner;
	private Expression<String> skriptMobType;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int var2, Kleenean var3, ParseResult var4) {
		skriptSpawner = (Expression<MythicSpawner>) expr[0];
		skriptMobType = (Expression<String>) expr[1];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean var2) {
		return getClass().getSimpleName()+e!=null?"@"+e.getEventName():"";
	}

	@Override
	protected void execute(Event e) {
		MythicSpawner ms = skriptSpawner.getSingle(e);
		String mobtype = skriptMobType.getSingle(e);
		if(!mobtype.isEmpty()&&ms!=null) ms.setType(mobtype);
	}
}

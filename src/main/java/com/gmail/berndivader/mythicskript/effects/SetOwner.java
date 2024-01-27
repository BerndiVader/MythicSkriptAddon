package com.gmail.berndivader.mythicskript.effects;

import java.util.UUID;

import org.jetbrains.annotations.Nullable;

import org.bukkit.entity.Entity;
import org.bukkit.event.Event;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import io.lumine.mythic.core.mobs.ActiveMob;

public class SetOwner extends Effect {
	private Expression<Entity> skriptEntity;
	private Expression<String> skriptUUID;
	private Expression<ActiveMob> skriptMob;
	private Boolean byUUID;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedParser, Kleenean var3, ParseResult var4) {
		skriptMob = (Expression<ActiveMob>) expr[0];
		byUUID=matchedParser==1;
		if (byUUID) {
			skriptUUID = (Expression<String>) expr[1];
		} else {
			skriptEntity = (Expression<Entity>) expr[1];
		}
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean var2) {
		return getClass().getSimpleName()+e!=null?"@"+e.getEventName():"";
	}

	@Override
	protected void execute(Event e) {
		ActiveMob am = skriptMob.getSingle(e);
		if (am==null) return;
		UUID uuid;
		if (byUUID) {
			try {
				uuid = UUID.fromString(skriptUUID.getSingle(e));
			} catch (ExceptionInInitializerError ex) {
				Skript.warning("Set owner for uuid "+skriptUUID.getSingle(e)+" wrong!");
				return;
			}
		} else {
			Entity owner = skriptEntity.getSingle(e);
			uuid = owner.getUniqueId();
		}
		am.setOwner(uuid);
	}
}

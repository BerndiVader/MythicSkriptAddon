package com.gmail.berndivader.mythicskript.effects;

import java.util.UUID;

import javax.annotation.Nullable;

import org.bukkit.entity.Entity;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import io.lumine.xikage.mythicmobs.mobs.ActiveMob;

public class SetOwner extends Effect {
	private Expression<Entity> skriptEntity;
	private Expression<String> skriptUUID;
	private Expression<ActiveMob> skriptMob;
	private Boolean byUUID;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedParser, Kleenean var3, ParseResult var4) {
		skriptMob = (Expression<ActiveMob>) expr[0];
		byUUID = matchedParser==1 ? true : false;
		if (byUUID) {
			skriptUUID = (Expression<String>) expr[1];
		} else {
			skriptEntity = (Expression<Entity>) expr[1];
		}
		return true;
	}

	@Override
	public String toString(@Nullable Event var1, boolean var2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void execute(Event e) {
		ActiveMob am = skriptMob.getSingle(e);
		UUID uuid;
		if (am==null) return;
		if (byUUID) {
			try {
				uuid = UUID.fromString(skriptUUID.getSingle(e));
			} catch (ExceptionInInitializerError ex) {return;}
		} else {
			Entity owner = skriptEntity.getSingle(e);
			uuid = owner.getUniqueId();
		}
		am.setOwner(uuid);
	}
}

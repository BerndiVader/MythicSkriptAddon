package com.gmail.berndivader.mythicskript.expressions;

import java.util.UUID;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import com.gmail.berndivader.mythicskript.Utils;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import io.lumine.mythic.core.mobs.ActiveMob;

public class GetMobByUUID extends SimpleExpression<ActiveMob> {
	private Expression<String> stringUUID;

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public Class<? extends ActiveMob> getReturnType() {
		return ActiveMob.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int var2, Kleenean var3, ParseResult var4) {
		stringUUID = (Expression<String>) expr[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean var2) {
		return getClass().getSimpleName()+e!=null?"@"+e.getEventName():"";
	}

	@Override
	@Nullable
	protected ActiveMob[] get(Event e) {
		UUID uuid;
		try {
			uuid = UUID.fromString(stringUUID.getSingle(e));
		} catch (ExceptionInInitializerError ex) {
			return null;
		}
		if (Utils.mobManager.isActiveMob(uuid)) {
			return new ActiveMob[]{Utils.mobManager.getActiveMob(uuid).get()};
		}
		return null;
	}
}

package com.gmail.berndivader.mmSkriptAddon.mm400.expressions;

import java.util.UUID;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import io.lumine.xikage.mythicmobs.MythicMobs;
import io.lumine.xikage.mythicmobs.mobs.ActiveMob;

public class ExprGetMobByUUID extends SimpleExpression<ActiveMob> {
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
	public String toString(@Nullable Event var1, boolean var2) {
		return null;
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
		if (MythicMobs.inst().getMobManager().isActiveMob(uuid)) {
			return new ActiveMob[]{MythicMobs.inst().getMobManager().getActiveMob(uuid).get()};
		}
		return null;
	}
}

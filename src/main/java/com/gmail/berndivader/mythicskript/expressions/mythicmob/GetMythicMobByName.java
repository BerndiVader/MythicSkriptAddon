package com.gmail.berndivader.mythicskript.expressions.mythicmob;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import com.gmail.berndivader.mythicskript.Utils;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import io.lumine.xikage.mythicmobs.mobs.MythicMob;

public class GetMythicMobByName extends SimpleExpression<MythicMob>{
	private Expression<String> skType; 

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public Class<? extends MythicMob> getReturnType() {
		return MythicMob.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matched, Kleenean var3, ParseResult var4) {
		this.skType = (Expression<String>) expr[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean bool) {
		return getClass().getSimpleName()+e!=null?"@"+e.getEventName():"";
	}

	@Override
	protected MythicMob[] get(Event e) {
		MythicMob mm = Utils.mobManager.getMythicMob(this.skType.getSingle(e));
        return new MythicMob[]{mm};
	}
}

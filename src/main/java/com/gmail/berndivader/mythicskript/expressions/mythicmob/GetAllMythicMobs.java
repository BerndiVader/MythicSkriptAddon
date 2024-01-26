package com.gmail.berndivader.mythicskript.expressions.mythicmob;

import java.util.ArrayList;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import com.gmail.berndivader.mythicskript.Utils;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import io.lumine.mythic.api.mobs.MythicMob;

public class GetAllMythicMobs extends SimpleExpression<MythicMob> {

	@Override
	public boolean isSingle() {
		return false;
	}

	@Override
	public Class<? extends MythicMob> getReturnType() {
		return MythicMob.class;
	}

	@Override
	public boolean init(Expression<?>[] expr, int matched, Kleenean var3, ParseResult var4) {
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean var2) {
		return getClass().getSimpleName()+e!=null?"@"+e.getEventName():"";
	}

	@Override
	protected MythicMob[] get(Event e) {
        ArrayList<MythicMob> mobs = new ArrayList<MythicMob>(Utils.mobManager.getMobTypes());
        return mobs.toArray(new MythicMob[0]);
	}

}

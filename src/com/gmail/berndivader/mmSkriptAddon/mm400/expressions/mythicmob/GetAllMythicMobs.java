package com.gmail.berndivader.mmSkriptAddon.mm400.expressions.mythicmob;

import java.util.ArrayList;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import io.lumine.xikage.mythicmobs.MythicMobs;
import io.lumine.xikage.mythicmobs.mobs.MythicMob;

public class GetAllMythicMobs extends SimpleExpression<MythicMob> {

	protected MythicMobs mythicmobs = MythicMobs.inst();

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
	public String toString(@Nullable Event var1, boolean var2) {
		return null;
	}

	@Override
	@Nullable
	protected MythicMob[] get(Event e) {
        ArrayList<MythicMob> mobs = new ArrayList<MythicMob>(this.mythicmobs.getMobManager().getMobTypes());
        return mobs.toArray(new MythicMob[0]);
	}

}

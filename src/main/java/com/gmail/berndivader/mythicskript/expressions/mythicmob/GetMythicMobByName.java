package com.gmail.berndivader.mythicskript.expressions.mythicmob;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import io.lumine.xikage.mythicmobs.MythicMobs;
import io.lumine.xikage.mythicmobs.mobs.MobManager;
import io.lumine.xikage.mythicmobs.mobs.MythicMob;

public class GetMythicMobByName extends SimpleExpression<MythicMob>{
	protected MobManager mobmanager = MythicMobs.inst().getMobManager();
	protected Expression<String> skType; 

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
		return null;
	}

	@Override
	@Nullable
	protected MythicMob[] get(Event e) {
		MythicMob mm = this.mobmanager.getMythicMob(this.skType.getSingle(e));
        return new MythicMob[]{mm};
	}
}

package com.gmail.berndivader.mythicskript.effects.mobitems;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import com.gmail.berndivader.mythicskript.classes.MobItem;
import com.gmail.berndivader.mythicskript.classes.MythicDrops;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class RemoveMobItem extends Effect {
	private Expression<MobItem> skriptItem;
	private Expression<MythicDrops> skriptDrops;
	private Boolean bool;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int i, Kleenean var3, ParseResult var4) {
		bool=i==0;
		if (bool) {
			skriptItem = (Expression<MobItem>) expr[0];
			skriptDrops = (Expression<MythicDrops>) expr[1];
		} else {
			skriptDrops = (Expression<MythicDrops>) expr[0];
		}
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean var2) {
		return getClass().getSimpleName()+e!=null?"@"+e.getEventName():"";
	}

	@Override
	protected void execute(Event e) {
		MythicDrops md = skriptDrops.getSingle(e);
		if (bool) {
			MobItem mi = skriptItem.getSingle(e);
			md.removeItem(mi.getItem());
		} else {
			md.getDrops().clear();
		}
	}
}

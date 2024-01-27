package com.gmail.berndivader.mythicskript.effects.mobitems;

import org.jetbrains.annotations.Nullable;
import com.gmail.berndivader.mythicskript.classes.MythicDrops;

import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class AddItemToMobDrop extends Effect {
	private Expression<MythicDrops> skriptDrops;
	private Expression<ItemStack> skriptItem;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int var2, Kleenean var3, ParseResult var4) {
		skriptItem = (Expression<ItemStack>) expr[0];
		skriptDrops = (Expression<MythicDrops>) expr[1];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean var2) {
		return getClass().getSimpleName()+e!=null?"@"+e.getEventName():"";
	}

	@Override
	protected void execute(Event e) {
		ItemStack i = skriptItem.getSingle(e);
		if (i!=null) {
			MythicDrops md = skriptDrops.getSingle(e);
			md.addItem(i);
		}
	}
}

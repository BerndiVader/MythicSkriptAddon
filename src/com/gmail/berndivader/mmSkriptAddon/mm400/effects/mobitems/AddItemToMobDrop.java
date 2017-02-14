package com.gmail.berndivader.mmSkriptAddon.mm400.effects.mobitems;

import javax.annotation.Nullable;
import com.gmail.berndivader.mmSkriptAddon.mm400.classes.MythicDrops;

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
	public String toString(@Nullable Event var1, boolean var2) {
		// TODO Auto-generated method stub
		return null;
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

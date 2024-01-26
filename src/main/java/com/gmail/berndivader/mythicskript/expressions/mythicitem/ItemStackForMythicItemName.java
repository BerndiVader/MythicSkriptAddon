package com.gmail.berndivader.mythicskript.expressions.mythicitem;

import java.util.Optional;

import javax.annotation.Nullable;

import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

import com.gmail.berndivader.mythicskript.Utils;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import io.lumine.mythic.bukkit.BukkitAdapter;
import io.lumine.mythic.core.items.MythicItem;

public class ItemStackForMythicItemName extends SimpleExpression<ItemStack> {
	
	private Expression<String>expression;

	@Override
	public Class<? extends ItemStack> getReturnType() {
		return ItemStack.class;
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matched, Kleenean arg2, ParseResult arg3) {
		expression=(Expression<String>)expr[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean arg1) {
		return getClass().getSimpleName()+e!=null?"@"+e.getEventName():"";
	}

	@Override
	protected ItemStack[] get(Event event) {
		String name=expression.getSingle(event);
		Optional<MythicItem>maybe=Utils.itemManager.getItem(name);
		if(maybe.isPresent()) {
			return new ItemStack[] {BukkitAdapter.adapt(maybe.get().generateItemStack(1))};
		} else {
			return new ItemStack[0];
		}
	}

}

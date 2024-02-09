package com.gmail.berndivader.mythicskript.expressions.mythicitem;

import java.util.Optional;

import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

import com.gmail.berndivader.mythicskript.Utils;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import io.lumine.mythic.bukkit.BukkitAdapter;
import io.lumine.mythic.core.items.MythicItem;

public class ItemStackForMythicItemByName extends SimpleExpression<ItemStack> {
	
	private Expression<String>skName;
	private Expression<Number>skAmount=null;

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
		skName=(Expression<String>)expr[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean arg1) {
		return getClass().getSimpleName()+e!=null?"@"+e.getEventName():"";
	}

	@Override
	protected ItemStack[] get(Event event) {
		String name=skName.getSingle(event);
		Optional<MythicItem>maybe=Utils.itemManager.getItem(name);
		if(maybe.isPresent()) {
			int amount=skAmount!=null?skAmount.getSingle(event).intValue():1;
			return new ItemStack[] {BukkitAdapter.adapt(maybe.get().generateItemStack(amount))};
		} else {
			return new ItemStack[0];
		}
	}

}

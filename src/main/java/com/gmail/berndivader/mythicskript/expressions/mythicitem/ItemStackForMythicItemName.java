package com.gmail.berndivader.mythicskript.expressions.mythicitem;

import java.util.Optional;

import javax.annotation.Nullable;

import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import io.lumine.xikage.mythicmobs.MythicMobs;
import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
import io.lumine.xikage.mythicmobs.items.ItemManager;
import io.lumine.xikage.mythicmobs.items.MythicItem;

public class ItemStackForMythicItemName extends SimpleExpression<ItemStack> {
	
	protected Expression<String>expression;
	ItemManager itemManager=MythicMobs.inst().getItemManager();

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
	public String toString(@Nullable Event event, boolean arg1) {
		return expression.getSingle(event);
	}

	@Override
	@Nullable
	protected ItemStack[] get(Event event) {
		String name=expression.getSingle(event);
		Optional<MythicItem>maybe=itemManager.getItem(name);
		if(maybe.isPresent()) {
			return new ItemStack[] {BukkitAdapter.adapt(maybe.get().generateItemStack(1))};
		} else {
			return new ItemStack[0];
		}
	}

}

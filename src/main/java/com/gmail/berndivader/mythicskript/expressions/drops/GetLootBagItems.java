package com.gmail.berndivader.mythicskript.expressions.drops;

import java.util.ArrayList;
import java.util.List;

import org.jetbrains.annotations.Nullable;

import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import io.lumine.mythic.api.drops.IItemDrop;
import io.lumine.mythic.bukkit.BukkitAdapter;
import io.lumine.mythic.core.drops.Drop;
import io.lumine.mythic.core.drops.LootBag;


public class GetLootBagItems extends SimpleExpression<ItemStack> {
	Expression<LootBag> bagExpr;

	@Override
	public Class<? extends ItemStack> getReturnType() {
		return ItemStack.class;
	}

	@Override
	public boolean isSingle() {
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expression, int arg1, Kleenean arg2, ParseResult arg3) {
		bagExpr=(Expression<LootBag>)expression[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean arg1) {
		return getClass().getSimpleName()+e!=null?"@"+e.getEventName():"";
	}

	@Override
	protected ItemStack[] get(Event event) {
		LootBag lootBag=bagExpr.getSingle(event);
		List<ItemStack>items=new ArrayList<>();
		for(Drop drop:lootBag.getDrops()) {
			if(drop instanceof IItemDrop) {
				/*
				 * TODO:
				 * What is that double in getDrop good for?
				 * 
				 */
				items.add(BukkitAdapter.adapt(((IItemDrop) drop).getDrop(lootBag.getMetadata(),1d)));
			}
		}
		return items.toArray(new ItemStack[items.size()]);
	}
}

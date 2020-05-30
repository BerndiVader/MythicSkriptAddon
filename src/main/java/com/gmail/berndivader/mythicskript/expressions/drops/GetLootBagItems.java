package com.gmail.berndivader.mythicskript.expressions.drops;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
import io.lumine.xikage.mythicmobs.drops.Drop;
import io.lumine.xikage.mythicmobs.drops.IItemDrop;
import io.lumine.xikage.mythicmobs.drops.LootBag;

public class GetLootBagItems extends SimpleExpression<ItemStack> {
	Expression<LootBag> bagExpr;

	@Override
	public Class<? extends ItemStack> getReturnType() {
		return ItemStack.class;
	}

	@Override
	public boolean isSingle() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean init(Expression<?>[] expression, int arg1, Kleenean arg2, ParseResult arg3) {
		bagExpr=(Expression<LootBag>)expression[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event event, boolean arg1) {
		return bagExpr.getSingle(event).toString();
	}

	@Override
	@Nullable
	protected ItemStack[] get(Event event) {
		LootBag lootBag=bagExpr.getSingle(event);
		List<ItemStack>items=new ArrayList<>();
		for(Drop drop:lootBag.getDrops()) {
			if(drop instanceof IItemDrop) {
				items.add(BukkitAdapter.adapt(((IItemDrop) drop).getDrop(lootBag.getMetadata())));
			}
		}
		return items.toArray(new ItemStack[items.size()]);
	}
}

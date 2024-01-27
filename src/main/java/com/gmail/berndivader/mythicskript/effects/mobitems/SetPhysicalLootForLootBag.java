package com.gmail.berndivader.mythicskript.effects.mobitems;

import java.util.ArrayList;
import java.util.List;

import org.jetbrains.annotations.Nullable;

import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import io.lumine.mythic.bukkit.BukkitAdapter;
import io.lumine.mythic.bukkit.adapters.BukkitItemStack;
import io.lumine.mythic.core.drops.Drop;
import io.lumine.mythic.core.drops.LootBag;
import io.lumine.mythic.core.drops.droppables.ItemDrop;

public class SetPhysicalLootForLootBag extends Effect {
	Expression<LootBag> exprBag;
	Expression<ItemStack> exprItem;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int arg1, Kleenean arg2, ParseResult arg3) {
		if(expr.length>1) {
			exprItem=(Expression<?>)expr[1]!=null?(Expression<ItemStack>)expr[1]:(Expression<ItemStack>)expr[2];
		}
		exprBag=(Expression<LootBag>)expr[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean arg1) {
		return getClass().getSimpleName()+e!=null?"@"+e.getEventName():"";
	}

	@Override
	protected void execute(Event event) {
		LootBag lootBag=exprBag.getSingle(event);
		List<Drop>newDrops=new ArrayList<>();
		if(exprItem!=null) {
			if(exprItem.isSingle()) {
				BukkitItemStack bit=(BukkitItemStack)BukkitAdapter.adapt(exprItem.getSingle(event));
				ItemDrop drop=new ItemDrop("MMSK_DROP",null,bit);
				drop.setAmount(1);
				newDrops.add(drop);
			} else {
				ItemStack[]items=(exprItem.getArray(event));
				int size=items.length;
				for(int i1=0;i1<size;i1++) {
					BukkitItemStack bit=(BukkitItemStack)BukkitAdapter.adapt(items[i1]);
					ItemDrop drop=new ItemDrop("MMSK_DROP",null,bit);
					drop.setAmount(1);
					newDrops.add(drop);
				}
			}
		}
		lootBag.setLootTable(newDrops);
	}

}

package com.gmail.berndivader.mythicskript.effects.mobitems;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitItemStack;
import io.lumine.xikage.mythicmobs.drops.Drop;
import io.lumine.xikage.mythicmobs.drops.LootBag;
import io.lumine.xikage.mythicmobs.drops.droppables.ItemDrop;

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
	public String toString(@Nullable Event arg0, boolean arg1) {
		return exprBag.getSingle(arg0).toString();
	}

	@Override
	protected void execute(Event event) {
		LootBag lootBag=exprBag.getSingle(event);
		List<Drop>newDrops=new ArrayList<>();
		if(exprItem!=null) {
			if(exprItem.isSingle()) {
				BukkitItemStack bit=(BukkitItemStack)BukkitAdapter.adapt(exprItem.getSingle(event));
				System.err.println("single: "+bit.toString());
				ItemDrop drop=new ItemDrop("MMSK",null,bit);
				drop.setAmount(bit.getAmount());
				newDrops.add(drop);
			} else {
				ItemStack[]items=(exprItem.getArray(event)).clone();
				int size=items.length;
				for(int i1=0;i1<size;i1++) {
					BukkitItemStack bit=(BukkitItemStack)BukkitAdapter.adapt(items[i1]);
					System.err.println("array: "+bit.toString());
					ItemDrop drop=new ItemDrop("MMSK",null,bit);
					drop.setAmount(bit.getAmount());
					newDrops.add(drop);
				}
			}
		}
		lootBag.setLootTable(newDrops);
	}

}

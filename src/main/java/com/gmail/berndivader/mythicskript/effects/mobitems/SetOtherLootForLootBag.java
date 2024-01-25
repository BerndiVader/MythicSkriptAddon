package com.gmail.berndivader.mythicskript.effects.mobitems;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import io.lumine.xikage.mythicmobs.drops.Drop;
import io.lumine.xikage.mythicmobs.drops.DropMetadata;
import io.lumine.xikage.mythicmobs.drops.IItemDrop;
import io.lumine.xikage.mythicmobs.drops.IMultiDrop;
import io.lumine.xikage.mythicmobs.drops.LootBag;

public class SetOtherLootForLootBag extends Effect {
	Expression<LootBag> exprBag;
	Expression<String> exprString;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int arg1, Kleenean arg2, ParseResult arg3) {
		if(expr.length>1) {
			exprString=(Expression<?>)expr[1]!=null?(Expression<String>)expr[1]:(Expression<String>)expr[2];
		}
		exprBag=(Expression<LootBag>)expr[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean arg1) {
		return getClass().getSimpleName()+e!=null?"@"+e.getEventName():"";
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected void execute(Event event) {
		LootBag lootBag=exprBag.getSingle(event);
		
		if(exprString!=null) {
			List<String>dropList;
			if(exprString.isSingle()) {
				dropList=Arrays.asList(exprString.getSingle(event));
			} else {
				dropList=Arrays.asList(exprString.getArray(event));
			}
			setDrops(dropList,lootBag);
		} else {
			lootBag.setLootTableIntangible(new HashMap<Class, Drop>());
		}
	}
	
	static void setDrops(List<String>dropList,LootBag lootBag) {
		@SuppressWarnings("rawtypes")
		Map<Class,Drop>intangibleDrops=new HashMap<Class,Drop>();
		for(String type:dropList) {
			Drop drop=Drop.getDrop("MMSK_DROP",type);
			if(drop instanceof IMultiDrop) {
				LootBag loot=((IMultiDrop)drop).get(new DropMetadata(lootBag.getMetadata().getCaster(),lootBag.getMetadata().getCause().orElse(null)));
				for(Drop d1:loot.getDrops()) {
					if(d1 instanceof IItemDrop) {
						lootBag.add(lootBag.getMetadata(),d1);
					} else {
						intangibleDrops.merge(d1.getClass(),d1,(o,n)->o.addAmount(n));
					}
				}
			} else {
				String[]arr1=type.split(" ");
				drop.setAmount(arr1.length==1?1.0D:Double.parseDouble(arr1[1]));
				intangibleDrops.merge(drop.getClass(),drop,(o,n)->o.addAmount(n));
			}
		}
		lootBag.setLootTableIntangible(intangibleDrops);
	}

}

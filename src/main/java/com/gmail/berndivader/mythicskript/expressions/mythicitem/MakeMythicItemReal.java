package com.gmail.berndivader.mythicskript.expressions.mythicitem;

import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import io.lumine.mythic.bukkit.BukkitAdapter;
import io.lumine.mythic.core.items.MythicItem;

public class MakeMythicItemReal extends SimpleExpression<ItemStack>{
	
	Expression<MythicItem>itemExpr;
	private Expression<Number>skAmount=null;

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public Class<? extends ItemStack> getReturnType() {
		return ItemStack.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		itemExpr=(Expression<MythicItem>) expressions[0];
		if(expressions.length==2) {
			skAmount=(Expression<Number>) expressions[1];
		}		
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return getClass().getSimpleName()+e!=null?"@"+e.getEventName():"";
	}

	@Override
	protected ItemStack[] get(Event event) {
		MythicItem item=itemExpr.getSingle(event);
		int amount=skAmount!=null?skAmount.getSingle(event).intValue():1;
		return new ItemStack[] {BukkitAdapter.adapt(item.generateItemStack(amount))};
	}

}

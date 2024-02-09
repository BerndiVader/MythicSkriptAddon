package com.gmail.berndivader.mythicskript.conditions.mythicitem;

import javax.annotation.Nullable;

import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

import com.gmail.berndivader.mythicskript.Utils;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class ItemStackisMythicItem extends Condition {
	
	Expression<ItemStack>expr;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		expr=(Expression<ItemStack>) expressions[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return getClass().getSimpleName()+e!=null?"@"+e.getEventName():"";
	}

	@Override
	public boolean check(Event event) {
		return Utils.VCH.getItemHandler().getNBTData(expr.getSingle(event)).containsKey("MYTHIC_ITEM");
	}

}

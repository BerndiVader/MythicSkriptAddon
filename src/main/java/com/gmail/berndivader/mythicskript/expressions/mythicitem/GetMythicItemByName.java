package com.gmail.berndivader.mythicskript.expressions.mythicitem;

import java.util.Optional;

import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

import com.gmail.berndivader.mythicskript.Utils;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import io.lumine.mythic.core.items.MythicItem;

public class GetMythicItemByName extends SimpleExpression<MythicItem>{
	
	Expression<String>itemNameExpression;

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public Class<? extends MythicItem> getReturnType() {
		return MythicItem.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		itemNameExpression=(Expression<String>)expressions[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return getClass().getSimpleName()+e!=null?"@"+e.getEventName():"";
	}

	@Override
	protected @Nullable MythicItem[] get(Event event) {
		String itemName=itemNameExpression.getSingle(event);
		Optional<MythicItem>maybe=Utils.itemManager.getItem(itemName);
		if(maybe.isPresent()) {
			return new MythicItem[] {maybe.get()};
		}		
		return null;
	}

}

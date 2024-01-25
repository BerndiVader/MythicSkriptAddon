package com.gmail.berndivader.mythicskript.expressions.drops;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

import com.gmail.berndivader.mythicskript.classes.MobItem;
import com.gmail.berndivader.mythicskript.classes.MythicDrops;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class GetAllDrops extends SimpleExpression<MobItem>{
	private Expression<MythicDrops> skriptDrops;
	
	@Override
	public boolean isSingle() {
		return false;
	}

	@Override
	public Class<? extends MobItem> getReturnType() {
		return MobItem.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int var2, Kleenean var3, ParseResult var4) {
		skriptDrops = (Expression<MythicDrops>) expr[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean var2) {
		return getClass().getSimpleName()+e!=null?"@"+e.getEventName():"";
	}

	@Override
	protected MobItem[] get(Event e) {
		List<MobItem>test = new ArrayList<MobItem>();
		MythicDrops md = skriptDrops.getSingle(e);
		for (ItemStack i : md.getDrops()) {
			MobItem mi = new MobItem(i);
			test.add(mi);
		}
		return test.toArray(new MobItem[0]);
	}
}

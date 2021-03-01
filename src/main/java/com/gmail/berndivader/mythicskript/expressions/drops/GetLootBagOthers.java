package com.gmail.berndivader.mythicskript.expressions.drops;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import io.lumine.xikage.mythicmobs.drops.Drop;
import io.lumine.xikage.mythicmobs.drops.IIntangibleDrop;
import io.lumine.xikage.mythicmobs.drops.ILocationDrop;
import io.lumine.xikage.mythicmobs.drops.IMessagingDrop;
import io.lumine.xikage.mythicmobs.drops.LootBag;

public class GetLootBagOthers extends SimpleExpression<String> {
	Expression<LootBag> bagExpr;

	@Override
	public Class<? extends String> getReturnType() {
		return String.class;
	}

	@Override
	public boolean isSingle() {
		// TODO Auto-generated method stub
		return false;
	}

	@SuppressWarnings("unchecked")
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
	protected String[] get(Event event) {
		LootBag lootBag=bagExpr.getSingle(event);
		List<String>drops=new ArrayList<>();
		
		for(Drop drop:lootBag.getDrops()) {
			if(drop instanceof ILocationDrop||drop instanceof IIntangibleDrop||drop instanceof IMessagingDrop) {
				drops.add(drop.getLine());
			}
		}
		return drops.toArray(new String[drops.size()]);
	}
}

package com.gmail.berndivader.mythicskript.expressions.skillmetadata;

import java.util.ArrayList;

import javax.annotation.Nullable;

import org.bukkit.Location;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import io.lumine.mythic.api.skills.SkillMetadata;
import io.lumine.mythic.bukkit.BukkitAdapter;

public class GetTargetLocations extends SimpleExpression<Location> {
	
	Expression<SkillMetadata>data;

	@Override
	public Class<? extends Location> getReturnType() {
		return Location.class;
	}

	@Override
	public boolean isSingle() {
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expression, int var1, Kleenean kleenean, ParseResult result) {
		return (data=(Expression<SkillMetadata>)expression[0])!=null;
	}

	@Override
	public String toString(@Nullable Event e, boolean bool) {
		return getClass().getSimpleName()+e!=null?"@"+e.getEventName():"";
	}

	@Override
	protected Location[] get(Event event) {
		SkillMetadata meta=data.getSingle(event);
		if(meta!=null&&meta.getLocationTargets()!=null) {
			ArrayList<Location>targets=new ArrayList<>();
			meta.getLocationTargets().forEach( aLocation -> {
				targets.add(BukkitAdapter.adapt(aLocation));
			});
			return targets.toArray(new Location[0]);
		}
		return new Location[0];
	}

}

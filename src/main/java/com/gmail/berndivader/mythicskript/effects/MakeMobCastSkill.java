package com.gmail.berndivader.mythicskript.effects;

import java.util.HashSet;

import org.jetbrains.annotations.Nullable;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;

import com.gmail.berndivader.mythicskript.Utils;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import io.lumine.mythic.bukkit.BukkitAdapter;
import io.lumine.mythic.core.mobs.ActiveMob;

public class MakeMobCastSkill extends Effect {
	private Expression<ActiveMob> skriptMob;
	private Expression<String> skriptSkill;
	private Expression<Entity> bukkitTarget, bukkitTrigger;
	private Expression<Location> bukkitLocation;
	private boolean bool;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean var3, ParseResult var4) {
		bool=matchedPattern==0;
		skriptMob = (Expression<ActiveMob>) expr[0];
		skriptSkill = (Expression<String>) expr[1];
		bukkitTrigger = (Expression<Entity>) expr[2];
		if (bool) {
			bukkitTarget = (Expression<Entity>) expr[3];
		} else {
			bukkitLocation = (Expression<Location>) expr[3];
		}
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean var2) {
		return getClass().getSimpleName()+e!=null?"@"+e.getEventName():"";
	}

	@Override
	protected void execute(Event e) {
		HashSet<Entity> etargets = new HashSet<Entity>();
		HashSet<Location> ltargets = new HashSet<Location>();
		ActiveMob am = skriptMob.getSingle(e);
		
		Entity trigger = bukkitTrigger.getSingle(e);
		String skill = skriptSkill.getSingle(e);
		if (bool) {
			etargets.add(bukkitTarget.getSingle(e));
		} else {
			ltargets.add(bukkitLocation.getSingle(e));
		}
		Utils.mythicHelper.castSkill(BukkitAdapter.adapt(am.getEntity()), skill, trigger, BukkitAdapter.adapt(am.getLocation()), etargets, ltargets, 1.0f);
	}
}

package com.gmail.berndivader.mythicskript.effects;

import javax.annotation.Nullable;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
import io.lumine.xikage.mythicmobs.mobs.ActiveMob;

public class SetLastAggroCause extends Effect {
	private Expression<Entity> bukkitEntity;
	private Expression<ActiveMob> activemob;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int i, Kleenean var3, ParseResult var4) {
		bukkitEntity = (Expression<Entity>) expr[1];
		activemob = (Expression<ActiveMob>) expr[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean bool) {
		return getClass().getSimpleName()+e!=null?"@"+e.getEventName():"";
	}

	@Override
	protected void execute(Event e) {
		Entity newaggro=bukkitEntity.getSingle(e);
		if (!(newaggro instanceof LivingEntity)) return;
		ActiveMob am = activemob.getSingle(e);
		am.setLastAggroCause(BukkitAdapter.adapt(newaggro));
	}
}

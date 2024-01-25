package com.gmail.berndivader.mythicskript.effects;

import javax.annotation.Nullable;

import org.bukkit.entity.Entity;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
import io.lumine.xikage.mythicmobs.mobs.ActiveMob;

public class RemoveThreatEntity extends Effect {
	private Expression<Entity> skriptEntity;
	private Expression<ActiveMob> skriptMob;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int mp, Kleenean var3, ParseResult var4) {
		this.skriptEntity = (Expression<Entity>) expr[0];
		this.skriptMob = (Expression<ActiveMob>) expr[1];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean var2) {
		return getClass().getSimpleName()+e!=null?"@"+e.getEventName():"";
	}

	@Override
	protected void execute(Event e) {
		ActiveMob am = this.skriptMob.getSingle(e);
		AbstractEntity ae = BukkitAdapter.adapt(this.skriptEntity.getSingle(e));
		am.getThreatTable().getAllThreatTargets().remove(ae);
	}

}

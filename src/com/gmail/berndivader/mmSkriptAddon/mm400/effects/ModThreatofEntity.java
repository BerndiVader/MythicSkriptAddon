package com.gmail.berndivader.mmSkriptAddon.mm400.effects;

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

public class ModThreatofEntity extends Effect {
	private Expression<Entity> skriptEntity;
	private Expression<ActiveMob> skriptMob;
	private Expression<Number> skriptAmount;
	private boolean bool;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int mp, Kleenean var3, ParseResult var4) {
		this.bool = mp==0?true:false;
		this.skriptEntity = (Expression<Entity>) expr[0];
		this.skriptAmount = (Expression<Number>) expr[1];
		this.skriptMob = (Expression<ActiveMob>) expr[2];
		return true;
	}

	@Override
	public String toString(@Nullable Event var1, boolean var2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void execute(Event e) {
		ActiveMob am = this.skriptMob.getSingle(e);
		AbstractEntity ae = BukkitAdapter.adapt(this.skriptEntity.getSingle(e));
		Double amount = this.skriptAmount.getSingle(e).doubleValue();
		if (this.bool) {
			am.getThreatTable().threatGain(ae, amount);
		} else {
			am.getThreatTable().threatLoss(ae, amount);
		}
	}
}

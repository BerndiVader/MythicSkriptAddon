package com.gmail.berndivader.mythicskript.effects;

import javax.annotation.Nullable;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import io.lumine.xikage.mythicmobs.MythicMobs;
import io.lumine.xikage.mythicmobs.mobs.ActiveMob;

public class SetArmor extends Effect {
	private Expression<Number> amount;
	private Expression<ActiveMob> activeMob;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int var2, Kleenean var3, ParseResult var4) {
		this.activeMob = (Expression<ActiveMob>) expr[0];
		this.amount = (Expression<Number>) expr[1];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean var2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void execute(Event e) {
//		ActiveMob am = this.activeMob.getSingle(e);
//		double amount = this.amount.getSingle(e).doubleValue();
//		if (am==null || !am.getEntity().isLiving()) return;
//		if (MythicMobs.inst().getMinecraftVersion()>=9) {
//			LivingEntity le = am.getLivingEntity();
//			if (le.getAttribute(org.bukkit.attribute.Attribute.GENERIC_ARMOR)!=null) {
//				AttributeInstance ai = le.getAttribute(org.bukkit.attribute.Attribute.GENERIC_ARMOR);
//				ai.setBaseValue(amount);
//			}
//		}
	}

}

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

public class SendSignal extends Effect {
	private Expression<String> skriptSignal;
	private Expression<ActiveMob> skriptMob;
	private Expression<Entity> skriptSender;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int var2, Kleenean var3, ParseResult var4) {
		skriptMob = (Expression<ActiveMob>) expr[1];
		skriptSignal = (Expression<String>) expr[0];
		skriptSender = (Expression<Entity>) expr[2];
		return true;
	}

	@Override
	public String toString(@Nullable Event var1, boolean var2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void execute(Event e) {
		AbstractEntity trigger = BukkitAdapter.adapt(skriptSender.getSingle(e));
		ActiveMob am = skriptMob.getSingle(e);
		String signal = skriptSignal.getSingle(e);
		if (!am.isDead()) am.signalMob(trigger, signal);
	}
}

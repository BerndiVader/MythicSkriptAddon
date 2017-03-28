package com.gmail.berndivader.mmSkriptAddon.mm400.effects;

import javax.annotation.Nullable;

import org.bukkit.entity.Entity;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
import io.lumine.xikage.mythicmobs.skills.SkillTrigger;
import io.lumine.xikage.mythicmobs.skills.TriggeredSkill;

public class TriggerSkill extends Effect {
	private Expression<String> trigger;
	private Expression<ActiveMob> activeMob;
	private Expression<Entity> scriptEntity;
	private boolean bool;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int pattern, Kleenean var3, ParseResult var4) {
		bool = pattern==0?false:true;
		this.trigger = (Expression<String>) expr[0];
		this.activeMob = (Expression<ActiveMob>) expr[1];
		if (bool) this.scriptEntity = (Expression<Entity>) expr[2];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean var2) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	protected void execute(Event e) {
		ActiveMob am = this.activeMob.getSingle(e);
		SkillTrigger trigger;
		try {
			trigger = SkillTrigger.valueOf(this.trigger.getSingle(e).toUpperCase());
		} catch (Exception ex) {
			return;
		}
		@SuppressWarnings("unused")
		TriggeredSkill ts = !bool?new TriggeredSkill(trigger, am, null, true)
				:new TriggeredSkill(trigger, am, BukkitAdapter.adapt(this.scriptEntity.getSingle(e)), true);
	}
}

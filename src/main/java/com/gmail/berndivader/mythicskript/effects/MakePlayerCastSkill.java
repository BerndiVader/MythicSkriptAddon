package com.gmail.berndivader.mythicskript.effects;

import java.util.HashSet;
import java.util.Optional;

import org.jetbrains.annotations.Nullable;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.bukkit.scheduler.BukkitRunnable;

import com.gmail.berndivader.mythicskript.ActivePlayer;
import com.gmail.berndivader.mythicskript.MythicSkript;
import com.gmail.berndivader.mythicskript.Utils;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import io.lumine.mythic.api.adapters.AbstractEntity;
import io.lumine.mythic.api.adapters.AbstractLocation;
import io.lumine.mythic.api.skills.Skill;
import io.lumine.mythic.bukkit.BukkitAdapter;
import io.lumine.mythic.core.skills.SkillMetadataImpl;
import io.lumine.mythic.core.skills.SkillTriggers;

public class MakePlayerCastSkill extends Effect {
	
	private Expression<Entity> skriptPlayer;
	private Expression<Entity> skriptTrigger;
	private Expression<String> skriptSkill;
	private Expression<Entity> skriptTarget;
	private Expression<Location> skriptLocation;
	private boolean bool;
	private boolean self;
	private Expression<Number> skriptDelay;
	private Expression<Number> skriptTimer;
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean var3, ParseResult var4) {
		bool=matchedPattern==0;
		self=matchedPattern==2;
		skriptPlayer = (Expression<Entity>) expr[0];
		skriptSkill = (Expression<String>) expr[1];
		skriptTrigger = (Expression<Entity>) expr[2];
		if (self) {
			skriptDelay = (Expression<Number>) expr[3];
			skriptTimer = (Expression<Number>) expr[4];
			return true;
		}
		skriptDelay = (Expression<Number>) expr[4];
		skriptTimer = (Expression<Number>) expr[5];
		if (bool) {
			skriptTarget = (Expression<Entity>) expr[3];
		} else {
			skriptLocation = (Expression<Location>) expr[3];
		}
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean var2) {
		return getClass().getSimpleName()+e!=null?"@"+e.getEventName():"";
	}

	@Override
	protected void execute(Event e) {
		Entity caster = skriptPlayer.getSingle(e);
		Entity trigger = skriptTrigger.getSingle(e);
		Entity etarget = null;
		int ttimer = skriptTimer.getSingle(e).intValue();
		long tdelay = skriptDelay.getSingle(e).longValue();
		Location ltarget = null;
		if (bool) {
			etarget = skriptTarget.getSingle(e);
		} else if (!self){
			ltarget = skriptLocation.getSingle(e);
		}
		if (self) etarget = caster;
		String skill = skriptSkill.getSingle(e);
        HashSet<AbstractEntity> eTargets = new HashSet<AbstractEntity>();
        HashSet<AbstractLocation> lTargets = new HashSet<AbstractLocation>();
        if (etarget != null) eTargets.add(BukkitAdapter.adapt(etarget));
        if (ltarget != null) lTargets.add(BukkitAdapter.adapt(ltarget));
		if(!castSkillFromPlayer(caster, skill, trigger, caster.getLocation(), eTargets, lTargets, 1.0f, ttimer, tdelay)) {
			Skript.warning("MythicMobs skill "+skill+" not found!");
		}
	}

	private static boolean castSkillFromPlayer(Entity e, String skillName, Entity trigger, 
			Location origin, HashSet<AbstractEntity> feTargets, HashSet<AbstractLocation> flTargets, float power,
			int ttimer, long tdelay) {

        Optional<Skill> maybeSkill = Utils.mythicMobs.getSkillManager().getSkill(skillName);
        if (!maybeSkill.isPresent()) {
            return false;
        }
        ActivePlayer ap = new ActivePlayer(e);
        Skill skill = maybeSkill.get();
		SkillMetadataImpl data;
        if (skill.isUsable(data = new SkillMetadataImpl(SkillTriggers.API, ap, BukkitAdapter.adapt(trigger), BukkitAdapter.adapt(origin), feTargets, flTargets, power), SkillTriggers.API)) {
        	new BukkitRunnable() {
        		int timer = ttimer;
        		public void run() {
        			if (timer!=-1) {
        				skill.execute(data);
        				timer--;
        			} else {
        				this.cancel();
        			}
                } 
            }.runTaskTimer(MythicSkript.plugin, 0, tdelay);
        }
		return true;
	}
}

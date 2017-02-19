package com.gmail.berndivader.mmSkriptAddon.mm400.effects;

import java.util.HashSet;
import java.util.Optional;

import javax.annotation.Nullable;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;

import com.gmail.berndivader.mmSkriptAddon.ActivePlayer;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import io.lumine.xikage.mythicmobs.MythicMobs;
import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
import io.lumine.xikage.mythicmobs.skills.Skill;
import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
import io.lumine.xikage.mythicmobs.skills.SkillTrigger;

public class MakePlayerCastSkill extends Effect {
	private Expression<Entity> skriptPlayer;
	private Expression<Entity> skriptTrigger;
	private Expression<String> skriptSkill;
	private Expression<Entity> skriptTarget;
	private Expression<Location> skriptLocation;
	private boolean bool;
	private boolean self;
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean var3, ParseResult var4) {
		bool = matchedPattern==0?true:false;
		self = matchedPattern==2?true:false;
		skriptPlayer = (Expression<Entity>) expr[0];
		skriptSkill = (Expression<String>) expr[1];
		skriptTrigger = (Expression<Entity>) expr[2];
		if (matchedPattern==0) {
			skriptTarget = (Expression<Entity>) expr[3];
		} else if (matchedPattern==1){
			skriptLocation = (Expression<Location>) expr[3];
		}
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean var2) {
		return null;
	}

	@Override
	protected void execute(Event e) {
		Entity caster = skriptPlayer.getSingle(e);
		Entity trigger = skriptTrigger.getSingle(e);
		Entity etarget = null;
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
		castSkillFromPlayer(caster, skill, trigger, caster.getLocation(), eTargets, lTargets, 1.0f);
	}

	private boolean castSkillFromPlayer(Entity e, String skillName, Entity trigger, 
			Location origin, HashSet<AbstractEntity> feTargets, HashSet<AbstractLocation> flTargets, float power) {

		SkillMetadata data;
        Optional<Skill> maybeSkill = MythicMobs.inst().getSkillManager().getSkill(skillName);
        if (!maybeSkill.isPresent()) {
            return false;
        }
        ActivePlayer ap = new ActivePlayer(e);
        Skill skill = maybeSkill.get();
        if (skill.usable(data = new SkillMetadata(SkillTrigger.API, ap, BukkitAdapter.adapt(trigger), BukkitAdapter.adapt(origin), feTargets, flTargets, power), SkillTrigger.API)) {
            skill.execute(data);
        }
		return true;
	}
}

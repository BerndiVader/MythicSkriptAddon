package com.gmail.berndivader.mythicskript.expressions;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;

import com.gmail.berndivader.mythicskript.ActivePlayer;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import io.lumine.xikage.mythicmobs.MythicMobs;
import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
import io.lumine.xikage.mythicmobs.skills.SkillCaster;
import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
import io.lumine.xikage.mythicmobs.skills.SkillTargeter;
import io.lumine.xikage.mythicmobs.skills.SkillTrigger;
import io.lumine.xikage.mythicmobs.skills.targeters.ILocationSelector;
import io.lumine.xikage.mythicmobs.skills.targeters.MTOrigin;
import io.lumine.xikage.mythicmobs.skills.targeters.MTTriggerLocation;

public class getLocationsFromSelector extends SimpleExpression<Location>{
	private Expression<Entity> bukkitEntity;
	private Expression<SkillTargeter> bukkitTargeter;

	@Override
	public boolean isSingle() {
		return false;
	}

	@Override
	public Class<? extends Location> getReturnType() {
		return Location.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean var3, ParseResult var4) {
		this.bukkitEntity = (Expression<Entity>) expr[0];
		this.bukkitTargeter = (Expression<SkillTargeter>) expr[1];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean var2) {
		return null;
	}

	@Override
	@Nullable
	protected Location[] get(Event e) {
		SkillTargeter targeter = this.bukkitTargeter.getSingle(e);
		SkillCaster caster = MythicMobs.inst().getAPIHelper().isMythicMob(this.bukkitEntity.getSingle(e))
				?MythicMobs.inst().getAPIHelper().getMythicMobInstance(this.bukkitEntity.getSingle(e))
				:new ActivePlayer(this.bukkitEntity.getSingle(e));
		SkillMetadata data = new SkillMetadata(SkillTrigger.API, caster, caster.getEntity(), caster.getLocation(), null, null, 1.0f);
		List<Location> eTargets = new ArrayList<Location>();
        if (targeter instanceof ILocationSelector) {
            data.setLocationTargets(((ILocationSelector)targeter).getLocations(data));
            ((ILocationSelector)targeter).filter(data);
            for (AbstractLocation ae : data.getLocationTargets()) {
            	eTargets.add(BukkitAdapter.adapt(ae));
            }
        } else if (targeter instanceof MTOrigin) {
            data.setLocationTargets(((MTOrigin)targeter).getLocation(data.getOrigin()));
            for (AbstractLocation ae : data.getLocationTargets()) {
            	eTargets.add(BukkitAdapter.adapt(ae));
            }
        } else if (targeter instanceof MTTriggerLocation) {
            eTargets.add(BukkitAdapter.adapt(data.getTrigger().getLocation()));
        }
        return eTargets.toArray(new Location[0]);
	}
}

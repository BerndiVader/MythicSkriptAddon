package com.gmail.berndivader.mythicskript.expressions;

import java.util.ArrayList;
import java.util.List;

import org.jetbrains.annotations.Nullable;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;

import com.gmail.berndivader.mythicskript.ActivePlayer;
import com.gmail.berndivader.mythicskript.Utils;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import io.lumine.mythic.api.adapters.AbstractLocation;
import io.lumine.mythic.api.skills.SkillCaster;
import io.lumine.mythic.bukkit.BukkitAdapter;
import io.lumine.mythic.core.skills.SkillMetadataImpl;
import io.lumine.mythic.core.skills.SkillTargeter;
import io.lumine.mythic.core.skills.SkillTriggers;
import io.lumine.mythic.core.skills.targeters.ILocationSelector;
import io.lumine.mythic.core.skills.targeters.OriginTargeter;
import io.lumine.mythic.core.skills.targeters.TriggerLocationTargeter;

public class GetLocationsFromSelector extends SimpleExpression<Location>{
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
		return getClass().getSimpleName()+e!=null?"@"+e.getEventName():"";
	}

	@Override
	protected Location[] get(Event e) {
		SkillTargeter targeter = this.bukkitTargeter.getSingle(e);
		SkillCaster caster = Utils.mythicHelper.isMythicMob(this.bukkitEntity.getSingle(e))
				?Utils.mythicHelper.getMythicMobInstance(this.bukkitEntity.getSingle(e))
				:new ActivePlayer(this.bukkitEntity.getSingle(e));
		SkillMetadataImpl meta = new SkillMetadataImpl(SkillTriggers.API, caster, caster.getEntity(), caster.getLocation(), null, null, 1.0f);
		List<Location> eTargets = new ArrayList<Location>();
        if (targeter instanceof ILocationSelector) {
        	meta.setLocationTargets(((ILocationSelector)targeter).getLocations(meta));
            ((ILocationSelector)targeter).filter(meta);
            for (AbstractLocation ae : meta.getLocationTargets()) {
            	eTargets.add(BukkitAdapter.adapt(ae));
            }
            
        } else if (targeter instanceof OriginTargeter) {
        	meta.setLocationTargets(((OriginTargeter)targeter).getLocations(meta));
            for (AbstractLocation ae : meta.getLocationTargets()) {
            	eTargets.add(BukkitAdapter.adapt(ae));
            }
        } else if (targeter instanceof TriggerLocationTargeter) {
            eTargets.add(BukkitAdapter.adapt(meta.getTrigger().getLocation()));
        }
        return eTargets.toArray(new Location[0]);
	}
}

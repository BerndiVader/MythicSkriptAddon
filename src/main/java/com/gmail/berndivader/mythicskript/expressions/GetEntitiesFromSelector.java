package com.gmail.berndivader.mythicskript.expressions;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import org.bukkit.entity.Entity;
import org.bukkit.event.Event;

import com.gmail.berndivader.mythicskript.ActivePlayer;
import com.gmail.berndivader.mythicskript.Utils;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import io.lumine.mythic.api.adapters.AbstractEntity;
import io.lumine.mythic.api.skills.SkillCaster;
import io.lumine.mythic.bukkit.BukkitAdapter;
import io.lumine.mythic.core.skills.SkillMetadataImpl;
import io.lumine.mythic.core.skills.SkillTargeter;
import io.lumine.mythic.core.skills.SkillTriggers;
import io.lumine.mythic.core.skills.targeters.IEntitySelector;

public class GetEntitiesFromSelector extends SimpleExpression<Entity>{
	private Expression<Entity> bukkitEntity;
	private Expression<SkillTargeter> bukkitTargeter;

	@Override
	public boolean isSingle() {
		return false;
	}

	@Override
	public Class<? extends Entity> getReturnType() {
		return Entity.class;
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
	@Nullable
	protected Entity[] get(Event e) {
		SkillTargeter targeter = this.bukkitTargeter.getSingle(e);
		SkillCaster caster = Utils.mythicHelper.isMythicMob(this.bukkitEntity.getSingle(e))
				?Utils.mythicHelper.getMythicMobInstance(this.bukkitEntity.getSingle(e))
				:new ActivePlayer(this.bukkitEntity.getSingle(e));
		SkillMetadataImpl data = new SkillMetadataImpl(SkillTriggers.API, caster, caster.getEntity(), caster.getLocation(), null, null, 1.0f);
        if (targeter instanceof IEntitySelector) {
    		List<Entity> eTargets = new ArrayList<Entity>();
            data.setEntityTargets(((IEntitySelector)targeter).getEntities(data));
            ((IEntitySelector)targeter).filter(data, false);
            for (AbstractEntity ae : data.getEntityTargets()) {
            	eTargets.add(BukkitAdapter.adapt(ae));
            }
            return eTargets.toArray(new Entity[0]);
        }
        return null;
	}
}

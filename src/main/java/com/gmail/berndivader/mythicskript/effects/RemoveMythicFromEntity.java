package com.gmail.berndivader.mythicskript.effects;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.event.Event;

import com.gmail.berndivader.mythicskript.Utils;

import java.util.UUID;

import javax.annotation.Nullable;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
import io.lumine.xikage.mythicmobs.mobs.ActiveMob;

public class RemoveMythicFromEntity extends Effect {
	private Expression<ActiveMob>skActiveMob;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int i, Kleenean var3, ParseResult var4) {
		this.skActiveMob = (Expression<ActiveMob>) expr[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean var2) {
		return getClass().getSimpleName()+e!=null?"@"+e.getEventName():"";
	}

	@Override
	protected void execute(Event e) {
		ActiveMob am = this.skActiveMob.getSingle(e);
		if (am!=null) RemoveMythicFromEntity.transformToNormalEntity(am);
	}

	private static void transformToNormalEntity(ActiveMob am) {
		Location l = am.getEntity().getBukkitEntity().getLocation().clone();
		l.setY(0);
		AbstractEntity d = BukkitAdapter.adapt(l.getWorld().spawnEntity(l, EntityType.BAT));
		am.getEntity().getBukkitEntity().removeMetadata("Faction", Utils.mythicMobs);
		unregisterActiveMob(am.getUniqueId());
		am.setEntity(d);
		d.remove();
		return;
	}
	
    private static void unregisterActiveMob(UUID uuid) {
		Utils.mobManager.unregisterActiveMob(uuid);
    }
	
	
}

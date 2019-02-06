package com.gmail.berndivader.mythicskript.effects;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.Event;

import java.util.UUID;

import javax.annotation.Nullable;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import io.lumine.xikage.mythicmobs.MythicMobs;
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
	public String toString(@Nullable Event var1, boolean var2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void execute(Event e) {
		ActiveMob am = this.skActiveMob.getSingle(e);
		if (am!=null) RemoveMythicFromEntity.transformToNormalEntity(am);
	}

	private static Entity transformToNormalEntity(ActiveMob am) {
		Entity entity = am.getEntity().getBukkitEntity();
		Location l = am.getEntity().getBukkitEntity().getLocation();
		l.setY(0);
		AbstractEntity d = BukkitAdapter.adapt(l.getWorld().spawnEntity(l, EntityType.BAT));
		am.getEntity().getBukkitEntity().removeMetadata("Faction", MythicMobs.inst());
		unregisterActiveMob(am.getUniqueId());
		am.setEntity(d);
		d.remove();
		return entity;
	}
	
    private static void unregisterActiveMob(UUID uuid) {
		MythicMobs.inst().getMobManager().unregisterActiveMob(uuid);
    }
	
	
}

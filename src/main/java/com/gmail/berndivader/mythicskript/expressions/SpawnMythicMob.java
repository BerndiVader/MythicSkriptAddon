package com.gmail.berndivader.mythicskript.expressions;

import javax.annotation.Nullable;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import io.lumine.xikage.mythicmobs.MythicMobs;
import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
import io.lumine.xikage.mythicmobs.adapters.AbstractWorld;
import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
import io.lumine.xikage.mythicmobs.mobs.ActiveMob;

public class SpawnMythicMob extends SimpleExpression<ActiveMob> {
	private Expression<String> skriptMobtype;
	private Expression<Location> skriptLocation;
	private Expression<Object> skriptWorld;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int i, Kleenean var3, ParseResult var4) {
		this.skriptMobtype = (Expression<String>) expr[0];
		this.skriptLocation = (Expression<Location>) expr[1];
		this.skriptWorld = (Expression<Object>) expr[2];
		return true;
	}

	@Override
	public String toString(@Nullable Event var1, boolean var2) {
		return null;
	}

	@Override
	public boolean isSingle() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Class<? extends ActiveMob> getReturnType() {
		// TODO Auto-generated method stub
		return ActiveMob.class;
	}

	@Override
	@Nullable
	protected ActiveMob[] get(Event e) {
		String mobtype = skriptMobtype.getSingle(e);
		ActiveMob am;
		AbstractLocation location = BukkitAdapter.adapt(skriptLocation.getSingle(e));
		Object worldObject = skriptWorld.getSingle(e);
		AbstractWorld world=null;
		if (worldObject instanceof String) {
			world=BukkitAdapter.adapt(Bukkit.getServer().getWorld((String)skriptWorld.getSingle(e)));
		} else if (worldObject instanceof World) {
			world=BukkitAdapter.adapt((World)skriptWorld.getSingle(e));
		}
		if (location==null || world == null) return null;
		AbstractLocation loc = new AbstractLocation(world, location.getX(), location.getY()+0.5, location.getZ());
		if ((am = MythicMobs.inst().getMobManager().spawnMob(mobtype, loc))==null) return null;
		return new ActiveMob[]{am};
	}
}

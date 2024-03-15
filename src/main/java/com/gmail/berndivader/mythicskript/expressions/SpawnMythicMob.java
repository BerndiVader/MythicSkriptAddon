package com.gmail.berndivader.mythicskript.expressions;

import org.jetbrains.annotations.Nullable;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.Event;

import com.gmail.berndivader.mythicskript.Utils;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.skript.util.LiteralUtils;
import ch.njol.util.Kleenean;
import io.lumine.mythic.api.adapters.AbstractLocation;
import io.lumine.mythic.api.adapters.AbstractWorld;
import io.lumine.mythic.bukkit.BukkitAdapter;
import io.lumine.mythic.core.mobs.ActiveMob;

public class SpawnMythicMob extends SimpleExpression<ActiveMob> {
	private Expression<String> skriptMobtype;
	private Expression<Location> skriptLocation;
	private Expression<Object> skriptWorld;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int i, Kleenean var3, ParseResult var4) {
		this.skriptMobtype = (Expression<String>) expr[0];
		this.skriptLocation = (Expression<Location>) expr[1];
		this.skriptWorld=(Expression<Object>)(expr[2]);
		return LiteralUtils.canInitSafely(expr[2]);
	}

	@Override
	public String toString(@Nullable Event e, boolean var2) {
		return getClass().getSimpleName()+e!=null?"@"+e.getEventName():"";
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public Class<? extends ActiveMob> getReturnType() {
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
		if (location==null||world==null) return null;
		AbstractLocation loc = new AbstractLocation(world, location.getX(), location.getY()+0.5, location.getZ());
		if ((am=Utils.mobManager.spawnMob(mobtype,loc))==null) return null;
		return new ActiveMob[]{am};
	}
}

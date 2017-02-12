package com.gmail.berndivader.mmSkriptAddon.mm400.expressions;

import java.util.UUID;

import javax.annotation.Nullable;

import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;

import com.gmail.berndivader.mmSkriptAddon.Main;
import com.gmail.berndivader.mmSkriptAddon.NMS.NMSUtils;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
import io.lumine.xikage.mythicmobs.mobs.ActiveMob;

public class ExprGetOwner extends SimpleExpression<Entity> {
	private Expression<ActiveMob> activeMob;
	private NMSUtils nmsutils = Main.NMSUtils();

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public Class<? extends Entity> getReturnType() {
		return Entity.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int var2, Kleenean var3, ParseResult var4) {
		activeMob = (Expression<ActiveMob>) expr[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event var1, boolean var2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Nullable
	protected Entity[] get(Event e) {
		ActiveMob am = activeMob.getSingle(e);
		if (am!=null && am.getOwner().isPresent()) {
			UUID uuid = am.getOwner().get();
			World world = BukkitAdapter.adapt(am.getEntity().getWorld());
			return new Entity[]{nmsutils.getEntity(world, uuid)};
		};
		return null;
	}
}

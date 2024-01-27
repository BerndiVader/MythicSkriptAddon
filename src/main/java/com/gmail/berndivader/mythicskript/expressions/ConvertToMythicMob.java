package com.gmail.berndivader.mythicskript.expressions;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.metadata.FixedMetadataValue;

import com.gmail.berndivader.mythicskript.Utils;

import java.util.Optional;

import org.jetbrains.annotations.Nullable;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import io.lumine.mythic.api.mobs.MythicMob;
import io.lumine.mythic.bukkit.BukkitAdapter;
import io.lumine.mythic.core.mobs.ActiveMob;

public class ConvertToMythicMob extends SimpleExpression <ActiveMob> {
	private Expression<String> skMobtype;
	private Expression<Entity> skEntity;
	private Expression<Number> skLevel;

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public Class<? extends ActiveMob> getReturnType() {
		return ActiveMob.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int pattern, Kleenean var3, ParseResult var4) {
		this.skEntity = (Expression<Entity>)expr[0];
		this.skMobtype = (Expression<String>)expr[1];
		this.skLevel = (Expression<Number>)expr[2];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean bool) {
		return getClass().getSimpleName()+e!=null?"@"+e.getEventName():"";
	}

	@Override
	@Nullable
	protected ActiveMob[] get(Event e) {
		Entity entity = this.skEntity.getSingle(e);
		if (!(entity instanceof LivingEntity)) return null;
		LivingEntity le = (LivingEntity)entity;
		if(le instanceof Player) return null;
		String type = this.skMobtype.getSingle(e);
		int level = this.skLevel.getSingle(e).intValue();
		Optional<MythicMob>maybe = Utils.mobManager.getMythicMob(type);
		if(!maybe.isPresent()) return null;
		MythicMob mm=maybe.get();
		ActiveMob am = new ActiveMob(BukkitAdapter.adapt(le), mm, level);
		if (am!=null) {
			ConvertToMythicMob.addActiveMobToFaction(mm, am);
			ConvertToMythicMob.registerActiveMob(am);
		}
		return new ActiveMob[]{am};
	}
	
	private static void addActiveMobToFaction(MythicMob mm, ActiveMob am) {
        if (mm.hasFaction()) {
            am.setFaction(mm.getFaction());
            am.getEntity().setMetadata("Faction", new FixedMetadataValue(Utils.mythicMobs,mm.getFaction()));
        }
	}	
    private static void registerActiveMob(ActiveMob am) {
        Utils.mobManager.registerActiveMob(am);
    }
	

}

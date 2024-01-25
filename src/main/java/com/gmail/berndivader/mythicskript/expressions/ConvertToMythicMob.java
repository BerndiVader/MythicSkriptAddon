package com.gmail.berndivader.mythicskript.expressions;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.metadata.FixedMetadataValue;

import com.gmail.berndivader.mythicskript.Utils;

import javax.annotation.Nullable;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
import io.lumine.xikage.mythicmobs.mobs.MythicMob;

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
		String type = this.skMobtype.getSingle(e);
		ActiveMob am = null;
		Entity entity = this.skEntity.getSingle(e);
		if (!(entity instanceof LivingEntity)) return null;
		LivingEntity le = (LivingEntity)entity;
		boolean isplayer = le instanceof Player?true:false;
		int level = this.skLevel.getSingle(e).intValue();
		MythicMob mm = Utils.mobManager.getMythicMob(type);
		if (mm==null) return null;
		if (isplayer 
				&& !mm.isPersistent()) return null;
		am = new ActiveMob(le.getUniqueId(), BukkitAdapter.adapt(le), mm, level);
		if (am!=null) {
			ConvertToMythicMob.addActiveMobToFaction(mm, am);
			ConvertToMythicMob.registerActiveMob(am);
		}
		return new ActiveMob[]{am};
	}
	
	public static void addActiveMobToFaction(MythicMob mm, ActiveMob am) {
        if (mm.hasFaction()) {
            am.setFaction(mm.getFaction());
            am.getEntity().setMetadata("Faction", new FixedMetadataValue(Utils.mythicMobs,mm.getFaction()));
        }
	}	
    public static void registerActiveMob(ActiveMob am) {
        Utils.mobManager.registerActiveMob(am);
    }
	

}

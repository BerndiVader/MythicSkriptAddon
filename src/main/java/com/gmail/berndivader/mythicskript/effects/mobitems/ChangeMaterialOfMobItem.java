package com.gmail.berndivader.mythicskript.effects.mobitems;

import org.jetbrains.annotations.Nullable;

import org.bukkit.Material;
import org.bukkit.event.Event;

import com.gmail.berndivader.mythicskript.classes.MobItem;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class ChangeMaterialOfMobItem extends Effect {
	private Expression<MobItem> skriptItem;
	private Expression<String> skriptMaterial;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int var2, Kleenean var3, ParseResult var4) {
		skriptItem = (Expression<MobItem>) expr[0];
		skriptMaterial = (Expression<String>) expr[1];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean var2) {
		return getClass().getSimpleName()+e!=null?"@"+e.getEventName():"";
	}

	@Override
	protected void execute(Event e) {
		MobItem mi = skriptItem.getSingle(e);
		Material material = Material.getMaterial(skriptMaterial.getSingle(e));
		if (material!=null) mi.getItem().setType(material);
	}
}

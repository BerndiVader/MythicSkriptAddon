package com.gmail.berndivader.mythicskript.events;

import org.bukkit.Bukkit;

import com.gmail.berndivader.mythicskript.Utils;
import com.gmail.berndivader.mythicskript.events.skript.MythicSkriptSkillEvent;

import io.lumine.mythic.api.adapters.AbstractEntity;
import io.lumine.mythic.api.adapters.AbstractLocation;
import io.lumine.mythic.api.config.MythicLineConfig;
import io.lumine.mythic.api.skills.INoTargetSkill;
import io.lumine.mythic.api.skills.ITargetedEntitySkill;
import io.lumine.mythic.api.skills.ITargetedLocationSkill;
import io.lumine.mythic.api.skills.SkillCaster;
import io.lumine.mythic.api.skills.SkillMetadata;
import io.lumine.mythic.api.skills.SkillResult;
import io.lumine.mythic.bukkit.BukkitAdapter;
import io.lumine.mythic.core.skills.SkillMechanic;
import io.lumine.mythic.core.skills.SkillString;
import io.lumine.mythic.core.skills.placeholders.parsers.PlaceholderStringImpl;

public class MythicSkriptSkill extends SkillMechanic implements ITargetedEntitySkill, ITargetedLocationSkill, INoTargetSkill {
	private String skill;
	private PlaceholderStringImpl placeholder;

	@SuppressWarnings("deprecation")
	public MythicSkriptSkill(SkillMechanic skill, MythicLineConfig mlc) {
		super(Utils.mythicMobs.getSkillManager(), skill.getConfigLine(), mlc);
		this.setAsyncSafe(false);
		this.skill = mlc.getString(new String[]{"skriptskill","skill","s"}, "");
		placeholder=new PlaceholderStringImpl(SkillString.parseMessageSpecialChars(mlc.getString(new String[]{"args","a"},"")));
	}

	@Override
	public SkillResult castAtLocation(SkillMetadata data, AbstractLocation target) {
		SkillCaster caster = data.getCaster();
		MythicSkriptSkillEvent e = new MythicSkriptSkillEvent(caster,null,BukkitAdapter.adapt(target),data.getTrigger(),skill,placeholder.get(data));
		Bukkit.getServer().getPluginManager().callEvent(e);
		return SkillResult.SUCCESS;
	}

	@Override
	public SkillResult castAtEntity(SkillMetadata data, AbstractEntity target) {
		SkillCaster caster = data.getCaster();
		MythicSkriptSkillEvent e = new MythicSkriptSkillEvent(caster,BukkitAdapter.adapt(target),null,data.getTrigger(),skill,placeholder.get(data, target));
		Bukkit.getServer().getPluginManager().callEvent(e);
		return SkillResult.SUCCESS;
	}

	@Override
	public SkillResult cast(SkillMetadata data) {
		SkillCaster caster = data.getCaster();
		MythicSkriptSkillEvent e = new MythicSkriptSkillEvent(caster,null,null,data.getTrigger(),skill,placeholder.get(data));
		Bukkit.getServer().getPluginManager().callEvent(e);
		return SkillResult.SUCCESS;
	}
}

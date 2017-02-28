package com.gmail.berndivader.mmSkriptAddon.mm400.events;

import org.bukkit.Bukkit;

import com.gmail.berndivader.mmSkriptAddon.mm400.events.custom.mmMythicMobsSkriptSkill;

import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
import io.lumine.xikage.mythicmobs.skills.INoTargetSkill;
import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
import io.lumine.xikage.mythicmobs.skills.ITargetedLocationSkill;
import io.lumine.xikage.mythicmobs.skills.SkillCaster;
import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
import io.lumine.xikage.mythicmobs.skills.SkillString;

public class mmSkriptSkill extends SkillMechanic implements ITargetedEntitySkill, ITargetedLocationSkill, INoTargetSkill {
	private String skill, skriptArgs;

	public mmSkriptSkill(SkillMechanic skill, MythicLineConfig mlc) {
		super(skill.getConfigLine(), mlc);
		this.ASYNC_SAFE=false;
		this.skill = mlc.getString(new String[]{"skriptskill","skill","s"}, "");
		this.skriptArgs = mlc.getString(new String[]{"args","a"},"");
	}

	@Override
	public boolean cast(SkillMetadata data) {
		SkillCaster caster = data.getCaster();
		String args = SkillString.parseMobVariables(this.skriptArgs, caster, caster.getEntity(), data.getTrigger());
		mmMythicMobsSkriptSkill e = new mmMythicMobsSkriptSkill(caster,null,null,data.getTrigger(),skill,args);
		Bukkit.getServer().getPluginManager().callEvent(e);
		return true;
	}

	@Override
	public boolean castAtLocation(SkillMetadata data, AbstractLocation target) {
		SkillCaster caster = data.getCaster();
		String args = SkillString.parseMobVariables(this.skriptArgs, caster, caster.getEntity(), data.getTrigger());
		mmMythicMobsSkriptSkill e = new mmMythicMobsSkriptSkill(caster,null,BukkitAdapter.adapt(target),data.getTrigger(),skill,args);
		Bukkit.getServer().getPluginManager().callEvent(e);
		return true;
	}

	@Override
	public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
		SkillCaster caster = data.getCaster();
		String args = SkillString.parseMobVariables(this.skriptArgs, caster, target, data.getTrigger());
		mmMythicMobsSkriptSkill e = new mmMythicMobsSkriptSkill(caster,BukkitAdapter.adapt(target),null,data.getTrigger(),skill,args);
		Bukkit.getServer().getPluginManager().callEvent(e);
		return true;
	}
}

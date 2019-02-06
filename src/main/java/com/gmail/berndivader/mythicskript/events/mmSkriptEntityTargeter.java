package com.gmail.berndivader.mythicskript.events;

import java.util.HashSet;

import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
import io.lumine.xikage.mythicmobs.skills.targeters.IEntitySelector;

public class mmSkriptEntityTargeter 
extends
IEntitySelector {

	public mmSkriptEntityTargeter(MythicLineConfig mlc) {
		super(mlc);
	}

	@Override
	public HashSet<AbstractEntity> getEntities(SkillMetadata var1) {
		return null;
	}

}

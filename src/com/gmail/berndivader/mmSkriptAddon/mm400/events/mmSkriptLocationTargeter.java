package com.gmail.berndivader.mmSkriptAddon.mm400.events;

import java.util.HashSet;

import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
import io.lumine.xikage.mythicmobs.skills.targeters.ILocationSelector;

public class mmSkriptLocationTargeter
extends
ILocationSelector {
	private String skTargeterName,skArgs;
	
	public mmSkriptLocationTargeter(MythicLineConfig mlc) {
		this.skTargeterName=mlc.getString(new String[] {"sktargeter","targeter","name","t"},null);
		this.skArgs=mlc.getString(new String[] {"args","a"},null);
	}

	@Override
	public HashSet<AbstractLocation> getLocations(SkillMetadata data) {
		return null;
	}

}

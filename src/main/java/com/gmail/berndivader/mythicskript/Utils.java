package com.gmail.berndivader.mythicskript;

import io.lumine.xikage.mythicmobs.MythicMobs;
import io.lumine.xikage.mythicmobs.api.bukkit.BukkitAPIHelper;
import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
import io.lumine.xikage.mythicmobs.items.ItemManager;
import io.lumine.xikage.mythicmobs.mobs.MobManager;
import io.lumine.xikage.mythicmobs.skills.SkillTargeter;

public
class 
Utils
{
	public static MythicMobs mythicMobs;
	public static MobManager mobManager;
	public static ItemManager itemManager;
	public static BukkitAPIHelper mythicHelper;
	
	static {
		mythicMobs=MythicMobs.inst();
		mobManager=mythicMobs.getMobManager();
		itemManager=mythicMobs.getItemManager();
		mythicHelper=mythicMobs.getAPIHelper();
	}
	
	/**
	 * 
	 * @param targeter_string {@link String}
	 * @return skill_targeter {@link SkillTargeter}
	 * 
	 */
	
	public static SkillTargeter parseSkillTargeter(String targeter_string) {
        String search = targeter_string.substring(1);
        MythicLineConfig mlc = new MythicLineConfig(search);
        String name = search.contains("{") ? search.substring(0, search.indexOf("{")) : search;
        return SkillTargeter.getMythicTargeter(name, mlc);
	}

}

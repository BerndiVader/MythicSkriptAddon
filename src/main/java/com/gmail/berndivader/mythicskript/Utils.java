package com.gmail.berndivader.mythicskript;

import io.lumine.mythic.api.items.ItemManager;
import io.lumine.mythic.bukkit.BukkitAPIHelper;
import io.lumine.mythic.bukkit.MythicBukkit;
import io.lumine.mythic.core.config.MythicLineConfigImpl;
import io.lumine.mythic.core.drops.DropExecutor;
import io.lumine.mythic.core.mobs.MobExecutor;
import io.lumine.mythic.core.skills.SkillTargeter;
import io.lumine.mythic.core.spawning.spawners.SpawnerManager;

public
class 
Utils
{
	public static MythicBukkit mythicMobs;
	public static MobExecutor mobManager;
	public static ItemManager itemManager;
	public static BukkitAPIHelper mythicHelper;
	public static DropExecutor dropExecutor;
	public static SpawnerManager spawnerManager;
	
	static {
		mythicMobs=MythicBukkit.inst();
		mobManager=mythicMobs.getMobManager();
		itemManager=mythicMobs.getItemManager();
		mythicHelper=mythicMobs.getAPIHelper();
		dropExecutor=mythicMobs.getDropManager();
	}
	
	/**
	 * 
	 * @param targeter_string {@link String}
	 * @return skill_targeter {@link SkillTargeter}
	 * 
	 */
	
	public static SkillTargeter parseSkillTargeter(String targeter_string) {
        String search = targeter_string.substring(1);
        MythicLineConfigImpl mlc = new MythicLineConfigImpl(search);
        String name = search.contains("{") ? search.substring(0, search.indexOf("{")) : search;
        return mythicMobs.getSkillManager().getTargeter(name, mlc);
	}

}

package com.gmail.berndivader.mythicskript;

import io.lumine.mythic.api.volatilecode.VolatileCodeHandler;
import io.lumine.mythic.bukkit.BukkitAPIHelper;
import io.lumine.mythic.bukkit.MythicBukkit;
import io.lumine.mythic.core.config.MythicLineConfigImpl;
import io.lumine.mythic.core.drops.DropExecutor;
import io.lumine.mythic.core.items.ItemExecutor;
import io.lumine.mythic.core.mobs.MobExecutor;
import io.lumine.mythic.core.skills.SkillTargeter;
import io.lumine.mythic.core.spawning.spawners.SpawnerManager;

public
class 
Utils
{
	public static MythicBukkit mythicMobs;
	public static MobExecutor mobManager;
	public static ItemExecutor itemManager;
	public static BukkitAPIHelper mythicHelper;
	public static DropExecutor dropExecutor;
	public static SpawnerManager spawnerManager;
	public static VolatileCodeHandler VCH;
	
	static {
		mythicMobs=MythicBukkit.inst();
		mobManager=mythicMobs.getMobManager();
		itemManager=mythicMobs.getItemManager();
		mythicHelper=mythicMobs.getAPIHelper();
		dropExecutor=mythicMobs.getDropManager();
		VCH=mythicMobs.getVolatileCodeHandler();
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

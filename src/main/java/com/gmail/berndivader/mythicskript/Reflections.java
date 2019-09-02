package com.gmail.berndivader.mythicskript;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;

import io.lumine.xikage.mythicmobs.skills.SkillTargeter;

public 
class 
Reflections
{
    protected static String versionPrefix = "";
	
	protected static Class<?> class_AbstractSkill;
    protected static Method class_AbstractSkill_parseSkillTargeterMethod;

    
    public static void init() {
        String className = Bukkit.getServer().getClass().getName();
        String[] packages = StringUtils.split(className, '.');
        if (packages.length == 5) {
            versionPrefix = packages[3] + ".";
        }
        
    	try {
			class_AbstractSkill=fixBukkitClass("io.lumine.xikage.mythicmobs.skills.AbstractSkill");
	        class_AbstractSkill_parseSkillTargeterMethod=class_AbstractSkill.getDeclaredMethod("parseSkillTargeter",String.class);
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
        
    }
    
    static Class<?> fixBukkitClass(String className) throws ClassNotFoundException {
        if (!versionPrefix.isEmpty()) {
            className=className.replace("org.bukkit.craftbukkit.","org.bukkit.craftbukkit."+versionPrefix);
            className=className.replace("net.minecraft.server.","net.minecraft.server."+versionPrefix);
        }
        return Reflections.class.getClassLoader().loadClass(className);
    }    
    
    
	/**
	 * 
	 * @param targeter_string {@link String}
	 * @return skill_targeter {@link SkillTargeter}
	 */
	
	public static SkillTargeter parseSkillTargeter(String targeter_string) {
		SkillTargeter targeter=null;
		try {
			targeter=(SkillTargeter)class_AbstractSkill_parseSkillTargeterMethod.invoke(null,targeter_string);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return targeter;
	}
    

}

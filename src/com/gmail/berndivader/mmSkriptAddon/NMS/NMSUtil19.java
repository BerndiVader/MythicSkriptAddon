package com.gmail.berndivader.mmSkriptAddon.NMS;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.block.BlockFace;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.MemorySection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class NMSUtil19 implements NMSUtils {
    protected static boolean failed = false;
    protected static boolean legacy = false;

    protected static String versionPrefix = "";

    protected final static int NBT_TYPE_COMPOUND = 10;
    protected final static int NBT_TYPE_INT_ARRAY= 11;
    protected final static int NBT_TYPE_DOUBLE = 6;
    protected final static int NBT_TYPE_FLOAT = 5;
    protected final static int NBT_TYPE_STRING = 8;

    protected static int WITHER_SKULL_TYPE = 66;
    protected static int FIREWORK_TYPE = 76;

    protected static Class<?> class_ItemStack;
    protected static Class<?> class_NBTBase;
    protected static Class<?> class_NBTTagCompound;
    protected static Class<?> class_NBTTagList;
    protected static Class<?> class_NBTTagByte;
    protected static Class<?> class_NBTTagString;
    protected static Class<?> class_CraftTask;
    protected static Class<?> class_CraftInventoryCustom;
    protected static Class<?> class_CraftItemStack;
    protected static Class<?> class_CraftBlockState;
    protected static Class<?> class_CraftLivingEntity;
    protected static Class<?> class_CraftWorld;
    protected static Class<?> class_Consumer;
    protected static Class<?> class_Entity;
    protected static Class<?> class_EntityCreature;
    protected static Class<?> class_EntityLiving;
    protected static Class<?> class_EntityHuman;
    protected static Class<?> class_DataWatcher;
    protected static Class<?> class_DamageSource;
    protected static Class<?> class_EntityDamageSource;
    protected static Class<?> class_World;
    protected static Class<?> class_WorldServer;
    protected static Class<?> class_Packet;
    protected static Class<Enum> class_EnumSkyBlock;
    protected static Class<?> class_EntityPainting;
    protected static Class<?> class_EntityItemFrame;
    protected static Class<?> class_EntityMinecartRideable;
    protected static Class<?> class_EntityTNTPrimed;
    protected static Class<?> class_AxisAlignedBB;
    protected static Class<?> class_PathPoint;
    protected static Class<?> class_PathEntity;
    protected static Class<?> class_EntityFirework;
    protected static Class<?> class_CraftSkull;
    protected static Class<?> class_CraftBanner;
    protected static Class<?> class_CraftMetaSkull;
    protected static Class<?> class_CraftMetaBanner;
    protected static Class<?> class_GameProfile;
    protected static Class<?> class_GameProfileProperty;
    protected static Class<?> class_BlockPosition;
    protected static Class<?> class_NBTCompressedStreamTools;
    protected static Class<?> class_TileEntity;
    protected static Class<?> class_TileEntitySign;
    protected static Class<?> class_TileEntityContainer;
    protected static Class<?> class_ChestLock;
    protected static Class<Enum> class_EnumDirection;
    protected static Class<?> class_EntityHorse;
    protected static Class<?> class_EntityWitherSkull;
    protected static Class<?> class_PacketPlayOutAttachEntity;
    protected static Class<?> class_PacketPlayOutEntityDestroy;
    protected static Class<?> class_PacketPlayOutSpawnEntity;
    protected static Class<?> class_PacketPlayOutSpawnEntityLiving;
    protected static Class<?> class_PacketPlayOutEntityMetadata;
    protected static Class<?> class_PacketPlayOutEntityStatus;
    protected static Class<?> class_PacketPlayOutCustomSoundEffect;
    protected static Class<?> class_PacketPlayOutExperience;
    protected static Class<?> class_PacketPlayOutAnimation;
    protected static Enum<?> enum_SoundCategory_PLAYERS;
    protected static Class<Enum> class_EnumSoundCategory;
    protected static Class<?> class_EntityFallingBlock;
    protected static Class<?> class_EntityArmorStand;
    protected static Class<?> class_EntityPlayer;
    protected static Class<?> class_PlayerConnection;
    protected static Class<?> class_Chunk;
    protected static Class<?> class_CraftPlayer;
    protected static Class<?> class_CraftChunk;
    protected static Class<?> class_CraftEntity;
    protected static Class<?> class_EntityProjectile;
    protected static Class<?> class_EntityFireball;
    protected static Class<?> class_EntityArrow;
    protected static Class<?> class_CraftArrow;
    protected static Class<?> class_MinecraftServer;
    protected static Class<?> class_CraftServer;
    protected static Class<?> class_DataWatcherObject;

    protected static Method class_NBTTagList_addMethod;
    protected static Method class_NBTTagList_getMethod;
    protected static Method class_NBTTagList_getDoubleMethod;
    protected static Method class_NBTTagList_sizeMethod;
    protected static Method class_NBTTagList_removeMethod;
    protected static Method class_NBTTagCompound_getKeysMethod;
    protected static Method class_NBTTagCompound_setMethod;
    protected static Method class_DataWatcher_setMethod;
    protected static Method class_DataWatcher_getMethod;
    protected static Method class_World_getEntitiesMethod;
    protected static Method class_Entity_setSilentMethod;
    protected static Method class_Entity_setYawPitchMethod;
    protected static Method class_Entity_getBukkitEntityMethod;
    protected static Method class_EntityLiving_damageEntityMethod;
    protected static Method class_DamageSource_getMagicSourceMethod;
    protected static Method class_EntityDamageSource_setThornsMethod;
    protected static Method class_World_explodeMethod;
    protected static Method class_NBTTagCompound_setBooleanMethod;
    protected static Method class_NBTTagCompound_setStringMethod;
    protected static Method class_NBTTagCompound_setDoubleMethod;
    protected static Method class_NBTTagCompound_setLongMethod;
    protected static Method class_NBTTagCompound_setIntMethod;
    protected static Method class_NBTTagCompound_removeMethod;
    protected static Method class_NBTTagCompound_getStringMethod;
    protected static Method class_NBTTagCompound_getBooleanMethod;
    protected static Method class_NBTTagCompound_getIntMethod;
    protected static Method class_NBTTagCompound_getByteMethod;
    protected static Method class_NBTTagCompound_getMethod;
    protected static Method class_NBTTagCompound_getCompoundMethod;
    protected static Method class_NBTTagCompound_getShortMethod;
    protected static Method class_NBTTagCompound_getByteArrayMethod;
    protected static Method class_NBTTagCompound_getListMethod;
    protected static Method class_Entity_saveMethod;
    protected static Method class_Entity_getTypeMethod;
    protected static Method class_TileEntity_loadMethod;
    protected static Method class_TileEntity_saveMethod;
    protected static Method class_TileEntity_updateMethod;
    protected static Method class_World_addEntityMethod;
    protected static Method class_CraftMetaBanner_getPatternsMethod;
    protected static Method class_CraftMetaBanner_setPatternsMethod;
    protected static Method class_CraftMetaBanner_getBaseColorMethod;
    protected static Method class_CraftMetaBanner_setBaseColorMethod;
    protected static Method class_CraftBanner_getPatternsMethod;
    protected static Method class_CraftBanner_setPatternsMethod;
    protected static Method class_CraftBanner_getBaseColorMethod;
    protected static Method class_CraftBanner_setBaseColorMethod;
    protected static Method class_NBTCompressedStreamTools_loadFileMethod;
    protected static Method class_CraftItemStack_asBukkitCopyMethod;
    protected static Method class_CraftItemStack_copyMethod;
    protected static Method class_CraftItemStack_mirrorMethod;
    protected static Method class_NBTTagCompound_hasKeyMethod;
    protected static Method class_CraftWorld_getTileEntityAtMethod;
    protected static Method class_CraftWorld_spawnMethod;
    protected static boolean class_CraftWorld_spawnMethod_isLegacy;
    protected static Method class_Entity_setLocationMethod;
    protected static Method class_Entity_getIdMethod;
    protected static Method class_Entity_getDataWatcherMethod;
    protected static Method class_Entity_getBoundingBox;
    protected static Method class_TileEntityContainer_setLock;
    protected static Method class_TileEntityContainer_getLock;
    protected static Method class_ChestLock_isEmpty;
    protected static Method class_ChestLock_getString;
    protected static Method class_ArmorStand_setInvisible;
    protected static Method class_ArmorStand_setGravity;
    protected static Method class_Entity_setNoGravity;
    protected static Method class_CraftPlayer_getHandleMethod;
    protected static Method class_CraftChunk_getHandleMethod;
    protected static Method class_CraftEntity_getHandleMethod;
    protected static Method class_CraftLivingEntity_getHandleMethod;
    protected static Method class_CraftWorld_getHandleMethod;
    protected static Method class_EntityPlayer_openSignMethod;
    protected static Method class_CraftServer_getServerMethod;
    protected static Method class_MinecraftServer_getResourcePackMethod;
    protected static Method class_MinecraftServer_getResourcePackHashMethod;
    protected static Method class_MinecraftServer_setResourcePackMethod;

    protected static Method class_ItemStack_isEmptyMethod;
    protected static Method class_ItemStack_createStackMethod;

    protected static Constructor class_NBTTagString_consructor;
    protected static Constructor class_CraftInventoryCustom_constructor;
    protected static Constructor class_NBTTagByte_constructor;
    protected static Constructor class_NBTTagByte_legacy_constructor;
    protected static Constructor class_EntityFireworkConstructor;
    protected static Constructor class_EntityPaintingConstructor;
    protected static Constructor class_EntityItemFrameConstructor;
    protected static Constructor class_BlockPositionConstructor;
    protected static Constructor class_PacketSpawnEntityConstructor;
    protected static Constructor class_PacketSpawnLivingEntityConstructor;
    protected static Constructor class_PacketPlayOutEntityMetadata_Constructor;
    protected static Constructor class_PacketPlayOutEntityStatus_Constructor;
    protected static Constructor class_PacketPlayOutEntityDestroy_Constructor;
    protected static Constructor class_PacketPlayOutCustomSoundEffect_Constructor;
    protected static Constructor class_PacketPlayOutExperience_Constructor;
    protected static Constructor class_PacketPlayOutAnimation_Constructor;
    protected static Constructor class_ChestLock_Constructor;
    protected static Constructor class_ArmorStand_Constructor;
    protected static Constructor class_AxisAlignedBB_Constructor;

    protected static Constructor class_ItemStack_consructor;

    protected static Field class_Entity_invulnerableField;
    protected static Field class_Entity_motXField;
    protected static Field class_Entity_motYField;
    protected static Field class_Entity_motZField;
    protected static Field class_WorldServer_entitiesByUUIDField;
    protected static Field class_ItemStack_tagField;
    protected static Field class_DamageSource_MagicField;
    protected static Field class_Firework_ticksFlownField;
    protected static Field class_Firework_expectedLifespanField;
    protected static Field class_CraftSkull_profile;
    protected static Field class_CraftMetaSkull_profile;
    protected static Field class_GameProfile_properties;
    protected static Field class_GameProfileProperty_value;
    protected static Field class_EntityTNTPrimed_source;
    protected static Field class_NBTTagList_list;
    protected static Field class_AxisAlignedBB_minXField;
    protected static Field class_AxisAlignedBB_minYField;
    protected static Field class_AxisAlignedBB_minZField;
    protected static Field class_AxisAlignedBB_maxXField;
    protected static Field class_AxisAlignedBB_maxYField;
    protected static Field class_AxisAlignedBB_maxZField;
    protected static Field class_EntityFallingBlock_hurtEntitiesField;
    protected static Field class_EntityFallingBlock_fallHurtMaxField;
    protected static Field class_EntityFallingBlock_fallHurtAmountField;
    protected static Field class_EntityArmorStand_disabledSlotsField;
    protected static Field class_EntityPlayer_playerConnectionField;
    protected static Field class_PlayerConnection_floatCountField;
    protected static Field class_Chunk_doneField;
    protected static Field class_CraftItemStack_getHandleField;
    protected static Field class_EntityArrow_lifeField = null;
    protected static Field class_EntityArrow_damageField;
    protected static Field class_CraftWorld_environmentField;
    protected static Field class_EntityLiving_potionBubblesField;
    protected static Field class_MemorySection_mapField;

    static
    {
        // Find classes Bukkit hides from us. :-D
        // Much thanks to @DPOHVAR for sharing the PowerNBT code that powers the reflection approach.
        String className = Bukkit.getServer().getClass().getName();
        String[] packages = className.split("\\.");
        if (packages.length == 5) {
            versionPrefix = packages[3] + ".";
        }

        try {
            class_Entity = fixBukkitClass("net.minecraft.server.Entity");
            class_EntityLiving = fixBukkitClass("net.minecraft.server.EntityLiving");
            class_EntityHuman = fixBukkitClass("net.minecraft.server.EntityHuman");
            class_ItemStack = fixBukkitClass("net.minecraft.server.ItemStack");
            class_DataWatcher = fixBukkitClass("net.minecraft.server.DataWatcher");
            class_DataWatcherObject = fixBukkitClass("net.minecraft.server.DataWatcherObject");
            class_NBTBase = fixBukkitClass("net.minecraft.server.NBTBase");
            class_NBTTagCompound = fixBukkitClass("net.minecraft.server.NBTTagCompound");
            class_NBTTagList = fixBukkitClass("net.minecraft.server.NBTTagList");
            class_NBTTagString = fixBukkitClass("net.minecraft.server.NBTTagString");
            class_NBTTagByte = fixBukkitClass("net.minecraft.server.NBTTagByte");
            class_CraftWorld = fixBukkitClass("org.bukkit.craftbukkit.CraftWorld");
            class_CraftInventoryCustom = fixBukkitClass("org.bukkit.craftbukkit.inventory.CraftInventoryCustom");
            class_CraftItemStack = fixBukkitClass("org.bukkit.craftbukkit.inventory.CraftItemStack");
            class_CraftBlockState = fixBukkitClass("org.bukkit.craftbukkit.block.CraftBlockState");
            class_CraftTask = fixBukkitClass("org.bukkit.craftbukkit.scheduler.CraftTask");
            class_CraftLivingEntity = fixBukkitClass("org.bukkit.craftbukkit.entity.CraftLivingEntity");
            class_Packet = fixBukkitClass("net.minecraft.server.Packet");
            class_World = fixBukkitClass("net.minecraft.server.World");
            class_WorldServer = fixBukkitClass("net.minecraft.server.WorldServer");
            class_EnumSkyBlock = (Class<Enum>)fixBukkitClass("net.minecraft.server.EnumSkyBlock");
            class_EnumSoundCategory = (Class<Enum>)fixBukkitClass("net.minecraft.server.SoundCategory");
            enum_SoundCategory_PLAYERS = Enum.valueOf(class_EnumSoundCategory, "PLAYERS");
            class_EntityPainting = fixBukkitClass("net.minecraft.server.EntityPainting");
            class_EntityCreature = fixBukkitClass("net.minecraft.server.EntityCreature");
            class_EntityItemFrame = fixBukkitClass("net.minecraft.server.EntityItemFrame");
            class_EntityMinecartRideable = fixBukkitClass("net.minecraft.server.EntityMinecartRideable");
            class_EntityTNTPrimed = fixBukkitClass("net.minecraft.server.EntityTNTPrimed");
            class_AxisAlignedBB = fixBukkitClass("net.minecraft.server.AxisAlignedBB");
            class_DamageSource = fixBukkitClass("net.minecraft.server.DamageSource");
            class_EntityDamageSource = fixBukkitClass("net.minecraft.server.EntityDamageSource");
            class_PathEntity = fixBukkitClass("net.minecraft.server.PathEntity");
            class_PathPoint = fixBukkitClass("net.minecraft.server.PathPoint");
            class_EntityFirework = fixBukkitClass("net.minecraft.server.EntityFireworks");
            class_CraftSkull = fixBukkitClass("org.bukkit.craftbukkit.block.CraftSkull");
            class_CraftMetaSkull = fixBukkitClass("org.bukkit.craftbukkit.inventory.CraftMetaSkull");
            class_NBTCompressedStreamTools = fixBukkitClass("net.minecraft.server.NBTCompressedStreamTools");
            class_TileEntity = fixBukkitClass("net.minecraft.server.TileEntity");
            class_EntityHorse = fixBukkitClass("net.minecraft.server.EntityHorse");
            class_EntityWitherSkull = fixBukkitClass("net.minecraft.server.EntityWitherSkull");
            class_PacketPlayOutAttachEntity = fixBukkitClass("net.minecraft.server.PacketPlayOutAttachEntity");
            class_PacketPlayOutEntityDestroy = fixBukkitClass("net.minecraft.server.PacketPlayOutEntityDestroy");
            class_PacketPlayOutSpawnEntity = fixBukkitClass("net.minecraft.server.PacketPlayOutSpawnEntity");
            class_PacketPlayOutSpawnEntityLiving = fixBukkitClass("net.minecraft.server.PacketPlayOutSpawnEntityLiving");
            class_PacketPlayOutEntityMetadata = fixBukkitClass("net.minecraft.server.PacketPlayOutEntityMetadata");
            class_PacketPlayOutEntityStatus = fixBukkitClass("net.minecraft.server.PacketPlayOutEntityStatus");
            class_PacketPlayOutCustomSoundEffect = fixBukkitClass("net.minecraft.server.PacketPlayOutCustomSoundEffect");
            class_PacketPlayOutExperience = fixBukkitClass("net.minecraft.server.PacketPlayOutExperience");
            class_PacketPlayOutAnimation = fixBukkitClass("net.minecraft.server.PacketPlayOutAnimation");
            class_EntityFallingBlock = fixBukkitClass("net.minecraft.server.EntityFallingBlock");
            class_EntityArmorStand = fixBukkitClass("net.minecraft.server.EntityArmorStand");
            class_EntityPlayer = fixBukkitClass("net.minecraft.server.EntityPlayer");
            class_PlayerConnection = fixBukkitClass("net.minecraft.server.PlayerConnection");
            class_Chunk = fixBukkitClass("net.minecraft.server.Chunk");
            class_CraftPlayer = fixBukkitClass("org.bukkit.craftbukkit.entity.CraftPlayer");
            class_CraftChunk = fixBukkitClass("org.bukkit.craftbukkit.CraftChunk");
            class_CraftEntity = fixBukkitClass("org.bukkit.craftbukkit.entity.CraftEntity");
            class_TileEntitySign = fixBukkitClass("net.minecraft.server.TileEntitySign");
            class_CraftServer = fixBukkitClass("org.bukkit.craftbukkit.CraftServer");
            class_MinecraftServer = fixBukkitClass("net.minecraft.server.MinecraftServer");
                    
            class_EntityProjectile = NMSUtil19.getBukkitClass("net.minecraft.server.EntityProjectile");
            class_EntityFireball = NMSUtil19.getBukkitClass("net.minecraft.server.EntityFireball");
            class_EntityArrow = NMSUtil19.getBukkitClass("net.minecraft.server.EntityArrow");
            class_CraftArrow = NMSUtil19.getBukkitClass("org.bukkit.craftbukkit.entity.CraftArrow");

            class_NBTTagList_addMethod = class_NBTTagList.getMethod("add", class_NBTBase);
            class_NBTTagList_getMethod = class_NBTTagList.getMethod("get", Integer.TYPE);
            class_NBTTagList_getDoubleMethod = class_NBTTagList.getMethod("e", Integer.TYPE);
            class_NBTTagList_sizeMethod = class_NBTTagList.getMethod("size");
            class_NBTTagList_removeMethod = class_NBTTagList.getMethod("remove", Integer.TYPE);
            class_NBTTagCompound_setMethod = class_NBTTagCompound.getMethod("set", String.class, class_NBTBase);
            class_World_getEntitiesMethod = class_World.getMethod("getEntities", class_Entity, class_AxisAlignedBB);
            class_CraftWorld_getTileEntityAtMethod = class_CraftWorld.getMethod("getTileEntityAt", Integer.TYPE, Integer.TYPE, Integer.TYPE);
            class_Entity_getBukkitEntityMethod = class_Entity.getMethod("getBukkitEntity");
            class_Entity_setYawPitchMethod = class_Entity.getDeclaredMethod("setYawPitch", Float.TYPE, Float.TYPE);
            class_Entity_setYawPitchMethod.setAccessible(true);
            class_AxisAlignedBB_Constructor = class_AxisAlignedBB.getConstructor(Double.TYPE, Double.TYPE, Double.TYPE, Double.TYPE, Double.TYPE, Double.TYPE);
            class_World_explodeMethod = class_World.getMethod("createExplosion", class_Entity, Double.TYPE, Double.TYPE, Double.TYPE, Float.TYPE, Boolean.TYPE, Boolean.TYPE);
            class_NBTTagCompound_setBooleanMethod = class_NBTTagCompound.getMethod("setBoolean", String.class, Boolean.TYPE);
            class_NBTTagCompound_setStringMethod = class_NBTTagCompound.getMethod("setString", String.class, String.class);
            class_NBTTagCompound_setDoubleMethod = class_NBTTagCompound.getMethod("setDouble", String.class, Double.TYPE);
            class_NBTTagCompound_setLongMethod = class_NBTTagCompound.getMethod("setLong", String.class, Long.TYPE);
            class_NBTTagCompound_setIntMethod = class_NBTTagCompound.getMethod("setInt", String.class, Integer.TYPE);
            class_NBTTagCompound_removeMethod = class_NBTTagCompound.getMethod("remove", String.class);
            class_NBTTagCompound_getStringMethod = class_NBTTagCompound.getMethod("getString", String.class);
            class_NBTTagCompound_getShortMethod = class_NBTTagCompound.getMethod("getShort", String.class);
            class_NBTTagCompound_getIntMethod = class_NBTTagCompound.getMethod("getInt", String.class);
            class_NBTTagCompound_getBooleanMethod = class_NBTTagCompound.getMethod("getBoolean", String.class);
            class_NBTTagCompound_getByteMethod = class_NBTTagCompound.getMethod("getByte", String.class);
            class_NBTTagCompound_getByteArrayMethod = class_NBTTagCompound.getMethod("getByteArray", String.class);
            class_NBTTagCompound_getListMethod = class_NBTTagCompound.getMethod("getList", String.class, Integer.TYPE);
            class_CraftItemStack_copyMethod = class_CraftItemStack.getMethod("asNMSCopy", org.bukkit.inventory.ItemStack.class);
            class_CraftItemStack_asBukkitCopyMethod = class_CraftItemStack.getMethod("asBukkitCopy", class_ItemStack);
            class_CraftItemStack_mirrorMethod = class_CraftItemStack.getMethod("asCraftMirror", class_ItemStack);
            class_NBTTagCompound_hasKeyMethod = class_NBTTagCompound.getMethod("hasKey", String.class);
            class_NBTTagCompound_getMethod = class_NBTTagCompound.getMethod("get", String.class);
            class_NBTTagCompound_getCompoundMethod = class_NBTTagCompound.getMethod("getCompound", String.class);
            class_EntityLiving_damageEntityMethod = class_EntityLiving.getMethod("damageEntity", class_DamageSource, Float.TYPE);
            class_DamageSource_getMagicSourceMethod = class_DamageSource.getMethod("b", class_Entity, class_Entity);
            class_World_addEntityMethod = class_World.getMethod("addEntity", class_Entity, CreatureSpawnEvent.SpawnReason.class);
            class_NBTCompressedStreamTools_loadFileMethod = class_NBTCompressedStreamTools.getMethod("a", InputStream.class);
            class_TileEntity_loadMethod = class_TileEntity.getMethod("a", class_NBTTagCompound);
            class_TileEntity_updateMethod = class_TileEntity.getMethod("update");
            class_Entity_setLocationMethod = class_Entity.getMethod("setLocation", Double.TYPE, Double.TYPE, Double.TYPE, Float.TYPE, Float.TYPE);
            class_Entity_getIdMethod = class_Entity.getMethod("getId");
            class_Entity_getDataWatcherMethod = class_Entity.getMethod("getDataWatcher");
            class_ArmorStand_setInvisible = class_EntityArmorStand.getDeclaredMethod("setInvisible", Boolean.TYPE);
            class_CraftPlayer_getHandleMethod = class_CraftPlayer.getMethod("getHandle");
            class_CraftChunk_getHandleMethod = class_CraftChunk.getMethod("getHandle");
            class_CraftEntity_getHandleMethod = class_CraftEntity.getMethod("getHandle");
            class_CraftLivingEntity_getHandleMethod = class_CraftLivingEntity.getMethod("getHandle");
            class_CraftWorld_getHandleMethod = class_CraftWorld.getMethod("getHandle");
            class_EntityPlayer_openSignMethod = class_EntityPlayer.getMethod("openSign", class_TileEntitySign);
            class_CraftServer_getServerMethod = class_CraftServer.getMethod("getServer");
            class_MinecraftServer_getResourcePackMethod = class_MinecraftServer.getMethod("getResourcePack");
            class_MinecraftServer_getResourcePackHashMethod = class_MinecraftServer.getMethod("getResourcePackHash");
            class_MinecraftServer_setResourcePackMethod = class_MinecraftServer.getMethod("setResourcePack", String.class, String.class);
            
            class_CraftInventoryCustom_constructor = class_CraftInventoryCustom.getConstructor(InventoryHolder.class, Integer.TYPE, String.class);
            class_EntityFireworkConstructor = class_EntityFirework.getConstructor(class_World, Double.TYPE, Double.TYPE, Double.TYPE, class_ItemStack);
            class_PacketSpawnEntityConstructor = class_PacketPlayOutSpawnEntity.getConstructor(class_Entity, Integer.TYPE);
            class_PacketSpawnLivingEntityConstructor = class_PacketPlayOutSpawnEntityLiving.getConstructor(class_EntityLiving);
            class_PacketPlayOutEntityMetadata_Constructor = class_PacketPlayOutEntityMetadata.getConstructor(Integer.TYPE, class_DataWatcher, Boolean.TYPE);
            class_PacketPlayOutEntityStatus_Constructor = class_PacketPlayOutEntityStatus.getConstructor(class_Entity, Byte.TYPE);
            class_PacketPlayOutEntityDestroy_Constructor = class_PacketPlayOutEntityDestroy.getConstructor(int[].class);
            class_PacketPlayOutCustomSoundEffect_Constructor = class_PacketPlayOutCustomSoundEffect.getConstructor(String.class, class_EnumSoundCategory, Double.TYPE, Double.TYPE, Double.TYPE, Float.TYPE, Float.TYPE);
            class_PacketPlayOutExperience_Constructor = class_PacketPlayOutExperience.getConstructor(Float.TYPE, Integer.TYPE, Integer.TYPE);
            class_PacketPlayOutAnimation_Constructor = class_PacketPlayOutAnimation.getConstructor(class_Entity, Integer.TYPE);
            
            class_CraftWorld_environmentField = class_CraftWorld.getDeclaredField("environment");
            class_CraftWorld_environmentField.setAccessible(true);
            class_Entity_invulnerableField = class_Entity.getDeclaredField("invulnerable");
            class_Entity_invulnerableField.setAccessible(true);
            class_Entity_motXField = class_Entity.getDeclaredField("motX");
            class_Entity_motXField.setAccessible(true);
            class_Entity_motYField = class_Entity.getDeclaredField("motY");
            class_Entity_motYField.setAccessible(true);
            class_Entity_motZField = class_Entity.getDeclaredField("motZ");
            class_Entity_motZField.setAccessible(true);
            class_WorldServer_entitiesByUUIDField = class_WorldServer.getDeclaredField("entitiesByUUID");
            class_WorldServer_entitiesByUUIDField.setAccessible(true);
            class_ItemStack_tagField = class_ItemStack.getDeclaredField("tag");
            class_ItemStack_tagField.setAccessible(true);
            class_DamageSource_MagicField = class_DamageSource.getField("MAGIC");
            class_EntityTNTPrimed_source = class_EntityTNTPrimed.getDeclaredField("source");
            class_EntityTNTPrimed_source.setAccessible(true);
            class_AxisAlignedBB_minXField = class_AxisAlignedBB.getField("a");
            class_AxisAlignedBB_minYField = class_AxisAlignedBB.getField("b");
            class_AxisAlignedBB_minZField = class_AxisAlignedBB.getField("c");
            class_AxisAlignedBB_maxXField = class_AxisAlignedBB.getField("d");
            class_AxisAlignedBB_maxYField = class_AxisAlignedBB.getField("e");
            class_AxisAlignedBB_maxZField = class_AxisAlignedBB.getField("f");
            class_EntityPlayer_playerConnectionField = class_EntityPlayer.getDeclaredField("playerConnection");

            class_Firework_ticksFlownField = class_EntityFirework.getDeclaredField("ticksFlown");
            class_Firework_ticksFlownField.setAccessible(true);
            class_Firework_expectedLifespanField = class_EntityFirework.getDeclaredField("expectedLifespan");
            class_Firework_expectedLifespanField.setAccessible(true);

            class_NBTTagString_consructor = class_NBTTagString.getConstructor(String.class);
            class_NBTTagByte_constructor = class_NBTTagByte.getConstructor(Byte.TYPE);

            class_NBTTagList_list = class_NBTTagList.getDeclaredField("list");
            class_NBTTagList_list.setAccessible(true);

            class_EntityFallingBlock_hurtEntitiesField = class_EntityFallingBlock.getDeclaredField("hurtEntities");
            class_EntityFallingBlock_hurtEntitiesField.setAccessible(true);
            class_EntityFallingBlock_fallHurtAmountField = class_EntityFallingBlock.getDeclaredField("fallHurtAmount");
            class_EntityFallingBlock_fallHurtAmountField.setAccessible(true);
            class_EntityFallingBlock_fallHurtMaxField = class_EntityFallingBlock.getDeclaredField("fallHurtMax");
            class_EntityFallingBlock_fallHurtMaxField.setAccessible(true);

            class_Chunk_doneField = class_Chunk.getDeclaredField("done");
            class_Chunk_doneField.setAccessible(true);
            class_CraftItemStack_getHandleField = class_CraftItemStack.getDeclaredField("handle");
            class_CraftItemStack_getHandleField.setAccessible(true);
            
            class_MemorySection_mapField = MemorySection.class.getDeclaredField("map");
            class_MemorySection_mapField.setAccessible(true);
            
            class_TileEntityContainer = fixBukkitClass("net.minecraft.server.TileEntityContainer");
            class_ChestLock = fixBukkitClass("net.minecraft.server.ChestLock");
            class_ChestLock_isEmpty = class_ChestLock.getMethod("a");
            class_ChestLock_getString = class_ChestLock.getMethod("b");
            class_Entity_getBoundingBox = class_Entity.getMethod("getBoundingBox");
            class_GameProfile = getClass("com.mojang.authlib.GameProfile");
            class_GameProfileProperty = getClass("com.mojang.authlib.properties.Property");
            class_CraftSkull_profile = class_CraftSkull.getDeclaredField("profile");
            class_CraftSkull_profile.setAccessible(true);
            class_CraftMetaSkull_profile = class_CraftMetaSkull.getDeclaredField("profile");
            class_CraftMetaSkull_profile.setAccessible(true);
            class_GameProfile_properties = class_GameProfile.getDeclaredField("properties");
            class_GameProfile_properties.setAccessible(true);
            class_GameProfileProperty_value = class_GameProfileProperty.getDeclaredField("value");
            class_GameProfileProperty_value.setAccessible(true);

            class_CraftMetaBanner = fixBukkitClass("org.bukkit.craftbukkit.inventory.CraftMetaBanner");
            class_CraftMetaBanner_getBaseColorMethod = class_CraftMetaBanner.getMethod("getBaseColor");
            class_CraftMetaBanner_getPatternsMethod = class_CraftMetaBanner.getMethod("getPatterns");
            class_CraftMetaBanner_setPatternsMethod = class_CraftMetaBanner.getMethod("setPatterns", List.class);
            class_CraftMetaBanner_setBaseColorMethod = class_CraftMetaBanner.getMethod("setBaseColor", DyeColor.class);

            class_CraftBanner = fixBukkitClass("org.bukkit.craftbukkit.block.CraftBanner");
            class_CraftBanner_getBaseColorMethod = class_CraftBanner.getMethod("getBaseColor");
            class_CraftBanner_getPatternsMethod = class_CraftBanner.getMethod("getPatterns");
            class_CraftBanner_setPatternsMethod = class_CraftBanner.getMethod("setPatterns", List.class);
            class_CraftBanner_setBaseColorMethod = class_CraftBanner.getMethod("setBaseColor", DyeColor.class);

            class_BlockPosition = fixBukkitClass("net.minecraft.server.BlockPosition");
            class_EnumDirection = (Class<Enum>)fixBukkitClass("net.minecraft.server.EnumDirection");
            class_BlockPositionConstructor = class_BlockPosition.getConstructor(Double.TYPE, Double.TYPE, Double.TYPE);
            class_EntityPaintingConstructor = class_EntityPainting.getConstructor(class_World, class_BlockPosition, class_EnumDirection);
            class_EntityItemFrameConstructor = class_EntityItemFrame.getConstructor(class_World, class_BlockPosition, class_EnumDirection);
            class_ChestLock_Constructor = class_ChestLock.getConstructor(String.class);
            class_ArmorStand_Constructor = class_EntityArmorStand.getConstructor(class_World);

            try {
                try {
                    // 1.11.?
                    class_Consumer = fixBukkitClass("org.bukkit.util.Consumer");
                    class_CraftWorld_spawnMethod = class_CraftWorld.getMethod("spawn", Location.class, Class.class, class_Consumer, CreatureSpawnEvent.SpawnReason.class);
                    class_CraftWorld_spawnMethod_isLegacy = false;
                } catch (Throwable ignore) {
                    class_CraftWorld_spawnMethod_isLegacy = true;
                    class_CraftWorld_spawnMethod = class_CraftWorld.getMethod("spawn", Location.class, Class.class, CreatureSpawnEvent.SpawnReason.class);
                }
                try {
                    // 1.11
                    class_ItemStack_consructor = class_ItemStack.getConstructor(class_NBTTagCompound);

                    class_EntityLiving_potionBubblesField = class_EntityLiving.getDeclaredField("g");
                    class_EntityLiving_potionBubblesField.setAccessible(true);
                    class_EntityArmorStand_disabledSlotsField = class_EntityArmorStand.getDeclaredField("bA");
                    if (class_EntityArmorStand_disabledSlotsField.getType() != Integer.TYPE) throw new Exception("Looks like 1.10");
                    class_TileEntityContainer_setLock = class_TileEntityContainer.getMethod("a", class_ChestLock);
                    class_TileEntityContainer_getLock = class_TileEntityContainer.getMethod("getLock");
                    class_ItemStack_isEmptyMethod = class_ItemStack.getMethod("isEmpty");
                } catch (Throwable ignore) {
                    // 1.10 and earlier
                    legacy = true;
                    class_ItemStack_createStackMethod = class_ItemStack.getMethod("createStack", class_NBTTagCompound);
                    class_EntityLiving_potionBubblesField = class_EntityLiving.getDeclaredField("f");
                    class_EntityLiving_potionBubblesField.setAccessible(true);
                    class_TileEntityContainer_setLock = class_TileEntityContainer.getMethod("a", class_ChestLock);
                    class_TileEntityContainer_getLock = class_TileEntityContainer.getMethod("y_");
                    
                    try {
                        class_EntityArmorStand_disabledSlotsField = class_EntityArmorStand.getDeclaredField("bB");
                        if (class_EntityArmorStand_disabledSlotsField.getType() != Integer.TYPE) throw new Exception("Looks like 1.9");
                    } catch (Throwable ignore2) {
                        try {
                            // 1.9.4
                            class_EntityArmorStand_disabledSlotsField = class_EntityArmorStand.getDeclaredField("bA");
                        } catch (Throwable ignore3) {
                            // 1.9.2
                            try {
                                class_EntityArmorStand_disabledSlotsField = class_EntityArmorStand.getDeclaredField("bz");
                            } catch (Throwable ignore4) {
                                // 1.8 and lower
                                class_EntityArmorStand_disabledSlotsField = class_EntityArmorStand.getDeclaredField("bi");
                            }
                        }
                    }
                }
                try {
                    // 1.10 and 1.11
                    class_Entity_setSilentMethod = class_Entity.getDeclaredMethod("setSilent", Boolean.TYPE);
                    class_Entity_setNoGravity = class_Entity.getDeclaredMethod("setNoGravity", Boolean.TYPE);
                    class_Entity_getTypeMethod = class_Entity.getDeclaredMethod("at");
                    class_Entity_getTypeMethod.setAccessible(true);
                    class_PlayerConnection_floatCountField = class_PlayerConnection.getDeclaredField("C");
                    if (class_PlayerConnection_floatCountField.getType() != Integer.TYPE) throw new Exception("Looks like 1.9");
                    class_PlayerConnection_floatCountField.setAccessible(true);
                } catch (Throwable ignore) {
                    // 1.9 and earlier
                    legacy = true;
                    class_ArmorStand_setGravity = class_EntityArmorStand.getDeclaredMethod("setGravity", Boolean.TYPE);
                    class_Entity_setSilentMethod = class_Entity.getDeclaredMethod("c", Boolean.TYPE);
                    class_Entity_getTypeMethod = class_Entity.getDeclaredMethod("as");
                    class_Entity_getTypeMethod.setAccessible(true);
                    class_PlayerConnection_floatCountField = class_PlayerConnection.getDeclaredField("g");
                    class_PlayerConnection_floatCountField.setAccessible(true);
                }
                try {
                    // 1.9 and up
                    class_DataWatcher_setMethod = class_DataWatcher.getMethod("set", class_DataWatcherObject, Object.class);
                    class_DataWatcher_getMethod = class_DataWatcher.getMethod("get", class_DataWatcherObject);
                    class_TileEntity_saveMethod = class_TileEntity.getMethod("save", class_NBTTagCompound);
                    class_Entity_saveMethod = class_Entity.getMethod("e", class_NBTTagCompound);
                    class_NBTTagCompound_getKeysMethod = class_NBTTagCompound.getMethod("c");
                    class_EntityDamageSource_setThornsMethod = class_EntityDamageSource.getMethod("w");
                } catch (Throwable ignore) {
                    // 1.8 and lower
                    legacy = true;
                    class_EntityDamageSource_setThornsMethod = class_EntityDamageSource.getMethod("v");
                    class_TileEntity_saveMethod = class_TileEntity.getMethod("b", class_NBTTagCompound);
                    class_TileEntityContainer_setLock = class_TileEntityContainer.getMethod("a", class_ChestLock);
                    class_TileEntityContainer_getLock = class_TileEntityContainer.getMethod("i");
                }
                
                class_EntityArmorStand_disabledSlotsField.setAccessible(true);
                class_EntityArrow_damageField = class_EntityArrow.getDeclaredField("damage");
                class_EntityArrow_damageField.setAccessible(true);
                // This is kinda hacky, like fer reals :\
                // OML, hating it!
                // Still hating it.
                try {
                    // 1.11
                    class_EntityArrow_lifeField = class_EntityArrow.getDeclaredField("ax");
                } catch (Throwable ignore5) {
                    try {
                        // 1.10
                        class_EntityArrow_lifeField = class_EntityArrow.getDeclaredField("ay"); // ayyyyy lmao
                    } catch (Throwable ignore4) {
                        legacy = true;
                        try {
                            // 1.8.3
                            class_EntityArrow_lifeField = class_EntityArrow.getDeclaredField("ar");
                        } catch (Throwable ignore3) {
                            try {
                                // 1.8
                                class_EntityArrow_lifeField = class_EntityArrow.getDeclaredField("ap");
                            } catch (Throwable ignore2) {
                                try {
                                    // 1.7
                                    class_EntityArrow_lifeField = class_EntityArrow.getDeclaredField("at");
                                } catch (Throwable ignore) {
                                    // Prior
                                    class_EntityArrow_lifeField = class_EntityArrow.getDeclaredField("j");
                                }
                            }
                        }
                    }
                }
            } catch (Throwable ex) {
                class_EntityArrow_lifeField = null;
            }
            if (class_EntityArrow_lifeField != null)
            {
                class_EntityArrow_lifeField.setAccessible(true);
            }
        }
        catch (Throwable ex) {
            failed = true;
            ex.printStackTrace();
        }
    }

    public static boolean getFailed() {
        return failed;
    }
    
    public static boolean isLegacy() {
        return legacy;
    }

    public static Class<?> getVersionedBukkitClass(String newVersion, String oldVersion) {
        Class<?> c = getBukkitClass(newVersion);
        if (c == null) {
            c = getBukkitClass(oldVersion);
            if (c == null) {
                Bukkit.getLogger().warning("Could not bind to " + newVersion + " or " + oldVersion);
            }
        }
        return c;
    }

    public static Class<?> getClass(String className) {
        Class<?> result = null;
        try {
            result = NMSUtil19.class.getClassLoader().loadClass(className);
        } catch (Exception ex) {
            result = null;
        }

        return result;
    }

    public static Class<?> getBukkitClass(String className) {
        Class<?> result = null;
        try {
            result = fixBukkitClass(className);
        } catch (Exception ex) {
            result = null;
        }

        return result;
    }

    public static Class<?> fixBukkitClass(String className) throws ClassNotFoundException {
        if (!versionPrefix.isEmpty()) {
            className = className.replace("org.bukkit.craftbukkit.", "org.bukkit.craftbukkit." + versionPrefix);
            className = className.replace("net.minecraft.server.", "net.minecraft.server." + versionPrefix);
        }

        return NMSUtil19.class.getClassLoader().loadClass(className);
    }

    public static Object getHandle(org.bukkit.Server server) {
        Object handle = null;
        try {
            handle = class_CraftServer_getServerMethod.invoke(server);
        } catch (Throwable ex) {
            handle = null;
        }
        return handle;
    }

    public static Object getHandle(org.bukkit.inventory.ItemStack stack) {
        Object handle = null;
        try {
            handle = class_CraftItemStack_getHandleField.get(stack);
        } catch (Throwable ex) {
            handle = null;
        }
        return handle;
    }

    public static Object getHandle(org.bukkit.World world) {
        if (world == null) return null;
        Object handle = null;
        try {
            handle = class_CraftWorld_getHandleMethod.invoke(world);
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
        return handle;
    }

    public static Object getHandle(org.bukkit.entity.Entity entity) {
        if (entity == null) return null;
        Object handle = null;
        try {
            handle = class_CraftEntity_getHandleMethod.invoke(entity);
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
        return handle;
    }

    public static Object getHandle(org.bukkit.entity.LivingEntity entity) {
        if (entity == null) return null;
        Object handle = null;
        try {
            handle = class_CraftLivingEntity_getHandleMethod.invoke(entity);
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
        return handle;
    }

    public static boolean isDone(org.bukkit.Chunk chunk) {
        Object chunkHandle = getHandle(chunk);
        boolean done = false;
        try {
            done = (Boolean)class_Chunk_doneField.get(chunkHandle);
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
        return done;
    }

    public static Object getHandle(org.bukkit.Chunk chunk) {
        Object handle = null;
        try {
            handle = class_CraftChunk_getHandleMethod.invoke(chunk);
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
        return handle;
    }

    public static Object getHandle(org.bukkit.entity.Player player) {
        Object handle = null;
        try {
            handle = class_CraftPlayer_getHandleMethod.invoke(player);
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
        return handle;
    }

    protected static void sendPacket(Server server, Location source, Collection<? extends Player> players, Object packet) throws Exception  {
        players = ((players != null && players.size() > 0) ? players : server.getOnlinePlayers());

        int viewDistance = Bukkit.getServer().getViewDistance() * 16;
        int viewDistanceSquared =  viewDistance * viewDistance;
        World sourceWorld = source.getWorld();
        for (Player player : players) {
            Location location = player.getLocation();
            if (!location.getWorld().equals(sourceWorld)) continue;
            if (location.distanceSquared(source) <= viewDistanceSquared) {
                sendPacket(player, packet);
            }
        }
    }

    protected static void sendPacket(Player player, Object packet) throws Exception {
        Object playerHandle = getHandle(player);
        Field connectionField = playerHandle.getClass().getField("playerConnection");
        Object connection = connectionField.get(playerHandle);
        Method sendPacketMethod = connection.getClass().getMethod("sendPacket", class_Packet);
        sendPacketMethod.invoke(connection, packet);
    }
    
    public static int getFacing(BlockFace direction)
    {
        int dir;
        switch (direction) {
        case SOUTH:
        default:
            dir = 0;
            break;
        case WEST:
            dir = 1;
            break;
        case NORTH:
            dir = 2;
            break;
        case EAST:
            dir = 3;
            break;
        }
        
        return dir;
    }

    public static org.bukkit.entity.Entity getBukkitEntity(Object entity)
    {
        if (entity == null) return null;
        try {
            Method getMethod = entity.getClass().getMethod("getBukkitEntity");
            Object bukkitEntity = getMethod.invoke(entity);
            if (!(bukkitEntity instanceof org.bukkit.entity.Entity)) return null;
            return (org.bukkit.entity.Entity)bukkitEntity;
        } catch (Throwable ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static Object getTag(Object mcItemStack) {
        Object tag = null;
        try {
            tag = class_ItemStack_tagField.get(mcItemStack);
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
        return tag;
    }

    protected static Object getNMSCopy(ItemStack stack) {
        Object nms = null;
        try {
            nms = class_CraftItemStack_copyMethod.invoke(null, stack);
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
        return nms;
    }

    public static ItemStack getCopy(ItemStack stack) {
        if (stack == null) return null;

        try {
            Object craft = getNMSCopy(stack);
            stack = (ItemStack)class_CraftItemStack_mirrorMethod.invoke(null, craft);
        } catch (Throwable ex) {
            stack = null;
        }

        return stack;
    }

    public static ItemStack makeReal(ItemStack stack) {
        if (stack == null) return null;
        Object nmsStack = getHandle(stack);
        if (nmsStack == null) {
            stack = getCopy(stack);
            nmsStack = getHandle(stack);
        }
        if (nmsStack == null) {
            return null;
        }
        try {
            Object tag = class_ItemStack_tagField.get(nmsStack);
            if (tag == null) {
                class_ItemStack_tagField.set(nmsStack, class_NBTTagCompound.newInstance());
            }
        } catch (Throwable ex) {
            ex.printStackTrace();
            return null;
        }

        return stack;
    }

    public static String getMeta(ItemStack stack, String tag, String defaultValue) {
        String result = getMeta(stack, tag);
        return result == null ? defaultValue : result;
    }

    public static boolean hasMeta(ItemStack stack, String tag) {
        return getNode(stack, tag) != null;
    }

    public static Object getNode(ItemStack stack, String tag) {
        Object meta = null;
        try {
            Object craft = getHandle(stack);
            if (craft == null) return null;
            Object tagObject = getTag(craft);
            if (tagObject == null) return null;
            meta = class_NBTTagCompound_getMethod.invoke(tagObject, tag);
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
        return meta;
    }

    public static boolean containsNode(Object nbtBase, String tag) {
        if (nbtBase == null) return false;
        Boolean result = false;
        try {
            result = (Boolean)class_NBTTagCompound_hasKeyMethod.invoke(nbtBase, tag);
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public static Object getNode(Object nbtBase, String tag) {
        if (nbtBase == null) return null;
        Object meta = null;
        try {
            meta = class_NBTTagCompound_getMethod.invoke(nbtBase, tag);
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
        return meta;
    }

    public static Object createNode(Object nbtBase, String tag) {
        if (nbtBase == null) return null;
        Object meta = null;
        try {
            meta = class_NBTTagCompound_getCompoundMethod.invoke(nbtBase, tag);
            class_NBTTagCompound_setMethod.invoke(nbtBase, tag, meta);
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
        return meta;
    }

    public static Object createNode(ItemStack stack, String tag) {
        Object outputObject = getNode(stack, tag);
        if (outputObject == null) {
            try {
                Object craft = getHandle(stack);
                if (craft == null) return null;
                Object tagObject = getTag(craft);
                if (tagObject == null) return null;
                outputObject = class_NBTTagCompound.newInstance();
                class_NBTTagCompound_setMethod.invoke(tagObject, tag, outputObject);
            } catch (Throwable ex) {
                ex.printStackTrace();
            }
        }
        return outputObject;
    }

    public static String getMeta(Object node, String tag, String defaultValue) {
        String meta = getMeta(node, tag);
        return meta == null || meta.length() == 0 ? defaultValue : meta;
    }

    public static String getMeta(Object node, String tag) {
        if (node == null || !class_NBTTagCompound.isInstance(node)) return null;
        String meta = null;
        try {
            meta = (String)class_NBTTagCompound_getStringMethod.invoke(node, tag);
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
        return meta;
    }

    public static Byte getMetaByte(Object node, String tag) {
        if (node == null || !class_NBTTagCompound.isInstance(node)) return null;
        Byte meta = null;
        try {
            meta = (Byte)class_NBTTagCompound_getByteMethod.invoke(node, tag);
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
        return meta;
    }

    public static Integer getMetaInt(Object node, String tag) {
        if (node == null || !class_NBTTagCompound.isInstance(node)) return null;
        Integer meta = null;
        try {
            meta = (Integer)class_NBTTagCompound_getIntMethod.invoke(node, tag);
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
        return meta;
    }

    public static Boolean getMetaBoolean(Object node, String tag) {
        if (node == null || !class_NBTTagCompound.isInstance(node)) return null;
        Boolean meta = null;
        try {
            meta = (Boolean)class_NBTTagCompound_getBooleanMethod.invoke(node, tag);
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
        return meta;
    }

    public static void setMeta(Object node, String tag, String value) {
        if (node == null|| !class_NBTTagCompound.isInstance(node)) return;
        try {
            if (value == null || value.length() == 0) {
                class_NBTTagCompound_removeMethod.invoke(node, tag);
            } else {
                class_NBTTagCompound_setStringMethod.invoke(node, tag, value);
            }
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }

    public static void setMetaLong(Object node, String tag, long value) {
        if (node == null|| !class_NBTTagCompound.isInstance(node)) return;
        try {
            class_NBTTagCompound_setLongMethod.invoke(node, tag, value);
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }

    public static void setMetaBoolean(Object node, String tag, boolean value) {
        if (node == null|| !class_NBTTagCompound.isInstance(node)) return;
        try {
            class_NBTTagCompound_setBooleanMethod.invoke(node, tag, value);
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }

    public static void setMetaDouble(Object node, String tag, double value) {
        if (node == null|| !class_NBTTagCompound.isInstance(node)) return;
        try {
            class_NBTTagCompound_setDoubleMethod.invoke(node, tag, value);
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }

    public static void setMetaInt(Object node, String tag, int value) {
        if (node == null|| !class_NBTTagCompound.isInstance(node)) return;
        try {
            class_NBTTagCompound_setIntMethod.invoke(node, tag, value);
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }

    public static void removeMeta(Object node, String tag) {
        if (node == null|| !class_NBTTagCompound.isInstance(node)) return;
        try {
            class_NBTTagCompound_removeMethod.invoke(node, tag);
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }

    public static void removeMeta(ItemStack stack, String tag) {
        try {
            Object craft = getHandle(stack);
            if (craft == null) return;
            Object tagObject = getTag(craft);
            if (tagObject == null) return;
            removeMeta(tagObject, tag);
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }

    public static void setMetaNode(Object node, String tag, Object child) {
        if (node == null || !class_NBTTagCompound.isInstance(node)) return;
        try {
            if (child == null) {
                class_NBTTagCompound_removeMethod.invoke(node, tag);
            } else {
                class_NBTTagCompound_setMethod.invoke(node, tag, child);
            }
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }

    public static boolean setMetaNode(ItemStack stack, String tag, Object child) {
        try {
            Object craft = getHandle(stack);
            if (craft == null) return false;
            Object node = getTag(craft);
            if (node == null) return false;
            if (child == null) {
                class_NBTTagCompound_removeMethod.invoke(node, tag);
            } else {
                class_NBTTagCompound_setMethod.invoke(node, tag, child);
            }
        } catch (Throwable ex) {
            ex.printStackTrace();
            return false;
        }
        
        return true;
    }

    public static String getMeta(ItemStack stack, String tag) {
        String meta = null;
        try {
            Object craft = getHandle(stack);
            if (craft == null) return null;
            Object tagObject = getTag(craft);
            if (tagObject == null) return null;
            meta = (String)class_NBTTagCompound_getStringMethod.invoke(tagObject, tag);
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
        return meta;
    }

    public static void setMeta(ItemStack stack, String tag, String value) {
        try {
            Object craft = getHandle(stack);
            if (craft == null) return;
            Object tagObject = getTag(craft);
            if (tagObject == null) return;
            class_NBTTagCompound_setStringMethod.invoke(tagObject, tag, value);
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }

    public static void addGlow(ItemStack stack) {

        try {
            Object craft = getHandle(stack);
            if (craft == null) return;
            Object tagObject = getTag(craft);
            if (tagObject == null) return;
            final Object enchList = class_NBTTagList.newInstance();
            class_NBTTagCompound_setMethod.invoke(tagObject, "ench", enchList);

            // Testing Glow API based on ItemMetadata storage
            Object bukkitData = createNode(stack, "bukkit");
            class_NBTTagCompound_setBooleanMethod.invoke(bukkitData, "glow", true);
        } catch (Throwable ex) {

        }
    }

    public static void removeGlow(ItemStack stack) {

        Collection<Enchantment> enchants = stack.getEnchantments().keySet();
        for (Enchantment enchant : enchants) {
            stack.removeEnchantment(enchant);
        }

        try {
            Object craft = getHandle(stack);
            if (craft == null) return;
            Object tagObject = getTag(craft);
            if (tagObject == null) return;

            // Testing Glow API based on ItemMetadata storage
            Object bukkitData = getNode(stack, "bukkit");
            if (bukkitData != null) {
                class_NBTTagCompound_setBooleanMethod.invoke(bukkitData, "glow", false);
            }
        } catch (Throwable ex) {

        }
    }

    public static boolean isUnbreakable(ItemStack stack) {
        Boolean unbreakableFlag = null;
        try {
            Object craft = getHandle(stack);
            if (craft == null) return false;
            Object tagObject = getTag(craft);
            if (tagObject == null) return false;
            unbreakableFlag = getMetaBoolean(tagObject, "Unbreakable");
        } catch (Throwable ex) {

        }
        
        return unbreakableFlag != null && unbreakableFlag;
    }

    public static void makeUnbreakable(ItemStack stack) {
        try {
            Object craft = getHandle(stack);
            if (craft == null) return;
            Object tagObject = getTag(craft);
            if (tagObject == null) return;

            Object unbreakableFlag = null;
            if (class_NBTTagByte_constructor != null) {
                unbreakableFlag = class_NBTTagByte_constructor.newInstance((byte) 1);
            } else {
                unbreakableFlag = class_NBTTagByte_legacy_constructor.newInstance("", (byte) 1);
            }
            class_NBTTagCompound_setMethod.invoke(tagObject, "Unbreakable", unbreakableFlag);
        } catch (Throwable ex) {

        }
    }

    public static void removeUnbreakable(ItemStack stack) {
        removeMeta(stack, "Unbreakable");
    }

    public static void hideFlags(ItemStack stack, byte flags) {
        try {
            Object craft = getHandle(stack);
            if (craft == null) return;
            Object tagObject = getTag(craft);
            if (tagObject == null) return;

            Object hideFlag = null;
            if (class_NBTTagByte_constructor != null) {
                hideFlag = class_NBTTagByte_constructor.newInstance(flags);
            } else {
                hideFlag = class_NBTTagByte_legacy_constructor.newInstance("", flags);
            }
            class_NBTTagCompound_setMethod.invoke(tagObject, "HideFlags", hideFlag);
        } catch (Throwable ex) {

        }
    }

    public static boolean createExplosion(Entity entity, World world, double x, double y, double z, float power, boolean setFire, boolean breakBlocks) {
        boolean result = false;
        if (world == null) return false;
        try {
            Object worldHandle = getHandle(world);
            if (worldHandle == null) return false;
            Object entityHandle = entity == null ? null : getHandle(entity);

            Object explosion = class_World_explodeMethod.invoke(worldHandle, entityHandle, x, y, z, power, setFire, breakBlocks);
            Field cancelledField = explosion.getClass().getDeclaredField("wasCanceled");
            result = (Boolean)cancelledField.get(explosion);
        } catch (Throwable ex) {
            ex.printStackTrace();
            result = false;
        }
        return result;
    }

    public static void makeTemporary(ItemStack itemStack, String message) {
        setMeta(itemStack, "temporary", message);
    }

    public static boolean isTemporary(ItemStack itemStack) {
        return hasMeta(itemStack, "temporary");
    }

    public static void makeUnplaceable(ItemStack itemStack) {
        setMeta(itemStack, "unplaceable", "true");
    }
    
    public static void removeUnplaceable(ItemStack itemStack) {
        removeMeta(itemStack, "unplaceable");
    }

    public static boolean isUnplaceable(ItemStack itemStack) {
        return hasMeta(itemStack, "unplaceable");
    }

    public static String getTemporaryMessage(ItemStack itemStack) {
        return getMeta(itemStack, "temporary");
    }

    public static void setReplacement(ItemStack itemStack, ItemStack replacement) {
        YamlConfiguration configuration = new YamlConfiguration();
        configuration.set("item", replacement);
        setMeta(itemStack, "replacement", configuration.saveToString());
    }

    public static ItemStack getReplacement(ItemStack itemStack) {
        String serialized = getMeta(itemStack, "replacement");
        if (serialized == null || serialized.isEmpty()) {
            return null;
        }
        YamlConfiguration configuration = new YamlConfiguration();
        ItemStack replacement = null;
        try {
            configuration.loadFromString(serialized);
            replacement = configuration.getItemStack("item");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return replacement;
    }

    protected static Object getTagString(String value) {
        try {
            return class_NBTTagString_consructor.newInstance(value);
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return null;
    }

    public static Object setStringList(Object nbtBase, String tag, Collection<String> values) {
        if (nbtBase == null) return null;
        Object listMeta = null;
        try {
            listMeta = class_NBTTagList.newInstance();

            for (String value : values) {
                Object nbtString = getTagString(value);
                class_NBTTagList_addMethod.invoke(listMeta, nbtString);
            }

            class_NBTTagCompound_setMethod.invoke(nbtBase, tag, listMeta);
        } catch (Throwable ex) {
            ex.printStackTrace();
            return null;
        }
        return listMeta;
    }

    public static ItemStack getItem(Object itemTag) {
        if (itemTag == null) return null;
        ItemStack item = null;
        try {
            Object nmsStack = null;
            if (class_ItemStack_consructor != null) {
                nmsStack = class_ItemStack_consructor.newInstance(itemTag);
            } else {
                nmsStack = class_ItemStack_createStackMethod.invoke(null, itemTag);
            }
            item = (ItemStack)class_CraftItemStack_mirrorMethod.invoke(null, nmsStack);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return item;
    }

    public static ItemStack[] getItems(Object rootTag, String tagName) {
        try {
            Object itemList = class_NBTTagCompound_getListMethod.invoke(rootTag, tagName, NBT_TYPE_COMPOUND);
            Integer size = (Integer)class_NBTTagList_sizeMethod.invoke(itemList);
            ItemStack[] items = new ItemStack[size];
            for (int i = 0; i < size; i++) {
                try {
                    Object itemData = class_NBTTagList_getMethod.invoke(itemList, i);
                    if (itemData != null) {
                        items[i] = getItem(itemData);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            return items;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static Object getTileEntityData(Location location) {
        Object data = null;
        try {
            World world = location.getWorld();
            Object tileEntity = class_CraftWorld_getTileEntityAtMethod.invoke(world, location.getBlockX(), location.getBlockY(), location.getBlockZ());
            if (tileEntity != null) {
                data = class_NBTTagCompound.newInstance();
                class_TileEntity_saveMethod.invoke(tileEntity, data);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return data;
    }

    public static Object getTileEntity(Location location) {
        Object tileEntity = null;
        try {
            World world = location.getWorld();
            tileEntity = class_CraftWorld_getTileEntityAtMethod.invoke(world, location.getBlockX(), location.getBlockY(), location.getBlockZ());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return tileEntity;
    }

    public static void clearItems(Location location) {
        if (location == null) return;
        try {
            World world = location.getWorld();
            if (world == null) return;

            Object tileEntity = class_CraftWorld_getTileEntityAtMethod.invoke(world, location.getBlockX(), location.getBlockY(), location.getBlockZ());
            if (tileEntity != null) {
                Object entityData = class_NBTTagCompound.newInstance();
                class_TileEntity_saveMethod.invoke(tileEntity, entityData);
                Object itemList = class_NBTTagCompound_getListMethod.invoke(entityData, "Items", NBT_TYPE_COMPOUND);
                if (itemList != null) {
                    List items = (List)class_NBTTagList_list.get(itemList);
                    items.clear();
                    class_TileEntity_loadMethod.invoke(tileEntity, entityData);
                    class_TileEntity_updateMethod.invoke(tileEntity);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void setTileEntityData(Location location, Object data) {
        if (location == null || data == null) return;
        try {
            World world = location.getWorld();
            if (world == null) return;

            Object tileEntity = class_CraftWorld_getTileEntityAtMethod.invoke(world, location.getBlockX(), location.getBlockY(), location.getBlockZ());
            if (tileEntity == null) return;

            class_NBTTagCompound_setIntMethod.invoke(data, "x", location.getBlockX());
            class_NBTTagCompound_setIntMethod.invoke(data, "y", location.getBlockY());
            class_NBTTagCompound_setIntMethod.invoke(data, "z", location.getBlockZ());

            class_TileEntity_loadMethod.invoke(tileEntity, data);
            class_TileEntity_updateMethod.invoke(tileEntity);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static Vector getPosition(Object entityData, String tag) {
        try {
            Object posList = class_NBTTagCompound_getListMethod.invoke(entityData, tag, NBT_TYPE_DOUBLE);
            Double x = (Double)class_NBTTagList_getDoubleMethod.invoke(posList, 0);
            Double y = (Double)class_NBTTagList_getDoubleMethod.invoke(posList, 1);
            Double z = (Double)class_NBTTagList_getDoubleMethod.invoke(posList, 2);
            if (x != null && y != null && z != null) {
                return new Vector(x, y, z);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Entity getEntity(World world, UUID uuid) {
        try {
            Object worldHandle = getHandle(world);
            final Map<UUID, Entity> entityMap = (Map<UUID, Entity>)class_WorldServer_entitiesByUUIDField.get(worldHandle);
            if (entityMap != null) {
                Object nmsEntity = entityMap.get(uuid);
                if (nmsEntity != null) {
                    return getBukkitEntity(nmsEntity);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static void setEnvironment(World world, World.Environment environment) {
        try {
            class_CraftWorld_environmentField.set(world, environment);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void playCustomSound(Player player, Location location, String sound, float volume, float pitch)
    {
        try {
            Object packet = class_PacketPlayOutCustomSoundEffect_Constructor.newInstance(sound, enum_SoundCategory_PLAYERS, location.getX(), location.getY(), location.getZ(), volume, pitch);
            sendPacket(player, packet);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static Map<String, Object> getMap(ConfigurationSection section)
    {
        if (section instanceof MemorySection)
        {
            try {
                Object mapObject = class_MemorySection_mapField.get(section);
                if (mapObject instanceof Map) {
                    return (Map<String, Object>)mapObject; 
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        // Do it the slow way
        Map<String, Object> map = new HashMap<>();
        Set<String> keys = section.getKeys(false);
        for (String key : keys) {
            map.put(key, section.get(key));
        }
        
        return map;
    }
}
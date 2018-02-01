package me.skymc.teleportprotect;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * 插件主类
 * 
 * @author sky
 * @since 2018年2月1日17:21:45
 */
public class TeleportProtect extends JavaPlugin implements Listener {
	
	@Override
	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(this, this);
	}
	
	@EventHandler
	public void teleport(PlayerTeleportEvent e) {
		e.setTo(getAllowLocation(e.getTo()));
	}
	
	/**
	 * 检查坐标是否玩家允许传送
	 * 
	 * @param location 坐标
	 * @return {@link Boolean}
	 */
	public boolean isAllowLocation(Location location) {
		return location.getBlock().getType().equals(Material.AIR) && location.getBlock().getRelative(BlockFace.UP).getType().equals(Material.AIR);
	}
	
	/**
	 * 根据坐标向上检查允许传送的坐标
	 * 
	 * @param startLocation 检查坐标
	 * @return {@Link Location}
	 */
	public Location getAllowLocation(Location startLocation) {
		Location location = startLocation.clone();
		/**
		 * 如果这个坐标不允许传送
		 */
		while (!isAllowLocation(location)) {
			/**
			 * Y轴+1
			 */
			location.add(0, 1, 0);
		}
		return location;
	}

}

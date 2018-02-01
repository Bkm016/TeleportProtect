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
 * �������
 * 
 * @author sky
 * @since 2018��2��1��17:21:45
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
	 * ��������Ƿ����������
	 * 
	 * @param location ����
	 * @return {@link Boolean}
	 */
	public boolean isAllowLocation(Location location) {
		return location.getBlock().getType().equals(Material.AIR) && location.getBlock().getRelative(BlockFace.UP).getType().equals(Material.AIR);
	}
	
	/**
	 * �����������ϼ�������͵�����
	 * 
	 * @param startLocation �������
	 * @return {@Link Location}
	 */
	public Location getAllowLocation(Location startLocation) {
		Location location = startLocation.clone();
		/**
		 * ���������겻������
		 */
		while (!isAllowLocation(location)) {
			/**
			 * Y��+1
			 */
			location.add(0, 1, 0);
		}
		return location;
	}

}

package com.speecker.rush;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Droptask extends BukkitRunnable {

	Itemspawnpoint droppoint;
	long period;
	int itemtype;

	Plugin plugin;
	public Droptask (Plugin plugin, Itemspawnpoint droppoint, long period, int itemtype){

        this.plugin=plugin;
		this.droppoint = droppoint;
		this.period = period;
		this.itemtype = itemtype;
		
	}

	@Override
	public void run() {

		double x = this.droppoint.x;
		double y = this.droppoint.y;
		double z = this.droppoint.z;
		World world = Bukkit.getWorld(this.droppoint.worldname);
		try {
			world.dropItemNaturally(new Location(world, x, y, z),
					new ItemStack(itemtype));
		} catch (Exception e) {
			Bukkit.getLogger().info(e.getMessage());
			e.printStackTrace();
		}

	}
	
	public void start (){
		
		runTaskTimer(plugin, 0l, this.period);
		
	}

	public String getWorldName (){
		
		return this.droppoint.worldname;
		
	}
	
}

package com.speecker.rush;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.tommytony.war.War;
import com.tommytony.war.Warzone;

public class Warstatechecker extends BukkitRunnable {
	
	private List<Warzone> warzonelist;
	
	private HashMap<Warzone, Boolean> states = new HashMap<Warzone, Boolean>();
	
	private Rush rush;
	
	public Warstatechecker (Rush rush){
		
		this.rush = rush;
		this.warzonelist = getWarzones();
		for (Warzone warzone: this.warzonelist){
			
			this.states.put(warzone, warzone.isEnoughPlayers());
			
		}
		
	}

	@Override
	public void run() {
		
		for (Warzone warzone: warzonelist){
			
			if (warzone.isEnoughPlayers() != states.get(warzone)){
				
				rush.warstatechanged(warzone.getWorld().getName(), warzone.isEnoughPlayers());
				
			}
			
		}
		
	}
	
	private List<Warzone> getWarzones (){
		
		List<Warzone> warzones = new LinkedList<Warzone>();
		Plugin plugin = Bukkit.getPluginManager().getPlugin("War");
		if (plugin !=null && plugin instanceof War){
		
			War war = (War)plugin;
			for (Warzone warzone: war.getWarzones()){
				
				warzones.add(warzone);
				
			}
		}
		
		return warzones;
		
	}

}

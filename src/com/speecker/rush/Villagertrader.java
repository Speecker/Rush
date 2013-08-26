package com.speecker.rush;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.bukkit.Location;
import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;
import com.esotericsoftware.yamlbeans.YamlWriter;

public final class Villagertrader {
	
	public String worldname;
	public double x, y, z;
	
	public static Villagertrader load(String base, String worldname){
		String filename=base+"_"+worldname+".yml";
		try {
			YamlReader reader=new YamlReader(new FileReader(filename));
			return reader.read(Villagertrader.class);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (YamlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public void save(String base){
	
	String filename=base+"_"+this.worldname+".yml";	
		try{
			(new File(filename)).getParentFile().mkdirs();
			YamlWriter writer=new YamlWriter(new FileWriter(filename));
			writer.write(this);
			writer.close();
		}catch (IOException ioexeption){
			
			System.out.println(ioexeption);
			
		}
	}

	public static Villagertrader create(Location location){ 
		Villagertrader villagertrader=new Villagertrader();
		villagertrader.worldname=location.getWorld().getName();
		villagertrader.x=location.getX();
		villagertrader.y=location.getY();
		villagertrader.z=location.getZ();
		
		return villagertrader;
	}
}

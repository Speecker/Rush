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

public class Itemspawnpoint {
	
	public String worldname;
	public double x, y, z;
	
	public static Itemspawnpoint load(String base, String worldname){
		String filename=base+"_"+worldname+".yml";
		try {
			YamlReader reader=new YamlReader(new FileReader(filename));
			return reader.read(Itemspawnpoint.class);
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

	public static Itemspawnpoint create(Location location){ 
		Itemspawnpoint itemspawn=new Itemspawnpoint();
		itemspawn.worldname=location.getWorld().getName();
		itemspawn.x=location.getX();
		itemspawn.y=location.getY();
		itemspawn.z=location.getZ();
		
		return itemspawn;
	}
}

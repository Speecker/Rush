package com.speecker.rush;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class Rush extends JavaPlugin {

	private final static String itemspawnfilered = "./plugins/Rush/Itemspawn/Red";
	private final static String itemspawnfileblue = "./plugins/Rush/Itemspawn/Blue";
	private final static String itemspawnfilegreen = "./plugins/Rush/Itemspawn/Green";
	private final static String itemspawnfileyellow = "./plugins/Rush/Itemspawn/Yellow";

	private final static String villagertraderweapons = "./plugins/Rush/Villagertrader/Weapons";
	private final static String villagertraderprotection = "./plugins/Rush/Villagertrader/Protection";
	private final static String villagertradermaterial = "./plugins/Rush/Villagertrader/Material";

	public boolean isRunning = false;

	public void onEnable() {
		getLogger()
				.info("\033[1;32mRush wurde aktiviert! | Rush has been enabled!\033[0m");
	}

	public void onDisable() {
		getLogger()
				.info("\033[1;32mRush wurde deaktiviert! | Rush has been disabled!\033[0m");
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {

		info(cmd.getName());

		if (cmd.getName().equals("rush")) {

			if (subcommand(args, "setitemspawn")) {

				info("setitemspawn aufgerufen", sender);
				setitemspawn(args, label, sender);

			} else if (subcommand(args, "settrader")) {
				settrader(args, label, label, sender);

			} else if (subcommand(args, "startitd")) {

			} else {

				info("CMNDs: rush, setitemspawn (rot/blau/gruen/gelb), settrader(waffen/schutz/material)",
						sender);

			}

		}

		return true;

	}

	private boolean subcommand(String[] args, String searchedCommand) {

		boolean found = false;

		for (int i = 0; i < args.length; i = i + 1) {
			String arg = args[i];

			if (arg.equals(searchedCommand)) {

				return true;
			}

		}

		return found;

	}

	public void info(String message) {

	}

	public void info(String message, CommandSender sender) {
		getLogger().info("\033[1;32m" + message + "\033[0m");

		if (sender != null) {

			sender.sendMessage(message);

		}
	}

	private void settrader(String[] args, String message, String label,
			CommandSender sender) {

		String filename = null;
		if (subsubcommand(args, sender, "weapons")) {
			waffen(args, label, sender);
			filename = villagertraderweapons;
			info("Waffen-Trader an aktuelle Position gesetzt! | Weapon-Trader set to this location!",
					sender);
		} else if (subsubcommand(args, sender, "protection")) {
			schutz(args, label, sender);
			filename = villagertraderprotection;
			info("Schutz-Trader an aktuelle Position gesetzt! | Protection-Trader set to this location!",
					sender);
		} else if (subsubcommand(args, sender, "material")) {
			material(args, label, sender);
			filename = villagertradermaterial;
			info("Material-Trader an aktuelle Position gesetzt! | Material-Trader set to this location!",
					sender);
		} else {
//			Deutch (German)
			info("Bitte gib einen Trade-Typ an: Waffen(weapons)/Schutz(protection)/Material(material)",
					sender);
//			English
			info("Please choose one of these Trade-Types: Weapons(weapons)/Protection(protection)/Material(material)",
					sender);

			return;

		}
		try {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				Location location = player.getLocation();
				Villagertrader villagertrader = Villagertrader.create(location);
				villagertrader.save(filename);

			}

		} catch (Exception e) {
			info("Error:" + e.getMessage());
			e.printStackTrace();
		}
	}

	private void setitemspawn(String[] args, String message,
			CommandSender sender) {

		String filename = null;
		if (subcommand(args, "ret")) {
			filename = itemspawnfilered;
		} else if (subcommand(args, "blue")) {
			filename = itemspawnfileblue;
		} else if (subcommand(args, "green")) {
			filename = itemspawnfilegreen;
		} else if (subcommand(args, "yellow")) {
			filename = itemspawnfileyellow;
		} else {
//			Deutsch (German)
			info("Bitte gib eine der Team-Farben an: Rot(red)/Blau(blue)/Gruen(green)/Gelb(yellow)",
					sender);
//			English
			info("Please choose one of these Team-Colors: Rot(red)/Blau(blue)/Gruen(green)/Gelb(yellow)",
					sender);

			return;
		}

		try {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				Location location = player.getLocation();
				Itemspawnpoint itemspawn = Itemspawnpoint.create(location);
				itemspawn.save(filename);
			}
		} catch (Exception e) {
			info("Error:" + e.getMessage());
			e.printStackTrace();

		}
	}

	private void waffen(String[] args, String message, CommandSender sender) {

		if (sender instanceof Player) {

		}
	}

	private void schutz(String[] args, String message, CommandSender sender) {

		if (sender instanceof Player) {
		}
	}

	private void material(String[] args, String message, CommandSender sender) {

		if (sender instanceof Player) {
		}
	}

	private boolean subsubcommand(String[] args, CommandSender sender,
			String searchedCommand) {

		info("SubSubCommand ausgefuehrt! | Subsubcommand called!", sender);

		boolean found = false;

		for (int i = 0; i < args.length; i = i + 1) {
			String arg = args[i];

			if (arg.equals(searchedCommand)) {

				return true;
			}

		}

		return found;

	}

}
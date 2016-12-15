package range;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class range extends JavaPlugin{

	public static Plugin instance;
	public static HashMap<String,Double> MAX_Dis = new HashMap<String, Double>();
	public static HashMap<String, Boolean> measu = new HashMap<String, Boolean>();
	public static HashMap<String, Boolean> always = new HashMap<String, Boolean>();



	@Override
	public void onEnable() {
		super.onEnable();

		//Register of PlayerListener
		new PlayerListener(this);

		//Register of instance
		instance = this;

		//register of Command
		getCommand("mrange").setExecutor(this);

	}

	@Override
	public void onDisable() {
		super.onDisable();
	}



	public boolean onCommand(CommandSender sender, Command cmd, String comanndLabel, String[]args){

		if(sender instanceof Player && cmd.getName().equalsIgnoreCase("mrange")){
			if(args.length == 0){
				sender.sendMessage(ChatColor.LIGHT_PURPLE + "[MoveRange] "
						+ ChatColor.GREEN + "/mrange => View this plugin's cmds list.");
				sender.sendMessage(ChatColor.LIGHT_PURPLE + "[MoveRange] "
						+ ChatColor.GREEN + "/mrange measu <on|off> => To measure will be enable or disable.");
				sender.sendMessage(ChatColor.LIGHT_PURPLE + "[MoveRange] "
						+ ChatColor.GREEN + "/mrange always <on|off> => Alyways measure will be enable or disable.");
				sender.sendMessage(ChatColor.LIGHT_PURPLE + "[MoveRange] "
						+ ChatColor.GREEN + "/mrange reset => To measurement MAX range will be reset.");
				sender.sendMessage(ChatColor.LIGHT_PURPLE + "[MoveRange] "
						+ ChatColor.GREEN + "/mrange status => View mode status.");
			}else if(args.length == 1 && args[0].equalsIgnoreCase("reset")){
				sender.sendMessage(ChatColor.LIGHT_PURPLE + "[MoveRange] "
						+ ChatColor.GREEN + "To measurement MAX range is reset!");
				MAX_Dis.put(sender.getName(), 0.0);
			}else if(args.length == 1 && args[0].equalsIgnoreCase("status")){
				sender.sendMessage(ChatColor.LIGHT_PURPLE + "[MoveRange] "
						+ ChatColor.GREEN + "measure mode: " + measu);
				sender.sendMessage(ChatColor.LIGHT_PURPLE + "[MoveRange] "
						+ ChatColor.GREEN + "always mode: " + always);
				sender.sendMessage(ChatColor.LIGHT_PURPLE + "[MoveRange] "
						+ ChatColor.GREEN + "Range MAX: " + MAX_Dis);
			}else if(args.length == 2 && args[0].equalsIgnoreCase("measu")){
				if(measu.getOrDefault(sender.getName(), false)){
					if(args[1].equalsIgnoreCase("off")){
						sender.sendMessage(ChatColor.LIGHT_PURPLE + "[MoveRange] "
								+ ChatColor.GREEN + "To measure is disabled!");
						measu.put(sender.getName(), false);
					}else if(args[1].equalsIgnoreCase("on")){
						sender.sendMessage(ChatColor.LIGHT_PURPLE + "[MoveRange] "
								+ ChatColor.GREEN + "To measure was enabled.");
					}else{
						sender.sendMessage(ChatColor.LIGHT_PURPLE + "[MoveRange] "
								+ ChatColor.RED + "Syntax mistake. Please type '/mrange'");
					}
				}else{
					if(args[1].equalsIgnoreCase("on")){
						sender.sendMessage(ChatColor.LIGHT_PURPLE + "[MoveRange] "
								+ ChatColor.GREEN + "To measure is enabled!");
						measu.put(sender.getName(), true);
					}else if(args[1].equalsIgnoreCase("off")){
						sender.sendMessage(ChatColor.LIGHT_PURPLE + "[MoveRange] "
								+ ChatColor.GREEN + "To measure was disabled.");
					}else{
						sender.sendMessage(ChatColor.LIGHT_PURPLE + "[MoveRange] "
								+ ChatColor.RED + "Syntax mistake. Please type '/mrange'");
					}
				}
			}else if(args.length == 2){
				if(always.getOrDefault(sender.getName(), false)){
					if(args[1].equalsIgnoreCase("off")){
						sender.sendMessage(ChatColor.LIGHT_PURPLE + "[MoveRange] "
								+ ChatColor.GREEN + "Always mode is disabled!");
						always.put(sender.getName(), false);
					}else if(args[1].equalsIgnoreCase("on")){
						sender.sendMessage(ChatColor.LIGHT_PURPLE + "[MoveRange] "
								+ ChatColor.GREEN + "Always mode was enabled.");
					}else{
						sender.sendMessage(ChatColor.LIGHT_PURPLE + "[MoveRange] "
								+ ChatColor.RED + "Syntax mistake. Please type '/mrange'");
					}
				}else{
					if(args[1].equalsIgnoreCase("on")){
						sender.sendMessage(ChatColor.LIGHT_PURPLE + "[MoveRange] "
								+ ChatColor.GREEN + "Always mode is enabled!");
						always.put(sender.getName(), true);
					}else if(args[1].equalsIgnoreCase("off")){
						sender.sendMessage(ChatColor.LIGHT_PURPLE + "[MoveRange] "
								+ ChatColor.GREEN + "Always mode was disabled.");
					}else{
						sender.sendMessage(ChatColor.LIGHT_PURPLE + "[MoveRange] "
								+ ChatColor.RED + "Syntax mistake. Please type '/mrange'");
					}
				}
			}else{
				sender.sendMessage(ChatColor.LIGHT_PURPLE + "[MoveRange] "
						+ ChatColor.RED + "Unknown Command. Please type '/mrange'");
			}
			return true;
		}else{
			sender.sendMessage(ChatColor.LIGHT_PURPLE + "[MoveRange] "
					+ ChatColor.RED + "Only player you can use this command.");
			return false;
		}

	}



	public static Plugin getInstance(){
		return instance;
	}

}

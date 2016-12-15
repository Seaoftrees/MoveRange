package range;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerListener implements Listener{

	public PlayerListener(range plugin){
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}



	@EventHandler
	public void Range(PlayerMoveEvent e){

		if(range.measu.getOrDefault(e.getPlayer().getName(), false)){
			double FromX = e.getFrom().getX();
			double FromZ = e.getFrom().getZ();
			double ToX = e.getTo().getX();
			double ToZ = e.getTo().getZ();

			int DisX = (int) ((FromX * 1000) - (ToX * 1000));
			int DisZ = (int) ((FromZ * 1000) - (ToZ * 1000));

			double Dis = Math.sqrt((DisX * DisX) + (DisZ * DisZ));

			if(!range.always.getOrDefault(e.getPlayer().getName(), false)){
				if(Dis > range.MAX_Dis.getOrDefault(e.getPlayer().getName(), 0.0)){
					e.getPlayer().sendMessage("MAX Range : " + Dis + " / 1000");
					range.MAX_Dis.put(e.getPlayer().getName(), Dis);
				}
			}else{
				e.getPlayer().sendMessage("Range : " + Dis + " / 1000");
			}

		}
	}


}

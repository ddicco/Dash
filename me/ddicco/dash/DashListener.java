package me.ddicco.dash;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAnimationEvent;

import com.projectkorra.projectkorra.BendingPlayer;
import com.projectkorra.projectkorra.ability.CoreAbility;


public class DashListener implements Listener {
	@EventHandler
	public void onPlayerAnimationEvent(PlayerAnimationEvent event) {
		
		Player player = event.getPlayer();
		BendingPlayer bPlayer = BendingPlayer.getBendingPlayer(player);
		player.sendMessage("Listener here");
		if (event.isCancelled() || bPlayer == null) {
			return;
		} else if (bPlayer.canBend(CoreAbility.getAbility(Dash.class)) && !CoreAbility.hasAbility(event.getPlayer(), Dash.class)) {
			new Dash (player);

		}
	}
}

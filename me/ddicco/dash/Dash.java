package me.ddicco.dash;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import com.projectkorra.projectkorra.ProjectKorra;
import com.projectkorra.projectkorra.ability.AddonAbility;
import com.projectkorra.projectkorra.ability.ChiAbility;
import com.projectkorra.projectkorra.util.ParticleEffect;
import com.projectkorra.projectkorra.util.ParticleEffect.ParticleData;

public class Dash extends ChiAbility implements AddonAbility {
	
	private long cooldown;
	private double speed;
	private long duration;
	private long starttime;
	
	public Dash(Player player) {
		super(player);
		// TODO Auto-generated constructor stub
		player.sendMessage("Ability started");
		if(bPlayer.isOnCooldown(this)) {
			remove();
			return;
		}
		cooldown = 5000;
		bPlayer.addCooldown(this);
		starttime = System.currentTimeMillis();
		duration = 3000;
		start();
	}

	@Override
	public long getCooldown() {
		// TODO Auto-generated method stub
		return cooldown;
	}

	@Override
	public Location getLocation() {
		// TODO Auto-generated method stub
		return player.getLocation();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Dash";
	}

	@Override
	public boolean isHarmlessAbility() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSneakAbility() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void progress() {
		// TODO Auto-generated method stub
		if(!player.isOnline()) {
			remove();
			return;
		}
		if(player.isDead() || player == null) {
			remove();
			return;
		}
		if(!bPlayer.canBend(this)) {
			remove();
			return;
		}
		if(starttime + duration > System.currentTimeMillis()) {
			remove();
			return;
		}
		final ParticleData particleData = new ParticleEffect.BlockData(Material.GOLD_BLOCK, (byte) 1);
		ParticleEffect.BLOCK_CRACK.display(particleData, 0.3F, 0.3F, 0.3F, 50, 50, player.getLocation(), 5);
		player.setVelocity(new Vector(player.getLocation().getDirection().multiply(speed).getX(), 0, player.getLocation().getDirection().multiply(speed).getZ()));
	}

	@Override
	public String getAuthor() {
		// TODO Auto-generated method stub
		return "ddicco";
	}

	@Override
	public String getVersion() {
		// TODO Auto-generated method stub
		return "0.3";
	}

	@Override
	public void load() {
		// TODO Auto-generated method stub
		 ProjectKorra.log.info("Successfully enabled " + getName() + " by " + getAuthor() + " Version " + getVersion());
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}

}

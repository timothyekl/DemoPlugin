package com.lithium3141.DemoPlugin;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class DemoPlugin extends JavaPlugin {
    
    public static final Logger LOG = Logger.getLogger("Minecraft");
    public static final String LOG_PREFIX = "[DemoPlugin] ";

    @Override
    public void onDisable() {
        LOG.info(LOG_PREFIX + "Disabled");
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
        // What command did we get? Notice use of .equals() for String comparison
        if(commandLabel.equals("demo")) {
            // Call another method to check the block
            this.lookAtBlock(sender);
            
            // Return true to say we handled this command
            return true;
        } else {
            // Not our command. Log it and continue
            LOG.info(LOG_PREFIX + "Not the /demo command - ignoring...");
            
            // Return false to say some other plugin can handle this command
            return false;
        }
    }
    
    /**
     * If sender is a Player, check the block they are looking at.
     * 
     * @param sender Sender of the /demo command
     */
    private void lookAtBlock(CommandSender sender) {
        if(sender instanceof Player) {
            LOG.info(LOG_PREFIX + "I am a player! What block do I see?");
            
            // Cast the sender to a Player type, so you can use Player methods
            Player player = (Player)sender;
            
            // Get the block the player is looking at
            // First argument is null because we want only air to be "transparent"
            // Second argument is maximum look distance - blocks over 100 blocks away are discarded
            Block block = player.getTargetBlock(null, 100);
            
            // Check the material of the block
            if(block.getType() == Material.DISPENSER) {
                // This block is a dispenser! Tell the player!
                player.sendMessage(ChatColor.AQUA + "This is a dispenser!");
            } else {
                // This block is not a dispenser.
                player.sendMessage(ChatColor.YELLOW + "This is not a dispenser.");
            }
        } else {
            LOG.info(LOG_PREFIX + "I am not a player.");
        }
    }

    @Override
    public void onEnable() {
        LOG.info(LOG_PREFIX + "Enabled - version " + this.getDescription().getVersion());
    }

}

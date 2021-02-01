package com.popupmc.realplaytime;

import org.bukkit.ChatColor;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class RealPlaytime extends JavaPlugin implements CommandExecutor {
    @Override
    public void onEnable() {
        Objects.requireNonNull(this.getCommand("rpt")).setExecutor(this);

        // Log enabled status
        getLogger().info("RealPlaytime is enabled.");
    }

    // Log disabled status
    @Override
    public void onDisable() {
        getLogger().info("RealPlaytime is disabled");
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "You have and always will have played");
            return true;
        }

        Player p = (Player)sender;

        // Get playtime ticks
        int playtimeTicks = p.getStatistic(Statistic.PLAY_ONE_MINUTE);

        // Get playtime in days like 1.65
        float playtimeDays = Math.round(((float)playtimeTicks / ((float)days)) * 100.0) / 100.0f;

        // Hours, Minutes, and Seconds
        int playtimeHours = playtimeTicks / hours;
        int playtimeHoursLeftover = playtimeTicks % hours;

        int playtimeMinutes = playtimeHoursLeftover / minutes;
        int playtimeMinutesLeftover = playtimeHoursLeftover % minutes;

        int playtimeSeconds = playtimeMinutesLeftover / seconds;

        sender.sendMessage(ChatColor.GOLD + "Your playtime is: " +
                playtimeDays + " days (" +
                playtimeHours + " hours, " +
                playtimeMinutes + " minutes, " +
                playtimeSeconds + " seconds)");

        return true;
    }

    public static final int seconds = 20;
    public static final int minutes = 60 * seconds;
    public static final int hours = 60 * minutes;
    public static final int days = 24 * hours;
}

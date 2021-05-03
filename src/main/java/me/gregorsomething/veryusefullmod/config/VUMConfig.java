package me.gregorsomething.veryusefullmod.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

import java.util.ArrayList;
import java.util.List;

@Config(name = "veryusefullmod")
public class VUMConfig implements ConfigData {
    @ConfigEntry.Gui.Tooltip
    public boolean filter_enabled = false;
    @ConfigEntry.Gui.Tooltip
    public List<String> whitelist = new ArrayList<String>();
    @ConfigEntry.Gui.Tooltip
    public boolean chat_commands = true;
    @ConfigEntry.Gui.Tooltip
    public  List<String> chat_symbols = new ArrayList<>();
}

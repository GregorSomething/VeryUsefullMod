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
    public boolean cape_elytra = true;
    @ConfigEntry.Gui.Tooltip
    public List<String> whitelist = new ArrayList<String>();
    @ConfigEntry.Gui.Tooltip
    public boolean chat_commands = true;
    @ConfigEntry.Gui.Tooltip
    public List<String> chat_symbols = new ArrayList<>();
    @ConfigEntry.Gui.CollapsibleObject
    public MorseThings morse_things = new MorseThings();
    @ConfigEntry.Gui.Tooltip
    public String SC_DATA = "%Y.%MO/%Y.%MO.%D-%H:%M:%S-xyz-%posx,%posy,%posz";

    public static class MorseThings {
        public boolean morse_msg = false;
        public boolean regex_primary_bool = true;
        public String regex_primary = "^\\w* whispers to you: ";
        public String regex_secondary = "^\\[\\w* -> Me\\]";
    }
}

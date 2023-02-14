package me.refracdevelopment.simplelinks.commands;

import dev.rosewood.rosegarden.RosePlugin;
import dev.rosewood.rosegarden.command.framework.RoseCommandWrapper;

import java.util.Collections;
import java.util.List;

public class SimpleCommandWrapper extends RoseCommandWrapper {

    public SimpleCommandWrapper(RosePlugin rosePlugin) {
        super(rosePlugin);
    }

    @Override
    public String getDefaultName() {
        return "links";
    }

    @Override
    public List<String> getDefaultAliases() {
        return Collections.singletonList("simplelinks");
    }

    @Override
    public List<String> getCommandPackages() {
        return Collections.singletonList("me.refracdevelopment.simplelinks.commands.command");
    }

    @Override
    public boolean includeBaseCommand() {
        return false;
    }

    @Override
    public boolean includeHelpCommand() {
        return true;
    }

    @Override
    public boolean includeReloadCommand() {
        return false;
    }
}
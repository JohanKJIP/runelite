package net.runelite.client.plugins.wildernessindicator;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

import java.awt.*;

@ConfigGroup("wildernessindicator")
public interface WildernessIndicatorConfig extends Config
{
    @ConfigItem(
            position = 1,
            keyName = "colorConfig",
            name = "Color of enemy",
            description = "Configures the color of players that can attack you"
    )
    default Color colorConfig()
    {
        return Color.RED;
    }

    @ConfigItem(
            position = 2,
            keyName = "showPanel",
            name = "Display panel",
            description = "Enables or disables the info panel in the top left"
    )
    default boolean showPanelWarning()
    {
        return true;
    }

    @ConfigItem(
            position = 3,
            keyName = "showFriends",
            name = "Show friends",
            description = "Display if friends can attack you"
    )
    default boolean showFriends()
    {
        return true;
    }

    @ConfigItem(
            position = 4,
            keyName = "showClan",
            name = "Show clan members",
            description = "Display if clan members can attack you"
    )
    default boolean showClan()
    {
        return true;
    }
}

package net.runelite.client.plugins.wildernessindicator;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.plugins.wildernessindicator.config.WildernessIndicatorNotifyMode;

import java.awt.*;

@ConfigGroup("wildernessindicator")
public interface WildernessIndicatorConfig extends Config
{
    @ConfigItem(
            position = 1,
            keyName = "notifyCondition",
            name = "Notify When",
            description = "Configures when to send notifications"
    )
    default WildernessIndicatorNotifyMode notifyCondition()
    {
        return WildernessIndicatorNotifyMode.WHEN_ATTACKABLE;
    }

    @ConfigItem(
            position = 2,
            keyName = "colorConfig",
            name = "Color of enemy",
            description = "Configures the color of players that can attack you"
    )
    default Color colorConfig()
    {
        return Color.RED;
    }

    @ConfigItem(
            position = 3,
            keyName = "showPanel",
            name = "Display panel",
            description = "Enables or disables the info panel in the top left"
    )
    default boolean showPanelWarning()
    {
        return true;
    }

    @ConfigItem(
            position = 4,
            keyName = "showFriends",
            name = "Show friends",
            description = "Display if friends can attack you"
    )
    default boolean showFriends()
    {
        return true;
    }

    @ConfigItem(
            position = 5,
            keyName = "showClan",
            name = "Show clan members",
            description = "Display if clan members can attack you"
    )
    default boolean showClan()
    {
        return true;
    }
}

/*
 * Copyright (c) 2018, Johan von Hacht <JohanKJIP@gmail.com>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package net.runelite.client.plugins.wildernessindicator;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Provides;
import net.runelite.api.Client;
import net.runelite.api.Player;
import net.runelite.api.events.GameTick;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetID;
import net.runelite.api.widgets.WidgetInfo;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.menus.MenuManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.ClientToolbar;
import net.runelite.client.ui.NavigationButton;

import javax.annotation.Nullable;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.ScheduledExecutorService;

@PluginDescriptor(
        name = "Wilderness Indicator",
        description = "Indicate if a player can attack you in wilderness",
        tags = {"wilderness", "highlight", "players"},
        enabledByDefault = false
)
public class WildernessIndicatorPlugin extends Plugin
{
    @Inject
    private Client client;

    @Inject
    private WildernessIndicatorConfig config;

    @Provides
    WildernessIndicatorConfig provideConfig(ConfigManager configManager)
    {
        return configManager.getConfig(WildernessIndicatorConfig.class);
    }

    @Override
    protected void startUp() throws Exception
    {

    }

    @Override
    protected void shutDown() throws Exception
    {

    }

    @Subscribe
    public void onGameTick(GameTick gameTick)
    {
        final Player localPlayer = client.getLocalPlayer();
        int wildernessLevel = getWildernessLevel();
        if (wildernessLevel > 0)
        {
            System.out.println("Wilderness level: " + wildernessLevel);
            List<Player> players = client.getPlayers();
            for (int i = 0; i < players.size(); i++)
            {
                Player p = players.get(i);
                if (p != localPlayer && Math.abs(p.getCombatLevel() - localPlayer.getCombatLevel()) <= wildernessLevel)
                {
                    System.out.println("YOU CAN BE ATTACKED");
                }
            }
        }
    }

    private int getWildernessLevel()
    {
        WidgetInfo widgetInfo = WidgetInfo.DEADMAN_PROTECTION_TEXT;
        Widget wildernessWidget = client.getWidget(widgetInfo);
        if (wildernessWidget != null)
        {
            String wildernessLevel = wildernessWidget.getText();
            return Integer.parseInt(wildernessLevel.split(" ")[1]);

        }
        return 0;
    }
}

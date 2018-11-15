package net.runelite.client.plugins.wildernessindicator;

import com.google.inject.Inject;
import net.runelite.api.Client;
import net.runelite.api.Player;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayUtil;
import net.runelite.client.ui.overlay.components.PanelComponent;
import net.runelite.client.ui.overlay.components.TitleComponent;

import java.awt.*;
import java.util.List;

public class WildernessIndicatorOverlay extends Overlay {
    private final Client client;
    private final WildernessIndicatorPlugin plugin;
    private final WildernessIndicatorConfig config;

    private final PanelComponent panelComponent = new PanelComponent();

    @Inject
    public WildernessIndicatorOverlay(Client client, WildernessIndicatorPlugin plugin, WildernessIndicatorConfig config)
    {
        setPosition(OverlayPosition.TOP_LEFT);
        this.client = client;
        this.plugin = plugin;
        this.config = config;
    }

    @Override
    public Dimension render(Graphics2D graphics) {
        if (!config.showPanelWarning())
        {
            return null;
        }

        panelComponent.getChildren().clear();
        if (plugin.isInWilderness())
        {
            if (plugin.isAttackable())
            {
                panelComponent.getChildren().add(TitleComponent.builder()
                        .text("You are not safe!")
                        .color(Color.RED)
                        .build());
            } else
                {
                panelComponent.getChildren().add(TitleComponent.builder()
                        .text("You are safe")
                        .color(Color.GREEN)
                        .build());
            }
        }

        for (Player p: plugin.getPlayersAbleToAttack()) {
            if ((!config.showFriends() && p.isFriend()) || (!config.showClan() && p.isClanMember())) continue;
            String text = "DANGER";
            OverlayUtil.renderActorOverlay(graphics, p, text, config.colorConfig());
        }
        return panelComponent.render(graphics);
    }
}

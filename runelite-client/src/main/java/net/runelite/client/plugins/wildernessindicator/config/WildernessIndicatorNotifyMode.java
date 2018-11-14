package net.runelite.client.plugins.wildernessindicator.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum WildernessIndicatorNotifyMode
{
    NONE("None"),
    WHEN_ATTACKABLE("Someone nearby able to attack");

    private final String name;

    @Override
    public String toString()
    {
        return name;
    }
}
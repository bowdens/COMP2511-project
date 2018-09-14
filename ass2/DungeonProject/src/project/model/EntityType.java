//package project.model;
package model;

import java.util.HashMap;
import java.util.Map;

public enum EntityType {
    FLOOR_TILE(1),
    WALL(2),
    EXIT(3),
	PLAYER(4),
	TREASURE(5),
	DOOR(6),
	KEY(7),
	BOULDER(8),
	FLOOR_SWITCH(9),
	UNLIT_BOMB(10),
	PIT(11),
	HUNTER(12),
	STRATEGIST(13),
	HOUND(14),
	COWARD(15),
	SWORD(16),
	ARROW(17),
	INVINCIBILITY_POTION(18),
	HOVER_POTION(19);

    private int value;
    private static Map<Integer, EntityType> map = new HashMap<>();

    private EntityType(int value) {
        this.value = value;
    }

    static {
        for (EntityType entityType : EntityType.values()) {
            map.put(entityType.value, entityType);
        }
    }

    public static EntityType valueOf(int pageType) {
        return (EntityType) map.get(pageType);
    }

    public int getValue() {
        return value;
    }
}

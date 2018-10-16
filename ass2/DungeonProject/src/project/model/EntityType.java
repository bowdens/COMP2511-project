package project.model;

import java.util.HashMap;
import java.util.Map;

public enum EntityType {
    PLAYER(1),
    WALL(2),
    EXIT(3),
	PIT(4),
	BOULDER(5),
	FLOOR_SWITCH(6),
	DOOR(7),
	KEY(8),
	TREASURE(9),
	UNLIT_BOMB(10),
	SWORD(11),
	ARROW(12),
	HUNTER(13),
	HOUND(14),
	STRATEGIST(15),
	COWARD(16),
	INVINCIBILITY_POTION(17),
	HOVER_POTION(18);

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

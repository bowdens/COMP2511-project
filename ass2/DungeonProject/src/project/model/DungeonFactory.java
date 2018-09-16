package project.model;
import project.model.obstacles.*;
import project.model.items.*;
import project.model.enemies.*;

public class DungeonFactory {
	 
	public BoardEntity makeBoardEntity(int entity, int x, int y) {
		EntityType newEntity = EntityType.valueOf(entity);
		
		switch (newEntity) {
			case WALL:
				return new Wall(x,y);
			case EXIT:
				return new Exit(x,y);
			case PLAYER:
				return new Player(x,y);
			case TREASURE:
				return new Treasure(x,y);
			case DOOR:
				//return new Door(x,y);
			case KEY:
				//return new Key(x,y);
			case BOULDER:
				return new Boulder(x,y);
			case FLOOR_SWITCH:
				return new FloorSwitch(x,y);
			case UNLIT_BOMB:
				return new UnlitBomb(x,y);
			case PIT:
				return new Pit(x,y);
			case HUNTER:
				return new Hunter(x,y);
			case STRATEGIST:
				return new Strategist(x,y);
			case HOUND:
				return new Hound(x,y);
			case COWARD:
				return new Coward(x,y);
			case SWORD:
				return new Sword(x,y);
			case ARROW:
				return new Arrow(x,y);
			case INVINCIBILITY_POTION:
				return new InvincibilityPotion(x,y);
			case HOVER_POTION:
				return new HoverPotion(x,y);
			default:
				break;
		}
		
		return null;
	}
	
}

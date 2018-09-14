//package project.model;
package model;

public class DungeonFactory {
	 
	public BoardEntity makeBoardEntity(int entity, int x, int y) {
		EntityType newEntity = EntityType.valueOf(entity);
		
		switch (newEntity) {
			case FLOOR_TILE:
				break;
			case WALL:
				break;
			case EXIT:
				break;
			case PLAYER:
				break;
			case TREASURE:
				break;
			case DOOR:
				break;
			case KEY:
				break;
			case BOULDER:
				break;
			case FLOOR_SWITCH:
				break;
			case UNLIT_BOMB:
				break;
			case PIT:
				break;
			case HUNTER:
				break;
			case STRATEGIST:
				break;
			case HOUND:
				break;
			case COWARD:
				break;
			case SWORD:
				break;
			case ARROW:
				break;
			case INVINCIBLITY_POTION:
				break;
			case HOVER_POTION:
				break;
			default:
				break;
		}
		
		return null;
	}
	
}

# User Stories

## Epic Story 1: As a player, I want to play the game either in a simple maze mode or a more advanced dungeon mode so that I may enjoy the challenges of both game modes.

### User Story 1.1: As a player, I want to have an exit in all simple mazes and some advanced dungeons so that I can complete each level.

#### Priority: 1

#### Estimate: 1

#### Acceptance Criteria:
1. An exit must exist at the end of each simple maze.
2. When the player touches the exit, they finish the level and are redirected to the main menu.
3. The exit mustn't be completely sealed from the player by a permanent barrier.


### User Story 1.2: As a player, I want there to be some walls in all dungeons, so that it is difficult to manoeuvre my character.

#### Priority: 1

#### Estimate: 2

#### Acceptance Criteria:
1. The player should never be spawned on top of a wall block.
2. The walls should never completely divide a player from the exit.
3. The player must not be able to walk through walls.


### User Story 1.3: As a player, I want to be able to move to adjacent squares that are not walled off, so that I can move around the dungeon.

#### Priority: 1

#### Estimate: 2

#### Acceptance Criteria:
1. The player should always move at the same rate as blocks per unit of time or per key press.
3. The player can move up, down, left or right using the arrow keys on a keyboard.
2. The player sprite should be animated while walking to show movement.
3. If the player attempts to move into a wall, they should not be able to.


### User Story 1.4: As a player, I want collecting treasure to be an objective in some advanced dungeon levels so that I can experience different challenges in completing levels.

#### Priority: 3

#### Estimate: 3

#### Acceptance Criteria:
1. Only accept collecting all treasure as an objective if there are no exits.
2. For all levels of this type, all treasure must be located in spots where it is possible to be collected by the player.
3. There must be a treasure sprite in the corner of the screen to indicate to the player that collecting the treasure is an objective of the dungeon.
4. If treasure collection is a goal of the dungeon when all treasure is collected and all other win conditions of the level are fulfilled, the dungeon should be completed and the player will be redirected to the main menu.
5. The treasure is collected when the player comes into contact with it.


### User Story 1.5: As a player, I want there to be a 'hunter' enemy in some advanced dungeon levels so that completing the dungeon will be more challenging.

#### Priority: 3

#### Estimate: 1.5

#### Acceptance Criteria:
1. The hunter must move as close as possible to the player.
2. If the hunter is as close as he can get to the player he must stop.
3. If the player collides with the hunter then the game is over and the player must be redirected to the main menu.
4. All objects on the grid besides switches and items should be treated as walls by the hunter. 


### User Story 1.6: As a player, I want there to be a 'hound' enemy in some advanced dungeon levels so that completing the dungeon will be more challenging.

#### Priority: 3

#### Estimate: 1.5

#### Acceptance Criteria:
1. The dungeon level must have a hunter to have a hound.
2. The hound must position itself between the hunter and the player.
3. If the player collides with the hound then the game is over and the player must be redirected to the main menu.
4. All objects on the grid besides switches and items should be treated as walls by the hound.


### User Story 1.7: As a player, I want there to be a 'strategist' enemy in some advanced dungeon levels so that completing the dungeon will be more challenging.

#### Priority: 3

#### Estimate: 1.5

#### Acceptance Criteria:
1. The strategist must move to a square the player is likely to move towards next.
2. If the player collides with the strategist then the game is over and the player must be redirected to the main menu.
3. All objects on the grid besides switches and items should be treated as walls by the strategist. 


### User Story 1.8: As a player, I want there to be a 'coward' enemy in some advanced dungeon levels so that completing the dungeon will be more challenging.

#### Priority: 3

#### Estimate: 1.5

#### Acceptance Criteria:
1. The coward must move towards the player until he is two grid squares away from the player after which the coward should run away.
2. If the player collides with the coward then the game is over and the player must be redirected to the main menu.
3. All objects on the grid besides switches and items should be treated as walls by the coward. 


### User Story 1.9: As a player, I want defeating all enemies to be an objective in some advanced dungeon levels so that I can experience different challenges in completing levels.

#### Priority: 4

#### Estimate: 1

#### Acceptance Criteria:
1. Defeating all enemies must only be an objective if there are no exits in the dungeon. 
2. All levels of this type must contain the items necessary to defeat the enemies (ie sword, arrows, bombs, invincibility potions).
3. The number of weapons must provide at least as many kill opportunities as there are enemies, i.e It is not valid
to have one sword that can be used five times but has 10 enemies in the dungeon.
4. All levels of this type must contain at least one enemy.
5. There must be an enemy sprite in the corner of the screen to indicate to the player that defeating all the enemies is the objective of the dungeon.


### User Story 1.10: As a player, I want there to be doors with keys so that I may use the keys to open the doors and progress through the dungeon.

#### Priority: 4

#### Estimate: 2

#### Acceptance Criteria:
1. For each door, there must be a key the player can obtain.
2. When the player comes into contact with the key it is indicated on the UI that the player has the key
3. For each door, there must be a corresponding key.
4. For each door/key pair, it must be apparent which key belongs to which door.
5. A door is unlocked by the player when they walk into it if they have picked up the corresponding key


### User Story 1.11: As a player, I want there to be pits in some advanced dungeons, so that I can experience different challenges in completing dungeon levels.

#### Priority: 4

#### Estimate: 2

#### Acceptance Criteria:
1. If included in a level, pits must be located on squares with no other objects.
2. If the player touches a pit, the game is over and they must be redirected to the main menu.
3. If a boulder is pushed into a pit it should be removed from the grid.


### User Story 1.12: As a player, I want to be able to push boulders by moving into them so that I may activate switches or move them into pits.

#### Priority: 5

#### Estimate: 1

#### Acceptance Criteria:
1. Moving into a boulder should move the boulder to an adjacent square in the direction the player is facing.
2. The boulders movement should be obstructed by any object that is not a switch or a pit.
3. If a boulder is pushed into a pit it should be removed from the grid.
4. If a boulder is pushed on top of a switch then the switch should activate.


### User Story 1.13: As a player, I want activating all the floor switches to be an objective in some advanced dungeons, so that I can experience different challenges in completing levels.

#### Priority: 5

#### Estimate: 3

#### Acceptance Criteria:
1. Only accept activating floor switches as an objective if there are no exits.
2. The number of boulders should be greater than or equal to the number of switches so that the level is beatable.
3. A switch should activate only if a boulder is moved into the same grid space as the switch.
4. If all switches are activated by a boulder and all other objectives are met, the level should be completed.
5. There must be floor switch sprite in the corner of the screen to indicate to the player that activating a combination of the floor switches is the objective of the dungeon.


### User Story 1.14: As a player, I want to be able to pick up unlit bombs which can later be dropped as lit bombs so that I can destroy enemies and boulders in the dungeon to help me finish the level.

#### Priority: 5

#### Estimate: 3

#### Acceptance Criteria:
1. The player should pick up an unlit bomb by moving over the bomb's grid space
2. There should be an indicator on the UI showing how many bombs the player has.
3. If the player has an unlit bomb then pressing the space bar should place a bomb in the grid square in front of the player if possible.
4. If another object is in front of the player then the bomb should not be placed.
5. Lit bombs should detonate in 3 seconds from when placed.
6. Upon explosion, any boulders or enemies in the squares immediately to the left, right, above or below are destroyed. 
7. If the player is in one of these squares they die.


### User Story 1.15: As a player, I want to be able to pick up sword so that I can use it to defeat enemies and help me finish the level.

#### Priority: 5

#### Estimate: 3

#### Acceptance Criteria:
1. The player should pick up a sword if they move over the sword's grid space. This should increase the number of hits the player has remaining by 5
2. If a player is holding a sword and they enter a square that an enemy is on, they should destroy the enemy with an animation to show what happened
4. The player can use the sword up to five times before it is destroyed
5. There should be an indicator on the UI showing how many hits of the sword the player has remaining.

### User Story 1.16: As a player, I want to be able to pick up arrows so that I can use them to defeat enemies and help me finish the level.

#### Priority: 5

#### Estimate: 3

#### Acceptance Criteria:
1. The player should pick up an arrow if they move over the sword's grid space.
2. When the player shoots an arrow by pressing ENTER on the keyboard it should fly in a straight line in the direction the player is facing and destroy any enemies it hits.
3. Arrows are destroyed upon hitting any object
4. There should be an indicator on the UI showing the number of arrows the player has remaining


### User Story 1.17: As a player, I want to be able to consume an invincibility potion so that I can use it to defeat enemies and help me finish the level.

#### Priority: 5

#### Estimate: 3

#### Acceptance Criteria:
1. The player should consume an invincibility potion if they move over the potion's grid space.
2. For 10 seconds any enemy hit by the player should die.
3. All enemies should run away from the player for the duration of the potion's effect.
4. For 10 seconds all the player should not die from exploding bombs.


### User Story 1.18: As a player, I want to be able to consume a hover potion so that I can use them to defeat enemies in order to help me finish the level.

#### Priority: 5

#### Estimate: 3

#### Acceptance Criteria:
1. The player should consume a hover potion if they move over the potion's grid space.
2. The effects of the potion should last until the player either dies or completes the level.
3. While the potion is in effect the player should be able to move over pits safely.

### User Story 1.18: As a player, I want to respawn when my player dies, so that I can attempt to beat the level again
#### Priority: 3

#### Estimate: 3

#### Acceptance Criteria:
1. The player must respawn at the same spot from which they started 
2. The player's inventory must be cleared and all items and objects are returned to their original positions

## Epic Story 2: As a designer, I want to create my own dungeon so that I or others can play my custom levels.
### User story 2.1: As a designer, I want to be able to give my dungeon levels a name so that I can identify them

#### Priority: 6

#### Estimate: 1

#### Acceptance Criteria:
1. When the level designer creates a level, they must be able to give it a name when they save it

### User story 2.2: As a designer, I want to be able to save a copy of my dungeon levels so that I can edit them later.

#### Priority: 6

#### Estimate: 2

#### Acceptance Criteria:
1. Dungeons should be saved as files onto the disk.
2. Dungeon files must be able to be read so they can be edited further, or played as a dungeon in the game.


### User story 2.3: As a designer, I want to be able to add, move and delete game objects in my level with ease so I can create levels easily

#### Priority: 4

#### Estimate: 3

#### Acceptance Criteria:
1. A sidebar should display the sprites of all the game objects.
2. On the same sidebar, the designer can set the objectives of the level by selecting a sprite of either an exit, a switch, treasure, or an enemy.
3. All objects can be placed in the level by dragging it from the sidebar and dropping it on a block.
4. Selecting a level object will highlight it.
5. A highlighted object can be moved by drag and drop.
6. A highlighted object will display a red x, which can be clicked to delete the object.
7. No objects can be placed or moved on top of another.
8. For a door to be placed a corresponding key must also be placed.


### User story 2.4: As a designer, I want to be able to place a player in my level, so that my level is playable

#### Priority: 4

#### Estimate: 1

#### Acceptance Criteria:
1. Only one player object can be added to the level.


### User story 2.5: As a designer, I want to be able to play my custom level before saving it, so that I may see if the level is playable.

#### Priority: 4

#### Estimate: 3

#### Acceptance Criteria:
1. There must be a play button the sidebar which allows the designer to play their game.
2. The designer can only play their game if they have placed their player sprite.
3. The designer should have an option to go back to designing at any time whilst playing.



### User story 2.6: As a designer, I want my games to be saved in the custom levels menu so that I and others can access and play my games.

#### Priority: 4

#### Estimate: 3

#### Acceptance Criteria:
1. Games created by the player must appear with the chosen name under the custom game menu.
2. The player should also be able to play games saved in 'dungeon' files anywhere on the file system

### User Story 2.7: As a designer, I want to be able to design levels where collecting treasure is an objective, so that I can be more creative with my level design

#### Priority: 

#### Estimate: 

#### Acceptance Criteria:


## Epic Story 3: As a user, I want the game to have a UI and graphics so that the game is easy to access and easy to play.

### User Story 3.1: As a user, I want the game to have the main menu so that I can view and access the various game modes available.

#### Priority: 2

#### Estimate: 1

### Acceptance Criteria:
1. The main menu should have a 'Menu' title to indicate it is the main menu.
2. The main menu should have three buttons for the simple maze, advanced dungeon, and custom game modes which direct the user to a level selection menu.
3. The main menu should have a third button for the level design mode.
4. All three buttons should be distinct.


### User Story 3.2: As a user, I want the game to have a level selection menu both game modes so that I can easily navigate to the level I want to play or a custom level.

#### Priority: 2

#### Estimate: 2

### Acceptance Criteria:
1. The simple maze mode, advanced dungeon mode, and custom levels should have a level selection pages accessible from the main menu.
2. The level selection menus should have a button to access all levels for the particular game modes.
3. Level selection menus should have a 'back' button in a corner that redirects the user back to the main menu. 


### User Story 3.3: As a user, I want to have distinguishable sprites so that I can navigate the dungeon with ease, and complete the puzzle, or design a level with them.

#### Priority: 4

#### Estimate: 4

#### Acceptance Criteria:
1. No sprite can be too similar to each other to cause confusion.
2. Sprites that are enemies should portray this using "dangerous" colours - eg reds etc.
3. Sprites that are helpful should portray this using "helpful" colours - blue green etc.
4. Other sprites should be neutrally coloured.

#### Acceptance Criteria:
1. Only accept collecting all treasure as an objective if there are no exits.
2. For all levels of this type, all treasure must be located in spots where it is possible to be collected by the player.
3. There must be a treasure sprite in the corner of the screen to indicate to the player that collecting the treasure is an objective of the dungeon.
4. If treasure collection is a goal of the dungeon when all treasure is collected and all other win conditions of the level are fulfilled, the dungeon should be completed and the player will be redirected to the main menu.

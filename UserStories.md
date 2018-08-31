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


### User Story 1.4: As a player, I want collecting treasure to be an objective in some advanced dungeon levels so that I can experience different challenges in completing dungeons.

#### Priority: 3

#### Estimate: 3

#### Acceptance Criteria:
1. Only accept collecting all treasure as an objective if there are no exits.
2. For all levels of this type, all treasure must be located in spots where it is possible to be collected by the player.
3. There must be a treasure sprite in the corner of the screen to indicate to the player that collecting the treasure is an objective of the dungeon.
4. If treasure collection is a goal of the dungeon when all treasure is collected and all other win conditions of the level are fulfilled, the dungeon should be completed and the player will be redirected to the main menu.

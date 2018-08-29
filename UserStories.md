# User Stories

## Epic Story 1: As a player, I want to play the simple maze so that I can solve it.

### User Story 1.1: As a player, I want to have an exit in the dungeon so that I can can complete the level

### Priority: 1

### Estimate: 1

#### Acceptance Criteria:
1. An exit must exist at the end of each simple maze
2. When the player touches the exit, they go to the main menu (or next level?)
3. The exit must not completely sealed from the player by a permanent barrier


### User Story 1.2: As a player, I want there to be walls in the dungeon, so that it difficult to reach the exit

### Priority: 1

### Estimate: 2

#### Acceptance Criteria:
1. The player should never be spawned ontop of a wall block
2. The walls should never completely divide a player from the exit
3. The player must not be able to walk through walls


### User Story 1.3: As a player, I want to be able to move to adjacent squares that are not walled off, so that I can move around the dungeon

### Priority: 1

### Estimate: 2

#### Acceptance Criteria:
1. The player should always move at the same rate of blocks per unit of time or per key press
2. The player sprite should be animated while walking to show movement
3. If the player attempts to move towards a wall, they should not be able to.

### User Story 1.5: As a player, I want to be able to see if the maze is simple before I select to play it, so I can choose the right level for me

### Priority: 2

### Estimate: 1

#### Acceptance Criteria:
1. In the main menu there should be a mode that the player can select to play a simple maze

## Epic Story 2: As a player, I want to play the advanced dungeon so that I can solve a more challenging puzzle

### User Story 2.1: As a player, I want there to be treasure so that I can collect it and complete the dungeon (if there are no doors)

### Priority: 3

### Estimate: 3

#### Acceptance Criteria:
1. For all levels of this type the treasure must be located in a spot where it is possible to be collected by the player
2. There must be a treasure sprite in the corner of the screen to indicate to the player that collecting the treasure is the objective of the dungeon
3. If treasure collection is the main goal of the dungeon, when all treasure is collected, the dungeon should be completed and the player will be taken back to the main menu

### User Story 2.2: As a player, I want there to be enemies with various behaviours so that it makes the dungeon more challenging

### Priority: 3

### Estimate: 6

#### Acceptance Criteria:
1. There must be a hunter that moves as close as possible to the player
2. There emust be a strategist who moves towards wherever the player is moving towards
3. Ther must be a hound that positions itself between the player and the hunter
4. There must be a coward that tries to get close to the player when they are far away, but runs away when they are close.

### User Story 2.3: As a player, I want to be able to win the game by defeating all of the enemies if that is the objective of the dungeon

### Priority: 4

### Estimate: 1

#### Acceptance Criteria:
1. All levels of this type must contain the items necessary to defeat the enemies (ie sword, arrows)
2. All levels of this type must contain atleast one enemy
3. There must be an enemy sprite in the corner of the screen to indicate to the player that defeating all the enemies is the objective of the dungeon
4. All such enemies must kill the play when they are touched by the player (ie if they are on the same square)

### User Story 2.4: As a player, I want there to be doors with keys so that I can open the doors with the keys to progress through the dungeon

### Priority: 4

### Estimate: 2

#### Acceptance Criteria:
1. For each door there must be a key that is possible to obtain by the player
2. The door should seal off a location. Although there may be alternate entrances, it would be no use to have a door that the player can simply walk around
3. For each door/key pair, it must be apparent which key belongs to which door


### User Story 2.5: As a player, I want to be able to see if the maze is advanced before I select to play it, so I can choose the right level for me

### Priority: 5

### Estimate: 1

#### Acceptance Criteria:
1. In the main menu, there should be a button the player can press to play an advanced maze level

### User Story 2.6: As a player, I want to be able to play levels where the objective is to activate all the floor switches, so that I can experience a variety of puzzles:

### Priority: 5

### Estimate: 3

#### Acceptance Criteria:
1. The number of boulders should equal exactly the number of switches <-Perhaps boulders >= switches?
2. If all switches are activated by a boulder, the level should be completed
3. There must be an floor switch sprite in the corner of the screen to indicate to the player that activating a combination of the floor switches is the objective of the dungeon

### User Story 2.7: As a player, I want there to be pits that prevent access across them, but allow boulders to be pushed into them

### Priority: 5

### Estimate: 2

#### Acceptance Criteria:
1. If included in a level, pits must be located on squares with no other objects
2. If the player touches a pit, they die
3. If a player touches a pit with a hover potion, they fly over the pit
4. Enemies must treat pits as walls

### User Story 2.8: As a player, I want there to be a number of items I can pick up to help me finish a level

### Priority: 4

### Estimate: 4

#### Acceptance Criteria:
1. There should be a sword the player can pick up. If a player is holding a sword and they enter a square that an enemy is on, they should destroy the enemy with an animation to show what happened. That can happen 5 times
2. There should be arrows the player can pick up. If a player shoots an arrow, it should fly through the air and destroy any enemies it hits. When it hits an obstruction, the arrow should be destroyed
3. There should be an invincibility potion the player can pick up. While its effects are active, enemies should flee the player, and they should be killed if hit by the player.
4. There should be a hover potion, that allows players to fly over pits. It lasts until the end of a dungeon

## Epic Story 3: As a designer, I want to create my own dungeon so that I can play my own (or others) levels.
### User story 3.1: As a designer, I want to be able to give my dungeon levels a name so that I can identify them

### Priority: 6

### Estimate: 5

#### Acceptance Criteria:
1. When the level designer creates a level, they must be able to give it a name when they save it

### User story 3.2: As a designer, I want to be able to save a copy of my dungeon levels so that I can edit them later

### Priority: 6

### Estimate: 2

#### Acceptance Criteria:
1. Dungeons should be saved as files onto the disk.
2. Dungeon files must be able to be read so they can be edited further, or played as a dungeon in the game


## Epic Story 4: As a user, I want the game to have graphics so that it is easy to play

### User Story 4.1: As a user, I want to have distinguishable sprites so that I can navingate the dungeon with ease, and complete the puzzle, or design a level with them.

### Priority: 4

### Estimate: 4

#### Acceptance Criteria:
1. No sprite can be too similar to each other to cause confusion
2. Sprites that are enemies should portray this using "dangerous" colours - eg reds etc.2
3. Sprites that are helpful should portray this using "helpful" colours - blue green etc.
4. Other sprites should be neutrally coloured

# COMP2511 Project

## Useful links:

* [Github Markdown (.md) guide](https://guides.github.com/features/mastering-markdown/)
* [Full project specification](https://www.cse.unsw.edu.au/~cs2511/project/project.html)

## Specification

### Due Dates:
* Milestone 1: 11:59PM Sunday 26th August (Week 5) (Feedback: Week 6 Lab)

* Milestone 2: 11:59PM Sunday 16th September (Week 8) (Feedback: Week 9 Lab)

* Final milestone: 11:59PM Friday 19th October (Week 12) (Demonstration: Week 13 Lab)

* Moodle Peer assessment: 11:55PM Sunday 21st October

### Overview 

You have received a request from a client for an application that enables the creation and playing of dungeon-style puzzles. You will form a team of 4 (in some cases 5) people from your tutorial and follow an agile development process to design and implement a desktop Java application that satisfies the requirements of the client (see below). The final piece of software you deliver is expected to be of high quality and demonstrate the knowledge and skills you have acquired in this course.

### Preliminary Client Requirements

The client desires an application that works in two modes. In the first mode, the user moves a player around a dungeon and tries to overcome various challenges to "complete" the dungeon. The simplest form of such a puzzle is a maze, where the player must find their way from the starting point to the exit.

![Maze](https://www.cse.unsw.edu.au/~cs2511/project/maze.png)

More advanced puzzles may contain things like boulders that need to be pushed onto floor switches,

![Boulders](https://www.cse.unsw.edu.au/~cs2511/project/boulders.png)

or enemies that need to be fought with weapons, potions, or treasure.

![Advanced dungeon](https://www.cse.unsw.edu.au/~cs2511/project/advanced.png)

In the second mode, the user designs their own dungeons. They should be able to place various walls, obstacles, enemies and other items in the dungeon to build puzzles. The client wants this to be tightly integrated with the first mode, so it should be possible, for example, for the user to try out an incomplete dungeon to help them while designing.

### Dungeon Layout
To be specific, each dungeon is defined by a grid of squares, each of which may contain one or more entities. The different types of entities are as follows:

Entity | Exaple | Description
-|-|-
Player | ![Player](https://www.cse.unsw.edu.au/~cs2511/project/human_new.png) | The player can be moved either up, down, left, or right into adjacent squares, as long as another entity does not stop them (e.g. a wall).
Wall | ![Wall](https://www.cse.unsw.edu.au/~cs2511/project/brick_brown_0.png) | Blocks the movement of the player, enemies, boulders and arrows.
Exit | ![Exit](https://www.cse.unsw.edu.au/~cs2511/project/exit.png) | If the player goes through an exit the puzzle is complete.
Treasure | ![Treasure](https://www.cse.unsw.edu.au/~cs2511/project/gold_pile.png) | Can be collected by the player.
Door | ![Door](https://www.cse.unsw.edu.au/~cs2511/project/open_door.png) ![Door](https://www.cse.unsw.edu.au/~cs2511/project/closed_door.png) | Exists in conjunction with a single key that can open it. If the player holds the key, they can open the door by moving through it. After opening it remains open. The client will be satisfied if dungeons can be made with up to 3 doors.
Key | ![Key](https://www.cse.unsw.edu.au/~cs2511/project/key.png) | Can be picked up by the player when they move into the containing square. The player can carry only one key at a time, and only one door has a lock that fits the key. The key disappears once it is used to open its corresponding door.
Boulder | ![Boulder](https://www.cse.unsw.edu.au/~cs2511/project/boulder.png) | Acts like a wall in most cases. The only differences are that it can be pushed by the player into adjacent squares and can be destroyed by a bomb. The player is only strong enough to push one boulder at a time.
Floor switch | ![Floor switch](https://www.cse.unsw.edu.au/~cs2511/project/pressure_plate.png) | Switches behave like empty squares so other entities can appear on top of them. When a boulder is pushed onto a floor switch, it is triggered. Pushing a boulder off the floor switch untriggers it. See below for how switch triggering affects dungeon completion.
Unlit bomb | ![Unlit bomb](https://www.cse.unsw.edu.au/~cs2511/project/bomb_unlit.png) | The bomb is picked up by the player when they move into the square containing it.
Lit bomb | ![Lit bomb](https://www.cse.unsw.edu.au/~cs2511/project/bomb_lit_1.png) ![Lit bomb](https://www.cse.unsw.edu.au/~cs2511/project/bomb_lit_2.png) ![Lit bomb](https://www.cse.unsw.edu.au/~cs2511/project/bomb_lit_3.png) ![Lit bomb](https://www.cse.unsw.edu.au/~cs2511/project/bomb_lit_4.png) | The player can light the bombs they have collected and drop them. The fuse burns down for a short fixed period of time before the bomb explodes. Upon explosion, any boulders or enemies in the squares immediately to the left, right, above or below are destroyed. If the player is in one of these squares they die.
Pit | ![Pit](https://www.cse.unsw.edu.au/~cs2511/project/shaft.png) | If the player falls into a pit they die. Boulders pushed into a pit disappear.
The hunter | ![The hunter](https://www.cse.unsw.edu.au/~cs2511/project/deep_elf_master_archer.png) | This enemy constantly moves toward the player, stopping if it cannot move any closer. Like all enemies, the player dies upon collision with them.
The strategist | ![The strategist](https://www.cse.unsw.edu.au/~cs2511/project/deep_elf_conjurer.png) | An enemy that moves towards a square the player is likely to move to next.
The hound | ![The hound](https://www.cse.unsw.edu.au/~cs2511/project/hound.png) | An enemy that assists the hunter by positioning itself such that the player is between it and the hunter.
The coward | ![The coward](https://www.cse.unsw.edu.au/~cs2511/project/gnome.png) | An enemy that behaves like the hunter when far away from the player, but runs away when it gets close. Like all enemies, the player dies upon collision with them.
Sword | ![Sword](https://www.cse.unsw.edu.au/~cs2511/project/greatsword_1_new.png) | This can be picked up the player and used to kill enemies. Only one sword can be carried at once. Each sword is only capable of 5 hits and disappears after that. One hit of the sword is sufficient to destroy any enemy.
Arrow | ![Arrow](https://www.cse.unsw.edu.au/~cs2511/project/arrow.png) | Can be collected by the player and at will either left, right, up or down. Enemies are destroyed if they are hit with an arrow. Arrows are destroyed upon collision with anything. There is no limit to how many arrows the player can carry.
Invincibility potion | ![Invincibility](https://www.cse.unsw.edu.au/~cs2511/project/brilliant_blue_new.png) | If the player picks this up they become invincible to all bombs and enemies. Colliding with an enemy should result in their immediate destruction. Because of this, all enemies will run away from the player when they are invincible. The effect of the potion only lasts a limited time.
Hover potion | ![Hover](https://www.cse.unsw.edu.au/~cs2511/project/bubbly.png) | Gives the player the ability to hover and fly over pits. This potion lasts until either the player is destroyed or the dungeon is complete.


### Dungeon Completion
Dungeons can be completed in different ways, depending on what entities are in them and choices made by the designer.

If there are exits, then the puzzle can only be completed by the player entering one of them. However, if there are no exits, then the dungeon can only be completed by some conjunction of the following:

* Destroying all enemies.
* Having a boulder on all floor switches.
* Collecting all treasure.

In the dungeon designing mode, the user should be able to select one or more of these as completion conditions.

### Visual Design
The client has given you free reign over the visual design of the program. In the above table, there are some example assets you may use, but you do not have to. You can find assets elsewhere or even create your own. The examples above came from [here](https://opengameart.org/).





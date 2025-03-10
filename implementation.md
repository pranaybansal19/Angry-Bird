## Classes

1. ### AngryBird
    - Main class initializing required screens such as MainMenuScreen, LoadingScreen, Level and rendering the LoadingScreen.
   
2. ### Handler
    - Handle event listeners that can be applied on LibGDX Image.

3. ### FileHandler
    - Handle saving of games and player object.

4. ### Player
    - Stores data of a player like username, best scores.

5. ### BlackBird
    - Create and draw dynamic body for black bird on given batch and world. Also provide methods - `useSpecialAbility()` and `isClicked()`.

6. ### RedBird
    - Create and draw dynamic body for red bird on given batch and world. Also provide methods - `useSpecialAbility()` and `isClicked()`.

7. ### BlueBird
    - Create and draw dynamic body for blue bird on given batch and world. Also provide methods - `useSpecialAbility()` and `isClicked()`.

8. ### YellowBird
    - Create and draw dynamic body for yellow bird on given batch and world. Also provide methods - `useSpecialAbility()` and `isClicked()`.

9. ### Pig1
    - Create and draw dynamic body for pig1 on given batch and world. Also provide method - `isClicked()` to check whether its clicked or not.

10. ### Pig2
    - Create and draw dynamic body for pig2 on given batch and world. Also provide method - `isClicked()` to check whether its clicked or not.

11. ### Pig3
    - Create and draw dynamic body for pig3 on given batch and world. Also provide method - `isClicked()` to check whether its clicked or not.

12. ### LoadingScreen
    - Draw and render loading screen - background, progress bar and load to MainMenuScreen.

13. ### MainMenuScreen
    - Draw and render main menu screen elements - start menu, load game, save game, setting, quit.

14. ### StartMenu
    - Provide and draw start menu options. Also add respective buttons event handlers.

15. ### Load
    - Draw load game screen. It shows saved games.

16. ### SaveProgress
    - Draw Save progress screen and save the current progress of game.

17. ### Setting
    - Draw setting screen and provide options - Music ON/OFF, Sound ON/OFF.

18. ### Profile
    - Draw profile screen and show username. Also to change username.

19. ### Quit
    - Draw quit screen and allow to exit the game.

20. ### Glass
    - Create and draw dynamic body for glass blocks - Plank(vertical/horizontal) and box.

21. ### Stone
    - Create and draw dynamic body for stone blocks - Plank(vertical/horizontal) and box.

22. ### Wood
    - Create and draw dynamic body for wooden blocks - Plank(vertical/horizontal) and box.

23. ### SlingShot
    - Create and draw slingshot.

24. ### Ground
    - Create and draw static ground body for levels.

25. ### LevelMenuScreen
    - Draw and render level menu screen which has initially 3 levels unlocked.

26. ### Level1
    - Draw and render level 1 screen. Adding different blocks, pigs, birds using their object.

27. ### Level1Container
    - Stores position, speed, angle, sizes of different blocks, pigs, birds to create Level 1.

28. ### Level2
    - Draw and render level 2 screen. Adding different blocks, pigs, birds using their object.

29. ### Level2Container
    - Stores position, speed, angle, sizes of different blocks, pigs, birds to create Level 2.

30. ### Level3
    - Draw and render level 3 screen. Adding different blocks, pigs, birds using their object.

31. ### Level3Container
    - Stores position, speed, angle, sizes of different blocks, pigs, birds to create Level 3.

32. ### PauseScreen
    - Draw and render in-game pause menu. Show following options - Resume, Restart, Save, Menu.

33. ### LevelCompleteScreen
    - Draw and render level complete screen when all pigs are dead.

34. ### LevelFailScreen
    - Draw and render level fail screen when all birds are used yet pigs are alive.


## Interfaces
- Used following interface to group object with common properties -
    
1. ### Bird
    - Implemented by `RedBird`, `BlackBird`, `BlueBird`, `YellowBird`.
   
2. ### Material
    - Implemented by `Glass`, `Wood`, `Stone`.



## Abstract Class

1. ### Level
    - Extended by `Level1`, `Level2`, `Level3`.

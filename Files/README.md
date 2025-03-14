

---

# Angry Bird

Angry Birds is a popular puzzle video game. In every level, the player uses a slingshot to launch the birds at the pigs
that are standing on buildings made of blocks. When a bird is launched, it can hit the buildings and make the blocks break. This can make the
pigs fall and disappear. The bird can also hit the pig itself to destroy it. The player has to destroy all the pigs in the
level to unlock another level.

## Table of Content

1. [Overview](#Overview)
2. [Classes](#classes)
3. [Interfaces](#classes)
4. [How to run?](#how-to-run)
5. [GitHub Link](#github-link---angry-birds)
6. [Learning and Troubleshooting Resources](#learning-and-troubleshooting-resources)
7. [Assets](#assets)
8. [Contributions]()

## Overview
- This project is a fun game "Angry Bird" developed using LibGDX framework.

## Classes

1. ### AngryBird :
   Main class initializing required screens such as MainMenuScreen, LoadingScreen, Level and rendering the LoadingScreen.
   
2. ### Handler
   Handle event listeners that can be applied on LibGDX Image.

3. ### BlackBird :
   Create and draw dynamic body for black bird on given batch and world. Also provide methods - `useSpecialAbility()` and `isClicked()`.

4. ### RedBird :
   Create and draw dynamic body for red bird on given batch and world. Also provide methods - `useSpecialAbility()` and `isClicked()`.

5. ### BlueBird :
   Create and draw dynamic body for blue bird on given batch and world. Also provide methods - `useSpecialAbility()` and `isClicked()`.

6. ### YellowBird :
   Create and draw dynamic body for yellow bird on given batch and world. Also provide methods - `useSpecialAbility()` and `isClicked()`.

7. ### Pig1
   Create and draw dynamic body for pig1 on given batch and world. Also provide method - `isClicked()` to check whether its clicked or not.

8. ### Pig2
   Create and draw dynamic body for pig2 on given batch and world. Also provide method - `isClicked()` to check whether its clicked or not.

9. ### Pig3
   Create and draw dynamic body for pig3 on given batch and world. Also provide method - `isClicked()` to check whether its clicked or not.

10. ### LoadingScreen
    Draw and render loading screen - background, progress bar and load to MainMenuScreen.

11. ### MainMenuScreen
    Draw and render main menu screen elements - start menu, load game, save game, setting, quit.

12. ### StartMenu
    Provide and draw start menu options. Also add respective buttons event handlers.

13. ### Load
    Draw load game screen. It shows saved games.

14. ### SaveProgress
    Draw Save progress screen and save the current progress of game.

15. ### Setting
    Draw setting screen and provide options - Music ON/OFF, Sound ON/OFF.

16. ### Profile
    Draw profile screen and show username. Also to change username.

17. ### Quit
    Draw quit screen and allow to exit the game.

18. ### Glass
    Create and draw dynamic body for glass blocks - Plank(vertical/horizontal) and box.

19. ### Stone
    Create and draw dynamic body for stone blocks - Plank(vertical/horizontal) and box.

20. ### Wood
    Create and draw dynamic body for wooden blocks - Plank(vertical/horizontal) and box.

21. ### TNT
    Create and draw dynamic body for TNT blocks.

22. ### SlingShot
    Create and draw slingshot.

23. ### Ground
    Create and draw static ground body for levels.

24. ### LevelMenuScreen
    Draw and render level menu screen which has initially 3 levels unlocked.

25. ### Level1
    Draw and render level 1 screen. Adding different blocks, pigs, birds using their object.

26. ### PauseScreen
    Draw and render in-game pause menu. Show following options - Resume, Restart, Save, Menu.

27. ### LevelCompleteScreen
    Draw and render level complete screen when all pigs are dead.

28. ### LevelFailScreen
    Draw and render level fail screen when all birds are used yet pigs are alive.


## Interfaces
- Used following interface to group object with common properties -
    
1. ### Bird
    - Implemented by `RedBird`, `BlackBird`, `BlueBird`, `YellowBird`.
   
2. ### Material
    - Implemented by `Glass`, `Wood`, `Stone`.
   
3. ### Level
    - Implemented by `Level1`.

## How to run?

- **Prerequisite** : Java (JDK) 8 or higher
- Steps:
  1. Clone the Repository/Download : `git clone https://github.com/pranaybansal19/Angry-Bird`
  2. Build the project : `gradlew build`
  3. Run the project : `gradlew lwjgl3:run`
    
    
## Github Link - [Angry Birds](https://github.com/pranaybansal19/Angry-Bird)

## Learning and Troubleshooting Resources

- [LibGDX Documentations](https://libgdx.com/dev/)
- [LibGdx Wiki](https://libgdx.com/wiki/)
- [Viewport Samples](https://github.com/raeleus/viewports-sample-project)
- [StackOverFlow](https://stackoverflow.com/questions/22295329/understanding-libgdx)
- [StackOverFlow](https://stackoverflow.com/questions/29044249/rendering-box2d-in-libgdx)
- [Youtube](https://www.youtube.com/playlist?list=PLZm85UZQLd2SXQzsF-a0-pPF6IWDDdrXt)


## Assets

- Our game includes various png assets for backgrounds, buttons and other elements.
- Used [Lucid](https://lucid.app/lucidchart/79b72177-ccb6-4f25-be26-365dfdecdd85/edit?viewport_loc=-3870%2C-8403%2C10618%2C4962%2C0_0&invitationId=inv_e58603ce-446b-4cb5-94d1-4fe7cb6f51d0) for creating `Use Case Diagram`.
- Used [yEd live](https://www.yworks.com/yed-live/) for creating `UML Class Diagram`.
- All these assets are stored in folder `assets`.
- Our designing and editing some images (buttons), we used [Figma](https://www.figma.com/design/IN7LS8BFa3v7RioKOIFJKJ/Untitled?node-id=0-1&t=JUDTK7d5C3WuQ8dL-1).
- Sources :
  - [Angrybirds Fandom](https://angrybirds.fandom.com/wiki/Angry_Birds_(game)/Gallery#Images)
  - [Google Image](https://images.app.goo.gl/Ac88ch2FmWGVgozp9)
  - [Pinterest](https://www.pinterest.com/deedeetidd/angry-birds-wallpaper/)
  - [Alpha Coders](https://alphacoders.com/angry-birds)

## Contribution

#### Members -
>- _Pranay Bansal 2023383_
>- _Ujjval Dargar 2023564_

Both members have made **_equal contributions_** to the project.

The responsibilities were divided as follows:

- Pranay Bansal
    
    - Assets : `Birds`, `Materials`(`Wood`, `Stone`, `Glass`), `SlingShot`, `Pig`, `Loading Screen Background`, `Loading Screen Progress Bar`, `Level Complete Screen`, `Level Fail Screen`, `Level Menu Screen`
    - Implemented classes related to drawing birds - `RedBird`, `BlueBird`, `BlackBird`, `YellowBird` classes
    - Implemented classes related to material - `Wood`, `Stone`, `Glass`, `TNT`, `Catapult` classes
    - Implemented `LoadingScreen` class
    - Implemented `LevelCompleteScreen` class
    - Implemented `LevelFailedScreen` class
    - Created `UML Class Diagram` class
    - Debugged the code to ensure it works correctly.
  

- Ujjval Dargar
    
    - Assets : `Icons`, `Profile Screen`, `Quit Screen`, `Save Progress Screen`, `Setting Screen`, `Start Menu Screen`, `Load Game Screen`, `Level 1 Screen`
    - Implemented classes related to drawing pigs - `Pig1`, `Pig2`, `Pig3` classes
    - Implemented classes related to Main Menu - `Setting`, `StartMenu`, `Load`, `SaveProgress`, `Quit`, `Profile` classes
    - Implemented `MainMenuScreen` class
    - Implemented `LevelMenuScreen` class
    - Implemented `Level1` class
    - Implemented `PauseScreen` class
    - Implemented `AngryBird` class
    - Implemented `Handler` class
    - Created `Use Case Diagram`
    - Documented the implementation ensuring clear documentation in `README`.

---

 






---

# Angry Bird
## A LibGDX-based Puzzle Game

[![Java Version](https://img.shields.io/badge/JDK-8%2B-blue.svg)](https://www.oracle.com/java/technologies/javase-downloads.html)
[![LibGDX](https://img.shields.io/badge/LibGDX-v1.10.0-green.svg)](https://libgdx.com/)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

---


## Table of Contents

1. [Overview](#overview)
2. [Bonus Features](#bonus-features)
3. [Design Patterns Used](#design-patterns-used)
4. [How to Run](#how-to-run)
5. [Learning and Troubleshooting Resources](#learning-and-troubleshooting-resources)
6. [Assets](#assets)
7. [Implementation Details](#implementation-details)

---



## Overview

Angry Bird is a modern reimagining of the classic puzzle game, developed using the LibGDX framework. In this game, players use a slingshot to launch birds at structures built by pigs. The objective is to demolish these structures and eliminate all pigs in a level to unlock the next challenge. Combining engaging gameplay with physics-based interactions and exciting bonus features, Angry Bird delivers a dynamic and immersive gaming experience.

Explore further details on the project's architecture and design by visiting our [Implementation Details](implementation.md) document.

---

## Bonus Features

1. **Bird Special Abilities:**
   - **Red Bird:** Temporarily increases in size for extra impact.
   - **Yellow Bird:** Gains a burst of speed to break through defenses.
   - **Black Bird:** Explodes on impact, causing area damage.
   - **Blue Bird:** Splits into three smaller birds mid-flight.
2. **Game Save System:**
   - Players can store and load multiple saved game states.

---

## Design Patterns Used

- **Singleton Pattern:**  
  Ensures that the `AngryBird` class has only one instance throughout the application.
- **Facade Pattern:**  
  Simplifies interactions by abstracting the complexity of managing different game levels (e.g., `Level1`, `Level2`, `Level3`).

---

## How to run?

- **Prerequisite** : Java (JDK) 8 or higher
- Steps:
  1. Clone the Repository/Download : `git clone https://github.com/pranaybansal19/Angry-Bird`
  2. Build the project : `gradlew build`
  3. To run tests : `gradlew test`
  4. Run the project : `gradlew lwjgl3:run`

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


---

 




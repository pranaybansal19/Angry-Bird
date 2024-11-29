package com.game.angrybird;

import static org.mockito.Mockito.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

public class AssetCheck {

    @Before
    public void setUp() {
        Gdx.files = mock(com.badlogic.gdx.Files.class);

        FileHandle mockFile = mock(FileHandle.class);
        when(mockFile.exists()).thenReturn(true);
        when(Gdx.files.internal(anyString())).thenReturn(mockFile);
    }

    @Test
    public void checkAllAssetsExist() {
        ArrayList<String> assets = new ArrayList<>();

        assets.add("Audio/backgroundMusic.ogg");
        assets.add("Audio/bomb.mp3");
        assets.add("Audio/button_click.mp3");
        assets.add("Audio/destroyed.mp3");
        assets.add("Audio/GlassDestroyed.mp3");
        assets.add("Audio/launch.mp3");
        assets.add("Audio/levelMusic.mp3");
        assets.add("Audio/music.mp3");
        assets.add("Audio/Slingshot.mp3");
        assets.add("Audio/StoneDestroyed.mp3");

        assets.add("Birds/BlackBird.png");
        assets.add("Birds/BlueBird.png");
        assets.add("Birds/explode.png");
        assets.add("Birds/RedBird.png");
        assets.add("Birds/YellowBird.png");

        assets.add("Glass/GlassBox.png");
        assets.add("Glass/GlassBoxDamaged.png");
        assets.add("Glass/GlassPlankHorizontal.png");
        assets.add("Glass/GlassPlankHorizontalDamaged.png");
        assets.add("Glass/GlassPlankVertical.png");
        assets.add("Glass/GlassPlankVerticalDamaged.png");

        assets.add("Icon/Icon128.png");
        assets.add("Icon/Icon16.png");
        assets.add("Icon/Icon32.png");
        assets.add("Icon/Icon64.png");

        assets.add("Level 1/Background.png");
        assets.add("Level 1/PauseBtn.png");
        assets.add("Level 2/Background.png");
        assets.add("Level 2/PauseBtn.png");
        assets.add("Level 3/Background.png");
        assets.add("Level 3/PauseBtn.png");

        assets.add("Level Complete Screen/Background.png");
        assets.add("Level Complete Screen/MenuBtn.png");
        assets.add("Level Complete Screen/NextLevelBtn.png");
        assets.add("Level Complete Screen/RestartBtn.png");
        assets.add("Level Complete Screen/Star(3).png");

        assets.add("Level Fail Screen/Background.png");
        assets.add("Level Fail Screen/Button.png");

        assets.add("Level Menu Screen/BackBtn.png");
        assets.add("Level Menu Screen/Background.png");
        assets.add("Level Menu Screen/Level1Btn.png");
        assets.add("Level Menu Screen/Level2Btn.png");
        assets.add("Level Menu Screen/Level3Btn.png");
        assets.add("Level Menu Screen/Level_Locked.png");

        assets.add("Load Screen/Background.png");
        assets.add("Load Screen/CloseBtn.png");

        assets.add("Loading Screen/Background.png");
        assets.add("Loading Screen/LoadingBarEmpty.png");
        assets.add("Loading Screen/LoadingBarFull.png");

        assets.add("Monospace.ttf");
        assets.add("uiskin.json");

        assets.add("Pause Screen/Background.png");
        assets.add("Pause Screen/MenuBtn.png");
        assets.add("Pause Screen/RestartBtn.png");
        assets.add("Pause Screen/ResumeBtn.png");
        assets.add("Pause Screen/SaveBtn.png");

        assets.add("Pigs/Pig1.png");
        assets.add("Pigs/Pig1Damaged.png");
        assets.add("Pigs/Pig2.png");
        assets.add("Pigs/Pig2Damaged.png");
        assets.add("Pigs/Pig3.png");
        assets.add("Pigs/Pig3Damaged.png");

        assets.add("Profile Screen/Background.png");
        assets.add("Profile Screen/closeBtn.png");
        assets.add("Profile Screen/SaveBtn.png");
        assets.add("Profile Screen/TextField.png");

        assets.add("Quit Screen/Background.png");
        assets.add("Quit Screen/CrossBtn.png");
        assets.add("Quit Screen/TickBtn.png");

        assets.add("Save Progress Screen/Background.png");
        assets.add("Save Progress Screen/SaveBtn.png");
        assets.add("Save Progress Screen/SavingBar.png");

        assets.add("Setting Screen/Background.png");
        assets.add("Setting Screen/ChangeProfileBtn.png");
        assets.add("Setting Screen/CloseBtn.png");
        assets.add("Setting Screen/MusicOFF.png");
        assets.add("Setting Screen/MusicON.png");
        assets.add("Setting Screen/SoundOFF.png");
        assets.add("Setting Screen/SoundON.png");

        assets.add("Slingshot/Slingshot.png");

        assets.add("Start Menu/Background.png");
        assets.add("Start Menu/LoadBtn.png");
        assets.add("Start Menu/PlayBtn.png");
        assets.add("Start Menu/QuitBtn.png");
        assets.add("Start Menu/SaveBtn.png");
        assets.add("Start Menu/SettingBtn.png");

        assets.add("Stone/StoneBox.png");
        assets.add("Stone/StoneBoxDamaged.png");
        assets.add("Stone/StonePlankHorizontal.png");
        assets.add("Stone/StonePlankHorizontalDamaged.png");
        assets.add("Stone/StonePlankVertical.png");
        assets.add("Stone/StonePlankVerticalDamaged.png");

        assets.add("Wood/WoodBox.png");
        assets.add("Wood/WoodBoxDamaged.png");
        assets.add("Wood/WoodPlankHorizontal.png");
        assets.add("Wood/WoodPlankHorizontalDamaged.png");
        assets.add("Wood/WoodPlankVertical.png");
        assets.add("Wood/woodPlankVerticalDamaged.png");

        for (String s : assets) {
            FileHandle file = Gdx.files.internal(s);
            assertTrue(file.exists());
        }

    }

}


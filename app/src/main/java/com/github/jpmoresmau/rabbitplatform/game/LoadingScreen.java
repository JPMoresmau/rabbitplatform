package com.github.jpmoresmau.rabbitplatform.game;

import android.graphics.Typeface;

import com.github.jpmoresmau.rabbitplatform.framework.Game;
import com.github.jpmoresmau.rabbitplatform.framework.Graphics;
import com.github.jpmoresmau.rabbitplatform.framework.GraphicsUtils;
import com.github.jpmoresmau.rabbitplatform.framework.Image;
import com.github.jpmoresmau.rabbitplatform.framework.ImageFormat;
import com.github.jpmoresmau.rabbitplatform.framework.Screen;
import com.github.jpmoresmau.rabbitplatform.framework.Sound;
import com.github.jpmoresmau.rabbitplatform.framework.android.AndroidGraphics;

import java.lang.reflect.Field;

/**
 * Created by jpmoresmau on 2/1/16.
 */
public class LoadingScreen extends Screen {
    private Image loading; // 185*32

    public LoadingScreen(Game game) {
        super(game);
        Graphics g = getGame().getGraphics();
        loading = g.newImage("loading.png", ImageFormat.RGB565);

    }

    @Override
    public void update(float deltaTime) {
        Graphics g = getGame().getGraphics();
        try {

            for (Field f:RAssets.class.getDeclaredFields()){
                String name=f.getName();
                if (f.getType().equals(Image.class)){
                    f.set(null,g.newImage(name+".png",ImageFormat.RGB565));
                } else if (f.getType().equals(Sound.class)){
                    f.set(null,getGame().getAudio().createSound(name+".ogg"));
                }
            }
        } catch (Exception e){
            throw new RuntimeException(e);
        }

        RAssets.block_font= g.loadTypeface("fonts/kenpixel_blocks.ttf");
        RAssets.initPaints();

        getGame().setScreen(new GameScreen(getGame()));


    }


    @Override
    public void paint(float deltaTime) {
        Graphics g = getGame().getGraphics();

        g.clearScreen(RAssets.GREEN);
        GraphicsUtils.drawCenter(g,loading);
    }


    @Override
    public void pause() {


    }


    @Override
    public void resume() {


    }


    @Override
    public void dispose() {


    }


    @Override
    public void backButton() {


    }
}


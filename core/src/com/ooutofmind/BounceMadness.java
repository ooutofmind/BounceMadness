package com.ooutofmind;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.ooutofmind.gfx.BB;
import com.ooutofmind.level.EntityListPool;
import com.ooutofmind.screen.AbsScreen;
import com.ooutofmind.screen.GameScreen;

public class BounceMadness extends Game {
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private OrthographicCamera screenCamera;
    private OrthographicCamera gameCamera;

    private Viewport viewport;
    private FrameBuffer backBuffer;

    @Override
    public void create() {
        batch = Reg.i.add(new SpriteBatch());
        backBuffer = Reg.i.add(new FrameBuffer(Pixmap.Format.RGBA8888, Const.WIDTH, Const.HEIGHT, false));
        backBuffer.getColorBufferTexture().setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        screenCamera = new OrthographicCamera();
        screenCamera.setToOrtho(true, Const.WIDTH, Const.HEIGHT);
        gameCamera = new OrthographicCamera();
        gameCamera.setToOrtho(true, Const.WIDTH, Const.HEIGHT);
        viewport = new ExtendViewport(Const.WIDTH, Const.HEIGHT, screenCamera);


        shapeRenderer = Reg.i.add(new ShapeRenderer());

        setScreen(new GameScreen());
    }

    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void render() {
        EntityListPool.reset();
        BB.reset();
        ///
        ((AbsScreen) screen).tick();
        ///

        backBuffer.begin();
        {
            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            ((AbsScreen) screen).render(shapeRenderer, gameCamera);
        }
        backBuffer.end();


        screenCamera.update();
        batch.setProjectionMatrix(screenCamera.combined);
        batch.begin();

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.draw(backBuffer.getColorBufferTexture(), 0, 0);
        batch.end();
    }

    @Override
    public void dispose() {
        Reg.i.dispose();
    }
}

package com.darkoverlordofdata.entitas
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.InputProcessor
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.*
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.utils.viewport.FillViewport

class GameScene() : Screen, InputProcessor {

    var toWorldX = 0f           /* mouse to world coordinates */
    var toWorldY = 0f           /* mouse to world coordinates */
    var height = 0f             /* window height */
    var width = 0f              /* window width */
    var height2 = 0f            /* original window height */
    var width2 = 0f             /* original window width */
    var pixelFactor = 0f        /* 1 = desktop, 2 = retina display, ...*/

    lateinit var mainBatch: SpriteBatch
    lateinit var camera: OrthographicCamera
    lateinit var viewport: FillViewport
    lateinit var game :Systems
    lateinit var textureAtlas : TextureAtlas
    lateinit var background: Sprite
    lateinit var fontTexture: Texture
    lateinit var fontRegion: TextureRegion
    lateinit var font: BitmapFont

    init {
        width = Gdx.graphics.width.toFloat()
        height = Gdx.graphics.height.toFloat()
        toWorldX = 320f/width
        toWorldY = 480f/height
        width2 = width*toWorldX
        height2 = height*toWorldY
        pixelFactor = if (Gdx.graphics.density > 1f) 2f else 1f

        textureAtlas = TextureAtlas("level1.atlas")
        mainBatch = SpriteBatch()
        camera = OrthographicCamera(width/pixelFactor, height/pixelFactor)
        viewport = FillViewport(width/pixelFactor, height/pixelFactor, camera)
        viewport.apply()
        camera.position.set(width/(pixelFactor*2f), height/(pixelFactor*2f), 0f)
        camera.update()
        background = textureAtlas.createSprite("BackdropBlackLittleSparkBlack")
        fontTexture = Texture(Gdx.files.internal("fonts/normal_0.png"))
        fontTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.MipMapLinearLinear)
        fontRegion = TextureRegion(fontTexture)
        font = BitmapFont(Gdx.files.internal("fonts/normal.fnt"), fontRegion, false)
        font.setUseIntegerPositions(false)
        Gdx.input.inputProcessor = this
        game = Systems(textureAtlas, font, width.toInt(), height.toInt(), width2.toInt(), height2.toInt())

    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        //Gdx.gl.glLineWidth(20f)
        mainBatch.setProjectionMatrix(camera.combined)
        mainBatch.begin()
        mainBatch.draw(background, 0f, 0f, width, height)
        game.render(mainBatch)
        mainBatch.end()
        game.update(delta)
    }

    inline fun moveTo(x: Int, y:Int) {
        game.mouseX = x.toFloat()/pixelFactor
        game.mouseY = (height - y.toFloat())/pixelFactor
    }
    override fun dispose() {}
    override fun show() {}
    override fun hide() {}
    override fun pause() {}
    override fun resize(width: Int, height:Int) {
        viewport.update(width,height)
        camera.position.set(camera.viewportWidth/2f, camera.viewportHeight/2f, 0f)
    }
    override fun resume() {}

    override fun touchDown(x: Int, y: Int, pointer: Int, button: Int) : Boolean {
        game.shoot = true
        moveTo(x, y)
        return false
    }
    override fun touchUp(x: Int, y: Int, pointer: Int, button: Int) : Boolean {
        game.shoot = false
        return true
    }
    override fun touchDragged(x: Int, y: Int, pointer: Int) : Boolean {
        moveTo(x, y)
        return false
    }
    override fun mouseMoved(x: Int, y: Int) : Boolean {
        moveTo(x, y)
        return false
    }
    override fun keyDown(keycode: Int) : Boolean {
        if (Input.Keys.Z == keycode) game.shoot = true
        return true
    }
    override fun keyUp(keycode: Int) : Boolean  {
        if (Input.Keys.Z == keycode) game.shoot = false
        return true
    }
    override fun keyTyped(character: Char) : Boolean {
        return false
    }
    override fun scrolled(amount: Int) : Boolean {
        return false
    }
}

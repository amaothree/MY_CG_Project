import GraphicsObjects.Utils;
import objects3D.Human;
import objects3D.Sky;
import objects3D.StandMan;
import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import java.io.IOException;
import java.nio.FloatBuffer;

public class MainWindow{

    //texture var
    private Texture texture,box,head,clo,sun,moon,road,sky,bb;
    //texture windows
    private static final int WindowsWidth = 1200;
    private static final int WindowsHeight = 800;
    //fps
    private int fps;
    private long lastFPS;
    //time
    private long StartTime;
    //frame
    private long lastFrame;
    //delta
    private long myDelta = 0;
    //offset
    private float offset_x = 0;
    private float offset_z = 0;
    private float offset_y = 0;
    //Flag Var
    private boolean WASD = false;

    //colours
    private static float grey[] = { 0.5f, 0.5f, 0.5f, 1.0f };
    private static float spot[] = { 0.1f, 0.1f, 0.1f, 0.5f };

    //Main function
    public static void main(String[]args) throws IOException {
        MainWindow lwjgl = new MainWindow();
        lwjgl.start();
    }

    private void start() throws IOException{

        StartTime = getTime();
        try {
            Display.setDisplayMode(new DisplayMode(WindowsWidth,WindowsHeight));
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
            System.exit(0);
        }

        initGL();
        getDelta();
        lastFPS = getTime();

        while (!Display.isCloseRequested()) {
            int delta = getDelta();
            update(delta);
            renderGL();
            Display.update();
            Display.sync(120);
        }

        Display.destroy();

    }

    private void update(int delta){
        WASD = false;

        if (Keyboard.isKeyDown(Keyboard.KEY_A)){
            offset_x +=0.2;
            WASD = true;
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_D)){
            offset_x -=0.2;
            WASD = true;
        }




        updateFPS();
    }

    private void updateFPS() {
        if (getTime() - lastFPS > 1000) {
            Display.setTitle("FPS: " + fps);
            fps = 0;
            lastFPS += 1000;
        }
        fps++;
    }

    private void initGL() {
        /** This method should use to set the light **/
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        changeOrth();

        FloatBuffer lightPos = BufferUtils.createFloatBuffer(4);
        lightPos.put(10000f).put(1000f).put(1000).put(0).flip();

        FloatBuffer lightPos2 = BufferUtils.createFloatBuffer(4);
        lightPos2.put(0f).put(1000f).put(0).put(-1000f).flip();

        FloatBuffer lightPos3 = BufferUtils.createFloatBuffer(4);
        lightPos3.put(-10000f).put(1000f).put(1000).put(0).flip();

        FloatBuffer lightPos4 = BufferUtils.createFloatBuffer(4);
        lightPos4.put(1000f).put(1000f).put(1000f).put(0).flip();

        GL11.glLight(GL11.GL_LIGHT0, GL11.GL_POSITION, lightPos); // specify the
        // position
        // of the
        // light
        GL11.glEnable(GL11.GL_LIGHT0); // switch light #0 on // I've setup specific materials so in real light it will look abit strange

        GL11.glLight(GL11.GL_LIGHT1, GL11.GL_POSITION, lightPos); // specify the
        // position
        // of the
        // light
        GL11.glEnable(GL11.GL_LIGHT1); // switch light #0 on
        GL11.glLight(GL11.GL_LIGHT1, GL11.GL_DIFFUSE, Utils.ConvertForGL(spot));

        GL11.glLight(GL11.GL_LIGHT2, GL11.GL_POSITION, lightPos3); // specify
        // the
        // position
        // of the
        // light
        GL11.glEnable(GL11.GL_LIGHT2); // switch light #0 on
        GL11.glLight(GL11.GL_LIGHT2, GL11.GL_DIFFUSE, Utils.ConvertForGL(grey));

        GL11.glLight(GL11.GL_LIGHT3, GL11.GL_POSITION, lightPos4); // specify
        // the
        // position
        // of the
        // light
        GL11.glEnable(GL11.GL_LIGHT3); // switch light #0 on
        GL11.glLight(GL11.GL_LIGHT3, GL11.GL_DIFFUSE, Utils.ConvertForGL(grey));

        GL11.glEnable(GL11.GL_LIGHTING); // switch lighting on
        GL11.glEnable(GL11.GL_DEPTH_TEST); // make sure depth buffer is switched
        // on
        GL11.glEnable(GL11.GL_NORMALIZE); // normalize normal vectors for safety
        GL11.glEnable(GL11.GL_COLOR_MATERIAL);

        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);


        try{
            init();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private long getTime() {
        return (Sys.getTime() * 1000) / Sys.getTimerResolution();
    }

    private int getDelta() {
        long time = getTime();
        int delta = (int) (time - lastFrame);
        lastFrame = time;

        return delta;

    }

    private void changeOrth() {
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        FloatBuffer CurrentMatrix = BufferUtils.createFloatBuffer(16);
        GL11.glGetFloat(GL11.GL_MODELVIEW_MATRIX,CurrentMatrix);
        GL11.glLoadMatrix(CurrentMatrix);
    }

    private void init() throws IOException {

        //Get texture from files
        texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/earthspace.png"));
//	    box = TextureLoader.getTexture("JPG",ResourceLoader.getResourceAsStream("res/box.jpg"));
        sun = TextureLoader.getTexture("PNG",ResourceLoader.getResourceAsStream("res/sol.png"));
        moon = TextureLoader.getTexture("PNG",ResourceLoader.getResourceAsStream("res/lua.png"));
        head = TextureLoader.getTexture("JPG", ResourceLoader.getResourceAsStream("res/texture-pige.jpg"));
        clo = TextureLoader.getTexture("JPG", ResourceLoader.getResourceAsStream("res/clo.jpg"));
        road = TextureLoader.getTexture("PNG",ResourceLoader.getResourceAsStream("res/road.png"));
        sky = TextureLoader.getTexture("PNG",ResourceLoader.getResourceAsStream("res/sky.png"));
        bb = TextureLoader.getTexture("PNG",ResourceLoader.getResourceAsStream("res/2018a.png"));
        System.out.println("Texture loaded okay ");
    }

    private void renderGL() throws IOException{
        /** This method should put the object **/
        changeOrth();

        GL11.glLoadIdentity();
        GLU.gluPerspective(60f, 1.3f, 0.1f, 10000);
        GLU.gluLookAt(0, 3.5f, -10, 0, 3, 0, 0, 1, 0);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glClearColor(1, 1, 1, 0);

        myDelta = getTime() - StartTime;
        float delta = ((float) myDelta) / 10000;
        float theta = (float) (delta *2 * Math.PI);
        float thetaDeg = delta * 360;
        float posn_x = (float) Math.cos(theta);
        float posn_y = (float) Math.sin(theta);

        if(!WASD) {
            GL11.glPushMatrix();
            GL11.glTranslatef(offset_x, -0.0f, 0.0f);
            GL11.glRotatef(0, 0, 1f, 0);
            StandMan human = new StandMan(head, clo);
            human.DrawHuman();
            GL11.glPopMatrix();
        } else {
            GL11.glPushMatrix();
            GL11.glTranslatef(offset_x, -0.0f, 0.0f);
            GL11.glRotatef(0, 0, 1f, 0);
            Human human = new Human(head, clo);
            human.DrawHuman(delta*30);
            GL11.glPopMatrix();
        }

    }

}
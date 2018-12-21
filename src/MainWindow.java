import GraphicsObjects.Utils;
import objects3D.Ground;
import objects3D.Human;
import objects3D.Sky;
import objects3D.StandMan;
import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import java.io.IOException;
import java.nio.FloatBuffer;

public class MainWindow{

    //texture var
    private Texture earth,box,head,clo,sun,moon,road,sky,bb;
    //texture windows
    private static final int WindowsWidth = 1200;
    private static final int WindowsHeight = 800;
    //World Size
    private static final int WorldSize = 100;
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
    //Eye Position
    private float eye_x;
    private float eye_y;
    private float eye_z;
    //Human Position
    private float man_x=0;
    private float man_y=0;
    private float man_z=0;
    //Man Face angle
    private double man_angle = 0;
    //Eye Ball Radio
    private float eye_r = 10;
    //Flag Var
    private boolean WASD = false;
    private boolean JUMP = false;
    //Jump height
    private float jump_flag = 0;

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
        //init_FALG
        WASD = false;
        int wheel = Mouse.getDWheel();

        //Wheel Listener
        if (wheel>0)
            eye_r++;

        if (wheel<0&&eye_r>3)
            eye_r--;

        if (Mouse.isButtonDown(2)) {
            eye_r=10;
        }

        //MOUSE POSITION
        int MOUSE_X = Mouse.getX()-WindowsWidth/2;
        int MOUSE_Y = Mouse.getY()-WindowsHeight/2;
        updatePosition(MOUSE_X,MOUSE_Y);

        //Keyboard Listener
        if (Keyboard.isKeyDown(Keyboard.KEY_W)){
            man_x += offset_x;
            man_z += offset_z;
            WASD = true;
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_A)){
            man_x += offset_z;
            man_z -= offset_x;
            WASD = true;
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_S)){
            man_x -= offset_x;
            man_z -= offset_z;
            WASD = true;
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_D)){
            man_x -= offset_z;
            man_z += offset_x;
            WASD = true;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_SPACE))
            JUMP = true;

        updateFPS();
        updateHeight();

        if (man_x>WorldSize)
            man_x=WorldSize;
        if (man_x<-WorldSize)
            man_x=-WorldSize;
        if (man_z>WorldSize)
            man_z=WorldSize;
        if (man_z<-WorldSize)
            man_z=-WorldSize;

        //LOOK_AT
        changeOrth();
        GL11.glLoadIdentity();
        GLU.gluPerspective(60f, 1.3f, 0.1f, 10000);
        GLU.gluLookAt(eye_x+man_x, eye_y, eye_z+man_z, man_x, man_y+2, man_z, 0, 1, 0);
    }

    private void updateFPS() {
        if (getTime() - lastFPS > 1000) {
            Display.setTitle("FPS: " + fps);
            fps = 0;
            lastFPS += 1000;
        }
        fps++;
    }

    private void updatePosition(float x, float y){

        double alpha = 2*Math.PI*x/WindowsWidth;
        double beta = Math.PI*y/WindowsHeight;

        man_angle = (alpha+Math.PI/2);
        offset_x = (float) (Math.cos(man_angle)*0.15);
        offset_z = (float) (Math.sin(man_angle)*0.15);

//        System.out.println(man_angle+" "+offset_x+" "+offset_z);

        eye_y = (float) (-eye_r*Math.sin(beta)+eye_r);
        eye_x = (float) (eye_r*Math.cos(beta)*Math.sin(alpha));
        eye_z = (float) (-eye_r*Math.cos(alpha));

    }

    private void updateHeight(){

        if(JUMP==false)
            return;

        if(jump_flag>Math.PI){
            JUMP=false;
            man_y=0;
            jump_flag=0;
            return;
        }

        man_y= (float) Math.sin(jump_flag)*5;
        jump_flag+=0.05;
        return;
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
        earth = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/earthspace.png"));
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

        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glClearColor(1, 1, 1, 0);

        myDelta = getTime() - StartTime;
        float delta = ((float) myDelta) / 10000;
        float theta = (float) (delta *2 * Math.PI);
        float thetaDeg = delta * 360;
        float posn_x = (float) Math.cos(theta);
        float posn_y = (float) Math.sin(theta);

        GL11.glPushMatrix();
        Ground ground = new Ground();
        Color.white.bind();
        earth.bind();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
        ground.DrawGround(1000);
        GL11.glPopMatrix();

        if(!WASD) {
            GL11.glPushMatrix();
            GL11.glTranslatef(man_x, man_y+1.1f, man_z);
            GL11.glRotatef(90-(float) (man_angle*180/Math.PI), 0, 1, 0);
            StandMan human = new StandMan(head, clo);
            human.DrawHuman();
            GL11.glPopMatrix();
        } else {
            GL11.glPushMatrix();
            GL11.glTranslatef(man_x, man_y+1.1f, man_z);
            GL11.glRotatef(90-(float) (man_angle*180/Math.PI), 0, 1, 0);
            Human human = new Human(head, clo);
            human.DrawHuman(delta*10);
            GL11.glPopMatrix();
        }

        //Sky
        GL11.glPushMatrix();
        Sky skyo = new Sky();
        sky.bind();
        Color.white.bind();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glTexParameteri( GL11.GL_TEXTURE_2D,  GL11.GL_TEXTURE_MAG_FILTER,  GL11.GL_NEAREST);
        skyo.DrawSky(WorldSize*2);
        GL11.glRotatef(180,0,1,0);
        skyo.DrawSky(WorldSize*2);
        GL11.glPopMatrix();
        GL11.glDisable(GL11.GL_TEXTURE_2D);

    }

}
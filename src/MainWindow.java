import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
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
        GLU.gluLookAt(10, 5, 10, 0, 0, 0, 0, 1, 0);
        {
            myDelta = getTime() - StartTime;
            float delta = ((float) myDelta) / 10000;
            float theta = (float) (delta *2 * Math.PI);
            float thetaDeg = delta * 360;
            float posn_x = (float) Math.cos(theta);
            float posn_y = (float) Math.sin(theta);

        }

    }

}
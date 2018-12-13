import GraphicsObjects.Utils;
import objects3D.*;
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


public class OldMainWindow {

	private static final int WindowsWidth = 1200;
	private static final int WindowsHeight = 800;


	private  boolean MouseOnepressed = true;
	private boolean  dragMode=false;
	/** position of pointer */
	private float x = 0, y = 0;
	/** angle of rotation */
	float rotation = 0;
	/** time at last frame */
	private long lastFrame;
	/** frames per second */
	private int fps;
	/** last fps time */
	private long lastFPS;

	private long  myDelta =0 ; //to use for animation
	float Alpha =0 ; //to use for animation
	private long StartTime; // beginAnimiation

//	private Arcball MyArcball= new Arcball();

	boolean DRAWGRID =false;
	private boolean waitForKeyrelease= true;
	/** Mouse movement */
	private int LastMouseX = -1 ;
	private int LastMouseY= -1;

	 float pullX = 0.0f; // arc ball  X cord.
	 float pullY = 0.0f; // arc ball  Y cord.


	private int OrthoNumber = 1200; // using this for screen size, making a window of 1200 x 800 so aspect ratio 3:2 // do not change this for assignment 3 but you can change everything for your project

	// basic colours
	static float black[] = { 0.0f, 0.0f, 0.0f, 1.0f };
	static float white[] = { 1.0f, 1.0f, 1.0f, 1.0f };

	private static float grey[] = { 0.5f, 0.5f, 0.5f, 1.0f };
	private static float spot[] = { 0.1f, 0.1f, 0.1f, 0.5f };

	// primary colours
	static float red[] = { 1.0f, 0.0f, 0.0f, 1.0f };
	static float green[] = { 0.0f, 1.0f, 0.0f, 1.0f };
	static float blue[] = { 0.0f, 0.0f, 1.0f, 1.0f };

	// secondary colours
	static float yellow[] = { 1.0f, 1.0f, 0.0f, 1.0f };
	static float magenta[] = { 1.0f, 0.0f, 1.0f, 1.0f };
	static float cyan[] = { 0.0f, 1.0f, 1.0f, 1.0f };

	// other colours
	static float orange[] = { 1.0f, 0.5f, 0.0f, 1.0f, 1.0f };
	static float brown[] = { 0.5f, 0.25f, 0.0f, 1.0f, 1.0f };
	static float dkgreen[] = { 0.0f, 0.5f, 0.0f, 1.0f, 1.0f };
	static float pink[] = { 1.0f, 0.6f, 0.6f, 1.0f, 1.0f };

	// static GLfloat light_position[] = {0.0, 100.0, 100.0, 0.0};

	//support method to aid in converting a java float array into a Floatbuffer which is faster for the opengl layer to process


	private void start() throws IOException {

		StartTime = getTime();
		try {
			Display.setDisplayMode(new DisplayMode(WindowsWidth, WindowsHeight));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}

		initGL(); // init OpenGL
		getDelta(); // call once before loop to initialise lastFrame
		lastFPS = getTime(); // call before loop to initialise fps timer

		while (!Display.isCloseRequested()) {
			int delta = getDelta();
			update(delta);
			renderGL();
			Display.update();
			Display.sync(120); // cap fps to 120fps
		}

		Display.destroy();
	}

	private void update(int delta) {
		// rotate quad
		//rotation += 0.01f * delta;


		  int MouseX= Mouse.getX();
		  int MouseY= Mouse.getY();
		  int WheelPostion = Mouse.getDWheel();


		  boolean  MouseButonPressed= Mouse.isButtonDown(0);



		  if(MouseButonPressed && !MouseOnepressed )
		  {
			  MouseOnepressed =true;
			//  System.out.println("Mouse drag mode");
//			  MyArcball.startBall( MouseX, MouseY, WindowsWidth, WindowsHeight);
			  dragMode=true;


		  }else if( !MouseButonPressed)
		  {
				// System.out.println("Mouse drag mode end ");
			  MouseOnepressed =false;
			  dragMode=false;
		  }

		  if(dragMode)
		  {
			  //MyArcball.updateBall( MouseX  , MouseY  , WindowsWidth, WindowsHeight);
		  }

		  if(WheelPostion>0)
		  {
			  OrthoNumber += 10;

		  }

		  if(WheelPostion<0)
		  {
			  OrthoNumber -= 10;
			  if( OrthoNumber<610)
			  {
				  OrthoNumber=610;
			  }

			//  System.out.println("Orth nubmer = " +  OrthoNumber);

		  }

		  /** rest key is R*/
		  if (Keyboard.isKeyDown(Keyboard.KEY_R))
//			  MyArcball.reset();

		  /* bad animation can be turn on or off using A key)*/

		if (Keyboard.isKeyDown(Keyboard.KEY_A))

		if (Keyboard.isKeyDown(Keyboard.KEY_D))

		if (Keyboard.isKeyDown(Keyboard.KEY_W))

		if (Keyboard.isKeyDown(Keyboard.KEY_S))

		if (Keyboard.isKeyDown(Keyboard.KEY_Q))

		if (Keyboard.isKeyDown(Keyboard.KEY_E))


		 if(waitForKeyrelease) // check done to see if key is released
		 {
			if (Keyboard.isKeyDown(Keyboard.KEY_G))
			{

				Keyboard.next();
				if(Keyboard.isKeyDown(Keyboard.KEY_G))
				{
					waitForKeyrelease=true;
				}else
				{
					waitForKeyrelease=false;

				}
			}
		 }

		 /** to check if key is released */
		 if(Keyboard.isKeyDown(Keyboard.KEY_G)==false)
			{
				waitForKeyrelease=true;
			}else
			{
				waitForKeyrelease=false;

			}

		// keep quad on the screen
		if (x < 0)
			x = 0;
		if (x > WindowsWidth)
			x = WindowsWidth;
		if (y < 0)
			y = 0;
		if (y > WindowsHeight)
			y = WindowsHeight;

		updateFPS(); // update FPS Counter

		LastMouseX= MouseX;
		LastMouseY= MouseY ;
	}


	/**
	 * Calculate how many milliseconds have passed since last frame.
	 *
	 * @return milliseconds passed since last frame
	 */
	private int getDelta() {
		long time = getTime();
		int delta = (int) (time - lastFrame);
		lastFrame = time;

		return delta;
	}

	/**
	 * Get the accurate system time
	 *
	 * @return The system time in milliseconds
	 */
	private long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

	/**
	 * Calculate the FPS and set it in the title bar
	 */
	private void updateFPS() {
		if (getTime() - lastFPS > 1000) {
			Display.setTitle("FPS: " + fps);
			fps = 0;
			lastFPS += 1000;
		}
		fps++;
	}

	private void initGL() {
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		changeOrth();
//		MyArcball.startBall(0, 0, WindowsWidth,WindowsHeight);
//		GL11.glMatrixMode(GL11.GL_MODELVIEW);
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
        try {
			init();
		} catch (IOException e) {
			e.printStackTrace();
		} //load in texture


	}



	private void changeOrth() {

		 GL11.glMatrixMode(GL11.GL_PROJECTION);
		 GL11.glLoadIdentity();
//		  GL11.glOrtho(WindowsWidth -  OrthoNumber , OrthoNumber, (WindowsHeight - (OrthoNumber  * 0.66f)) , (OrthoNumber * 0.66f), 100000, -100000);
//		 	GL11.glMatrixMode(GL11.GL_MODELVIEW);

		 	FloatBuffer CurrentMatrix = BufferUtils.createFloatBuffer(16);
		 	GL11.glGetFloat(GL11.GL_MODELVIEW_MATRIX, CurrentMatrix);

		 //	if(MouseOnepressed)
		 //	{

//		 	MyArcball.getMatrix(CurrentMatrix);
		 //	}

		    GL11.glLoadMatrix(CurrentMatrix);

	}

	/*
	 * You can edit this method to add in your own objects /  remember to load in textures in the INIT method as they take time to load
	 *
	 */
	private void renderGL() throws IOException {
		changeOrth();

		GL11.glLoadIdentity();
		GLU.gluPerspective(60f, 1.3f, 0.1f, 10000);
		GLU.gluLookAt(10, 5, 10, 0, 0, 0, 0, 1, 0);

		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
//		GL11.glColor3f(0.5f, 0.5f, 1.0f);

		myDelta = getTime() - StartTime;
		float delta = ((float) myDelta) / 10000;

		// code to aid in animation
		float theta = (float) (delta * 2 * Math.PI);
//		System.out.println(delta);
		float thetaDeg = delta * 360;
		float posn_x = (float) Math.cos(theta); // same as your circle code in your notes
		float posn_y = (float) Math.sin(theta);
		final float new_y = (float) (posn_y*Math.cos(45));
		final float new_x = (float) Math.cos(theta*15);



		//Road
		GL11.glPushMatrix();
		GL11.glTranslatef(-posn_x/3,-0.0f,0.0f);
		Road roadq = new Road();
//		Color.white.bind();
		road.bind();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glTexParameteri( GL11.GL_TEXTURE_2D,  GL11.GL_TEXTURE_MAG_FILTER,  GL11.GL_NEAREST);
		roadq.DrawRoad(10);
		GL11.glPopMatrix();
		GL11.glDisable(GL11.GL_TEXTURE_2D);

		//Ground
		GL11.glPushMatrix();

		Ground ground = new Ground();
		ground.DrawGround(10);
		GL11.glPopMatrix();

		//Sky
		GL11.glPushMatrix();
		Sky skyo = new Sky();
		sky.bind();
		Color.white.bind();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glTexParameteri( GL11.GL_TEXTURE_2D,  GL11.GL_TEXTURE_MAG_FILTER,  GL11.GL_NEAREST);
		skyo.DrawSky(10);
		GL11.glPopMatrix();
		GL11.glDisable(GL11.GL_TEXTURE_2D);

		//River
//		GL11.glTranslatef(3.0f,-1.0f,1.0f*-posn_y*2);
		River river = new River();
		int w,l;
		for (w=1;w<25;w++) {
			for (l = 1; l < 300; l++) {
				GL11.glPushMatrix();
				GL11.glTranslatef(1.0f+0.2f*w,-1.9f,-10.0f+0.2f*l+Math.abs(new_x/2));
				river.DrawRiver(0.2f);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				GL11.glTranslatef(-1.0f-0.2f*w,-1.9f,-10.0f+0.2f*l+Math.abs(new_x/2));
				river.DrawRiver(0.2f);
				GL11.glPopMatrix();
			}
		}


		//Human
		GL11.glPushMatrix();
		GL11.glTranslatef(posn_x/3,-0.0f,-posn_x*2);
		Human human = new Human(head, clo);
		human.DrawHuman(delta*10);
		GL11.glPopMatrix();

		//plane
		GL11.glPushMatrix();
		GL11.glTranslatef(posn_x,posn_y,-5-posn_x*2);
		Plane plane = new Plane();
		plane.DrawPlane();
		GL11.glPopMatrix();

		//baby
		GL11.glPushMatrix();
		GL11.glTranslatef(posn_x,posn_y,-5-posn_x*2);
		Baby baby = new Baby();
		bb.bind();
		Color.white.bind();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glTexParameteri( GL11.GL_TEXTURE_2D,  GL11.GL_TEXTURE_MAG_FILTER,  GL11.GL_NEAREST);
		baby.DrawBaby();
		GL11.glPopMatrix();

		//Sun
		GL11.glPushMatrix();
		GL11.glTranslatef(-15.0f*new_y,-6.0f*posn_x,15.0f*new_y);
		TexSphere Sun = new TexSphere();
//		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_CLAMP);
		Color.yellow.bind();
		sun.bind();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glTexParameteri( GL11.GL_TEXTURE_2D,  GL11.GL_TEXTURE_MAG_FILTER,  GL11.GL_NEAREST);
		if (posn_x<0.2f)
		Sun.DrawTexSphere(0.5f,100,100,sun);
		GL11.glPopMatrix();

		//Moon
		GL11.glPushMatrix();
		GL11.glTranslatef(15.0f*new_y,6.0f*posn_x,-15.0f*new_y);
		TexSphere Moon = new TexSphere();
//		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_CLAMP);
		Color.white.bind();
		moon.bind();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glTexParameteri( GL11.GL_TEXTURE_2D,  GL11.GL_TEXTURE_MAG_FILTER,  GL11.GL_NEAREST);
		if (posn_x>-0.2f)
		Moon.DrawTexSphere(0.5f,100,100,moon);
		GL11.glPopMatrix();




		GL11.glDisable(GL11.GL_TEXTURE_2D);
	}

	public static void main(String[] argv) throws IOException {
		OldMainWindow hello = new OldMainWindow();
		hello.start();
	}

	private Texture texture,box,head,clo,sun,moon,road,sky,bb;

	/*
	 * Any additional textures for your assignment should be written in here.
	 * Make a new texture variable for each one so they can be loaded in at the beginning
	 */
	private void init() throws IOException {

		//Get texture from files
	  texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/earthspace.png"));
//	  box = TextureLoader.getTexture("JPG",ResourceLoader.getResourceAsStream("res/box.jpg"));
	  sun = TextureLoader.getTexture("PNG",ResourceLoader.getResourceAsStream("res/sol.png"));
	  moon = TextureLoader.getTexture("PNG",ResourceLoader.getResourceAsStream("res/lua.png"));
	  head = TextureLoader.getTexture("JPG", ResourceLoader.getResourceAsStream("res/texture-pige.jpg"));
	  clo = TextureLoader.getTexture("JPG", ResourceLoader.getResourceAsStream("res/clo.jpg"));
	  road = TextureLoader.getTexture("PNG",ResourceLoader.getResourceAsStream("res/road.png"));
	  sky = TextureLoader.getTexture("PNG",ResourceLoader.getResourceAsStream("res/sky.png"));
	  bb = TextureLoader.getTexture("PNG",ResourceLoader.getResourceAsStream("res/2018a.png"));
	  System.out.println("Texture loaded okay ");
	}
}

package objects3D;


import org.lwjgl.opengl.GL11;

public class Sky {

    public void DrawSky(float length){


        GL11.glBegin(GL11.GL_QUADS);
        GL11.glTexCoord2f(1,1);
        GL11.glVertex3f(-length,-3,length);
        GL11.glTexCoord2f(0.5f,1);
        GL11.glVertex3f(-length,-3,-length);
        GL11.glTexCoord2f(0.5f,0);
        GL11.glVertex3f(-length,length,-length);
        GL11.glTexCoord2f(1,0);
        GL11.glVertex3f(-length,length,length);
        GL11.glEnd();

        GL11.glBegin(GL11.GL_QUADS);
        GL11.glTexCoord2f(0.5f,1);
        GL11.glVertex3f(-length,-3,-length);
        GL11.glTexCoord2f(0,1);
        GL11.glVertex3f(length,-3,-length);
        GL11.glTexCoord2f(0,0);
        GL11.glVertex3f(length,length,-length);
        GL11.glTexCoord2f(0.5f,0);
        GL11.glVertex3f(-length,length,-length);
        GL11.glEnd();
    }

}

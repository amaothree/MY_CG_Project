package objects3D;

import org.lwjgl.opengl.GL11;

public class Road {
    public void DrawRoad(float length){

        GL11.glBegin(GL11.GL_QUADS);

        GL11.glTexCoord2f(0,0);
        GL11.glVertex3f(2f,-1.5f,length);
        GL11.glTexCoord2f(1,0);
        GL11.glVertex3f(-2f,-1.5f,length);
        GL11.glTexCoord2f(1,1);
        GL11.glVertex3f(-2f,-1.5f,-length);
        GL11.glTexCoord2f(0,1);
        GL11.glVertex3f(2f,-1.5f,-length);
        GL11.glEnd();
    }
}

package objects3D;

import org.lwjgl.opengl.GL11;

public class Baby {

    public void DrawBaby(){

            GL11.glPushMatrix();
            {
                GL11.glBegin(GL11.GL_QUADS);
                GL11.glTexCoord2f(0,1);
                GL11.glVertex3f(-0.3f,0,0.4f);
                GL11.glTexCoord2f(1,1);
                GL11.glVertex3f(0.3f,0,-0.4f);
                GL11.glTexCoord2f(1,0);
                GL11.glVertex3f(0.3f,2.0f,-0.4f);
                GL11.glTexCoord2f(0,0);
                GL11.glVertex3f(-0.3f,2.0f,0.4f);
                GL11.glEnd();
            }
            GL11.glPopMatrix();


    }
}

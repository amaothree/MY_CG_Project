package objects3D;

import GraphicsObjects.Utils;
import org.lwjgl.opengl.GL11;

public class Ground {

    static float green[] = { 0.0f, 1.0f, 0.0f, 1.0f };
    static float blue[] = {0.0f, 0.0f, 1.0f, 1.0f};
    public void DrawGround(float length){


        GL11.glBegin(GL11.GL_QUADS);
        GL11.glColor3f(green[0], green[1], green[2]);
        GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(green));
        GL11.glTexCoord2f(1,1);
        GL11.glVertex3f(length,0,length);
        GL11.glTexCoord2f(0,1);
        GL11.glVertex3f(-length,0,length);
        GL11.glTexCoord2f(0,0);
        GL11.glVertex3f(-length,0,-length);
        GL11.glTexCoord2f(1,0);
        GL11.glVertex3f(length,0,-length);
        GL11.glEnd();
    }
}

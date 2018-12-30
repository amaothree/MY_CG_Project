package objects3D;

import GraphicsObjects.Utils;
import org.lwjgl.opengl.GL11;

public class Wood {

    public void DrawWood(float delta){
        float theta = (float) (delta * 2 * Math.PI);
        Cylinder cylinder = new Cylinder();
        GL11.glPushMatrix();

//        GL11.glTranslatef(0.5f,0,0);
        GL11.glRotatef(delta*8000,0,1,0);

        GL11.glTranslatef(5f,2,0);
        GL11.glRotatef(delta*5000,0,1,0);
        cylinder.DrawCylinder(0.1f,1,10);

        GL11.glPopMatrix();

    }
}

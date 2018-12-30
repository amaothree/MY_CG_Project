package objects3D;

import org.lwjgl.opengl.GL11;

public class Wood {

    public void DrawWood(float delta){
        float theta = (float) (delta * 2 * Math.PI);
        float LimbDistance;
        LimbDistance = (float) Math.cos(theta*10) * 10 ;
        LimbDistance = Math.abs(LimbDistance);
        Cylinder cylinder = new Cylinder();
        GL11.glPushMatrix();



        GL11.glTranslatef(0,3,LimbDistance);
        GL11.glRotatef(delta*8000,1,1,1);
        cylinder.DrawCylinder(0.1f,1,10);

        GL11.glPopMatrix();

    }
}

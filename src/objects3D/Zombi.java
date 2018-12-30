package objects3D;

import org.lwjgl.opengl.GL11;

import java.io.IOException;

public class Zombi {

    public void DrawZombi(float delta) throws IOException{
        float theta = (float) (delta * 2 * Math.PI);
        float LimbRotation;
        LimbRotation = (float) Math.cos(theta) * 50;

        Rectangle rectangle = new Rectangle();
        Cylinder cylinder = new Cylinder();

        //Body
        GL11.glPushMatrix();{
            GL11.glTranslatef(0,3.2f,0);

            rectangle.DrawRectangle(1.6f,1.6f,1);

            //Left Leg
            GL11.glPushMatrix();{

                GL11.glTranslatef(0.4f,-1.6f,0);
                GL11.glRotatef(LimbRotation,-1,0,0);
                rectangle.DrawRectangle(0.8f,1.6f,1.0f);


            }GL11.glPopMatrix();

            //Right Leg
            GL11.glPushMatrix();{

                GL11.glTranslatef(-0.4f,-1.6f,0);
                GL11.glRotatef(LimbRotation,1,0,0);
                rectangle.DrawRectangle(0.8f,1.6f,1.0f);

            }GL11.glPopMatrix();

            //Left Arm
            GL11.glPushMatrix();{

                GL11.glTranslatef(1.1f,-0.4f,-0.3f);
                GL11.glRotatef(90,-1,0,0);
                rectangle.DrawRectangle(0.6f,1.6f,0.8f);

            }GL11.glPopMatrix();

            //Right Arm
            GL11.glPushMatrix();{

                GL11.glTranslatef(-1.1f,-0.4f,-0.3f);
                GL11.glRotatef(90,-1,0,0);
                rectangle.DrawRectangle(0.6f,1.6f,0.8f);

            }GL11.glPopMatrix();

            //Head
            GL11.glPushMatrix();{

            GL11.glTranslatef(0,0.8f,0);
            rectangle.DrawRectangle(0.8f,0.8f,0.8f);

            }GL11.glPopMatrix();


        }GL11.glPopMatrix();
    }
}

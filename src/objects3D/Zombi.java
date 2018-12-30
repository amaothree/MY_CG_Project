package objects3D;

import GraphicsObjects.Utils;
import org.lwjgl.opengl.GL11;

import java.io.IOException;

public class Zombi {

    static float green[] = {0.0f, 1.0f, 0.0f, 1.0f};
    static float brown[] = {0.5f, 0.25f, 0.0f, 1.0f, 1.0f};
    static float grey[] = {0.5f, 0.5f, 0.5f, 1.0f};

    public void DrawZombi(float delta) throws IOException{
        float theta = (float) (delta * 2 * Math.PI);
        float LimbRotation;
        LimbRotation = (float) Math.cos(theta*5) * 50;

        Rectangle rectangle = new Rectangle();
        Cylinder cylinder = new Cylinder();

        //Body
        GL11.glColor3f(brown[0], brown[1], brown[2]);
        GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(brown));
        GL11.glPushMatrix();{
            GL11.glTranslatef(0,3.2f,0);

            rectangle.DrawRectangle(1.6f,1.6f,1);

            //Left Leg
            GL11.glColor3f(green[0], green[1], green[2]);
            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(green));
            GL11.glPushMatrix();{

                GL11.glTranslatef(0.4f,-1.6f,0);
                GL11.glRotatef(LimbRotation,-1,0,0);
                rectangle.DrawRectangle(0.8f,1.6f,1.0f);


            }GL11.glPopMatrix();

            //Right Leg
            GL11.glColor3f(green[0], green[1], green[2]);
            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(green));
            GL11.glPushMatrix();{

                GL11.glTranslatef(-0.4f,-1.6f,0);
                GL11.glRotatef(LimbRotation,1,0,0);
                rectangle.DrawRectangle(0.8f,1.6f,1.0f);

            }GL11.glPopMatrix();

            //Left Arm
            GL11.glColor3f(green[0], green[1], green[2]);
            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(green));
            GL11.glPushMatrix();{

                GL11.glTranslatef(1.1f,-0.4f,-0.3f);
                GL11.glRotatef(90,-1,0,0);
                rectangle.DrawRectangle(0.6f,1.6f,0.8f);

            }GL11.glPopMatrix();

            //Right Arm
            GL11.glColor3f(green[0], green[1], green[2]);
            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(green));
            GL11.glPushMatrix();{

                GL11.glTranslatef(-1.1f,-0.4f,-0.3f);
                GL11.glRotatef(90,-1,0,0);
                rectangle.DrawRectangle(0.6f,1.6f,0.8f);

            }GL11.glPopMatrix();

            //Head
            GL11.glColor3f(grey[0], grey[1], grey[2]);
            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(grey));
            GL11.glPushMatrix();{

            GL11.glTranslatef(0,0.8f,0);
            rectangle.DrawRectangle(0.8f,0.8f,0.8f);

            }GL11.glPopMatrix();


        }GL11.glPopMatrix();
    }
}

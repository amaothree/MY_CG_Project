package objects3D;

import GraphicsObjects.Utils;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import java.io.IOException;

public class StandMan {

    Texture head_tex, clo;

    // basic colours
    static float black[] = {0.0f, 0.0f, 0.0f, 1.0f};
    static float white[] = {1.0f, 1.0f, 1.0f, 1.0f};

    static float grey[] = {0.5f, 0.5f, 0.5f, 1.0f};
    static float spot[] = {0.1f, 0.1f, 0.1f, 0.5f};

    // primary colours
    static float red[] = {1.0f, 0.0f, 0.0f, 1.0f};
    static float green[] = {0.0f, 1.0f, 0.0f, 1.0f};
    static float blue[] = {0.0f, 0.0f, 1.0f, 1.0f};

    // secondary colours
    static float yellow[] = {1.0f, 1.0f, 0.0f, 1.0f};
    static float magenta[] = {1.0f, 0.0f, 1.0f, 1.0f};
    static float cyan[] = {0.0f, 1.0f, 1.0f, 1.0f};

    // other colours
    static float orange[] = {1.0f, 0.5f, 0.0f, 1.0f, 1.0f};
    static float brown[] = {0.5f, 0.25f, 0.0f, 1.0f, 1.0f};
    static float dkgreen[] = {0.0f, 0.5f, 0.0f, 1.0f, 1.0f};
    static float pink[] = {1.0f, 0.6f, 0.6f, 1.0f, 1.0f};


    public StandMan(Texture t, Texture c) {
        head_tex=t;
        clo = c;
    }

    // Implement using notes  in Animation lecture
    public void DrawHuman( ) throws IOException {


        Sphere sphere = new Sphere();
        Cylinder cylinder = new Cylinder();
        TexSphere texSphere = new TexSphere();


        GL11.glPushMatrix();

        {
            GL11.glTranslatef(0.0f, 0.5f, 0.0f);
            GL11.glRotatef(180, 0.0f, 1.0f, 0.0f);
            sphere.DrawSphere(0.5f, 32, 32);

            //  chest
            GL11.glColor3f(blue[0], blue[1], blue[2]);
            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
            GL11.glPushMatrix();
            {
                GL11.glTranslatef(0.0f, 0.5f, 0.0f);
                GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_CLAMP);
                Color.white.bind();
                clo.bind();
                GL11.glEnable(GL11.GL_TEXTURE_2D);
                GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
                texSphere.DrawTexSphere(0.5f, 32, 32,clo);
                GL11.glDisable(GL11.GL_TEXTURE_2D);


                // neck
                GL11.glColor3f(orange[0], orange[1], orange[2]);
                GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
                GL11.glPushMatrix();
                {
                    GL11.glTranslatef(0.0f, 0.0f, 0.0f);
                    GL11.glRotatef(-90.0f, 1.0f, 0.0f, 0.0f);
                    cylinder.DrawCylinder(0.15f, 0.7f, 32);


                    // head
                    GL11.glColor3f(red[0], red[1], red[2]);
                    GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(red));
                    GL11.glPushMatrix();
                    {

                        GL11.glTranslatef(0.0f, 0.0f, 1.0f);
                        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_CLAMP);
//                        Texture pige = TextureLoader.getTexture("JPG", ResourceLoader.getResourceAsStream("res/texture-pige.jpg"));
                        Color.white.bind();
                        head_tex.bind();
//                        pige.bind();
                        GL11.glEnable(GL11.GL_TEXTURE_2D);
                        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
                        texSphere.DrawTexSphere(0.5f, 32, 32,head_tex);
                        GL11.glDisable(GL11.GL_TEXTURE_2D);


                        //nose
                        GL11.glColor3f(orange[0],orange[1],orange[2]);
                        GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
                        GL11.glPushMatrix();
                        {
                            GL11.glTranslatef(0.0f,0.5f,0.0f);
                            sphere.DrawSphere(0.1f,32,32);


                            // GL11.glPopMatrix();
                        }
                        GL11.glPopMatrix();

                        //hire
                        GL11.glColor3f(black[0],black[1],black[2]);
                        GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(black));
                        GL11.glPushMatrix();
                        {
                            GL11.glTranslatef(0.0f,0.0f,0.0f);
                            //GL11.glRotatef(180, 0.0f, 0.0f, 0.0f);
                            cylinder.DrawCylinder(0.05f, 1, 10);
                            GL11.glPopMatrix();

                            //hireII
                            GL11.glColor3f(black[0],black[1],black[2]);
                            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(black));
                            GL11.glPushMatrix();
                            {
                                GL11.glTranslatef(-0.3f,0.0f,0.8f);
                                GL11.glRotatef(90, 0.0f, 1.0f, 0.0f);
                                cylinder.DrawCylinder(0.05f, 0.6f, 10);
                                GL11.glPopMatrix();

                            }

                        }
                        GL11.glPopMatrix();
                    }
                    GL11.glPopMatrix();


                    // left shoulder
                    GL11.glColor3f(red[0], red[1], red[2]);
                    GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(red));
                    GL11.glPushMatrix();
                    {
                        GL11.glTranslatef(0.5f, 0.4f, 0.0f);
                        GL11.glRotatef(30,0,0,1.0f);
                        sphere.DrawSphere(0.25f, 32, 32);


                        // left arm
                        GL11.glColor3f(red[0], red[1], red[2]);
                        GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(red));
                        GL11.glPushMatrix();
                        {
                            GL11.glTranslatef(0.0f, 0.0f, 0.0f);
                            GL11.glRotatef(90, 1.0f, 0.0f, 0.0f);


                            GL11.glRotatef(0, 1.0f, 0.0f, 0.0f);
                            cylinder.DrawCylinder(0.15f, 0.7f, 32);


                            // left elbow
                            GL11.glColor3f(brown[0], brown[1], brown[2]);
                            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(brown));
                            GL11.glPushMatrix();
                            {
                                GL11.glTranslatef(0.0f, 0.0f, 0.75f);
                                sphere.DrawSphere(0.2f, 32, 32);

                                //left forearm
                                GL11.glColor3f(red[0], red[1], red[2]);
                                GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(red));
                                GL11.glPushMatrix();
                                {
                                    GL11.glTranslatef(0.0f, 0.0f, 0.0f);
                                    GL11.glRotatef(90, 1.0f, 0.0f, 0.0f);
                                    GL11.glRotatef(0, 1.0f, 1.0f, 0.0f);
                                    cylinder.DrawCylinder(0.1f, 0.7f, 32);

                                    // left hand
                                    GL11.glColor3f(white[0], white[1], white[2]);
                                    GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
                                    GL11.glPushMatrix();
                                    {
                                        GL11.glTranslatef(0.0f, 0.0f, 0.75f);
                                        sphere.DrawSphere(0.2f, 32, 32);
                                    }
                                    GL11.glPopMatrix();
                                }
                                GL11.glPopMatrix();
                            }
                            GL11.glPopMatrix();
                        }
                        GL11.glPopMatrix();
                    }
                    GL11.glPopMatrix();
                    // to chest

                    // right shoulder
                    GL11.glColor3f(red[0], red[1], red[2]);
                    GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(red));
                    GL11.glPushMatrix();
                    {
                        GL11.glTranslatef(-0.5f, 0.4f, 0.0f);
                        GL11.glRotatef(30,0,0,-1.0f);
                        sphere.DrawSphere(0.25f, 32, 32);

                        // right arm
                        GL11.glColor3f(red[0], red[1], red[2]);
                        GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(red));
                        GL11.glPushMatrix();
                        {
                            GL11.glTranslatef(0.0f, 0.0f, 0.0f);
                            GL11.glRotatef(90, 1.0f, 0.0f, 0.0f);
                            cylinder.DrawCylinder(0.15f, 0.7f, 32);

                            // right elbow
                            GL11.glColor3f(brown[0], brown[1], brown[2]);
                            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(brown));
                            GL11.glPushMatrix();
                            {
                                GL11.glTranslatef(0.0f, 0.0f, 0.75f);
                                sphere.DrawSphere(0.2f, 32, 32);

                                //right forearm
                                GL11.glColor3f(red[0], red[1], red[2]);
                                GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(red));
                                GL11.glPushMatrix();
                                {
                                    GL11.glTranslatef(0.0f, 0.0f, 0.0f);
                                    GL11.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
                                    GL11.glRotatef(0, 1.0f, 1.0f, 0.0f);
                                    cylinder.DrawCylinder(0.1f, 0.7f, 32);

                                    // right hand
                                    GL11.glColor3f(white[0], white[1], white[2]);
                                    GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
                                    GL11.glPushMatrix();
                                    {
                                        GL11.glTranslatef(0.0f, 0.0f, 0.75f);
                                        sphere.DrawSphere(0.2f, 32, 32);

                                        //torch
                                        GL11.glColor3f(black[0], black[1], black[2]);
                                        GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(black));
                                        GL11.glPushMatrix();
                                        {
                                            GL11.glTranslatef(0.0f, -0.7f, 0.0f);
                                            GL11.glRotatef(-90.0f,1.0f,0.0f,0.0f);
                                            cylinder.DrawCylinder(0.1f, 1, 10);

                                        }
                                        GL11.glPopMatrix();
                                    }
                                    GL11.glPopMatrix();
                                }
                                GL11.glPopMatrix();
                            }
                            GL11.glPopMatrix();
                        }
                        GL11.glPopMatrix();
                    }
                    GL11.glPopMatrix();
                    //chest
                }
                GL11.glPopMatrix();


                // pelvis

                // left hip
                GL11.glColor3f(blue[0], blue[1], blue[2]);
                GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
                GL11.glPushMatrix();
                {
                    GL11.glTranslatef(-0.5f, -0.2f, 0.0f);

                    sphere.DrawSphere(0.25f, 32, 32);


                    // left high leg
                    GL11.glColor3f(blue[0], blue[1], blue[2]);
                    GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
                    GL11.glPushMatrix();
                    {
                        GL11.glTranslatef(0.0f, 0.0f, 0.0f);

//                        GL11.glRotatef(0, 1.0f, 0.0f, 0.0f);
                        GL11.glRotatef(90.0f,1.0f,0.0f,0.0f);
                        cylinder.DrawCylinder(0.15f, 0.7f, 32);


                        // left knee
                        GL11.glColor3f(blue[0], blue[1], blue[2]);
                        GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
                        GL11.glPushMatrix();
                        {
                            GL11.glTranslatef(0.0f, 0.0f, 0.75f);
                            GL11.glRotatef(0.0f, 0.0f, 0.0f, 0.0f);
                            sphere.DrawSphere(0.25f, 32, 32);

                            //left low leg
                            GL11.glColor3f(blue[0], blue[1], blue[2]);
                            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
                            GL11.glPushMatrix();
                            {
                                GL11.glTranslatef(0.0f, 0.0f, 0.0f);
                                //  GL11.glRotatef(120.0f,1.0f,0.0f,0.0f);
                                GL11.glRotatef(0, 1.0f, 0.0f, 0.0f);
                                cylinder.DrawCylinder(0.15f, 0.7f, 32);

                                // left foot
                                GL11.glColor3f(brown[0], brown[1], brown[2]);
                                GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(brown));
                                GL11.glPushMatrix();
                                {
                                    GL11.glTranslatef(0.0f, 0.0f, 0.75f);
                                    sphere.DrawSphere(0.3f, 32, 32);

                                }
                                GL11.glPopMatrix();
                            }
                            GL11.glPopMatrix();
                        }
                        GL11.glPopMatrix();
                    }
                    GL11.glPopMatrix();
                }
                GL11.glPopMatrix();

                // pelvis


                // right hip
                GL11.glColor3f(blue[0], blue[1], blue[2]);
                GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
                GL11.glPushMatrix();
                {
                    GL11.glTranslatef(0.5f, -0.2f, 0.0f);

                    sphere.DrawSphere(0.25f, 32, 32);


                    // right high leg
                    GL11.glColor3f(blue[0], blue[1], blue[2]);
                    GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
                    GL11.glPushMatrix();
                    {
                        GL11.glTranslatef(0.0f, 0.0f, 0.0f);
                        GL11.glRotatef(90, 1.0f, 0.0f, 0.0f);


                        GL11.glRotatef(0, 0.0f, 0.0f, 0.0f);
                        //   GL11.glRotatef(90.0f,1.0f,0.0f,0.0f);
                        cylinder.DrawCylinder(0.15f, 0.7f, 32);


                        // right knee
                        GL11.glColor3f(blue[0], blue[1], blue[2]);
                        GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
                        GL11.glPushMatrix();
                        {
                            GL11.glTranslatef(0.0f, 0.0f, 0.75f);
                            GL11.glRotatef(0.0f, 0.0f, 0.0f, 0.0f);
                            sphere.DrawSphere(0.25f, 32, 32);

                            //right low leg
                            GL11.glColor3f(blue[0], blue[1], blue[2]);
                            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
                            GL11.glPushMatrix();
                            {
                                GL11.glTranslatef(0.0f, 0.0f, 0.0f);

                                GL11.glRotatef(0, 1.0f, 0.0f, 0.0f);
                                cylinder.DrawCylinder(0.15f, 0.7f, 32);

                                // right foot
                                GL11.glColor3f(brown[0], brown[1], brown[2]);
                                GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(brown));
                                GL11.glPushMatrix();
                                {
                                    GL11.glTranslatef(0.0f, 0.0f, 0.75f);
                                    sphere.DrawSphere(0.3f, 32, 32);

                                }
                                GL11.glPopMatrix();
                            }
                            GL11.glPopMatrix();
                        }
                        GL11.glPopMatrix();
                    }
                    GL11.glPopMatrix();
                }
                GL11.glPopMatrix();
            }
            GL11.glPopMatrix();

        }


    }


}

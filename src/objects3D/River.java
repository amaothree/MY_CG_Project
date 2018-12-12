package objects3D;

import GraphicsObjects.Point4f;
import org.lwjgl.opengl.GL11;

import java.io.IOException;

public class River {

    static float blue[] = {0.0f, 0.0f, 1.0f, 1.0f};

    public void DrawRiver(float length) throws IOException{

        Point4f vertices[] = {
                new Point4f(-length/2,0.0f,-length/2,0.0f),
                new Point4f(-length/2,0.0f,length/2,0.0f),
                new Point4f(length/2,0.0f,length/2,0.0f),
                new Point4f(length/2,0.0f,-length/2,0.0f)
        };

        GL11.glBegin(GL11.GL_QUADS);

                GL11.glColor3f(0, 0, 1);
                GL11.glVertex3f(vertices[3].x, vertices[3].y, vertices[3].z);
                GL11.glColor3f(0.5f, 0.5f, 1);
                GL11.glVertex3f(vertices[0].x, vertices[0].y, vertices[0].z);
                GL11.glColor3f(1, 1, 1);
                GL11.glVertex3f(vertices[1].x, vertices[1].y, vertices[1].z);
                GL11.glColor3f(0.5f, 0.5f, 1);
                GL11.glVertex3f(vertices[2].x, vertices[2].y, vertices[2].z);

        GL11.glEnd();

//        GL11.glBegin(GL11.GL_POINTS);
//        float m;
//        int w,l;
//        for (w=1;w<3;w++) {
//            for (l=1;l<10;l++) {
//                for (float i = 0.1f; i <= 1.0f; i += 0.002f) {
//                    for (float j = 0.0f; j <= 1.0f; j += 0.002f) {
//                        m = length/2 + ((i + j) / 2 * 0.8f);
//                        GL11.glColor3f(m, m, 1);
//                        GL11.glVertex3f(i * w, 0.0f, j * l);
//                    }
//                }
//            }
//        }
//        GL11.glEnd();
    }

}

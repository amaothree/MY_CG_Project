package objects3D;

import GraphicsObjects.Point4f;
import GraphicsObjects.Utils;
import GraphicsObjects.Vector4f;
import org.lwjgl.opengl.GL11;

public class Plane {
    static float white[] = {1.0f, 1.0f, 1.0f, 1.0f};
    static float red[] = { 1.0f, 0.0f, 0.0f, 1.0f };

    public void DrawPlane() {

        Cylinder cylinder = new Cylinder();
        Point4f vertices[] = {
                new Point4f(0.3f, 0.1f, 0.8f, 0.0f),
                new Point4f(0.3f, -0.1f, 0.8f, 0.0f),
                new Point4f(-0.3f, -0.1f, 0.8f, 0.0f),
                new Point4f(-0.3f, 0.1f, 0.8f, 0.0f),
                new Point4f(0.3f, 0.1f, -0.8f, 0.0f),
                new Point4f(0.3f, -0.1f, -0.8f, 0.0f),
                new Point4f(-0.3f, -0.1f, -0.8f, 0.0f),
                new Point4f(-0.3f, 0.1f, -0.8f, 0.0f),
                new Point4f(0, 0, 1.5f, 0.0f)};

        int faces[][] = {{0, 1, 2, 3},
                {4, 5, 1, 0},
                {7, 6, 5, 4},
                {3, 2, 6, 7},
                {7, 4, 0, 3},
                {5, 6, 2, 1},
                {0,1,8},
                {1,2,8},
                {2,3,8},
                {3,0,8}
                };

        GL11.glPushMatrix();
        {
            GL11.glBegin(GL11.GL_QUADS);
            GL11.glColor3f(white[0], white[1], white[2]);
            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
            for (int face = 0; face < 6; face++) { // per face
                Vector4f v = vertices[faces[face][1]].MinusPoint(vertices[faces[face][0]]);
                Vector4f w = vertices[faces[face][3]].MinusPoint(vertices[faces[face][0]]);
                Vector4f normal = v.cross(w).Normal();
                GL11.glNormal3f(normal.x, normal.y, normal.z);
                GL11.glVertex3f(vertices[faces[face][0]].x, vertices[faces[face][0]].y, vertices[faces[face][0]].z);
                GL11.glVertex3f(vertices[faces[face][1]].x, vertices[faces[face][1]].y, vertices[faces[face][1]].z);
                GL11.glVertex3f(vertices[faces[face][2]].x, vertices[faces[face][2]].y, vertices[faces[face][2]].z);
                GL11.glVertex3f(vertices[faces[face][3]].x, vertices[faces[face][3]].y, vertices[faces[face][3]].z);
            } // per face
            GL11.glEnd();

            GL11.glPushMatrix();
            {
                GL11.glBegin(GL11.GL_TRIANGLES);
                GL11.glColor3f(red[0], red[1], red[2]);
                GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(red));
                for (int face = 6; face < 10; face++) { // per face

                    GL11.glVertex3f(vertices[faces[face][0]].x, vertices[faces[face][0]].y, vertices[faces[face][0]].z);
                    GL11.glVertex3f(vertices[faces[face][1]].x, vertices[faces[face][1]].y, vertices[faces[face][1]].z);
                    GL11.glVertex3f(vertices[faces[face][2]].x, vertices[faces[face][2]].y, vertices[faces[face][2]].z);
                }
                GL11.glEnd();
            }
            GL11.glPopMatrix();
        }
        GL11.glPopMatrix();
    }
}

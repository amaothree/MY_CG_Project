package objects3D;

import GraphicsObjects.Point4f;
import GraphicsObjects.Vector4f;
import org.lwjgl.opengl.GL11;

public class Rectangle {

    public void DrawRectangle(float x,float y,float z){

        x=x/2;
        z=z/2;

        Point4f vertices[] = {
                new Point4f(-x, -y, -z, 0.0f),
                new Point4f(-x, -y, z, 0.0f),
                new Point4f(-x, 0, -z, 0.0f),
                new Point4f(-x, 0, z, 0.0f),
                new Point4f(x, -y, -z, 0.0f),
                new Point4f(x, -y, z, 0.0f),
                new Point4f(x, 0, -z, 0.0f),
                new Point4f(x, 0, z, 0.0f) };

        int faces[][] = {
                { 0, 4, 5, 1 },
                { 0, 2, 6, 4 },
                { 0, 1, 3, 2 },
                { 4, 6, 7, 5 },
                { 1, 5, 7, 3 },
                { 2, 3, 7, 6 } };

        GL11.glBegin(GL11.GL_QUADS);
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

    }
}

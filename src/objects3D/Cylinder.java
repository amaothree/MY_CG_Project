package objects3D;

import org.lwjgl.opengl.GL11;
import GraphicsObjects.Point4f;
import GraphicsObjects.Vector4f;
import java.math.*;

public class Cylinder {

	
	public Cylinder() { 
	}
	
	// remember to use Math.PI isntead PI 
	// Implement using notes and examine Tetrahedron to aid in the coding  look at lecture  7 , 7b and 8 
	public void DrawCylinder(float radius, float height, int nSegments ) 
	{
		/* a loop around circumference of a tube */
		for (float i = 0.0f; i < nSegments; i += 1.0)
		{ 	
			//calculate the angle to decide the position of the rectangle
			float angle = (float) (Math.PI * i * 2.0 / nSegments) ;
			float nextAngle = (float) (Math.PI * (i + 1.0) * 2.0 / nSegments);
		
			/* compute sin & cosine to get the the x and y value of triangle */
			float x1 = (float) Math.sin(angle), y1 = (float) Math.cos(angle);
			float x2 = (float) Math.sin(nextAngle), y2 = (float) Math.cos(nextAngle);
			
			GL11.glBegin(GL11.GL_TRIANGLES);
		
			// draw top triangle 
			GL11.glNormal3f(x1, y1, 0.0f); GL11.glVertex3f(radius*x1, radius*y1, 0.0f); 
			GL11.glNormal3f(x2, y2, height); GL11.glVertex3f(radius*x2, radius*y2, height); 
			GL11.glNormal3f(x1, y1, height); GL11.glVertex3f(radius*x1, radius*y1, height);
			
			//add a lid on the top
			GL11.glNormal3f(x1, y1, height); GL11 .glVertex3f(radius*x1, radius*y1, 0.0f);
			GL11.glNormal3f(x2, y2, height); GL11 .glVertex3f(radius*x2, radius*y2, 0.0f);
			GL11.glNormal3f(0.0f, 0.0f, height); GL11 .glVertex3f(0.0f, 0.0f, 0.0f);
		
			// draw bottom triangle      
			GL11.glNormal3f(x1, y1, 0.0f); GL11.glVertex3f(radius*x1, radius*y1, 0.0f); 
			GL11.glNormal3f(x2, y2, 0.0f); GL11.glVertex3f(radius*x2, radius*y2, 0.0f); 
			GL11.glNormal3f(x2, y2, height); GL11.glVertex3f(radius*x2, radius*y2, height); 
			
			//add a lid on the bottom
			GL11.glNormal3f(x1, y1, 0.0f); GL11 .glVertex3f(radius*x1, radius*y1, height);
			GL11.glNormal3f(x2, y2, 0.0f); GL11 .glVertex3f(radius*x2, radius*y2, height);
			GL11.glNormal3f(0.0f, 0.0f, 0.0f); GL11 .glVertex3f(0.0f, 0.0f, height);
		
			GL11.glEnd();
		} 
	}
}

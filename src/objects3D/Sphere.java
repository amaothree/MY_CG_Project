package objects3D;

import org.lwjgl.opengl.GL11;

public class Sphere {

	
	public Sphere() {

	}
	// Implement using notes and examine Tetrahedron to aid in the coding  look at lecture  7 , 7b and 8 
	// 7b should be your primary source, we will cover more about circles in later lectures to understand why the code works
	public void DrawSphere(float radius,float nSlices,float nSegments) {

	    GL11.glBegin(GL11.GL_QUADS);

        /* compute inctheta & incphi */
	    float inctheta = (float) ((2.0f*Math.PI)/nSlices);
		float incphi = (float) (Math.PI/nSegments);

		for (double theta = -(Math.PI); theta<Math.PI; theta+=inctheta){
		    for (double phi= -(Math.PI)/2.0f; phi<(Math.PI/2.0f); phi+=incphi){
                /* a loop around circumference of a tube */

                /*Draw every point on the sphere*/
                /*Section 1*/
		        for(int i=0;i<2;i++) {
                    for(int j=0;j<2;j++) {

                        float z = (float) (radius * Math.sin(phi+incphi*i));
                        float x = (float) (radius * Math.cos(theta+inctheta*j) * Math.cos(phi+incphi*i));
                        float y = (float) (radius * Math.sin(theta+inctheta*j) * Math.cos(phi+incphi*i));


                        GL11.glNormal3f(x, y, z);
                        GL11.glVertex3f(x, y, z);
                    }
                }

                /*Section 2*/
                float z = (float) (radius * Math.sin(phi));
                float x = (float) (radius * Math.cos(theta) * Math.cos(phi));
                float y = (float) (radius * Math.sin(theta) * Math.cos(phi));
                GL11.glNormal3f(x, y, z);
                GL11.glVertex3f(x, y, z);

                float z1 = (float) (radius * Math.sin(phi+incphi));
                float x1 = (float) (radius * Math.cos(theta) * Math.cos(phi+incphi));
                float y1 = (float) (radius * Math.sin(theta) * Math.cos(phi+incphi));
                GL11.glNormal3f(x1, y1, z1);
                GL11.glVertex3f(x1, y1, z1);

                float z2 = (float) (radius * Math.sin(phi));
                float x2 = (float) (radius * Math.cos(theta+inctheta) * Math.cos(phi));
                float y2 = (float) (radius * Math.sin(theta+inctheta) * Math.cos(phi));
                GL11.glNormal3f(x2, y2, z2);
                GL11.glVertex3f(x2, y2, z2);

                float z3 = (float) (radius * Math.sin(phi+incphi));
                float x3 = (float) (radius * Math.cos(theta+inctheta) * Math.cos(phi+incphi));
                float y3 = (float) (radius * Math.sin(theta+inctheta) * Math.cos(phi+incphi));
                GL11.glNormal3f(x3, y3, z3);
                GL11.glVertex3f(x3, y3, z3);
            }
        }

        GL11.glEnd();


	}
}

 
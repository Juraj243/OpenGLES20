package com.example.android.opengl;

import android.opengl.GLES30;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;

/**
 * Created by Juraj on 22.10.2018.
 */

public class MyRenderer implements GLSurfaceView.Renderer {
    public void render(Model model){
        /*GLES30.glBindVertexArray(model.getVaoID());
        GLES30.glEnableVertexAttribArray(0);
        GLES30.glDrawArrays(GL11.GL_TRIANGLES, 0, model.getVertexCount());
        GLES30.glDisableVertexAttribArray(0);
        GLES30.glBindVertexArray(0);*/
    }

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        GLES30.glClearColor(1f, 0f, 0f, 0f);
    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int i, int i1) {

    }

    @Override
    public void onDrawFrame(GL10 gl10) {

    }
}

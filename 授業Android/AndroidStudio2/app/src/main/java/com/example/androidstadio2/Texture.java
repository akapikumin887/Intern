package com.example.androidstadio2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.Hashtable;

import javax.microedition.khronos.opengles.GL10;

// テクスチャクラス
public class Texture
{
	public int texId;
	public int wid, hei;

	private static final BitmapFactory.Options options =
			new BitmapFactory.Options();
	static {
		options.inScaled = false;
		options.inPreferredConfig = Bitmap.Config.ARGB_8888;
	}

	public void Load(GL10 gl, Context context, int resId)
	{
		// 画像読み込み
		Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resId, options);
		if(bitmap == null) {
			return;
		}

		// テクスチャ生成用に、縦横それぞれを2の冪乗にリサイズ
		wid = bitmap.getWidth();
		hei = bitmap.getHeight();

		int dst_wid = 2, dst_hei = 2;
		while(dst_wid < wid) dst_wid *= 2;
		while(dst_hei < hei) dst_hei *= 2;
		bitmap = Bitmap.createScaledBitmap(bitmap, dst_wid, dst_hei, true);
		if(bitmap == null)
		{
			return;
		}


		int[] texture = new int[1];

		GameView.gl10.glGenTextures(1, texture, 0);

		texId = texture[0];

		GameView.gl10.glBindTexture(GL10.GL_TEXTURE_2D, texId);

		GameView.gl10.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR);
		GameView.gl10.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);

		GameView.gl10.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_REPEAT);
		GameView.gl10.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_REPEAT);

		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);

		bitmap.recycle();
	}
	
	public void Set()
	{
		GameView.gl10.glEnable(GL10.GL_TEXTURE_2D);
		GameView.gl10.glBindTexture(GL10.GL_TEXTURE_2D, texId);
	}

	// 2Dテクスチャ描画関連
	private static Hashtable<Integer, float[]> verticesPool =
			new Hashtable<Integer, float[]>();
	private static Hashtable<Integer, float[]> colorsPool =
			new Hashtable<Integer, float[]>();
	private static Hashtable<Integer, float[]> coordsPool =
			new Hashtable<Integer, float[]>();

	private static Hashtable<Integer, FloatBuffer> polygonVerticesPool =
			new Hashtable<Integer, FloatBuffer>();
	private static Hashtable<Integer, FloatBuffer> polygonColorsPool =
			new Hashtable<Integer, FloatBuffer>();
	private static Hashtable<Integer, FloatBuffer> texCoordsPool =
			new Hashtable<Integer, FloatBuffer>();

	public static final FloatBuffer makeFloatBuffer(float[] arr) {
		ByteBuffer bb = ByteBuffer.allocateDirect(arr.length * 4);
		bb.order(ByteOrder.nativeOrder());
		FloatBuffer fb = bb.asFloatBuffer();
		fb.put(arr);
		fb.position(0);
		return fb;
	}

	public static final FloatBuffer makeVerticesBuffer(float[] arr) {
		FloatBuffer fb = null;
		if(polygonVerticesPool.containsKey(arr.length)) {
			fb = polygonVerticesPool.get(arr.length);
			fb.clear();
			fb.put(arr);
			fb.position(0);
			return fb;
		}

		fb = makeFloatBuffer(arr);
		polygonVerticesPool.put(arr.length, fb);
		return fb;
	}

	public static final FloatBuffer makeColorsBuffer(float[] arr) {
		FloatBuffer fb = null;
		if(polygonColorsPool.containsKey(arr.length)) {
			fb = polygonColorsPool.get(arr.length);
			fb.clear();
			fb.put(arr);
			fb.position(0);
			return fb;
		}

		fb = makeFloatBuffer(arr);
		polygonColorsPool.put(arr.length, fb);
		return fb;
	}

	public static final FloatBuffer makeTexCoordsBuffer(float[] arr) {
		FloatBuffer fb = null;
		if(texCoordsPool.containsKey(arr.length)) {
			fb = texCoordsPool.get(arr.length);
			fb.clear();
			fb.put(arr);
			fb.position(0);
			return fb;
		}

		fb = makeFloatBuffer(arr);
		texCoordsPool.put(arr.length, fb);
		return fb;
	}

	public static float[] getVertices(int n) {
		if(verticesPool.containsKey(n)) {
			return verticesPool.get(n);
		}

		float[] vertices = new float[n];
		verticesPool.put(n, vertices);
		return vertices;
	}

	public static float[] getColors(int n) {
		if(colorsPool.containsKey(n)) {
			return colorsPool.get(n);
		}

		float[] colors = new float[n];
		colorsPool.put(n, colors);
		return colors;
	}

	public static float[] getCoords(int n) {
		if(coordsPool.containsKey(n)) {
			return coordsPool.get(n);
		}

		float[] coords = new float[n];
		coordsPool.put(n, coords);
		return coords;
	}

	public void Draw(GL10 gl,
					 float x, float y, float wid, float hei,
					 float u, float v, float tex_w, float tex_h,
					 float r, float g, float b, float a)
	{
		Draw(gl, x, y, wid, hei, 0, false, u, v, tex_w, tex_h, r, g, b, a);
	}

	public void Draw(GL10 gl,
					 float x, float y, float wid, float hei,
					 float rotate, boolean reverse,
					 float u, float v, float tex_w, float tex_h,
					 float r, float g, float b, float a)
	{
		float[] vertices = getVertices(8);
		vertices[0] = -0.5f * wid; vertices[1] = -0.5f * hei;
		vertices[2] =  0.5f * wid; vertices[3] = -0.5f * hei;
		vertices[4] = -0.5f * wid; vertices[5] =  0.5f * hei;
		vertices[6] =  0.5f * wid; vertices[7] =  0.5f * hei;

		float[] colors = getColors(16);
		for(int i = 0; i < 4; i++) {
			colors[i * 4 + 0] = r;
			colors[i * 4 + 1] = g;
			colors[i * 4 + 2] = b;
			colors[i * 4 + 3] = a;
		}

		float[] coords = getCoords(8);
		if(!reverse)
		{
			coords[0] =         u; coords[1] =  v + tex_h;
			coords[2] = u + tex_w; coords[3] =  v + tex_h;
			coords[4] =         u; coords[5] =          v;
			coords[6] = u + tex_w; coords[7] =          v;
		}
		else
		{
			coords[0] = u + tex_w; coords[1] =  v + tex_h;
			coords[2] =         u; coords[3] =  v + tex_h;
			coords[4] = u + tex_w; coords[5] =          v;
			coords[6] =         u; coords[7] =          v;
		}

		FloatBuffer polygonvertices = makeVerticesBuffer(vertices);
		FloatBuffer polygoncolors = makeColorsBuffer(colors);
		FloatBuffer texCoords = makeTexCoordsBuffer(coords);

		gl.glEnable(GL10.GL_TEXTURE_2D);
		gl.glBindTexture(GL10.GL_TEXTURE_2D, texId);
		gl.glVertexPointer(2, GL10.GL_FLOAT, 0, polygonvertices);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glColorPointer(4, GL10.GL_FLOAT, 0, polygoncolors);
		gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
		gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, texCoords);
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		gl.glTranslatef(x, y, 0);
		gl.glRotatef(rotate, 0, 0, 1);

		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);

		gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		gl.glDisable(GL10.GL_TEXTURE_2D);
	}

}

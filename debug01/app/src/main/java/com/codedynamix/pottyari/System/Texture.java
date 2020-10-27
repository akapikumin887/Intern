package com.codedynamix.pottyari.System;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;

import com.codedynamix.pottyari.Activity.GameActivity;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.Hashtable;

import javax.microedition.khronos.opengles.GL10;

public class Texture
{
    public int texId;
    public Vector2 texSize;

    public Texture()
    {
        texSize = new Vector2();
    }

    //OutOfMemory回避のお供らしい
    private static final BitmapFactory.Options options = new BitmapFactory.Options();
    static
    {
        options.inScaled = false;
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
    }

    //テクスチャのロード GLRenderのスレッド以外で呼び出すと失敗するので注意
    public void loadTexture(Context context, int resId)
    {
        //if(texId != 0) return;

        //pngやjpegの画像をbitmapに戻す
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resId, options);
        if(bitmap == null)
            return;

        // テクスチャ生成用に、縦横それぞれを2の冪乗にリサイズ
        //2のべき数にこだわる理由はおそらく軽量化だと思われる
        texSize.x = bitmap.getWidth();
        texSize.y = bitmap.getHeight();

        int dst_wid = 2, dst_hei = 2;
        while(dst_wid < texSize.x) dst_wid *= 2;
        while(dst_hei < texSize.y) dst_hei *= 2;

        bitmap = Bitmap.createScaledBitmap(bitmap, dst_wid, dst_hei, true);
        if(bitmap == null)
            return;

        int[] texture = new int[1];

        GameActivity.getGL().glGenTextures(1, texture, 0);

        texId = texture[0];

        GameActivity.getGL().glBindTexture(GL10.GL_TEXTURE_2D, texId);

        GameActivity.getGL().glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR);
        GameActivity.getGL().glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);

        GameActivity.getGL().glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_REPEAT);
        GameActivity.getGL().glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_REPEAT);

        GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);

        bitmap.recycle();
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

    //texUVとtexWHについて簡単な説明
    //UVは左上から測る、最大値は1.0f
    //WHはUVの地点からどれだけのサイズ欲しいかを入力する
    public void draw(Vector2 pos, Vector2 size,
                     float rotate, boolean reverse,
                     Vector2 texUV, Vector2 texWH,
                     Color color)
    {
        float[] vertices = getVertices(8);
        vertices[0] = -0.5f * size.x; vertices[1] = -0.5f * size.y;
        vertices[2] =  0.5f * size.x; vertices[3] = -0.5f * size.y;
        vertices[4] = -0.5f * size.x; vertices[5] =  0.5f * size.y;
        vertices[6] =  0.5f * size.x; vertices[7] =  0.5f * size.y;

        float[] colors = getColors(16);
        for(int i = 0; i < 4; i++) {
            colors[i * 4 + 0] = color.r;
            colors[i * 4 + 1] = color.g;
            colors[i * 4 + 2] = color.b;
            colors[i * 4 + 3] = color.a;
        }

        float[] coords = getCoords(8);
        if(!reverse)
        {
            coords[0] =           texUV.x; coords[1] = texUV.y + texWH.y;
            coords[2] = texUV.x + texWH.x; coords[3] = texUV.y + texWH.y;
            coords[4] =           texUV.x; coords[5] =           texUV.y;
            coords[6] = texUV.x + texWH.x; coords[7] =           texUV.y;
        }
        else
        {
            coords[0] = texUV.x + texWH.x; coords[1] = texUV.y + texWH.y;
            coords[2] =           texUV.x; coords[3] = texUV.y + texWH.y;
            coords[4] = texUV.x + texWH.x; coords[5] =           texUV.y;
            coords[6] =           texUV.x; coords[7] =           texUV.y;
        }

        FloatBuffer polygonvertices = makeVerticesBuffer(vertices);
        FloatBuffer polygoncolors = makeColorsBuffer(colors);
        FloatBuffer texCoords = makeTexCoordsBuffer(coords);

        GameActivity.getGL().glEnable(GL10.GL_TEXTURE_2D);
        GameActivity.getGL().glBindTexture(GL10.GL_TEXTURE_2D, texId);
        GameActivity.getGL().glVertexPointer(2, GL10.GL_FLOAT, 0, polygonvertices);
        GameActivity.getGL().glEnableClientState(GL10.GL_VERTEX_ARRAY);
        GameActivity.getGL().glColorPointer(4, GL10.GL_FLOAT, 0, polygoncolors);
        GameActivity.getGL().glEnableClientState(GL10.GL_COLOR_ARRAY);
        GameActivity.getGL().glTexCoordPointer(2, GL10.GL_FLOAT, 0, texCoords);
        GameActivity.getGL().glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

        GameActivity.getGL().glMatrixMode(GL10.GL_MODELVIEW);
        GameActivity.getGL().glLoadIdentity();
        GameActivity.getGL().glTranslatef(pos.x, pos.y, 0);
        GameActivity.getGL().glRotatef(rotate, 0, 0, 1);

        GameActivity.getGL().glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);

        GameActivity.getGL().glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        GameActivity.getGL().glDisable(GL10.GL_TEXTURE_2D);
    }
}
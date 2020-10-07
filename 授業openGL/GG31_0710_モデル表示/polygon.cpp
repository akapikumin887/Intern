//polygon.cpp

#include "main.h"
#include "polygon.h"
//#include "loadPNG.h"
#include "texture.h"

void CPolygon::Init()
{
	m_Texture = LoadTexture("asset\\texture\\texture.tga");
}

void CPolygon::Uninit()
{
	UnloadTexture(m_Texture);
}

void CPolygon::Update()
{

}

void CPolygon::Draw()
{
	//ライティング無効
	glDisable(GL_LIGHTING);

	////2D用マトリクス設定
	glMatrixMode(GL_PROJECTION);
	glPushMatrix();
	glLoadIdentity();
	glOrtho(0,SCREEN_WIDTH,SCREEN_HEIGHT,0,0,1);

	glMatrixMode(GL_MODELVIEW);
	glPushMatrix();
	glLoadIdentity();

	//テクスチャ設定
	glBindTexture(GL_TEXTURE_2D, m_Texture);
	//Load_png("asset\\texture\\hoge.png");

	//ポリゴン描画
	glBegin(GL_TRIANGLE_STRIP);


	glColor4f(1.0f,1.0f,1.0f,1.0f);
	glTexCoord2f(0.0f,0.0f);
	glVertex3f(0.0f,0.0f,0.0f);

	glColor4f(1.0f,1.0f,1.0f,1.0f);
	glTexCoord2f(0.0f,-1.0f);
	glVertex3f(0.0f,200.0f,0.0f);

	glColor4f(1.0f,1.0f,1.0f,1.0f);
	glTexCoord2f(1.0f,0.0f);
	glVertex3f(200.0f,0.0f,0.0f);

	glColor4f(1.0f,1.0f,1.0f,1.0f);
	glTexCoord2f(1.0f,-1.0f);
	glVertex3f(200.0f,200.0f,0.0f);


	glEnd();

	//ライティング有効
	glEnable(GL_LIGHTING);

	//マトリクスを戻す
	glMatrixMode(GL_PROJECTION);
	glPopMatrix();
	glMatrixMode(GL_MODELVIEW);
	glPopMatrix();
}
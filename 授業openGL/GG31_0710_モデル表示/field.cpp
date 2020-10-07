//field.cpp

#include "main.h"
#include "model.h"
#include "field.h"
#include "texture.h"
#include "loadPNG.h"

void CField::Init()
{
	m_Texture = LoadPng("asset\\model\\tank01.png");
	//m_Texture = LoadTexture("asset\\texture\\field.tga");
}

void CField::Uninit()
{
	UnLoadPng(m_Texture);
}

void CField::Update()
{

}

void CField::Draw()
{
	//ライティング有効
	glEnable(GL_LIGHTING);

	//マトリクス退避
	glMatrixMode(GL_MODELVIEW);
	glPushMatrix();

	GLfloat anbient[] = {0.1f,0.1f,0.1f,1.0f};
	GLfloat diffuse[] = {1.0f,1.0f,1.0f,1.0f};
	GLfloat specular[] = {0.1f,0.1f,0.1f,1.0f};
	GLfloat shininess[] = {0.0f};

	glMaterialfv(GL_FRONT_AND_BACK, GL_AMBIENT, anbient);
	glMaterialfv(GL_FRONT_AND_BACK, GL_DIFFUSE, diffuse);
	glMaterialfv(GL_FRONT_AND_BACK, GL_SPECULAR, specular);
	glMaterialfv(GL_FRONT_AND_BACK, GL_SHININESS, shininess);

	//テクスチャ設定
	glBindTexture(GL_TEXTURE_2D, m_Texture);

	//ポリゴン描画
	glBegin(GL_TRIANGLE_STRIP);

	glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
	glTexCoord2f(0.0f, 0.0f);
	glNormal3f(0.0f, 1.0f, 0.0f);
	glVertex3f(-10.0f, -1.0f, 10.0f);

	glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
	glTexCoord2f(1.0f, 0.0f);
	glNormal3f(0.0f, 1.0f, 0.0f);
	glVertex3f(10.0f, -1.0f, 10.0f);

	glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
	glTexCoord2f(0.0f, 1.0f);
	glNormal3f(0.0f, 1.0f, 0.0f);
	glVertex3f(-10.0f, -1.0f, -10.0f);

	glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
	glTexCoord2f(1.0f, 1.0f);
	glNormal3f(0.0f, 1.0f, 0.0f);
	glVertex3f(10.0f, -1.0f, -10.0f);


	glEnd();

	glBegin(GL_LINES);

	glVertex3f(-10.0f, -1.0f,  10.0f);
	glVertex3f(-10.0f, 0.0f,  10.0f);

	glVertex3f( 10.0f, -1.0f,  10.0f);
	glVertex3f( 10.0f, 0.0f,  10.0f);

	glVertex3f(-10.0f, -1.0f, -10.0f);
	glVertex3f(-10.0f, 0.0f, -10.0f);

	glVertex3f( 10.0f, -1.0f, -10.0f);
	glVertex3f( 10.0f, 0.0f, -10.0f);

	glEnd();


	//マトリクスを戻す
	glMatrixMode(GL_MODELVIEW);
	glPopMatrix();

	//ライティング無効
	glDisable(GL_LIGHTING);
}

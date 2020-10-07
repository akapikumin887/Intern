//cube.cpp
#include "main.h"
#include "cube.h"
#include "texture.h"
#include "input.h"

void CCube::Init()
{
	m_Position  = Vector3(5.0f, 0.0f, 0.0f);
	m_Rotation  = Vector3(0.0f, 0.0f, 0.0f);
	m_Scale		= Vector3(1.0f, 1.0f, 1.0f);

	m_Texture = LoadTexture("asset\\texture\\texture.tga");
}

void CCube::Uninit()
{
	UnloadTexture(m_Texture);
}

void CCube::Update()
{
	//if (CInput::GetKeyPress(VK_UP))
	//{
	//	m_Scale.x += 0.01f;
	//	m_Scale.y += 0.01f;
	//	m_Scale.z += 0.01f;
	//	m_Position.y += 0.01f;
	//}
	//if (CInput::GetKeyPress(VK_DOWN))
	//{
	//	m_Scale.x -= 0.01f;
	//	m_Scale.y -= 0.01f;
	//	m_Scale.z -= 0.01f;
	//	m_Position.y -= 0.01f;
	//}
	//if (CInput::GetKeyPress(VK_RIGHT))
	//	m_Rotation.y += 1.0f;
	//if (CInput::GetKeyPress(VK_LEFT))
	//	m_Rotation.y -= 1.0f;
	//if (CInput::GetKeyPress('W'))
	//	m_Position.z += 0.1f;
	//if (CInput::GetKeyPress('S'))
	//	m_Position.z -= 0.1f;
	//if (CInput::GetKeyPress('D'))
	//	m_Position.x -= 0.1f;
	//if (CInput::GetKeyPress('A'))
	//	m_Position.x += 0.1f;
}

void CCube::Draw()
{
	//���C�e�B���O�L��
	glEnable(GL_LIGHTING);

	//�e�N�X�`���ޔ�
	glMatrixMode(GL_MODELVIEW);
	glPushMatrix();

	GLfloat anbient[] = { 0.7f,0.7f,0.7f,1.0f };
	GLfloat diffuse[] = { 1.0f,1.0f,1.0f,1.0f };
	GLfloat specular[] = { 0.1f,0.1f,0.1f,1.0f };
	GLfloat shininess[] = { 0.0f };

	glMaterialfv(GL_FRONT_AND_BACK, GL_AMBIENT, anbient);
	glMaterialfv(GL_FRONT_AND_BACK, GL_DIFFUSE, diffuse);
	glMaterialfv(GL_FRONT_AND_BACK, GL_SPECULAR, specular);
	glMaterialfv(GL_FRONT_AND_BACK, GL_SHININESS, shininess);


	//�ړ���]�g��k���}�g���N�X�ݒ�
	glTranslatef(m_Position.x,m_Position.y,m_Position.z);
	glRotatef(m_Rotation.y,0.0f,1.0f,0.0f);
	glScalef(m_Scale.x,m_Scale.y,m_Scale.z);


	//�e�N�X�`���ݒ�
	glBindTexture(GL_TEXTURE_2D, m_Texture);

	//�|���S���`��
	{
		//���
		glBegin(GL_TRIANGLE_STRIP);

		glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		glTexCoord2f(1.0f, 1.0f);
		glNormal3f(0.0f, 1.0f, 0.0f);
		glVertex3f(-1.0f, 1.0f, 1.0f);

		glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		glTexCoord2f(0.0f, 1.0f);
		glNormal3f(0.0f, 1.0f, 0.0f);
		glVertex3f(1.0f, 1.0f, 1.0f);

		glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		glTexCoord2f(1.0f, 0.0f);
		glNormal3f(0.0f, 1.0f, 0.0f);
		glVertex3f(-1.0f, 1.0f, -1.0f);

		glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		glTexCoord2f(0.0f, 0.0f);
		glNormal3f(0.0f, 1.0f, 0.0f);
		glVertex3f(1.0f, 1.0f, -1.0f);

		glEnd();

		glBegin(GL_LINES);

		glVertex3f(-1.0f,1.0f,1.0f);
		glVertex3f(-1.0f,2.0f,1.0f);

		glVertex3f(1.0f,1.0f,1.0f);
		glVertex3f(1.0f,2.0f,1.0f);

		glVertex3f(-1.0f,1.0f,-1.0f);
		glVertex3f(-1.0f,2.0f,-1.0f);

		glVertex3f(1.0f,1.0f,-1.0f);
		glVertex3f(1.0f,2.0f,-1.0f);

		glEnd();
	}
	{
		//�O��
		glBegin(GL_TRIANGLE_STRIP);

		glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		glTexCoord2f(1.0f, 1.0f);
		glNormal3f(0.0f, 0.0f, -1.0f);
		glVertex3f(-1.0f, 1.0f, -1.0f);

		glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		glTexCoord2f(0.0f, 1.0f);
		glNormal3f(0.0f, 0.0f, -1.0f);
		glVertex3f(1.0f, 1.0f, -1.0f);

		glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		glTexCoord2f(1.0f, 0.0f);
		glNormal3f(0.0f, 0.0f, -1.0f);
		glVertex3f(-1.0f, -1.0f, -1.0f);

		glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		glTexCoord2f(0.0f, 0.0f);
		glNormal3f(0.0f, 0.0f, -1.0f);
		glVertex3f(1.0f, -1.0f, -1.0f);

		glEnd();

		glBegin(GL_LINES);

		glVertex3f(-1.0f, 1.0f, -1.0f);
		glVertex3f(-1.0f, 1.0f, -2.0f);

		glVertex3f(1.0f, 1.0f, -1.0f);
		glVertex3f(1.0f, 1.0f, -2.0f);

		glVertex3f(-1.0f, -1.0f, -1.0f);
		glVertex3f(-1.0f, -1.0f, -2.0f);

		glVertex3f(1.0f, -1.0f, -1.0f);
		glVertex3f(1.0f, -1.0f, -2.0f);

		glEnd();
	}
	{
		//���
		glBegin(GL_TRIANGLE_STRIP);

		glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		glTexCoord2f(1.0f, 1.0f);
		glNormal3f(0.0f, -1.0f, 0.0f);
		glVertex3f(-1.0f, -1.0f, -1.0f);

		glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		glTexCoord2f(0.0f, 1.0f);
		glNormal3f(0.0f, -1.0f, 0.0f);
		glVertex3f(1.0f, -1.0f, -1.0f);

		glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		glTexCoord2f(1.0f, 0.0f);
		glNormal3f(0.0f, -1.0f, 0.0f);
		glVertex3f(-1.0f, -1.0f, 1.0f);

		glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		glTexCoord2f(0.0f, 0.0f);
		glNormal3f(0.0f, -1.0f, 0.0f);
		glVertex3f(1.0f, -1.0f, 1.0f);

		glEnd();

		glBegin(GL_LINES);

		glVertex3f(-1.0f, -1.0f, -1.0f);
		glVertex3f(-1.0f, -2.0f, -1.0f);

		glVertex3f(1.0f, -1.0f, -1.0f);
		glVertex3f(1.0f, -2.0f, -1.0f);

		glVertex3f(-1.0f, -1.0f, 1.0f);
		glVertex3f(-1.0f, -2.0f, 1.0f);

		glVertex3f(1.0f, -1.0f, 1.0f);
		glVertex3f(1.0f, -2.0f, 1.0f);

		glEnd();
	}
	{
		//���
		glBegin(GL_TRIANGLE_STRIP);

		glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		glTexCoord2f(1.0f, 1.0f);
		glNormal3f(0.0f, 0.0f, 1.0f);
		glVertex3f(-1.0f, -1.0f, 1.0f);

		glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		glTexCoord2f(0.0f, 1.0f);
		glNormal3f(0.0f, 0.0f, 1.0f);
		glVertex3f(1.0f, -1.0f, 1.0f);

		glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		glTexCoord2f(1.0f, 0.0f);
		glNormal3f(0.0f, 0.0f, 1.0f);
		glVertex3f(-1.0f, 1.0f, 1.0f);

		glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		glTexCoord2f(0.0f, 0.0f);
		glNormal3f(0.0f, 0.0f, 1.0f);
		glVertex3f(1.0f, 1.0f, 1.0f);

		glEnd();

		glBegin(GL_LINES);

		glVertex3f(-1.0f, -1.0f, 1.0f);
		glVertex3f(-1.0f, -1.0f, 2.0f);

		glVertex3f(1.0f, -1.0f, 1.0f);
		glVertex3f(1.0f, -1.0f, 2.0f);

		glVertex3f(-1.0f, 1.0f, 1.0f);
		glVertex3f(-1.0f, 1.0f, 2.0f);

		glVertex3f(1.0f, 1.0f, 1.0f);
		glVertex3f(1.0f, 1.0f, 2.0f);

		glEnd();
	}
	{
		//�E��
		glBegin(GL_TRIANGLE_STRIP);

		glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		glTexCoord2f(1.0f, 1.0f);
		glNormal3f(-1.0f, 0.0f, 0.0f);
		glVertex3f(-1.0f, 1.0f, 1.0f);

		glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		glTexCoord2f(0.0f, 1.0f);
		glNormal3f(-1.0f, 0.0f, 0.0f);
		glVertex3f(-1.0f, 1.0f, -1.0f);

		glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		glTexCoord2f(1.0f, 0.0f);
		glNormal3f(-1.0f, 0.0f, 0.0f);
		glVertex3f(-1.0f, -1.0f, 1.0f);

		glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		glTexCoord2f(0.0f, 0.0f);
		glNormal3f(-1.0f, 0.0f, 0.0f);
		glVertex3f(-1.0f, -1.0f, -1.0f);

		glEnd();

		glBegin(GL_LINES);

		glVertex3f(-1.0f, 1.0f, 1.0f);
		glVertex3f(-2.0f, 1.0f, 1.0f);

		glVertex3f(-1.0f, 1.0f, -1.0f);
		glVertex3f(-2.0f, 1.0f, -1.0f);

		glVertex3f(-1.0f, -1.0f, 1.0f);
		glVertex3f(-2.0f, -1.0f, 1.0f);

		glVertex3f(-1.0f, -1.0f, -1.0f);
		glVertex3f(-2.0f, -1.0f, -1.0f);

		glEnd();
	}
	{
		//����
		glBegin(GL_TRIANGLE_STRIP);

		glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		glTexCoord2f(1.0f, 1.0f);
		glNormal3f(1.0f, 0.0f, 0.0f);
		glVertex3f(1.0f, 1.0f, -1.0f);

		glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		glTexCoord2f(0.0f, 1.0f);
		glNormal3f(1.0f, 0.0f, 0.0f);
		glVertex3f(1.0f, 1.0f, 1.0f);

		glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		glTexCoord2f(1.0f, 0.0f);
		glNormal3f(1.0f, 0.0f, 0.0f);
		glVertex3f(1.0f, -1.0f, -1.0f);

		glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		glTexCoord2f(0.0f, 0.0f);
		glNormal3f(1.0f, 0.0f, 0.0f);
		glVertex3f(1.0f, -1.0f, 1.0f);

		glEnd();

		glBegin(GL_LINES);

		glVertex3f(1.0f, 1.0f, -1.0f);
		glVertex3f(2.0f, 1.0f, -1.0f);

		glVertex3f(1.0f, 1.0f, 1.0f);
		glVertex3f(2.0f, 1.0f, 1.0f);

		glVertex3f(1.0f, -1.0f, -1.0f);
		glVertex3f(2.0f, -1.0f, -1.0f);

		glVertex3f(1.0f, -1.0f, 1.0f);
		glVertex3f(2.0f, -1.0f, 1.0f);

		glEnd();
	}

	//�}�g���N�X��߂�
	glMatrixMode(GL_MODELVIEW);
	glPopMatrix();

	//���C�e�B���O����
	glDisable(GL_LIGHTING);
}

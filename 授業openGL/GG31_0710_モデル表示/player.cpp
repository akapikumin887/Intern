//player.cpp

#include "main.h"
#include "model.h"
#include "input.h"
#include "player.h"

void CPlayer::Init()
{
	m_Model = new CModel();
	m_Model->Load("asset\\model\\cube.obj");

	m_Position  = Vector3(-5.0f,0.0f,0.0f);
	m_Rotation  = Vector3(0.0f,0.0f,0.0f);
	m_Scale		= Vector3(1.0f,1.0f,1.0f);
}

void CPlayer::Uninit()
{
	m_Model->Unload();
	delete m_Model;
}

void CPlayer::Update()
{
	//if (CInput::GetKeyPress(VK_RIGHT))
	//	m_Rotation.y += 1.0f;
	//if (CInput::GetKeyPress(VK_LEFT))
	//	m_Rotation.y -= 1.0f;
	//if (CInput::GetKeyPress(VK_UP))
	//{
	//	m_Scale.x += 0.01f;
	//	m_Scale.y += 0.01f;
	//	m_Scale.z += 0.01f;
	//}
	//if (CInput::GetKeyPress(VK_DOWN))
	//{
	//	m_Scale.x -= 0.01f;
	//	m_Scale.y -= 0.01f;
	//	m_Scale.z -= 0.01f;
	//}
	//if (CInput::GetKeyPress('W'))
	//	m_Position.z += 0.05f;
	//if (CInput::GetKeyPress('S'))
	//	m_Position.z -= 0.05f;
	//if (CInput::GetKeyPress('D'))
	//	m_Position.x -= 0.05f;
	//if (CInput::GetKeyPress('A'))
	//	m_Position.x += 0.05f;
}

void CPlayer::Draw()
{
	//���C�e�B���O�L��
	glEnable(GL_LIGHTING);

	//�e�N�X�`���ޔ�
	glMatrixMode(GL_MODELVIEW);
	glPushMatrix();

	//�ړ���]�g��k���}�g���N�X�ݒ�
	glTranslatef(m_Position.x, m_Position.y, m_Position.z);
	glRotatef(m_Rotation.y, 0.0f, 1.0f, 0.0f);
	glScalef(m_Scale.x, m_Scale.y, m_Scale.z);

	m_Model->Draw();

	//�}�g���N�X����
	glMatrixMode(GL_MODELVIEW);
	glPopMatrix();

	//���C�e�B���O����
	glDisable(GL_LIGHTING);
}
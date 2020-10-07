

#include "main.h"
#include "manager.h"
#include "model.h"

#include "tank.h"
#include "input.h"

void CTank::Init()
{
	m_Body = new CModel();
	m_Body->Load("asset\\model\\tank01.obj");

	m_Head = new CModel();
	m_Head->Load("asset\\model\\tank02.obj");

	m_Turret = new CModel();
	m_Turret->Load("asset\\model\\tank03.obj");

	m_Position   = Vector3(0.0f, 0.0f, 0.0f);
	m_Position02 = Vector3(0.0f, 0.0f, 0.0f);
	m_Position03 = Vector3(0.0f, 0.0f, 0.0f);
	m_Rotation   = Vector3(0.0f, 0.0f, 0.0f);
	m_Scale		 = Vector3(2.0f, 2.0f, 2.0f);

	m_HeadRotate = Vector3(0.0f, 0.0f, 0.0f);
	m_TurretRotate = Vector3(0.0f, 0.0f, 0.0f);
}

void CTank::Uninit()
{
	m_Body->Unload();
	delete m_Body;
	m_Head->Unload();
	delete m_Head;
	m_Turret->Unload();
	delete m_Turret;
}

void CTank::Update()
{
	if (CInput::GetKeyPress('W'))
	{
		m_Position.z += 0.3f;
	}
	if (CInput::GetKeyPress('S'))
	{
		m_Position.z -= 0.3f;
	}
	if (CInput::GetKeyPress('A'))
	{
		m_HeadRotate.y += 1.0f;
	}
	if (CInput::GetKeyPress('D'))
	{
		m_HeadRotate.y -= 1.0f;
	}
	if (CInput::GetKeyPress('Q'))
	{
		m_TurretRotate.z += 1.0f;
	}
	if (CInput::GetKeyPress('Z'))
	{
		m_TurretRotate.z -= 1.0f;
	}
}

void CTank::Draw()
{
	//���C�e�B���O�L��
	glEnable(GL_LIGHTING);

	//�e�N�X�`���ޔ�
	glMatrixMode(GL_MODELVIEW);
	glPushMatrix();

	//�e�̃}�g���N�X�ݒ�
	glTranslatef(m_Position.x, m_Position.y, m_Position.z);
	glRotatef(m_Rotation.y, 0.0f, 1.0f, 0.0f);
	glScalef(m_Scale.x, m_Scale.y, m_Scale.z);

	m_Body->Draw();

	//�q�̃}�g���N�X�ݒ�...�e�̃}�g���N�X*�q�̃}�g���N�X
	glTranslatef(m_Position02.x,m_Position02.y + 0.5f,m_Position02.z);
	glRotatef( m_HeadRotate.y, 0.0f, 1.0f, 0.0f);

	m_Head->Draw();

	//���̃}�g���N�X�ݒ�...�e�̃}�g���N�X*�q�̃}�g���N�X*���̃}�g���N�X?
	glTranslatef(m_Position03.x,m_Position03.y,m_Position03.z);
	glRotatef(m_TurretRotate.y, 0.0f, 1.0f, 0.0f);
	glRotatef(m_TurretRotate.y, 1.0f, 0.0f, 1.0f);

	m_Turret->Draw();
	//�C�������񂵂���
}
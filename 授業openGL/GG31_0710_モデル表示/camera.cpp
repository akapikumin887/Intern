//camera.cpp

#include "main.h"
#include "camera.h"
#include "input.h"

void CCamera::Init()
{
	m_Position	= Vector3(0.0f, 5.0f, -20.0f);
	m_Rotation  = Vector3(0.0f,0.0f,0.0f);
	m_Scale		= Vector3(1.0f,1.0f,1.0f);

	m_Target	= Vector3(0.0f,0.0f,0.0f);
}

void CCamera::Uninit()
{

}

void CCamera::Update()
{
	if (CInput::GetKeyPress(VK_LEFT))
	{
		m_Position.x += 0.1f;
		m_Target.x += 0.1f;
	}
	if (CInput::GetKeyPress(VK_RIGHT))
	{
		m_Position.x -= 0.1f;
		m_Target.x -= 0.1f;
	}
	if (CInput::GetKeyPress(VK_UP))
	{
		m_Position.z += 0.1f;
		m_Target.z += 0.1f;
	}
	if (CInput::GetKeyPress(VK_DOWN))
	{
		m_Position.z -= 0.1f;
		m_Target.z -= 0.1f;
	}
}

void CCamera::Draw()
{
	//ビューアスペクト
	glViewport(0,0,SCREEN_WIDTH,SCREEN_HEIGHT);

	//アスペクト比
	double aspect = (double)SCREEN_WIDTH / (double)SCREEN_HEIGHT;

	//プロジェクションマトリクス
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	gluPerspective(45.0f,aspect,0.1f,100.0f);

	//ビューマトリクス
	glMatrixMode(GL_MODELVIEW);
	glLoadIdentity();
	gluLookAt((double)m_Position.x,(double)m_Position.y,(double)m_Position.z,
		      (double)m_Target.x,(double)m_Target.y,(double)m_Target.z,
		       0.0f,1.0f,0.0f);
}
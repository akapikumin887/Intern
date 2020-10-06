//enemy.cpp

#include "main.h"
#include "renderer.h"
#include "model.h"
#include "enemy.h"

void CEnemy::Init()
{
	m_Scale = D3DXVECTOR3(0.5f, 0.5f, 0.5f);
	m_Position = D3DXVECTOR3(0.0f, 0.0f, 0.0f);
	m_Rotation = D3DXVECTOR3(0.0f, 0.0f, 0.0f);

	m_MoveVec = D3DXVECTOR3(rand() % 4 + 1, rand() % 3 + 1, rand() % 5 + 1);
}

void CEnemy::Uninit()
{
}

void CEnemy::Update()
{
	if(m_Num != 1)
		m_Position += m_MoveVec * 0.05f;
	else
		m_Position += m_MoveVec * 0.075f;

	if (m_Position.x > 19.5f || m_Position.x < -19.5f)
		m_MoveVec.x *= -1;
	if (m_Position.y > 19.0f || m_Position.y < 0.0f)
		m_MoveVec.y *= -1;
	if (m_Position.z > 19.5f || m_Position.z < -19.5f)
		m_MoveVec.z *= -1;
}

void CEnemy::Draw()
{
	//マトリクス設定
	D3DXMATRIX world, scale, rot, trans;
	D3DXMatrixScaling(&scale, m_Scale.x, m_Scale.y, m_Scale.z);
	D3DXMatrixRotationYawPitchRoll(&rot, m_Rotation.y, m_Rotation.x, m_Rotation.z);
	D3DXMatrixTranslation(&trans, m_Position.x, m_Position.y, m_Position.z);
	world = scale * rot * trans;

	CRenderer::SetWorldMatrix(&world);

	switch (m_Num)
	{
	case 1:
		m_Enemy01->Draw();
		break;
	case 2:
		m_Enemy02->Draw();
		break;
	case 3:
		m_Enemy03->Draw();
		break;
	}
}

void CEnemy::Load()
{
	m_Enemy01 = new CModel();
	m_Enemy01->Load((CModel::fileName[CModel::MODEL_ENEMY01].c_str()));

	m_Enemy02 = new CModel();
	m_Enemy02->Load((CModel::fileName[CModel::MODEL_ENEMY02].c_str()));

	m_Enemy03 = new CModel();
	m_Enemy03->Load((CModel::fileName[CModel::MODEL_ENEMY03].c_str()));
}

void CEnemy::Unload()
{
	m_Enemy01->Unload();
	delete m_Enemy01;
	m_Enemy02->Unload();
	delete m_Enemy02;
	m_Enemy03->Unload();
	delete m_Enemy03;
}

void CEnemy::SetNum(int num)
{
	//実質的なinitかもしれない
	m_Num = num;

	switch (m_Num)
	{
	case 1:
		//備考:ちょっと早い
		m_Scale = D3DXVECTOR3(0.3f, 0.3f, 0.3f);
		m_Position = D3DXVECTOR3(-1.0f, 1.0f, 0.0f);
		m_Collision = 0.5f; 
		m_Score = 2000;
		break;
	case 2:
		m_Scale = D3DXVECTOR3(0.4f, 0.4f, 0.4f);
		m_Position = D3DXVECTOR3(0.0f, 1.0f, 0.0f);
		m_Collision = 0.5f;
		m_Score = 1000;
		break;
	case 3:
		m_Scale = D3DXVECTOR3(0.5f, 0.5f, 0.5f);
		m_Position = D3DXVECTOR3(1.0f, 1.0f, 0.0f);
		m_Collision = 1.0f;
		m_Score = 500;
		break;
	}
	m_MoveVec -= m_Position;
}
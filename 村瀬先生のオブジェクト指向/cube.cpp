//cube.cpp

#include "main.h"
#include "renderer.h"
#include "model.h"
//#include "manager.h"
#include "cube.h"

void CCube::Init()
{
	m_Position	= D3DXVECTOR3(0.0f, 0.0f, 0.0f);
	m_Rotation	= D3DXVECTOR3(0.0f, 0.0f, 0.0f);
	m_Scale		= D3DXVECTOR3(0.5f,0.5f,0.5f);
}

void CCube::Uninit()
{
}

void CCube::Update()
{

}

void CCube::Draw()
{
	//マトリクス設定
	D3DXMATRIX world, scale, rot, trans;
	D3DXMatrixScaling(&scale, m_Scale.x, m_Scale.y, m_Scale.z);
	D3DXMatrixRotationYawPitchRoll(&rot, m_Rotation.y, m_Rotation.x, m_Rotation.z);
	D3DXMatrixTranslation(&trans, m_Position.x, m_Position.y, m_Position.z);
	world = scale * rot * trans;

	CRenderer::SetWorldMatrix(&world);

	m_Model->Draw();
}

void CCube::Load()
{
	m_Model = new CModel();
	m_Model->Load((CModel::fileName[CModel::MODEL_CUBE].c_str()));
}

void CCube::Unload()
{
	m_Model->Unload();
	delete m_Model;
}
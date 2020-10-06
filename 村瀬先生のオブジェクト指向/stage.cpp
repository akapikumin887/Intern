//stage.cpp

#include "main.h"
#include "renderer.h"
#include "model.h"
#include "stage.h"

void CStage::Init()
{
	m_Position = D3DXVECTOR3(0.0f, 0.0f, 0.0f);
	m_Rotation = D3DXVECTOR3(0.0f, 0.0f, 0.0f);
	m_Scale    = D3DXVECTOR3(20.0f, 10.0f, 20.0f);
}

void CStage::Uninit()
{

}

void CStage::Update()
{

}

void CStage::Draw()
{
	//ライト無効
	LIGHT light;
	light.Enable = false;
	CRenderer::SetLight(light);

	//マトリクス設定
	D3DXMATRIX world, scale, rot, trans;
	D3DXMatrixScaling(&scale, m_Scale.x, m_Scale.y, m_Scale.z);
	D3DXMatrixRotationYawPitchRoll(&rot, m_Rotation.y, m_Rotation.x, m_Rotation.z);
	D3DXMatrixTranslation(&trans, m_Position.x, m_Position.y, m_Position.z);
	world = scale * rot * trans;

	CRenderer::SetWorldMatrix(&world);

	m_Model->Draw();
}

void CStage::Load()
{
	m_Model = new CModel();
	m_Model->Load((CModel::fileName[CModel::MODEL_STAGE].c_str()));
}

void CStage::Unload()
{
	m_Model->Unload();
	delete m_Model;
}


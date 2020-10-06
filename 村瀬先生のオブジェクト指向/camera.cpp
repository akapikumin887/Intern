#include "main.h"
#include "renderer.h"
#include "model.h"
#include "fade.h"
#include "manager.h"
#include "camera.h"
#include "input.h"
#include "scene.h"
#include "player.h"
#include "mouse.h"

void CCamera::Init()
{
	m_Position = D3DXVECTOR3(0.0f,2.0f,-1.0f);
	m_Target   = D3DXVECTOR3(0.0f,1.0f, 0.0f);
}

void CCamera::Uninit()
{

}

void CCamera::Update()
{
	CPlayer* player = CManager::GetScene()->GetGameObject<CPlayer>(LAYER_3D);

	m_Target = player->GetTarget();
	m_Position = player->GetPosition() + player->GetVector(VECTOR_BACKWORD) * 4.0f + D3DXVECTOR3(0.0f,2.0f,0.0f);

	if (m_Position.x > 19.0f)
		m_Position.x = 19.0f;
	if (m_Position.x < -19.0f)
		m_Position.x = -19.0f;

	if (m_Position.z > 19.0f)
		m_Position.z = 19.0f;
	if (m_Position.z < -19.0f)
		m_Position.z = -19.0f;

}

void CCamera::Draw()
{
	//ビューマトリクス設定
	D3DXMatrixLookAtLH(&m_ViewMatrix,&m_Position,&m_Target,
		&D3DXVECTOR3(0.0f,1.0f,0.0f));

	CRenderer::SetViewMatrix(&m_ViewMatrix);

	//プロジェクションマトリクス設定
	D3DXMatrixPerspectiveFovLH(&m_ProjectionMatrix,1.0f,
		(float)SCREEN_WIDTH / SCREEN_HEIGHT,1.0f,1000.0f);

	CRenderer::SetProjectionMatrix(&m_ProjectionMatrix);
}

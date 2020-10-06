//bullet.cpp
#include "main.h"
#include "renderer.h"
#include "model.h"
#include "bullet.h"
#include "enemy.h"
#include "game.h"
#include "manager.h"
#include "explosion.h"
#include "player.h"
#include "score.h"

void CBullet::Init()
{
	m_Position = D3DXVECTOR3(0.0f, 1.0f, 0.0f);
	m_Rotation = D3DXVECTOR3(0.0f, 1.0f, 0.0f);
	m_Scale = D3DXVECTOR3(0.25f, 0.25f, 0.25f);
	m_BoundCount = 0;
	m_Forward = CManager::GetScene()->GetGameObject<CPlayer>(LAYER_3D)->GetBulletTarget();


	int charge = CManager::GetScene()->GetGameObject<CPlayer>(LAYER_3D)->GetCharge();
	if (charge > 150)
		m_BulletLevel = 3;
	else if(charge > 60)
		m_BulletLevel = 2;
	else
		m_BulletLevel = 1;

	switch (m_BulletLevel)
	{
	case 1:
		m_BoundMax = 1;
		m_ScorePower = 1.0f;
		m_BoundPower = 1.1f;
		break;
	case 2:
		m_BoundMax = 2;
		m_ScorePower = 1.3f;
		m_BoundPower = 1.3f;
		break;
	case 3:
		m_BoundMax = 4;
		m_ScorePower = 1.5f;
		m_BoundPower = 1.4f;
		break;
	}
}

void CBullet::Uninit()
{

}

void CBullet::Update()
{
	CScene* scene = CManager::GetScene();
	std::vector<CEnemy*> enemyList = scene->GetGameObjects<CEnemy>(LAYER_3D);
	
	m_Position += m_Forward * 0.02f;

	//壁衝突時の処理
	if (m_Position.x > 19.0f || m_Position.x < -19.0f)
	{
		if (m_BoundCount < m_BoundMax)
		{
			m_Forward.x *= -1;
			m_BoundCount++;
		}
		else
		{
			scene->AddGameObject<CExplosion>(LAYER_EFFECT)->SetPosition(m_Position);
			SetDestroy();
		}
	}
	if (m_Position.y > 19.0f || m_Position.y < 0.0f)
	{
		if (m_BoundCount < m_BoundMax)
		{
			m_Forward.y *= -1;
			m_BoundCount++;
		}
		else
		{
			scene->AddGameObject<CExplosion>(LAYER_EFFECT)->SetPosition(m_Position);
			SetDestroy();
		}
	}
	if (m_Position.z > 19.0f || m_Position.z < -19.0f)
	{
		if (m_BoundCount < m_BoundMax)
		{
			m_Forward.z *= -1;
			m_BoundCount++;
		}
		else
		{
			scene->AddGameObject<CExplosion>(LAYER_EFFECT)->SetPosition(m_Position);
			SetDestroy();
		}
	}

	//m_Count++;
	//弾が経過フレームで消える処理
	//if (m_Count > 60)
	//{
	//	scene->AddGameObject<CExplosion>(LAYER_EFFECT)->SetPosition(m_Position);
	//	SetDestroy();
	//	return;
	//}

	//敵に命中時消える処理
	for (CEnemy* enemy : enemyList)
	{
		D3DXVECTOR3 enemyPosition = enemy->GetPosition();

		D3DXVECTOR3 direction = m_Position - enemyPosition;
		float length = D3DXVec3Length(&direction);

		if (length < enemy->GetLength())
		{
			int enemyScore = enemy->GetScore();
			int score = enemyScore * m_ScorePower * (int)powf(m_BoundPower, m_BoundCount);
			scene->GetGameObject<CScore>(LAYER_2D)->AddScore(score);

			scene->AddGameObject<CExplosion>(LAYER_EFFECT)->SetPosition(m_Position);

			enemy->SetDestroy();
			return;
		}
	}
}

void CBullet::Draw()
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

void CBullet::Load()
{
	m_Model = new CModel();
	m_Model->Load((CModel::fileName[CModel::MODEL_BULLET].c_str()));
}

void CBullet::Unload()
{
	m_Model->Unload();
	delete m_Model;
}

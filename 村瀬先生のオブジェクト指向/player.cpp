//player.cpp

#include "main.h"
#include "renderer.h"
#include "model.h"
#include "manager.h"
#include "player.h"
#include "input.h"
#include "scene.h"
#include "bullet.h"
#include "mouse.h"
#include "gameUI.h"
#include "time.h"

void CPlayer::Init()
{
	m_Model = new CModel();
	m_Model->Load(CModel::fileName[CModel::MODEL_PLAYER].c_str());
	assert(m_Model);

	m_Model->SetColor(D3DXCOLOR(1.0f,1.0f,1.0f,1.0f));

	m_Position	= D3DXVECTOR3(0.0f,0.5f,0.0f);
	m_Rotation	= D3DXVECTOR3(0.0f,0.0f,0.0f);
	m_Scale		= D3DXVECTOR3(0.5f,0.5f,0.5f);
	m_PlayerTarget = GetVector(VECTOR_FORWARD) * 3.0f + D3DXVECTOR3(0.0f,.0f,4.0f);
	D3DXQuaternionIdentity(&m_Quaternion);

	m_MouseX = CMouse::GetmpX();
	m_MouseY = 400.0f;			//Yは初期化時に値を固定化しておくことで変な一向いたままになってしまうことを防ぐ
	m_TargetRotateY = 0.0f;
	m_RestBullet = 10;
	m_Charge = 0;
	m_BulletMove = D3DXVECTOR3(0.0f,0.0f,0.0f);
	m_ChargeFlag = false;
	m_Color = D3DXCOLOR(1.0f,1.0f,1.0f,1.0f);
	m_Freeze = true;
}

void CPlayer::Uninit()
{
	m_Model->Unload();
	delete m_Model;
}

void CPlayer::Update()
{
	//経過時間管理用クラスの取得
	CTime* time = CManager::GetScene()->GetGameObject<CTime>(LAYER_2D);

	//start,finish辺りの値を管理しているクラスの取得
	CGameUI* gameUI = CManager::GetScene()->GetGameObject<CGameUI>(LAYER_2D);

	//プレイヤーの動作開始
	if (gameUI->GetCount() > 200)
		m_Freeze = false;

	if(gameUI->GetEndFlag())
		m_Freeze = true;

	//前方向ベクトルの取得
	D3DXVECTOR3 forward = GetVector(VECTOR_FORWARD);
	
	//マウスによるプレイヤー制御
	m_Rotation.y += (CMouse::GetmpX() - m_MouseX) / 200;
	m_TargetRotateY += (m_MouseY - CMouse::GetmpY() ) / 100;

	//あえて1f前のマウスの座標を保存
	m_MouseX = CMouse::GetmpX();
	m_MouseY = CMouse::GetmpY();

	//UIがある程度消えてから始まる
	if (!m_Freeze)
	{
		//プレイヤー移動
		if (CInput::GetKeyPress('W'))
		{
			m_Position.x += GetVector(VECTOR_FORWARD).x * 0.1f;
			m_Position.z += GetVector(VECTOR_FORWARD).z * 0.1f;
			m_PlayerTarget.x += GetVector(VECTOR_FORWARD).x * 0.1f;
			m_PlayerTarget.z += GetVector(VECTOR_FORWARD).z * 0.1f;
		}
		if (CInput::GetKeyPress('S'))
		{
			m_Position.z += GetVector(VECTOR_BACKWORD).z * 0.1f;
			m_Position.x += GetVector(VECTOR_BACKWORD).x * 0.1f;
			m_PlayerTarget.x += GetVector(VECTOR_BACKWORD).x * 0.1f;
			m_PlayerTarget.z += GetVector(VECTOR_BACKWORD).z * 0.1f;
		}
		if (CInput::GetKeyPress('A'))
		{
			m_Position.x += GetVector(VECTOR_LEFTSIDE).x * 0.1f;
			m_Position.z += GetVector(VECTOR_LEFTSIDE).z * 0.1f;
			m_PlayerTarget.x += GetVector(VECTOR_LEFTSIDE).x * 0.1f;
			m_PlayerTarget.z += GetVector(VECTOR_LEFTSIDE).z * 0.1f;

			m_Rotation.y -= 0.02f;
		}
		if (CInput::GetKeyPress('D'))
		{
			m_Position.x += GetVector(VECTOR_RIGHTSIDE).x * 0.1f;
			m_Position.z += GetVector(VECTOR_RIGHTSIDE).z * 0.1f;
			m_PlayerTarget.x += GetVector(VECTOR_RIGHTSIDE).x * 0.1f;
			m_PlayerTarget.z += GetVector(VECTOR_RIGHTSIDE).z * 0.1f;

			m_Rotation.y += 0.02f;
		}

		//視点変更
		if (CInput::GetKeyPress('Q'))
			m_Rotation.y -= 0.04f;
		if (CInput::GetKeyPress('E'))
			m_Rotation.y += 0.04f;
	}

	//壁の当たり判定
	if (m_Position.x > 19.0f)
		m_Position.x = 19.0f;
	if (m_Position.x < -19.0f)
		m_Position.x = -19.0f;

	if (m_Position.z >  19.0f)
		m_Position.z =  19.0f;
	if (m_Position.z < -19.0f)
		m_Position.z = -19.0f;

	//プレイヤーの狙い及びカメラの注視点の変更
	m_PlayerTarget = m_Position + GetVector(VECTOR_FORWARD) * 10.0f + GetVector(VECTOR_UPWARD) * m_TargetRotateY;

	//弾を発射するベクトルの設定
	m_BulletMove = m_PlayerTarget - m_Position;

	if (m_ChargeFlag)
	{
		m_Charge++;
		if (m_Charge < 50)
		{
			m_Color.b -= 0.02f;
			m_Model->SetColor(m_Color);
		}
		else
		{
			m_Color.g -= 0.01f;
			m_Model->SetColor(m_Color);
		}
	}

	//UIがある程度消えてから始まる
	if (!m_Freeze)
	{
		if (CInput::GetKeyTrigger(VK_SPACE) || CInput::GetKeyTrigger(VK_LBUTTON))
		{
			m_ChargeFlag = true;
			m_Charge = 0;
		}
		if (CInput::GetKeyRelease(VK_SPACE) || CInput::GetKeyRelease(VK_LBUTTON))
		{
			if (m_ChargeFlag)
			{
				m_RestBullet--;
				m_Color = D3DXCOLOR(1.0f, 1.0f, 1.0f, 1.0f);
				m_Model->SetColor(m_Color);
				CScene* scene = CManager::GetScene();
				scene->AddGameObject<CBullet>(LAYER_3D)->SetPosition(m_Position);
				m_ChargeFlag = false;
			}
		}
	}

	//正規化
	D3DXQuaternionNormalize(&m_Quaternion,&m_Quaternion);
}

void CPlayer::Draw()
{
	//マトリクス設定
	D3DXMATRIX world, scale, rot, trans;
	D3DXMatrixScaling(&scale, m_Scale.x, m_Scale.y, m_Scale.z);
	D3DXMatrixRotationYawPitchRoll(&rot, m_Rotation.y, m_Rotation.x, m_Rotation.z);
	//D3DXMatrixRotationQuaternion(&rot,&m_Quaternion);
	D3DXMatrixTranslation(&trans, m_Position.x, m_Position.y, m_Position.z);
	world = scale * rot * trans;

	CRenderer::SetWorldMatrix(&world);

	m_Model->Draw();
}

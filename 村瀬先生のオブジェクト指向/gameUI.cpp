//gameUI.cpp

#include "main.h"
#include "renderer.h"
#include "gameUI.h"
#include "texture.h"
#include "manager.h"
#include "time.h"
#include "player.h"

void CGameUI::Init()
{
	VERTEX_3D vertex[4];
	{
		vertex[0].Position = D3DXVECTOR3(-1.0f, 1.0f, 0.0f);
		vertex[0].Normal = D3DXVECTOR3(0.0f, 1.0f, 0.0f);
		vertex[0].Diffuse = D3DXVECTOR4(1.0f, 1.0f, 1.0f, 1.0f);
		vertex[0].TexCoord = D3DXVECTOR2(0.0f, 0.0f);

		vertex[1].Position = D3DXVECTOR3(1.0f, 1.0f, 0.0f);
		vertex[1].Normal = D3DXVECTOR3(0.0f, 1.0f, 0.0f);
		vertex[1].Diffuse = D3DXVECTOR4(1.0f, 1.0f, 1.0f, 1.0f);
		vertex[1].TexCoord = D3DXVECTOR2(10.0f, 0.0f);

		vertex[2].Position = D3DXVECTOR3(-1.0f, -1.0f, 0.0f);
		vertex[2].Normal = D3DXVECTOR3(0.0f, 1.0f, 0.0f);
		vertex[2].Diffuse = D3DXVECTOR4(1.0f, 1.0f, 1.0f, 1.0f);
		vertex[2].TexCoord = D3DXVECTOR2(0.0f, 10.0f);

		vertex[3].Position = D3DXVECTOR3(1.0f, -1.0f, 0.0f);
		vertex[3].Normal = D3DXVECTOR3(0.0f, 1.0f, 0.0f);
		vertex[3].Diffuse = D3DXVECTOR4(1.0f, 1.0f, 1.0f, 1.0f);
		vertex[3].TexCoord = D3DXVECTOR2(10.0f, 10.0f);
	}

	//頂点バッファ生成
	D3D11_BUFFER_DESC bd;
	ZeroMemory(&bd, sizeof(bd));
	bd.Usage = D3D11_USAGE_DYNAMIC;
	bd.ByteWidth = sizeof(VERTEX_3D) * 4;
	bd.BindFlags = D3D11_BIND_VERTEX_BUFFER;
	bd.CPUAccessFlags = D3D11_CPU_ACCESS_WRITE;

	D3D11_SUBRESOURCE_DATA sd;
	ZeroMemory(&sd, sizeof(sd));
	sd.pSysMem = vertex;

	CRenderer::GetDevice()->CreateBuffer(&bd, &sd, &m_VertexBuffer);

	//テクスチャ読み込み
	D3DX11CreateShaderResourceViewFromFile(CRenderer::GetDevice(),
		"asset/texture/ready.png",
		NULL,
		NULL,
		&m_Ready,
		NULL);

	//エラー通知でデバックを簡単に
	assert(m_Ready);

	//テクスチャ読み込み
	D3DX11CreateShaderResourceViewFromFile(CRenderer::GetDevice(),
		"asset/texture/go.png",
		NULL,
		NULL,
		&m_Go,
		NULL);

	//エラー通知でデバックを簡単に
	assert(m_Go);

	//テクスチャ読み込み
	D3DX11CreateShaderResourceViewFromFile(CRenderer::GetDevice(),
		"asset/texture/finish.png",
		NULL,
		NULL,
		&m_Finish,
		NULL);

	//エラー通知でデバックを簡単に
	assert(m_Finish);

	//テクスチャ読み込み
	D3DX11CreateShaderResourceViewFromFile(CRenderer::GetDevice(),
		"asset/texture/restbullet.png",
		NULL,
		NULL,
		&m_RBullet,
		NULL);

	//エラー通知でデバックを簡単に
	assert(m_RBullet);

	//テクスチャ読み込み
	D3DX11CreateShaderResourceViewFromFile(CRenderer::GetDevice(),
		"asset/texture/bullet.png",
		NULL,
		NULL,
		&m_Bullet,
		NULL);

	//エラー通知でデバックを簡単に
	assert(m_Bullet);

	m_Alpha = 1.0f;
	m_ReadyPosition = SCREEN_WIDTH * 1.5f;
	m_GoPosition    = SCREEN_WIDTH * 1.5f;
	m_FinishPosition = SCREEN_WIDTH * 1.5f;

	m_Count = 0;

	m_StartFlag = false;
	m_EndFlag   = false;
	m_SceneFadeFlag = false;
}

void CGameUI::Uninit()
{
	m_VertexBuffer->Release();
	m_Ready->Release();
	m_Go->Release();
	m_Finish->Release();
	m_RBullet->Release();
}

void CGameUI::Update()
{
	m_Count++;

	CTime* time = CManager::GetScene()->GetGameObject<CTime>(LAYER_2D);
	CPlayer* player = CManager::GetScene()->GetGameObject<CPlayer>(LAYER_3D);

	if (m_Count > 195 && !m_StartFlag)
	{
		if (!time->GetCountFlag())
			time->CountStart();
		m_Alpha -= 0.1f;
		if (m_Alpha < 0.0f)
			m_StartFlag = true;
	}

	//残弾数か時間が0になったらエンドフラグを立てる
	if (player->GetRestBullet() == 0 || time->GetCount() == 0)
		if (m_EndFlag == false)
		{
			m_EndFlag = true;
			m_Count = 0;		//カウントを初期化して次に備える
		}
}

void CGameUI::Draw()
{
	//残弾数取得
	int restBullet = CManager::GetScene()->GetGameObject<CPlayer>(LAYER_3D)->GetRestBullet();

	CTexture::Draw(m_VertexBuffer, m_RBullet, D3DXVECTOR2(SCREEN_WIDTH / 6 * 5, 32.5f),
		D3DXVECTOR2(300.0f, 75.0f), D3DXVECTOR2(0.0f, 0.0f), D3DXVECTOR2(1.0f, 1.0f));


	for (int i = 0; i < restBullet; i++)
	{
		//残弾数の描画
		CTexture::Draw(m_VertexBuffer, m_Bullet, D3DXVECTOR2(1215.0f - 39.0f * i, 100.0f),
			D3DXVECTOR2(37.5f,65.0f), D3DXVECTOR2(0.0f,0.0f), D3DXVECTOR2(1.0f,1.0f));
	}

	if (m_Count > 45 && !m_StartFlag)
	{
		CTexture::SetAlpha(m_Alpha);
		CTexture::Draw(m_VertexBuffer, m_Ready,
			D3DXVECTOR2(m_ReadyPosition, center.y * 0.25f), D3DXVECTOR2(1000.0f, 360.0f),
			D3DXVECTOR2(0.0f, 0.0f), D3DXVECTOR2(1.0f, 1.0f));
		if(m_ReadyPosition > center.x * 0.5f + 60.0f)
			m_ReadyPosition -= 30.0f;
	}
	if (m_Count > 120 && !m_StartFlag)
	{
		CTexture::SetAlpha(m_Alpha);
		CTexture::Draw(m_VertexBuffer, m_Go,
			D3DXVECTOR2(m_GoPosition, center.y * 0.75f), D3DXVECTOR2(1000.0f, 360.0f),
			D3DXVECTOR2(0.0f, 0.0f), D3DXVECTOR2(1.0f, 1.0f));
		if (m_GoPosition > center.x * 0.5f + 30.0f)
			m_GoPosition -= 30.0f;
	}

	if (m_EndFlag)
	{
		CTexture::Draw(m_VertexBuffer, m_Finish,
			D3DXVECTOR2(m_FinishPosition, center.y * 0.5f), D3DXVECTOR2(1000.0f, 360.0f),
			D3DXVECTOR2(0.0f, 0.0f), D3DXVECTOR2(1.0f, 1.0f));
		if (m_FinishPosition > center.x * 0.5f + 30.0f)
			m_FinishPosition -= 30.0f;
		if (m_Count > 180)
			m_SceneFadeFlag = true;
	}
}


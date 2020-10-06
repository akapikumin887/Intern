//resultUI.cpp

#include "main.h"
#include "renderer.h"
#include "manager.h"
#include "game.h"
#include "resultUI.h"
#include "texture.h"
#include "score.h"
#include "scene.h"
#include "gameUI.h"
#include "mouse.h"

void CResultUI::Init()
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
		"asset/texture/result.png",
		NULL,
		NULL,
		&m_ResultUI,
		NULL);

	//エラー通知でデバックを簡単に
	assert(m_ResultUI);

	//テクスチャ読み込み
	D3DX11CreateShaderResourceViewFromFile(CRenderer::GetDevice(),
		"asset/texture/number.png",
		NULL,
		NULL,
		&m_Score,
		NULL);

	//エラー通知でデバックを簡単に
	assert(m_Score);

	m_DrawPosition = -center.y;
	m_ResultFlag = false;
	m_Mouse = true;
}

void CResultUI::Uninit()
{
	m_VertexBuffer->Release();
	m_ResultUI->Release();
	m_Score->Release();
}

void CResultUI::Update()
{
	if (CManager::GetScene()->GetGameObject<CGameUI>(LAYER_2D)->GetFadeFlag() && m_Mouse)
	{
		CManager::GetScene()->GetGameObject<CMouse>(LAYER_2D)->SetDestroy();
		m_Mouse = false;
	}
}

void CResultUI::Draw()
{
	//ゲームが終わったら上から降ってくる
	if(CManager::GetScene()->GetGameObject<CGameUI>(LAYER_2D)->GetFadeFlag())
	{
		CScore* score = CManager::GetScene()->GetGameObject<CScore>(LAYER_2D);

		CTexture::Draw(m_VertexBuffer, m_ResultUI, D3DXVECTOR2(center.x, m_DrawPosition),
			center * 2.0f, D3DXVECTOR2(0.0f, 0.0f), D3DXVECTOR2(1.0f, 1.0f));


		int nowScore = score->GetScore();
		D3DXVECTOR2 drawScore = D3DXVECTOR2(0.0f, 0.0f);

		for (int i = 0; i < DIGIT; i++)
		{
			drawScore.x = nowScore % 10;
			drawScore.y = 0.0f;
			nowScore *= 0.1f;
			if (drawScore.x > 4.0f)
			{
				drawScore.x -= 5.0f;
				drawScore.y++;
			}
			drawScore *= 0.2f;

			CTexture::Draw(m_VertexBuffer, m_Score,
				D3DXVECTOR2(center.x + (size.x * ((DIGIT - 1)* 0.5f - i)), m_DrawPosition),
				size, drawScore, D3DXVECTOR2(drawScore.x + 0.2f, drawScore.y + 0.2f));
		}
		if (m_DrawPosition < center.y)
			m_DrawPosition += 30.0f;
		else
			m_ResultFlag = true;
	}
}


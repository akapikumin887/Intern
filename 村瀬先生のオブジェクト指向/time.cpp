//time.cpp

#include "main.h"
#include "renderer.h"
#include "time.h"
#include "texture.h"
#include "game.h"
#include "manager.h"
#include "gameUI.h"

void CTime::Init()
{
	m_TimeCount = 0;
	m_Time = 15;
	m_Flag = false;
}

void CTime::Uninit()
{

}

void CTime::Update()
{
	CGameUI* gameUI = CManager::GetScene()->GetGameObject<CGameUI>(LAYER_2D);

	if(m_Flag)
		m_TimeCount++;
	if (m_TimeCount >= 60)
	{
		m_Time--;
		m_TimeCount = 0;
	}
	if (gameUI->GetEndFlag())
		CountStop();
}

void CTime::Draw()
{
	int nowTime = m_Time;
	D3DXVECTOR2 drawTime = D3DXVECTOR2(0.0f, 0.0f);

	for (int i = 0; i < DIGIT; i++)
	{
		drawTime.x = nowTime % 10;
		drawTime.y = 0.0f;
		nowTime *= 0.1f;
		if (drawTime.x > 4.0f)
		{
			drawTime.x -= 5.0f;
			drawTime.y++;
		}

		drawTime *= 0.2;
		CTexture::Draw(m_VertexBuffer, m_Texture, 
			D3DXVECTOR2(center.x + (size.x * ((DIGIT - 1)* 0.5f - i)), center.y * 3.0f),
			size, drawTime, D3DXVECTOR2(drawTime.x + 0.2f, drawTime.y + 0.2f));
	}
	CTexture::Draw(m_VertexBuffer, m_TextureMoji, D3DXVECTOR2(center.x,center.y),
		D3DXVECTOR2(size.x * 4.0f,size.y), D3DXVECTOR2(0.0f,0.0f), D3DXVECTOR2(1.0f,1.0f));


}

void CTime::Load()
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
		"asset/texture/number.png",
		NULL,
		NULL,
		&m_Texture,
		NULL);

	//エラー通知でデバックを簡単に
	assert(m_Texture);


	//テクスチャ読み込み
	D3DX11CreateShaderResourceViewFromFile(CRenderer::GetDevice(),
		"asset/texture/time.png",
		NULL,
		NULL,
		&m_TextureMoji,
		NULL);

	//エラー通知でデバックを簡単に
	assert(m_TextureMoji);
}

void CTime::Unload()
{
	m_VertexBuffer->Release();
	m_Texture->Release();
	m_TextureMoji->Release();
}


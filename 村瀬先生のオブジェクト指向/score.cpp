//score.cpp

#include "main.h"
#include "renderer.h"
#include "score.h"
#include "input.h"
#include "texture.h"

void CScore::Init()
{
	m_Score = 0;
	m_DrawScore = 0;
}

void CScore::Uninit()
{

}

void CScore::Update()
{
}

void CScore::Draw()
{
	if (m_Score > m_DrawScore)
		m_DrawScore += 7;
	if (m_Score < m_DrawScore)
		m_DrawScore--;

	int nowScore = m_DrawScore;
	D3DXVECTOR2 drawScore = D3DXVECTOR2(0.0f,0.0f);

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

		CTexture::Draw(m_VertexBuffer, m_Texture,
			D3DXVECTOR2(center.x + (size.x * ((DIGIT - 1)* 0.5f - i)), center.y * 3.5f),
			size, drawScore, D3DXVECTOR2(drawScore.x + 0.2f, drawScore.y + 0.2f));
	}

	CTexture::Draw(m_VertexBuffer, m_TextureMoji, D3DXVECTOR2(center.x, center.y),
		D3DXVECTOR2(size.x * 4.0f * 1.5f, size.y * 1.5f), D3DXVECTOR2(0.0f, 0.0f), D3DXVECTOR2(1.0f, 1.0f));
}

void CScore::Load()
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
		"asset/texture/score.png",
		NULL,
		NULL,
		&m_TextureMoji,
		NULL);

	//エラー通知でデバックを簡単に
	assert(m_TextureMoji);
}

void CScore::Unload()
{
	m_VertexBuffer->Release();
	m_Texture->Release();
	m_TextureMoji->Release();
}

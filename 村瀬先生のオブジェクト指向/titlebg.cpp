//titlebg.cpp

#include "main.h"
#include "renderer.h"
#include "titlebg.h"
#include "texture.h"

void CTitleBG::Init()
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
		"asset/texture/title01.png",
		NULL,
		NULL,
		&m_Texture,
		NULL);

	//エラー通知でデバックを簡単に
	assert(m_Texture);

	//テクスチャ読み込み
	D3DX11CreateShaderResourceViewFromFile(CRenderer::GetDevice(),
		"asset/texture/title02.png",
		NULL,
		NULL,
		&m_TextureMoji,
		NULL);

	//エラー通知でデバックを簡単に
	assert(m_TextureMoji);

	m_Alpha = 0.0f;
	m_Plus = 0.015f;
}

void CTitleBG::Uninit()
{
	m_VertexBuffer->Release();
	m_Texture->Release();
	m_TextureMoji->Release();
}

void CTitleBG::Update()
{
	m_Alpha += m_Plus;
	if (m_Alpha < 0.0f || m_Alpha > 1.0f)
		m_Plus *= -1.0f;
}

void CTitleBG::Draw()
{
	CTexture::Draw(m_VertexBuffer,m_Texture,center * 0.5f,center,D3DXVECTOR2(0.0f,0.0f), D3DXVECTOR2(1.0f,1.0f));
	CTexture::SetAlpha(m_Alpha);
	CTexture::Draw(m_VertexBuffer,m_TextureMoji,D3DXVECTOR2(center.x * 0.5f,center.y * 0.75), D3DXVECTOR2(720.0f,300.0f),D3DXVECTOR2(0.0f,0.0f), D3DXVECTOR2(1.0f,1.0f));
}


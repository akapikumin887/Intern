//mouse.cpp

#include "main.h"
#include "renderer.h"
#include "mouse.h"
#include "camera.h"
#include "manager.h"
#include "scene.h"

void CMouse::Init()
{
	GetCursorPos(&pt);

	GetClientRect(GetWindow(), &cpos);
	GetWindowRect(GetWindow(), &wpos);

	//微調整
	mpX = pt.x - wpos.left - 7.0f;
	mpY = pt.y - wpos.top - 32.0f;
}

void CMouse::Uninit()
{
}

void CMouse::Update()
{
	GetCursorPos(&pt);

	GetClientRect(GetWindow(), &cpos);
	GetWindowRect(GetWindow(), &wpos);

	//微調整
	mpX = pt.x - wpos.left - 7.0f;
	mpY = pt.y - wpos.top - 32.0f;


	//if (GetAsyncKeyState(VK_LBUTTON))
	//{
	//	if (flag == true)
	//	{
	//		flag = false;
	//		count = 60;
	//		return true;
	//	}
	//}
	//else
	//{
	//	flag = true;
	//}
	//count--;
	//return false;
}

void CMouse::Draw()
{
	//ライト無効
	LIGHT light;
	light.Enable = false;
	CRenderer::SetLight(light);

	D3D11_MAPPED_SUBRESOURCE msr;
	CRenderer::GetDeviceContext()->Map(m_VertexBuffer, 0, D3D11_MAP_WRITE_DISCARD, 0, &msr);

	VERTEX_3D* vertex = (VERTEX_3D*)msr.pData;

	//vertex[0].Position = D3DXVECTOR3(mpX - 48.0f, mpY - 48.0f, 0.0f);
	vertex[0].Position = D3DXVECTOR3((SCREEN_WIDTH / 2) - 48.0f, (SCREEN_HEIGHT / 2) - 48.0f - 50.0f, 0.0f);
	vertex[0].Normal = D3DXVECTOR3(0.0f, 1.0f, 0.0f);
	vertex[0].Diffuse = D3DXVECTOR4(1.0f, 1.0f, 1.0f, 1.0f);
	vertex[0].TexCoord = D3DXVECTOR2(0.0f, 0.0f);

	//vertex[1].Position = D3DXVECTOR3(mpX + 48.0f, mpY - 48.0f, 0.0f);
	vertex[1].Position = D3DXVECTOR3((SCREEN_WIDTH / 2) + 48.0f, (SCREEN_HEIGHT / 2) - 48.0f - 50.0f, 0.0f);
	vertex[1].Normal = D3DXVECTOR3(0.0f, 1.0f, 0.0f);
	vertex[1].Diffuse = D3DXVECTOR4(1.0f, 1.0f, 1.0f, 1.0f);
	vertex[1].TexCoord = D3DXVECTOR2(1.0f, 0.0f);

	//vertex[2].Position = D3DXVECTOR3(mpX - 48.0f, mpY + 48.0f, 0.0f);
	vertex[2].Position = D3DXVECTOR3((SCREEN_WIDTH / 2) - 48.0f, (SCREEN_HEIGHT / 2) + 48.0f - 50.0f, 0.0f);
	vertex[2].Normal = D3DXVECTOR3(0.0f, 1.0f, 0.0f);
	vertex[2].Diffuse = D3DXVECTOR4(1.0f, 1.0f, 1.0f, 1.0f);
	vertex[2].TexCoord = D3DXVECTOR2(0.0f, 1.0f);

	//vertex[3].Position = D3DXVECTOR3(mpX + 48.0f, mpY + 48.0f, 0.0f);
	vertex[3].Position = D3DXVECTOR3((SCREEN_WIDTH / 2) + 48.0f, (SCREEN_HEIGHT / 2) + 48.0f - 50.0f, 0.0f);
	vertex[3].Normal = D3DXVECTOR3(0.0f, 1.0f, 0.0f);
	vertex[3].Diffuse = D3DXVECTOR4(1.0f, 1.0f, 1.0f, 1.0f);
	vertex[3].TexCoord = D3DXVECTOR2(1.0f, 1.0f);

	CRenderer::GetDeviceContext()->Unmap(m_VertexBuffer, 0);


	//マトリクス設定
	CRenderer::SetWorldViewProjection2D();			//先生が用意したお手軽関数

	//頂点バッファ設定
	UINT stride = sizeof(VERTEX_3D);
	UINT offset = 0;
	CRenderer::GetDeviceContext()->IASetVertexBuffers(
		0, 1, &m_VertexBuffer, &stride, &offset);

	//マテリアル設定
	MATERIAL material;
	ZeroMemory(&material, sizeof(material));
	material.Diffuse = D3DXCOLOR(1.0f, 1.0f, 1.0f, 1.0f);
	CRenderer::SetMaterial(material);

	//テクスチャ設定
	CRenderer::GetDeviceContext()->PSSetShaderResources(0, 1, &m_Texture);

	//プリミティブトポロジ設定
	CRenderer::GetDeviceContext()->IASetPrimitiveTopology(
		D3D11_PRIMITIVE_TOPOLOGY_TRIANGLESTRIP);

	//ポリゴン描画
	CRenderer::GetDeviceContext()->Draw(4, 0);
}

void CMouse::Load()
{
	VERTEX_3D vertex[4];
	{
		vertex[0].Position = D3DXVECTOR3(-1.0f, 1.0f, 0.0f);
		vertex[0].Normal   = D3DXVECTOR3(0.0f, 1.0f, 0.0f);
		vertex[0].Diffuse  = D3DXVECTOR4(1.0f, 1.0f, 1.0f, 1.0f);
		vertex[0].TexCoord = D3DXVECTOR2(0.0f, 0.0f);

		vertex[1].Position = D3DXVECTOR3(1.0f, 1.0f, 0.0f);
		vertex[1].Normal   = D3DXVECTOR3(0.0f, 1.0f, 0.0f);
		vertex[1].Diffuse  = D3DXVECTOR4(1.0f, 1.0f, 1.0f, 1.0f);
		vertex[1].TexCoord = D3DXVECTOR2(1.0f, 0.0f);

		vertex[2].Position = D3DXVECTOR3(-1.0f, -1.0f, 0.0f);
		vertex[2].Normal   = D3DXVECTOR3(0.0f, 1.0f, 0.0f);
		vertex[2].Diffuse  = D3DXVECTOR4(1.0f, 1.0f, 1.0f, 1.0f);
		vertex[2].TexCoord = D3DXVECTOR2(0.0f, 1.0f);

		vertex[3].Position = D3DXVECTOR3(1.0f, -1.0f, 0.0f);
		vertex[3].Normal   = D3DXVECTOR3(0.0f, 1.0f, 0.0f);
		vertex[3].Diffuse  = D3DXVECTOR4(1.0f, 1.0f, 1.0f, 1.0f);
		vertex[3].TexCoord = D3DXVECTOR2(1.0f, 1.0f);
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
		"asset/texture/target.png",
		NULL,
		NULL,
		&m_Texture,
		NULL);

	//エラー通知でデバックを簡単に
	assert(m_Texture);
}

void CMouse::Unload()
{
	m_VertexBuffer->Release();
	m_Texture->Release();
}

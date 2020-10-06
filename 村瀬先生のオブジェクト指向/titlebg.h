#pragma once
//titlebg.h

#include "gameobject.h"

class CTitleBG : public CGameObject
{
private:
	ID3D11Buffer*				m_VertexBuffer = NULL;
	ID3D11ShaderResourceView*	m_Texture = NULL;
	ID3D11ShaderResourceView*	m_TextureMoji = NULL;

	float m_Alpha,m_Plus;

	const D3DXVECTOR2 center = D3DXVECTOR2(SCREEN_WIDTH,SCREEN_HEIGHT);
public:
	CTitleBG() {}
	~CTitleBG() {}

	void Init();
	void Uninit();
	void Update();
	void Draw();
};
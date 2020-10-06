#pragma once

#include "gameobject.h"

class CPolygon : public CGameObject
{
private:
	ID3D11Buffer*				m_VertexBuffer = NULL;
	ID3D11ShaderResourceView*	m_Texture = NULL;

public:
	CPolygon() {}
	~CPolygon() {}

	void Init()override;
	void Uninit()override;
	void Update()override;
	void Draw()override;
};
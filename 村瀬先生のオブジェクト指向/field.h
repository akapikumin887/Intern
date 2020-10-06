#pragma once
//field.h

#include "gameobject.h"

class CField : public CGameObject
{
private:
	ID3D11Buffer*				m_VertexBuffer = NULL;
	ID3D11ShaderResourceView*	m_Texture = NULL;

public:
	CField() {}
	~CField() {}

	void Init()override;
	void Uninit()override;
	void Update()override;
	void Draw()override;
};
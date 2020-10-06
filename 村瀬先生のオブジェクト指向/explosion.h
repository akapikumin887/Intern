#pragma once
//explotion.h

#include "gameobject.h"

class CExplosion : public CGameObject
{
private:
	int m_Count;

	inline static ID3D11Buffer*			m_VertexBuffer = NULL;
	inline static ID3D11ShaderResourceView*	m_Texture = NULL;

public:
	CExplosion() {}
	~CExplosion() {}

	static void Load();
	static void Unload();

	void Init()override;
	void Uninit()override;
	void Update()override;
	void Draw()override;
};
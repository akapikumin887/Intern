#pragma once
//fade.h

#include "gameobject.h"

class CFade : public CGameObject
{
private:
	inline static ID3D11Buffer*				m_VertexBuffer = NULL;
	inline static ID3D11ShaderResourceView*	m_Texture = NULL;

	inline static float alpha = 1.0f;
	const D3DXVECTOR2 center = D3DXVECTOR2(SCREEN_WIDTH,SCREEN_HEIGHT);
public:
	CFade() {}
	~CFade() {}

	static void Load();
	static void Unload();

	void Init()override;
	void Uninit()override;
	void Update()override;
	void Draw()override;

	enum fadestate
	{
		FADE_NONE,
		FADE_IN,
		FADE_OUT
	};
	inline static fadestate fs = FADE_NONE;
	inline static class CScene*	m_NextScene = NULL;
};
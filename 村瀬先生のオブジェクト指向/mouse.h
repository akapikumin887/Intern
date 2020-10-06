#pragma once
//mouse.h

#include "gameobject.h"

class CMouse : public CGameObject
{
private:
	inline static ID3D11Buffer*			m_VertexBuffer = NULL;
	inline static ID3D11ShaderResourceView*	m_Texture = NULL;

	inline static float mpX = 0.0f, mpY = 0.0f;

	inline static POINT pt;
	inline static RECT wpos, cpos;

	inline static int count = 0;
	inline static int pushcount = 0;

	inline static bool flag = true;

public:
	CMouse() {}
	~CMouse() {}

	static void Load();
	static void Unload();

	void Init()override;
	void Uninit()override;
	void Update()override;
	void Draw()override;

	static float GetmpX() { return mpX; }
	static float GetmpY() { return mpY; }
};
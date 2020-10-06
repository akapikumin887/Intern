#pragma once
//time.h

#include "gameobject.h"

class CTime : public CGameObject
{
private:
	inline static ID3D11Buffer*				m_VertexBuffer = NULL;
	inline static ID3D11ShaderResourceView*	m_Texture = NULL;
	inline static ID3D11ShaderResourceView*	m_TextureMoji = NULL;

	const int   DIGIT = 2;					//Œ…”
	const D3DXVECTOR2 size  = D3DXVECTOR2(75.0f,75.0f);
	const D3DXVECTOR2 center = D3DXVECTOR2((SCREEN_WIDTH / 2), size.y * 0.5f);

	int m_Time;				//Œo‰ßŠÔ‚ğ”‚¦‚é
	int m_TimeCount;		//ƒtƒŒ[ƒ€‚ğ”‚¦‚é

	bool m_Flag;
public:
	CTime() {}
	~CTime() {}

	void Init();
	void Uninit();
	void Update();
	void Draw();

	static void Load();
	static void Unload();

	void CountStart() { m_Flag = true; }
	void CountStop() { m_Flag = false; }
	bool GetCountFlag() { return m_Flag; }
	void SetCount(int count) { m_Time = count; }
	int  GetCount() { return m_Time; }
};
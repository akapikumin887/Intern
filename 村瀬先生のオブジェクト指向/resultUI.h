#pragma once
//resultUI.h

#include "gameobject.h"

class CResultUI : public CGameObject
{
private:
	ID3D11Buffer*				m_VertexBuffer = NULL;
	ID3D11ShaderResourceView*	m_ResultUI = NULL;
	ID3D11ShaderResourceView*	m_Score = NULL;

	const int DIGIT = 5;

	const D3DXVECTOR2 size = D3DXVECTOR2(100.0f, 100.0f);
	const D3DXVECTOR2 center = D3DXVECTOR2(SCREEN_WIDTH * 0.5f,SCREEN_HEIGHT * 0.5f);

	float m_DrawPosition;

	bool m_ResultFlag;
	bool m_Mouse;
public:
	CResultUI() {}
	~CResultUI() {}

	void Init();
	void Uninit();
	void Update();
	void Draw();

	bool GetResultFlag() { return m_ResultFlag; }
};


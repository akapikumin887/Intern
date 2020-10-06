#pragma once
//gameUI.h

#include "gameobject.h"

//readygo,finishï”ÇËÇÕÇ±ÇÍÇ≈èëÇ≠Ç¬Ç‡ÇË
class CGameUI :public CGameObject
{
private:
	ID3D11Buffer*				m_VertexBuffer = NULL;

	ID3D11ShaderResourceView*	m_Ready = NULL;
	ID3D11ShaderResourceView*	m_Go = NULL;
	ID3D11ShaderResourceView*	m_Finish = NULL;
	ID3D11ShaderResourceView*	m_Bullet = NULL;
	ID3D11ShaderResourceView*	m_RBullet = NULL;

	float m_Alpha;
	const D3DXVECTOR2 center = D3DXVECTOR2(SCREEN_WIDTH, SCREEN_HEIGHT);

	float m_ReadyPosition;
	float m_GoPosition;
	float m_FinishPosition;
	
	int m_Count;

	bool m_StartFlag;
	bool m_EndFlag;
	bool m_SceneFadeFlag;
public:
	CGameUI() {}
	~CGameUI() {}

	void Init();
	void Uninit();
	void Update();
	void Draw();

	bool GetEndFlag() { return m_EndFlag; }
	void SetEndFlag(bool end) { m_EndFlag = end; }
	bool GetFadeFlag() { return m_SceneFadeFlag; }

	int GetCount() { return m_Count; }
};


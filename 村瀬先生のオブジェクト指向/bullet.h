#pragma once

#include "gameobject.h"
//#include "3dObject.h"

class CBullet : public CGameObject
{
private:
	inline static class CModel* m_Model = NULL;
	//int m_Count;
	int m_BulletLevel;
	int m_BoundMax;
	int m_BoundCount;

	D3DXVECTOR3 m_Forward;

	float m_ScorePower;		//スコア倍率
	float m_BoundPower;		//バウンド倍率
public:
	CBullet() {}
	~CBullet() {}

	static void Load();
	static void Unload();

	void Init()override;
	void Uninit()override;
	void Update()override;
	void Draw()override;
};
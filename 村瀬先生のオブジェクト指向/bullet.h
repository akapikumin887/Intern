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

	float m_ScorePower;		//�X�R�A�{��
	float m_BoundPower;		//�o�E���h�{��
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
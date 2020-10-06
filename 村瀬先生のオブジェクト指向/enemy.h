#pragma once

#include "gameobject.h"

class CModel;

class CEnemy : public CGameObject
{
private:
	inline static CModel* m_Enemy01 = NULL;		//NUM01
	inline static CModel* m_Enemy02 = NULL;		//NUM02
	inline static CModel* m_Enemy03 = NULL;		//NUM03

	int m_Num;
	float m_Collision;
	D3DXVECTOR3 m_MoveVec;
	int m_Score;
public:
	CEnemy() {}
	~CEnemy() {}

	void Init();
	void Uninit();
	void Update();
	void Draw();

	enum ENEMY : int
	{
		ENEMY_SUPER = 1,
		ENEMY_RARE = 2,
		ENEMY_NORMAL = 3,
	};

	static void Load();
	static void Unload();

	void SetNum(int num);
	//int GetNum() { return m_Num; }
	float GetLength() { return m_Collision; }
	int GetScore() { return m_Score; }

};
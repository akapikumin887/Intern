#pragma once
//cube.h

#include "gameobject.h"

//�ŏ��̈�񂾂�model��ǂݍ����2�x�ڈȍ~�͂����load���Ĕz�u����

class CCube : public CGameObject
{
private:
	static inline class CModel* m_Model = NULL;
public:
	CCube() {}
	~CCube() {}

	void Init()override;
	void Uninit()override;
	void Update()override;
	void Draw()override;

	static void Load();
	static void Unload();
};
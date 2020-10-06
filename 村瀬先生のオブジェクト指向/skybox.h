#pragma once

#include "gameobject.h"

class CSkybox :public CGameObject
{
private:
	class CModel* m_Model;
public:
	CSkybox() {}
	~CSkybox() {}

	void Init()override;
	void Uninit()override;
	void Update()override;
	void Draw()override;
};


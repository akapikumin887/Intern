#pragma once
//stage.h

#include "gameobject.h"

//‚â‚è‚½‚¢‚±‚Æ‚Ískybox
class CStage : public CGameObject
{
private:
	static inline class CModel* m_Model = NULL;

public:
	CStage() {}
	~CStage() {}

	void Init()override;
	void Uninit()override;
	void Update()override;
	void Draw()override;

	static void Load();
	static void Unload();
};
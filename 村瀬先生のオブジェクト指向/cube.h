#pragma once
//cube.h

#include "gameobject.h"

//Å‰‚Ìˆê‰ñ‚¾‚¯model‚ğ“Ç‚İ‚ñ‚Å2“x–ÚˆÈ~‚Í‚»‚ê‚ğload‚µ‚Ä”z’u‚·‚é

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
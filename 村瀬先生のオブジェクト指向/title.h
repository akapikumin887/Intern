#pragma once
//title.h

#include "scene.h"

class CTitle : public CScene
{
public:
	void Init()override;
	void Update()override;
protected:
	void LoadObjects()override;
	void UnloadObjects()override;
};
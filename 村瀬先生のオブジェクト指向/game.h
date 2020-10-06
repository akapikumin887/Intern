#pragma once
//game.h

#include "scene.h"

class CGame : public CScene
{
public:
	void Init()override;
	void Update()override;
	void Draw()override;
protected:
	void LoadObjects()override;
	void UnloadObjects()override;
};


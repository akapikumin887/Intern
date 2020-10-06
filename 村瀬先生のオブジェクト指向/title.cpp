//title.cpp

#include "main.h"
#include "renderer.h"
#include "model.h"
#include "input.h"
#include "manager.h"
#include "score.h"
#include "polygon.h"
#include "time.h"
#include "texture.h"
#include "titlebg.h"

#include "title.h"
#include "game.h"

void CTitle::Init()
{
	LoadObjects();
	AddGameObject<CTitleBG>(LAYER_2D);
}

void CTitle::Update()
{
	CScene::Update();

	//ÉVÅ[ÉìëJà⁄
	if (CInput::GetKeyTrigger(VK_SPACE) || CInput::GetKeyTrigger(VK_LBUTTON))
		CManager::SetScene<CGame>();
}

void CTitle::LoadObjects()
{
}

void CTitle::UnloadObjects()
{
}
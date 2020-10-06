//game.h

#include "main.h"
#include "renderer.h"
#include "model.h"
#include "manager.h"
#include "input.h"
#include "mouse.h"
#include "game.h"
#include "title.h"

#include "camera.h"
#include "polygon.h"
#include "enemy.h"
#include "player.h"
#include "cube.h"
#include "bullet.h"
#include "explosion.h"
#include "stage.h"
#include "score.h"
#include "time.h"
#include "skybox.h"
#include "gameUI.h"
#include "resultUI.h"

#define ENEMY_COUNT 5

void CGame::Init()
{
	LoadObjects();

	AddGameObject<CMouse>(LAYER_2D);
	AddGameObject<CScore>(LAYER_2D);
	AddGameObject<CTime>(LAYER_2D)->SetCount(30);
	AddGameObject<CPlayer>(LAYER_3D)->SetPosition(D3DXVECTOR3(0.0f,0.0f,-5.0f));
	AddGameObject<CCamera>(LAYER_CAMERA);

	//ìGÇÃêî3î{ÇÕÇ¢ÇÈ
	for (int i = 0; i < ENEMY_COUNT; i++)
	{
		AddGameObject<CEnemy>(LAYER_3D)->SetNum(CEnemy::ENEMY_SUPER);
		AddGameObject<CEnemy>(LAYER_3D)->SetNum(CEnemy::ENEMY_RARE);
		AddGameObject<CEnemy>(LAYER_3D)->SetNum(CEnemy::ENEMY_NORMAL);
	}

	//è∞ÇÃê∂ê¨
	for (int z = 0; z < 42; z++)
	{
		for (int x = 0; x < 42; x++)
			AddGameObject<CCube>(LAYER_3D)->SetPosition(D3DXVECTOR3((float)x - 21.0f, -0.95f, (float)z - 21.0f));
	}

	AddGameObject<CStage>(LAYER_BG);
	AddGameObject<CSkybox>(LAYER_BG);
	AddGameObject<CGameUI>(LAYER_2D);
	AddGameObject<CResultUI>(LAYER_2D);
}

void CGame::Update()
{
	CScene::Update();

	//ÉVÅ[ÉìëJà⁄
	if (GetGameObject<CResultUI>(LAYER_2D)->GetResultFlag())
	{
		if (CInput::GetKeyTrigger(VK_SPACE) || CInput::GetKeyTrigger(VK_LBUTTON))
			CManager::SetScene<CTitle>();
	}
}

void CGame::Draw()
{
	CScene::Draw();
}

void CGame::LoadObjects()
{
	CMouse::Load();
	CEnemy::Load();
	CScore::Load();
	CTime::Load();
	CCube::Load();
	CStage::Load();
	CBullet::Load();
	CExplosion::Load();
}

void CGame::UnloadObjects()
{
	CMouse::Unload();
	CEnemy::Unload();
	CScore::Unload();
	CTime::Unload();
	CCube::Unload();
	CStage::Unload();
	CBullet::Unload();
	CExplosion::Unload();
}
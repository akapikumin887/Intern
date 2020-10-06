
#include "main.h"
#include "fade.h"
#include "renderer.h"
#include "model.h"
#include "manager.h"
#include "input.h"
#include "scene.h"
#include "title.h"
#include "game.h"
#include "mouse.h"

#include <time.h>

CFade CManager::m_fade;

void CManager::Init()
{
	srand((unsigned int)time(NULL));

	CRenderer::Init();
	CModel::Init();
	CInput::Init();

	CFade::Load();

	SetScene<CTitle>();
	m_fade.fs = CFade::FADE_IN;

	ChangeScene(CFade::m_NextScene);
}

void CManager::Uninit()
{
	CFade::Unload();

	m_Scene->Uninit();
	delete m_Scene;

	CInput::Uninit();
	CRenderer::Uninit();
}

void CManager::Update()
{
	//inputはここで得た情報で色々するので先頭に配置する
	//フェード中はキー入力を無効にしたかった

	CInput::Update();
	m_fade.Update();

	m_Scene->Update();
}

void CManager::Draw()
{
	CRenderer::Begin();

	//3D用ライト設定
	LIGHT light;
	light.Enable = true;
	light.Direction = D3DXVECTOR4(1.0f,-1.0f,1.0f,0.0f);
	D3DXVec4Normalize(&light.Direction,&light.Direction);
	light.Ambient = D3DXCOLOR(0.1f,0.1f,0.1f,1.0f);
	light.Diffuse = D3DXCOLOR(1.0f, 1.0f, 1.0f, 1.0f);
	CRenderer::SetLight(light);

	m_Scene->Draw();
	m_fade.Draw();

	CRenderer::End();
}

void CManager::ChangeScene(CScene* scene)
{
	if (m_Scene)
	{
		m_Scene->Uninit();
		delete m_Scene;
	}

	m_Scene = scene;
	scene->Init();
}

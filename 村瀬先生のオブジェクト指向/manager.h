#pragma once

#include "fade.h"
#include "scene.h"

class CManager
{
private:
	inline static class CScene*	m_Scene = NULL;
	static class CFade m_fade;

public:
	static void Init();
	static void Uninit();
	static void Update();
	static void Draw();

	static class CScene* GetScene() { return m_Scene; }
	static void ChangeScene(CScene* scene);

	template <typename T>
	static void SetScene()
	{
		if (m_fade.fs != CFade::FADE_NONE)
			return;
		m_fade.fs = CFade::FADE_OUT;
		T* scene = new T;
		m_fade.m_NextScene = scene;
	}

	//template <typename T>
	//static void SetScene()
	//{
	//	if (m_Scene)
	//	{
	//		m_Scene->Uninit();
	//		delete m_Scene;
	//	}
	//	T* scene = new T;
	//	m_Scene = scene;
	//	scene->Init();
	//}
};
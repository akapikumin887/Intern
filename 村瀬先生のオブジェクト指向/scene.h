#pragma once
//scene.h
#include <list>
#include <vector>
#include <typeinfo>
#include <string>

#include "gameobject.h"
#include "model.h"

enum layer:int
{
	LAYER_CAMERA,	//0
	LAYER_3D,		//1
	LAYER_BG,		//2
	LAYER_EFFECT,	//3
	LAYER_2D,		//4
	LAYER_MAX		//5
};

class CScene
{
private:

protected:
	std::list<CGameObject*> m_GameObject[LAYER_MAX];

	virtual void LoadObjects() = 0;
	virtual void UnloadObjects() = 0;
public:
	CScene() {}
	virtual ~CScene() {}

	virtual void Init() = 0;

	virtual void Uninit()
	{
		for (int i = 0; i < LAYER_MAX; i++)
		{
			for (CGameObject* object : m_GameObject[i])
			{
				object->Uninit();
				delete object;
			}
			m_GameObject[i].clear();
		}
		UnloadObjects();
	}

	virtual void Update()
	{
		for (CGameObject* object : m_GameObject[LAYER_EFFECT])
			object->Update();

		for (CGameObject* object : m_GameObject[LAYER_BG])
			object->Update();

		for (CGameObject* object : m_GameObject[LAYER_3D])
			object->Update();

		for (CGameObject* object : m_GameObject[LAYER_CAMERA])
			object->Update();

		for (CGameObject* object : m_GameObject[LAYER_2D])
			object->Update();
		
		for (int i = 0; i < LAYER_MAX; i++)
		{
			//ÉâÉÄÉ_éÆ(ñ≥ñºä÷êî)
			m_GameObject[i].remove_if([](CGameObject* object)
			{return object->Destroy(); });
		}
	}

	virtual void Draw()
	{
		for (int i = 0; i < LAYER_MAX; i++)
		{
			for (CGameObject* object : m_GameObject[i])
				object->Draw();
		}
	}

	//TÇ…à¯êîÇÃå^Ç™ì¸ÇÈ
	template <typename T>
	T* AddGameObject(int layer)
	{
		T* gameObject = new T();
		m_GameObject[layer].push_back(gameObject);
		gameObject->Init();

		return gameObject;
	}


	template<typename T>
	T* GetGameObject(int layer)
	{
		for (CGameObject* object : m_GameObject[layer])
		{
			if (typeid(*object) == typeid(T))
				return (T*)object;
		}
		return NULL;
	}

	template<typename T>
	std::vector<T*>GetGameObjects(int layer)
	{
		std::vector<T*>objects;
		for (CGameObject* object : m_GameObject[layer])
		{
			if (typeid(*object) == typeid(T))
				objects.push_back((T*)object);
		}
		return objects;
	}
};

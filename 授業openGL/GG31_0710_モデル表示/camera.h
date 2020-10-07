#pragma once
//camera.h
#include "main.h"

class CCamera
{
private:
	Vector3	m_Position;
	Vector3 m_Rotation;
	Vector3	m_Scale;

	Vector3 m_Target;

public:
	void Init();
	void Uninit();
	void Update();
	void Draw();
};
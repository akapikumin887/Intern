#pragma once

class CCube
{
private:
	int m_Texture;

	Vector3 m_Position;
	Vector3 m_Rotation;
	Vector3 m_Scale;

public:
	void Init();
	void Uninit();
	void Update();
	void Draw();
};
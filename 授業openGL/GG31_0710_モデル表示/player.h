#pragma once

class CPlayer
{
private:
	Vector3 m_Position;
	Vector3 m_Rotation;
	Vector3 m_Scale;

	class CModel* m_Model;

public:
	void Init();
	void Uninit();
	void Update();
	void Draw();
};

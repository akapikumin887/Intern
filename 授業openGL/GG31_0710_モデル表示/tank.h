#pragma once

class CTank
{
private:
	Vector3 m_Position;
	Vector3 m_Rotation;
	Vector3 m_Scale;

	Vector3 m_Position02;

	Vector3 m_Position03;

	Vector3 m_HeadRotate;

	Vector3 m_TurretRotate;

	class CModel* m_Body;
	class CModel* m_Head;
	class CModel* m_Turret;

public:
	void Init();
	void Uninit();
	void Update();
	void Draw();
};


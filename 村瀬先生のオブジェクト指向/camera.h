#pragma once

#include "gameobject.h"

class CCamera : public CGameObject
{
private:
	D3DXVECTOR3 m_Target;

	D3DXMATRIX m_ViewMatrix;
	D3DXMATRIX m_ProjectionMatrix;
public:
	CCamera() {}
	~CCamera() {}

	void Init()override;
	void Uninit()override;
	void Update()override;
	void Draw()override;

	D3DXMATRIX GetViewMatrix() { return m_ViewMatrix; }
};
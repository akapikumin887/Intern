#pragma once
//player.h

#include "gameobject.h"
#include "3dObject.h"

class CPlayer :public CGameObject	//�p��(�C���w���^���X)
{
private:
	//�O���錾
	class CModel* m_Model;

	//true�̎��͓����Ȃ�
	bool m_Freeze;

	D3DXQUATERNION m_Quaternion;
	D3DXVECTOR3	m_PlayerTarget;		//�v���C���[���e��ł���

	//�}�E�X�֘A
	float m_MouseX, m_MouseY;
	float m_TargetRotateY;

	//�e�֘A
	D3DXVECTOR3 m_BulletMove;
	int m_Charge;
	bool m_ChargeFlag;
	int m_RestBullet;

	//�v���C���[�`���[�W
	D3DXCOLOR m_Color;

public:
	CPlayer() {}
	~CPlayer() {}

	void Init()override;
	void Uninit()override;
	void Update()override;
	void Draw()override;

 	D3DXVECTOR3 GetBulletTarget() {return m_BulletMove;}
	D3DXVECTOR3 GetTarget() {return m_PlayerTarget;}
	int GetCharge() { return m_Charge; }
	int GetRestBullet() {return m_RestBullet;}
};
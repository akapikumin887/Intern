#pragma once
//player.h

#include "gameobject.h"
#include "3dObject.h"

class CPlayer :public CGameObject	//継承(インヘリタンス)
{
private:
	//前方宣言
	class CModel* m_Model;

	//trueの時は動けない
	bool m_Freeze;

	D3DXQUATERNION m_Quaternion;
	D3DXVECTOR3	m_PlayerTarget;		//プレイヤーが弾を打つ方向

	//マウス関連
	float m_MouseX, m_MouseY;
	float m_TargetRotateY;

	//弾関連
	D3DXVECTOR3 m_BulletMove;
	int m_Charge;
	bool m_ChargeFlag;
	int m_RestBullet;

	//プレイヤーチャージ
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
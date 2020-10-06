#pragma once
//score.h

#include "gameobject.h"

class CScore : public CGameObject
{
private:
	inline static ID3D11Buffer*				m_VertexBuffer = NULL;
	inline static ID3D11ShaderResourceView*	m_Texture = NULL;
	inline static ID3D11ShaderResourceView*	m_TextureMoji = NULL;

	const int   DIGIT = 5;					//����

	const D3DXVECTOR2 size = D3DXVECTOR2(50.0f, 50.0f);
	const D3DXVECTOR2 center = D3DXVECTOR2((SCREEN_WIDTH / 6), size.y * 0.5f);


	int m_Score;		//���ۂ̃X�R�A
	int m_DrawScore;	//�`�掞�̃X�R�A�A�኱�̒x���A��
public:
	CScore() {}
	~CScore() {}

	void Init()override;
	void Uninit()override;
	void Update()override;
	void Draw()override;

	static void Load();
	static void Unload();

	int GetScore() { return m_Score; }
	void SetScore(int set) { m_Score = set; }
	void AddScore(int set) { m_Score += set; }
};
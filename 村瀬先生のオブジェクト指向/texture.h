#pragma once

class CTexture
{
private:
	inline static float m_alpha  = 1.0f;
public:
	//drawPosition�͎��ۂɕ`�悷��摜�̒��S���W
	//drawSize�͎��ۂɕ`�悷��摜�̃T�C�Y
	//texUpLeft�͕`�悷�錳�摜�̑傫��1.0���ő�
	//texDoenRight�͕`�悷�錳�摜�̑傫��1.0���ő�
	static void Draw(ID3D11Buffer* vertexBuffer, 
		ID3D11ShaderResourceView* texture,
		D3DXVECTOR2 drawPosition,
		D3DXVECTOR2 drawSize,
		D3DXVECTOR2 texUpLeft,
		D3DXVECTOR2 texDownRight);

	static void SetAlpha(float alpha) { m_alpha = alpha; }
};


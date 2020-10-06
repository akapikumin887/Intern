#pragma once

class CTexture
{
private:
	inline static float m_alpha  = 1.0f;
public:
	//drawPositionは実際に描画する画像の中心座標
	//drawSizeは実際に描画する画像のサイズ
	//texUpLeftは描画する元画像の大きさ1.0が最大
	//texDoenRightは描画する元画像の大きさ1.0が最大
	static void Draw(ID3D11Buffer* vertexBuffer, 
		ID3D11ShaderResourceView* texture,
		D3DXVECTOR2 drawPosition,
		D3DXVECTOR2 drawSize,
		D3DXVECTOR2 texUpLeft,
		D3DXVECTOR2 texDownRight);

	static void SetAlpha(float alpha) { m_alpha = alpha; }
};


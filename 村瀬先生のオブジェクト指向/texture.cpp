//texture.cpp

#include "main.h"
#include "renderer.h"
#include "texture.h"

void CTexture::Draw(ID3D11Buffer* vertexBuffer,
	ID3D11ShaderResourceView* texture,
	D3DXVECTOR2 drawPosition,
	D3DXVECTOR2 drawSize,
	D3DXVECTOR2 texUpLeft,
	D3DXVECTOR2 texDownRight)
{
	//���C�g����
	LIGHT light;
	light.Enable = false;
	CRenderer::SetLight(light);

	D3D11_MAPPED_SUBRESOURCE msr;
	CRenderer::GetDeviceContext()->Map(vertexBuffer, 0, D3D11_MAP_WRITE_DISCARD, 0, &msr);

	VERTEX_3D* vertex = (VERTEX_3D*)msr.pData;

	vertex[0].Position = D3DXVECTOR3(drawPosition.x - drawSize.x * 0.5f, drawPosition.y - drawSize.y * 0.5f, 0.0f);
	vertex[0].Normal = D3DXVECTOR3(0.0f, 1.0f, 0.0f);
	vertex[0].Diffuse = D3DXVECTOR4(1.0f, 1.0f, 1.0f, 1.0f);
	vertex[0].TexCoord = texUpLeft;

	vertex[1].Position = D3DXVECTOR3(drawPosition.x + drawSize.x * 0.5f, drawPosition.y - drawSize.y * 0.5f, 0.0f);
	vertex[1].Normal = D3DXVECTOR3(0.0f, 1.0f, 0.0f);
	vertex[1].Diffuse = D3DXVECTOR4(1.0f, 1.0f, 1.0f, 1.0f);
	vertex[1].TexCoord = D3DXVECTOR2(texDownRight.x, texUpLeft.y);

	vertex[2].Position = D3DXVECTOR3(drawPosition.x - drawSize.x * 0.5f, drawPosition.y + drawSize.y * 0.5f, 0.0f);
	vertex[2].Normal = D3DXVECTOR3(0.0f, 1.0f, 0.0f);
	vertex[2].Diffuse = D3DXVECTOR4(1.0f, 1.0f, 1.0f, 1.0f);
	vertex[2].TexCoord = D3DXVECTOR2(texUpLeft.x, texDownRight.y);

	vertex[3].Position = D3DXVECTOR3(drawPosition.x + drawSize.x * 0.5f, drawPosition.y + drawSize.y * 0.5f, 0.0f);
	vertex[3].Normal = D3DXVECTOR3(0.0f, 1.0f, 0.0f);
	vertex[3].Diffuse = D3DXVECTOR4(1.0f, 1.0f, 1.0f, 1.0f);
	vertex[3].TexCoord = texDownRight;

	CRenderer::GetDeviceContext()->Unmap(vertexBuffer, 0);

	//�}�g���N�X�ݒ�
	CRenderer::SetWorldViewProjection2D();			//�搶���p�ӂ�������y�֐�

	//���_�o�b�t�@�ݒ�
	UINT stride = sizeof(VERTEX_3D);
	UINT offset = 0;
	CRenderer::GetDeviceContext()->IASetVertexBuffers(
		0, 1, &vertexBuffer, &stride, &offset);

	//�}�e���A���ݒ�
	MATERIAL material;
	ZeroMemory(&material, sizeof(material));
	material.Diffuse = D3DXCOLOR(1.0f, 1.0f, 1.0f, m_alpha);
	CRenderer::SetMaterial(material);

	//�e�N�X�`���ݒ�
	CRenderer::GetDeviceContext()->PSSetShaderResources(0, 1, &texture);

	//�v���~�e�B�u�g�|���W�ݒ�
	CRenderer::GetDeviceContext()->IASetPrimitiveTopology(
		D3D11_PRIMITIVE_TOPOLOGY_TRIANGLESTRIP);

	//�|���S���`��
	CRenderer::GetDeviceContext()->Draw(4, 0);

	//�ύX��͖߂��Ă���
	m_alpha = 1.0f;
}
#pragma once
//field.h

class CField
{
private:
	unsigned int m_Texture;

public:
	void Init();
	void Uninit();
	void Update();
	void Draw();
};
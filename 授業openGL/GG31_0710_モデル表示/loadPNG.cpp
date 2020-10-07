//loadPNG.cpp
#include "main.h"
#include <string>

//以下ライブラリを利用
#define STB_IMAGE_IMPLEMENTATION
#include "stb_image.h"

unsigned int LoadPng(const char* fileName)
{
	unsigned int texture;
	int width;
	int height;
	int bpp;

	unsigned char* pixels = stbi_load(fileName, &width, &height, &bpp, STBI_rgb_alpha);

	if (pixels == nullptr)
		throw(std::string("failed to load texture"));

	glGenTextures(1, &texture);

	glBindTexture(GL_TEXTURE_2D, texture);

	glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
	glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

	if (bpp == 3)
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB, width, height, 0, GL_RGB, GL_UNSIGNED_BYTE, pixels);
	else if (bpp == 4)
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, pixels);

	glBindTexture(GL_TEXTURE_2D, 0);

	// メモリ上の画像データを破棄
	stbi_image_free(pixels);

	return texture;
}


void UnLoadPng(unsigned int Texture)
{
	glDeleteTextures(1, &Texture);
}


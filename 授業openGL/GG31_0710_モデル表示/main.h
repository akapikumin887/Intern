#pragma once

#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <windows.h>
#include <assert.h>		//エラー処理

#include<GL/gl.h>
#include<GL/glu.h>


#pragma comment (lib, "winmm.lib")
#pragma comment (lib, "opengl32.lib")
#pragma comment (lib, "glu32.lib")

#define SCREEN_WIDTH	(960)			// ウインドウの幅
#define SCREEN_HEIGHT	(540)			// ウインドウの高さ

class Vector3
{
public:
	float x,y,z;

	Vector3() {}
	Vector3(float fx, float fy, float fz)
	{
		x = fx;
		y = fy;
		z = fz;
	}
};

HWND GetWindow();

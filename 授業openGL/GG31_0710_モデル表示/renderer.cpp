
#include "main.h"
#include "renderer.h"
#include <io.h>

HWND	CRenderer::m_Wnd;
HGLRC	CRenderer::m_GLRC;
HDC		CRenderer::m_DC;

void CRenderer::Init()
{
	//�E�B���h�E�n���h���ۑ�
	m_Wnd = GetWindow();

	//�s�N�Z���t�H�[�}�b�g
	PIXELFORMATDESCRIPTOR pfd =
	{
		sizeof(PIXELFORMATDESCRIPTOR),
		1,
		PFD_DRAW_TO_WINDOW |
		PFD_SUPPORT_OPENGL |
		PFD_DOUBLEBUFFER,
		PFD_TYPE_RGBA,
		24,
		0,0,0,0,0,0,
		0,
		0,
		0,
		0,0,0,0,
		32,
		0,
		0,
		PFD_MAIN_PLANE,
		0,
		0,0,0
	};

	// Window�f�o�C�X�R���e�L�X�g�擾
	m_DC = GetDC(m_Wnd);

	int pixelFormat = ChoosePixelFormat(m_DC, &pfd);
	SetPixelFormat(m_DC, pixelFormat, &pfd);

	// GL�R���e�L�X�g�쐬
	m_GLRC = wglCreateContext(m_DC);
	wglMakeCurrent(m_DC, m_GLRC);

	// OpenGL�`��ݒ�
	glEnable(GL_CULL_FACE);
	//glDisable(GL_CULL_FACE);
	glEnable(GL_DEPTH_TEST);
	glEnable(GL_LIGHTING);

	//�e�N�X�`���}�b�s���O��L���ɂ���
	glEnable(GL_TEXTURE_2D);


	//���u�����h�ݒ�
	//glEnable(GL_BLEND);
	//glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
}

void CRenderer::Uninit()
{
	wglMakeCurrent(NULL,NULL);
	ReleaseDC(m_Wnd,m_DC);
	wglDeleteContext(m_GLRC);
}

void CRenderer::Begin()
{
	// ��ʃN���A
	glClearColor(0.3f, 1.0f, 0.3f, 1.0f);
	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
}

void CRenderer::End()
{
	//�t�����g�o�b�t�@�E�o�b�N�o�b�t�@����ւ�
	SwapBuffers(m_DC);
}
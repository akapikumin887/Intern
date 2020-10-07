
#include "main.h"
#include "manager.h"
#include "polygon.h"
#include "renderer.h"
#include "input.h"
#include "camera.h"
#include "field.h"
#include "cube.h"
#include "player.h"
#include "tank.h"
#include "model.h"

CCamera*	g_camera;
CField*		g_field;
CPolygon*	g_polygon;
CCube*		g_cube;
CPlayer*	g_player;
CTank*		g_tank;

void CManager::Init()
{
	CRenderer::Init();
	CInput::Init();

	g_camera = new CCamera;
	g_camera->Init();

	g_tank = new CTank();
	g_tank->Init();

	//g_player = new CPlayer();
	//g_player->Init();

	g_field = new CField;
	g_field->Init();

	//g_cube = new CCube;
	//g_cube->Init();

	g_polygon = new CPolygon;
	g_polygon->Init();
}

void CManager::Uninit()
{
	g_polygon->Uninit();
	delete g_polygon;
	//g_cube->Uninit();
	//delete g_cube;
	g_field->Uninit();
	delete g_field;
	//g_player->Uninit();
	//delete g_player;
	g_tank->Uninit();
	delete g_tank;
	g_camera->Uninit();
	delete g_camera;

	CInput::Uninit();
	CRenderer::Uninit();
}

void CManager::Update()
{
	CInput::Update();

	g_camera->Update();
	g_tank->Update();
	//g_player->Update();
	g_field->Update();
	//g_cube->Update();
	g_polygon->Update();
}

void CManager::Draw()
{
	CRenderer::Begin();
	g_camera->Draw();

	VECTOR4D position;
	COLOR ambient, diffuse, specular;

	position.x = 3.0f;
	position.y = 5.0f;
	position.z = 5.0f;
	position.w = 0.0f;

	ambient.r = 0.3f;
	ambient.g = 0.3f;
	ambient.b = 0.3f;
	ambient.a = 1.0f;

	diffuse.r = 1.0f;
	diffuse.g = 1.0f;
	diffuse.b = 1.0f;
	diffuse.a = 1.0f;

	specular.r = 1.0f;
	specular.g = 1.0f;
	specular.b = 1.0f;
	specular.a = 1.0f;

	glLightfv(GL_LIGHT0,GL_POSITION,(float*)&position);
	glLightfv(GL_LIGHT0,GL_AMBIENT,(float*)&ambient);
	glLightfv(GL_LIGHT0,GL_DIFFUSE,(float*)&diffuse);
	glLightfv(GL_LIGHT0,GL_SPECULAR,(float*)&specular);

	glEnable(GL_LIGHT0);

	g_field->Draw();
	//g_player->Draw();
	g_tank->Draw();
	//g_cube->Draw();
	g_polygon->Draw();

	CRenderer::End();
}

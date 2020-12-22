/*
 * mock.c
 *
 *  Created on: Dec 22, 2020
 *      Author: slimakanzer
 */
#include "mock.h"
#include "cmsis_os.h"

void mockDelay(int ticks)
{
	osDelay(ticks);
}

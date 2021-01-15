/*
 * network.h
 *
 *  Created on: Dec 22, 2020
 *      Author: slimakanzer
 */

#ifndef INC_NETWORK_H_
#define INC_NETWORK_H_
#include <stdint.h>

#define SERVER_IP "127.0.0.1"

typedef struct
{
	char productName[32];
	int currency;
	float value;
} Product;

void receiveProduct();
void sendImageToServer(uint8_t *);

#endif /* INC_NETWORK_H_ */

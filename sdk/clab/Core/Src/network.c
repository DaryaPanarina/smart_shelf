/*
 * network.c
 *
 *  Created on: Dec 22, 2020
 *      Author: slimakanzer
 */
#include "network.h"
#include "lcd.h"
#include "trace.h"
#include "mock.h"
#define PSIZE 5

Product products[PSIZE] = {
		{"Milk", 643, 59.99},
		{"Bread", 643, 29.99},
		{"Coca-cola", 643, 89.99},
		{"Milk caucazian", 840, 3.99},
		{"Milk invalid", 120, 3.99},
};

int product_id;
int delay = 400;

void receiveProduct()
{
	mockDelay(delay);
	if (PSIZE-1 == product_id){
		product_id = 0;
	}
	else {
		product_id += 1;
	}

	Product product = products[product_id];
	lcd_code rc = LCD_sendProduct(product.productName, product.currency, product.value);

	if (LCD_SUCCESS != rc)
	{
		SDK_TRACE_Timestamp(PRINT, 1);

		if (LCD_ERROR_CURRENCY == rc)
		{
			SDK_TRACE_Print("ERROR: cannot set product to the LCD display: Invalid currency");
		}
		else if (LCD_ERROR_NAME == rc)
		{
			SDK_TRACE_Print("ERROR: cannot set product to the LCD display: Invalid name");
		}
		else if (LCD_ERROR_VALUE == rc)
		{
			SDK_TRACE_Print("ERROR: cannot set product to the LCD display: Invalid value");
		}
		SDK_TRACE_Timestamp(PRINT, 0);
	}

	delay += 343;
}

void sendImageToServer(uint8_t *buffer)
{
	SDK_TRACE_Timestamp(P9, 1);
	SDK_TRACE_Print("SEND: Send image to server");
	mockDelay(350);
	SDK_TRACE_Timestamp(P9, 0);
}

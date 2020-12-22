/*
 * led.h
 *
 *  Created on: Oct 21, 2020
 *      Author: slimakanzer
 */

#ifndef INC_LCD_H_
#define INC_LCD_H_
#include <stdint.h>

#define LCD_ADDR 			(0x27 << 1)
#define LCD_CURSOR_START 	0b00000010
#define LCD_CURSOR_NEXT		0b11000000
#define LCD_CURSOR_DISABLE 	0b00001100
#define LCD_CLEAR			0b00000001
#define LCD_FLAG_PIN_RS     (1 << 0)
#define LCD_FLAG_PIN_EN    	(1 << 2)
#define LCD_FLAG_BACKLIGHT 	(1 << 3)
#define LCD_DELAY_MS		5

#define CURRENCY_RUB		643
#define CURRENCY_USD		840
#define CURRENCY_EUR		978

typedef uint8_t lcd_code;
#define LCD_SUCCESS			0x0000
#define LCD_ERROR_CURRENCY	0x0001
#define LCD_ERROR_NAME		0x0002
#define LCD_ERROR_VALUE		0x0003

void LCD_send(uint8_t data, uint8_t flags);
void LCD_sendString(char *str);
lcd_code LCD_sendProduct(char *nameProduct, int currency, float value);

#endif /* INC_LCD_H_ */

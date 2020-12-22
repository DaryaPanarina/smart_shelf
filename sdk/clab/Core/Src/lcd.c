/*
 * led.c
 *
 *  Created on: Oct 21, 2020
 *      Author: slimakanzer
 */
#include <lcd.h>
#include "i2c.h"
#include "trace.h"
#include "mock.h"

void LCD_send(uint8_t data, uint8_t flags)
{
	HAL_StatusTypeDef res;
	for(;;)
	{
		res = HAL_I2C_IsDeviceReady(&hi2c1, LCD_ADDR, 1, HAL_MAX_DELAY);
		if(res == HAL_OK) break;
	}

	uint8_t up = data & 0xF0;
    uint8_t lo = (data << 4) & 0xF0;

	uint8_t data_arr[4];
	data_arr[0] = up|flags|LCD_FLAG_BACKLIGHT|LCD_FLAG_PIN_EN;
	data_arr[1] = up|flags|LCD_FLAG_BACKLIGHT;
	data_arr[2] = lo|flags|LCD_FLAG_BACKLIGHT|LCD_FLAG_PIN_EN;
	data_arr[3] = lo|flags|LCD_FLAG_BACKLIGHT;

	HAL_I2C_Master_Transmit(&hi2c1, LCD_ADDR, data_arr, sizeof(data_arr), HAL_MAX_DELAY);
	HAL_Delay(LCD_DELAY_MS);
}

void LCD_sendString(char *str)
{
	while (*str)
	{
		LCD_send((uint8_t)(*str), 1);
		str++;
	}
}

lcd_code LCD_sendProduct(char *nameProduct, int currency, float value)
{
	/*
	char str[16];
	sprintf(str, "%.2f ", value);

	LCD_send(LCD_CURSOR_START, 0);
	LCD_send(LCD_CURSOR_DISABE, 0);
	LCD_send(LCD_CLEAR, 0);

	LCD_sendString(nameProduct);
	LCD_send(LCD_CURSOR_NEXT, 0);
	*/

	SDK_TRACE_Timestamp(P1, 1);

	if (nameProduct == NULL) return LCD_ERROR_NAME;

	int dec_val = (int)value;
	int frac_val = (int)((value - dec_val) * 100);

	if (CURRENCY_RUB == currency)
	{
		SDK_TRACE_Print("SET: %s %d.%d RUB", nameProduct, dec_val, frac_val);
	}
	else if (CURRENCY_USD == currency)
	{
		SDK_TRACE_Print("SET: %s %d.%d USD", nameProduct, dec_val, frac_val);
	}
	else if (CURRENCY_EUR == currency)
	{
		SDK_TRACE_Print("SET: %s %d.%d EUR", nameProduct, dec_val, frac_val);
	}
	else
	{
		SDK_TRACE_Timestamp(P1, 0);
		return LCD_ERROR_CURRENCY;
	}

	mockDelay(180);

	SDK_TRACE_Timestamp(P1, 0);
	return LCD_SUCCESS;
}

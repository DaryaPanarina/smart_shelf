package com.embedded.shell.services

import com.embedded.shell.models.Price
import java.lang.String
import java.io.InputStream
import java.lang.Thread.sleep
import java.net.*
import kotlin.concurrent.thread

var url = "http://8.8.8.8:8080/newPrice"

var currency: Int = 643
var value: Double = 100.0

class PriceSender(val priceService: PriceService) {

    fun Init() {
        var price: Price? = priceService.getFirst()
        if (price != null) {
            currency = price.currency;
            value = price.value;
        }

        thread {
            SendPrice();
            sleep(1000*60*60);
        }

    }

    fun SendPrice() {

        var price: Price? = priceService.getFirst()
        if (price != null) {
            currency = price.currency;
            value = price.value;
        }
        /*var query = String.format("currency=%d&value=%f", currency, value)
        val connection = URL("$url?$query").openConnection() as HttpURLConnection
        val status: Int = connection.getResponseCode()*/
        System.out.println("Sended!");
    }

}
package com.embedded.shell.controllers

import com.embedded.shell.models.ImageStatistic
import com.embedded.shell.models.Logs
import com.embedded.shell.services.*
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

import org.springframework.web.bind.annotation.GetMapping
import java.io.FileOutputStream

import java.io.BufferedOutputStream
import java.io.File
import java.lang.Exception
import java.util.*


@RestController
@RequestMapping("api")
class MainController(private val statisticService: StatisticService, private val logService: LogService, private val priceService: PriceService, private val userService: UserService) {

    @GetMapping("/statistic")
    fun getStatistic(pageable: Pageable = PageRequest.of(0, 100)): Page<ImageStatistic> {
        return statisticService.getAll(pageable)
    }

    @GetMapping("/logs")
    fun getLogs(pageable: Pageable = PageRequest.of(0, 100)): Page<Logs> {
        return logService.getAll(pageable)
    }

    @PostMapping("/postImage")
    fun postNewImage(@RequestBody file: MultipartFile) {
            try {
                val date: Date = Date();
                val bytes = file.bytes
                val stream = BufferedOutputStream(FileOutputStream(File(date.toString() + "-uploaded")))
                stream.write(bytes)
                stream.close()
            } catch (e: Exception) {
                logService.insert(Logs(value = ("Error during file loading!" +e.message)))
            }
    }

    @PostMapping("/postLog")
    fun postNewLog(@RequestBody logString: String) {
        logService.insert(Logs(value = logString))
    }

    class PriceClass(val currencyString: String, val valueString: String)

    @PostMapping("/postPrice")
    fun postNewPrice(@RequestBody price: PriceClass) {
        try {
            val currency: Int = price.currencyString.toInt()
            val value: Double = price.valueString.toDouble();

            priceService.setNewPrice(currency, value);
            val priceSender: PriceSender = PriceSender(priceService);
            priceSender.SendPrice()
        }
        catch(e: NumberFormatException){
            println(e.message)
        }
    }

    class LoginClass(val Login: String, val Password: String)

    @PostMapping("/login")
    fun postNewPrice(@RequestBody loginClass: LoginClass): String {

        val login = loginClass.Login
        val password = loginClass.Password

        if (userService.checkCorrectPassword(login, password)) {
            return "Correct!"
        } else {
            return "Incorrect!"
        }
    }


}
package com.embedded.shell

import com.embedded.shell.models.ImageStatistic
import com.embedded.shell.models.Logs
import com.embedded.shell.models.User
import com.embedded.shell.services.*
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.util.*

@SpringBootApplication
class ShellApplication (private val statisticDAO: ImageStatisticDAO, private val logsDAO: LogsDAO, private val priceService: PriceService, private val userDAO: UserDAO): ApplicationRunner {
	override fun run(args: ApplicationArguments?) {
		if(statisticDAO.count()<1) this.createStatistic()
		if(logsDAO.count()<1) this.createLogs()
		if (userDAO.count()<1) this.createUser()
		var priceSender: PriceSender = PriceSender(priceService)
		priceSender.Init();
	}


	private fun createStatistic(){
		statisticDAO.deleteAll()

		val statistic = listOf(
				ImageStatistic(recordTime = Date(), isMale = true, age = 15, happiness = 1.0),
				ImageStatistic(recordTime = Date(), isMale = false, age = 20, happiness = 0.5),
				ImageStatistic(recordTime = Date(), isMale = true, age = 25, happiness = 1.5)
		)
		statisticDAO.insert(statistic)
	}

	private fun createLogs(){
		logsDAO.deleteAll()

		val logs = listOf(
				Logs(value = "Everything is ok!"),
				Logs(value = "Everything is not ok!"),
				Logs(value = "Everything is ok again!")
		)
		logsDAO.insert(logs)
	}

	private fun createUser(){
		userDAO.deleteAll()
		val user = User(login = "login", password = "password");
		userDAO.insert(user)
	}
}

fun main(args: Array<String>) {
	runApplication<ShellApplication>(*args)
}
package com.embedded.shell.services

import com.embedded.shell.models.ImageStatistic
import com.embedded.shell.models.Logs
import com.embedded.shell.models.Price
import com.embedded.shell.models.User
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

interface ImageStatisticDAO:MongoRepository<ImageStatistic,String>
interface LogsDAO:MongoRepository<Logs,String>
interface PriceDAO:MongoRepository<Price,String>

@Repository
interface UserDAO:MongoRepository<User,String> {
    fun findByLogin(Login: String): User?
}
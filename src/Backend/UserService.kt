package com.embedded.shell.services

import com.embedded.shell.models.ImageStatistic
import com.embedded.shell.models.User
import com.embedded.shell.util.BasicCrud
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(private val userDAO: UserDAO): BasicCrud<String, User> {
    override fun getAll(pageable: Pageable): Page<User> {
        return userDAO.findAll(pageable);
    }

    override fun getById(id: String): Optional<User> {
        return userDAO.findById(id);
    }


    fun checkCorrectPassword(login: String, password: String): Boolean {
        val user: User? = userDAO.findByLogin(login);
        if (user != null) {
            if (password.equals(user.password)) {
                return true;
            }
        }
        return false;
    }

    override fun insert(obj: User): User {
        return userDAO.insert(obj);
    }

    override fun deleteById(id: String): Optional<User> {
        return userDAO.findById(id).apply {
            this.ifPresent {
                userDAO.delete(it)
            }
        }
    }
}
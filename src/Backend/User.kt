package com.embedded.shell.models

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class User(@Id val _id: String? = null, var login: String, var password: String)

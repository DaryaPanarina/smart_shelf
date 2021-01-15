package com.embedded.shell.models

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Price(@Id val _id: String? = null, var currency: Int, var value: Double)

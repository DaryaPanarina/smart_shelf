package com.embedded.shell.models

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document
data class ImageStatistic(@Id val _id:String? = null, var recordTime:Date, var isMale:Boolean, var age:Int, var happiness:Double)

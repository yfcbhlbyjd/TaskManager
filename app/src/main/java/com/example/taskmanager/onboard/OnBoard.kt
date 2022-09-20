package com.example.taskmanager.onboard

import java.io.Serializable

data class OnBoard(
    var image: String? =null,
    var title: String? =null,
    var description: String? =null,
    var isLast: Boolean? =null
): Serializable

package com.example.taskmanager.onboard

import java.io.Serializable

data class OnBoard(
    var animation: Int? =null,
    var title: String? =null,
    var description: String? =null,
): Serializable



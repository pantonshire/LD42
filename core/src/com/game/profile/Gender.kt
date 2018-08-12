package com.game.profile

enum class Gender {
    MALE,
    FEMALE;

    fun other(): Gender = when(this) {
        MALE -> FEMALE
        FEMALE -> MALE
    }
}
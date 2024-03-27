package xyz.teamgravity.errorhandlinglayers.domain.util

import xyz.teamgravity.errorhandlinglayers.domain.error.PasswordError

class UserDataValidator {

    ///////////////////////////////////////////////////////////////////////////
    // API
    ///////////////////////////////////////////////////////////////////////////

    fun validatePassword(password: String): Result<Unit, PasswordError> {
        if (password.length < 9) return Result.Error(PasswordError.TooShort)
        if (!password.any { it.isUpperCase() }) return Result.Error(PasswordError.NoUppercase)
        if (!password.any { it.isDigit() }) return Result.Error(PasswordError.NoDigit)
        return Result.Success(Unit)
    }
}
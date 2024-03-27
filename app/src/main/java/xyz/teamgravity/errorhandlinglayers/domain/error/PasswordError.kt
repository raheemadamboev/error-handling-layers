package xyz.teamgravity.errorhandlinglayers.domain.error

enum class PasswordError : Error {
    TooShort,
    NoUppercase,
    NoDigit;
}
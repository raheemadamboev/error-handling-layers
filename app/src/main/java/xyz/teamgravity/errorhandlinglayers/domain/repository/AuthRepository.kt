package xyz.teamgravity.errorhandlinglayers.domain.repository

import xyz.teamgravity.errorhandlinglayers.domain.error.DataError
import xyz.teamgravity.errorhandlinglayers.domain.util.Result
import xyz.teamgravity.errorhandlinglayers.domain.model.UserModel

interface AuthRepository {
    suspend fun register(password: String): Result<UserModel, DataError.Network>
}
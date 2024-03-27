package xyz.teamgravity.errorhandlinglayers.data.repository

import xyz.teamgravity.errorhandlinglayers.domain.error.DataError
import xyz.teamgravity.errorhandlinglayers.domain.util.Result
import xyz.teamgravity.errorhandlinglayers.domain.model.UserModel
import xyz.teamgravity.errorhandlinglayers.domain.repository.AuthRepository
import java.net.HttpRetryException

class AuthRepositoryImp : AuthRepository {

    override suspend fun register(password: String): Result<UserModel, DataError.Network> {
        return try {
            val user = UserModel(
                fullName = "Raheem Adamboev",
                token = "The strongest",
                email = "strong@gmail.com"
            )
            Result.Success(user)
        } catch (e: HttpRetryException) {
            when (e.responseCode()) {
                408 -> Result.Error(DataError.Network.RequestTimeout)
                413 -> Result.Error(DataError.Network.PayloadTooLarge)
                else -> Result.Error(DataError.Network.Unknown)
            }
        }
    }
}
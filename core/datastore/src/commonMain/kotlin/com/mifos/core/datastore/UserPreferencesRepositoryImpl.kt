package com.mifos.core.datastore

import com.mifos.core.model.objects.ServerConfig
import com.mifos.core.model.objects.UserData
import com.mifos.core.model.objects.users.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class UserPreferencesRepositoryImpl(
    private val preferenceManager: UserPreferencesDataSource,
    private val ioDispatcher: CoroutineDispatcher,
    unconfinedDispatcher: CoroutineDispatcher,
) : UserPreferencesRepository {

    private val unconfinedScope = CoroutineScope(unconfinedDispatcher)

    override val userInfo: Flow<UserData>
        get() = preferenceManager.userInfo

    override val userData: Flow<User>
        get() = preferenceManager.userData

    override val serverConfig: Flow<ServerConfig>
        get() = preferenceManager.serverConfig

    // Implementing updateUserInfo as required by the interface
    override suspend fun updateUserInfo(user: UserData): Result<Unit> {
        return withContext(ioDispatcher) {
            try {
                preferenceManager.updateUserInfo(user)
                Result.success(Unit)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    // Implementing updateUser as required by the interface
    override suspend fun updateUser(user: UserData): Result<Unit> {
        return withContext(ioDispatcher) {
            try {
                preferenceManager.updateUserInfo(user)
                Result.success(Unit)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    // Implementing updateServerConfig correctly
    override suspend fun updateServerConfig(serverConfig: ServerConfig): Result<Unit> {
        return withContext(ioDispatcher) {
            try {
                preferenceManager.updateServerConfig(serverConfig)
                Result.success(Unit)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    override suspend fun logOut() {
        withContext(ioDispatcher) {
            preferenceManager.clearInfo()
        }
    }
}

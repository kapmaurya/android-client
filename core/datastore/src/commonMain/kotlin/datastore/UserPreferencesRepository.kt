/*
 * Copyright 2024 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * See https://github.com/openMF/android-client/blob/master/LICENSE.md
 */
package org.mifos.core.datastore

import datastore.model.UserData
import kotlinx.coroutines.flow.Flow
interface UserPreferencesRepository {
    val userInfo: Flow<UserData>
    suspend fun updateUser(user: UserData): Result<Unit>
    suspend fun logOut(): Unit
}

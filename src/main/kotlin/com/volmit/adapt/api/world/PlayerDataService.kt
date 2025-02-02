package com.volmit.adapt.api.world

import gg.scala.store.controller.DataStoreObjectControllerCache
import gg.scala.store.storage.type.DataStoreStorageType
import java.util.UUID

/**
 * @author GrowlyX
 * @since 2/1/2025
 */
object PlayerDataService
{
    fun configure()
    {
        with(DataStoreObjectControllerCache.create<PlayerData>()) {
            mongo().withCustomCollection("AdaptPlayerData")
        }
    }

    fun byId(uniqueId: UUID) = DataStoreObjectControllerCache
        .findNotNull<PlayerData>()
        .load(uniqueId, DataStoreStorageType.MONGO)

    fun save(data: PlayerData) = DataStoreObjectControllerCache
        .findNotNull<PlayerData>()
        .save(data, DataStoreStorageType.MONGO)

    fun delete(uniqueId: UUID) = DataStoreObjectControllerCache
        .findNotNull<PlayerData>()
        .delete(uniqueId, DataStoreStorageType.MONGO)
}

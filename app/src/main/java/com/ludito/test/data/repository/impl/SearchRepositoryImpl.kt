package com.ludito.test.data.repository.impl

import com.ludito.test.data.repository.SearchRepository
import com.ludito.test.model.SearchItem
import com.yandex.mapkit.geometry.Geometry
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.search.Response
import com.yandex.mapkit.search.SearchManager
import com.yandex.mapkit.search.SearchOptions
import com.yandex.mapkit.search.Session
import com.yandex.runtime.Error
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.math.roundToInt

/**
 * Created by: androdev
 * Date: 12-03-2025
 * Time: 10:20 PM
 * Email: Kjuraev.001@gmail.com
 */

class SearchRepositoryImpl @Inject constructor(
    private val searchManager: SearchManager
): SearchRepository {
    override suspend fun search(query: String, visiblePolygon: Geometry): List<SearchItem> {
        if (query.isBlank()) return emptyList()

        return suspendCancellableCoroutine { cont ->
            searchManager.submit(
                query,
                visiblePolygon,
                SearchOptions(),
                object : Session.SearchListener {
                    override fun onSearchResponse(resp: Response) {
                        if (cont.isActive) {
                            val searchItems = resp.collection.children
                                .mapNotNull { it?.obj }
                                .mapNotNull {  SearchItem.from(it) }

                            cont.resume(searchItems)
                        }
                    }

                    override fun onSearchError(p0: Error) {
                        if (cont.isActive) {
                            cont.resume(emptyList())
                        }
                    }
                }
            )
        }
    }

    override suspend fun search(position: CameraPosition): List<SearchItem> {

        return suspendCancellableCoroutine { cont ->
            searchManager.submit(
                position.target,
                position.zoom.roundToInt(),
                SearchOptions(),
                object : Session.SearchListener {
                    override fun onSearchResponse(resp: Response) {
                        if (cont.isActive) {
                            val searchItems = resp.collection.children
                                .mapNotNull { it?.obj }
                                .mapNotNull {  SearchItem.from(it) }

                            cont.resume(searchItems)
                        }
                    }

                    override fun onSearchError(p0: Error) {
                        if (cont.isActive) {
                            cont.resume(emptyList())
                        }
                    }
                }
            )
        }
    }
}
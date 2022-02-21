package com.abhiwisesa.moviecatalogue.utils

import androidx.sqlite.db.SimpleSQLiteQuery

object SortUtils {
    const val NEWEST = "Newest"
    const val OLDEST = "Oldest"
    const val ALPHABETICAL_ASCENDING = "Alphabet Ascending"
    const val ALPHABETICAL_DESCENDING = "Alphabet Descending"
    fun getSortedQuery(filter: String,isMovie : Boolean): SimpleSQLiteQuery {
        val bol = if(isMovie){
            1
        }else 0
        val simpleQuery = StringBuilder().append("SELECT * FROM movieentity ")
        when (filter) {
            NEWEST -> {
                simpleQuery.append("WHERE isMovie = $bol ORDER BY dateAdded DESC")
            }
            OLDEST -> {
                simpleQuery.append("WHERE isMovie = $bol ORDER BY dateAdded ASC")
            }
            ALPHABETICAL_ASCENDING -> {
                simpleQuery.append("WHERE isMovie = $bol ORDER BY title ASC")
            }
            ALPHABETICAL_DESCENDING -> {
                simpleQuery.append("WHERE isMovie = $bol ORDER BY title DESC")
            }
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }
}
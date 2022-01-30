package com.home.assignment.model

class LatestArticle {

    var copyright: String? = null
    var num_results: Int? = null
    var results: List<Result>?= null
    var status: String?= null

    inner class Result(
        var `abstract`: String,
        var adx_keywords: String,
        var asset_id: Long,
        var byline: String,
        var column: Any,
        var des_facet: List<String>,
        var eta_id: Int,
        var geo_facet: List<String>,
        var id: Long,
        var media: List<Media>,
        var nytdsection: String,
        var org_facet: List<String>,
        var per_facet: List<String>,
        var published_date: String,
        var section: String,
        var source: String,
        var subsection: String,
        var title: String,
        var type: String,
        var updated: String,
        var uri: String,
        var url: String
    )
}
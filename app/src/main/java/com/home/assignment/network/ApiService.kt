package club.aashiyana.app.network
import com.google.gson.JsonElement
import com.home.assignment.common.Constants.Companion.API_KEY
import com.home.assignment.model.LatestArticle
import retrofit2.Call
import retrofit2.http.*


interface ApiService {
    @GET("/svc/mostpopular/v2/emailed/7.json?api-key=$API_KEY")
    fun getLatestArticles(): Call<JsonElement>


}
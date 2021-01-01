package so.ido.oauthtest_1117


import retrofit2.Call
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface CenterAuthApiRetrofitInterface {

    // access_token 획득
    @FormUrlEncoded
    @POST("/oauth/2.0/token")
    fun token(@FieldMap params:Map<String, String>):Call<Map<String,String>>
}
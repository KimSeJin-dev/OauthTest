package so.ido.oauthtest_1117

data class sendAccessToken (

    val code:String,
    val client_id:String,
    val client_sercret:String,
    val redirect_uri:String,
    val grant_type:String

)

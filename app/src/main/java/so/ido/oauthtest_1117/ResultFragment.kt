package so.ido.oauthtest_1117

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import kotlinx.android.synthetic.main.fragment_result.*
import kotlinx.android.synthetic.main.fragment_result.view.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.exp


class ResultFragment : Fragment() {
    private lateinit var activity:MainActivity

    private var args: Bundle? = null
    private var getCode:String = ""
    private var client_id:String = "EA3k8ZGGG29ay5MVnCKxLu7U775QEyQzrUz5vH88"
    private var client_secret:String = "wruupPbwBe6ApdAO7I2WcFVQ0chJWinhp2SrbUvy"
    private var redirect_uri:String = "bentley://bentleyhost"
    private var grant_type:String ="authorization_code"

    private lateinit var retrofit : Retrofit
    private lateinit var accessTokenService : CenterAuthApiRetrofitInterface
    private lateinit var mView : View
    private var gson = GsonBuilder().create()
    companion object {
        const val code = "code"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        args = arguments
        if (args == null) args = Bundle()

        getCode = args!!.getString(code, "")

        retrofit = Retrofit.Builder()
                .baseUrl("https://testapi.openbanking.or.kr")
                //.addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().enableComplexMapKeySerialization().create()))
                .build()

        accessTokenService = retrofit.create(CenterAuthApiRetrofitInterface::class.java)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = getActivity() as MainActivity
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.fragment_result, container, false)

        initView()
        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(getCode != "") {
            authorizationcode_content.text = getCode
        }
        }

    private fun initView(){

        mView.requestAccessbtn.setOnClickListener {
            getAccessToken()
        }
    }

    private fun getAccessToken(){
        val paramMap = HashMap<String, String>()

        paramMap["code"] = getCode
        paramMap["client_id"] = client_id
        paramMap["client_secret"] = client_secret
        paramMap["redirect_uri"] = redirect_uri
        paramMap["grant_type"] = grant_type

        Log.d("TAG", "token : $paramMap")
        val callAccessToken = accessTokenService.token(paramMap)

        callAccessToken.enqueue(object :
            Callback<Map<String,String>> {
            override fun onFailure(call: Call<Map<String,String>>, t: Throwable) {
                Toast.makeText(activity, "t_message : ${t.message}", Toast.LENGTH_SHORT).show();
                Log.d("TAG", "t_message : ${t.message} ")
            }

            override fun onResponse(call: Call<Map<String,String>>, response: Response<Map<String,String>>) {

                val responseJson = gson.toJson(response.body())
                activity.changeFragment(4,responseJson)

            }
        })
    }

}
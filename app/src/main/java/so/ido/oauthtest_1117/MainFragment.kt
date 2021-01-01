package so.ido.oauthtest_1117

import android.content.Context
import android.content.Intent.getIntent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*


class MainFragment : Fragment() {
    private var args: Bundle? = null
    private lateinit var activity:MainActivity
    private var uri: String = "https://testapi.openbanking.or.kr/oauth/2.0/authorize"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        args = arguments
        if (args == null) args = Bundle()

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
         activity = getActivity() as MainActivity
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {


        val view: View = inflater!!.inflate(R.layout.fragment_main, container, false)

        view.btn_change.setOnClickListener {
          initView()

        }
        return view
    }

    fun initView(){
        val state = "abcdefghijklmnopqrstuvwxyz123456" // 난수값
        val paramMap:HashMap<String, String> = HashMap<String, String>()
        //반환형태(고정)
        paramMap["response_type"] = "code"
        //이용기관 ID
        paramMap["client_id"] = "EA3k8ZGGG29ay5MVnCKxLu7U775QEyQzrUz5vH88"
        //콜백 URL
        paramMap["redirect_uri"] = "bentley://bentleyhost"

        //토큰권한범위
        paramMap["scope"] = "login transfer inquiry"
        //이용기관 임의정보
        paramMap["client_info"] = "[test] whatever you want"
        //state값은 향후 비교를 위해 별도로 처리
        paramMap["state"] = state
        //사용자인증 타입 구분분
        paramMap["auth_type"] = "0"
        //언어
        paramMap["lang"] = "kor"
        paramMap["authorized_cert_yn"] = "N"

        val parameters = "?" + Utils.convertMapToQuerystring(paramMap)
        val Information = uri + parameters
        Log.d("TAG", "Information : $Information")

        activity.changeFragment(2,Information)

    }


}
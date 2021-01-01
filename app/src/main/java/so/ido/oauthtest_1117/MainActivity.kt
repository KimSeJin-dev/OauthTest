package so.ido.oauthtest_1117

import android.app.PendingIntent.getActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : FragmentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            val fragmentMain = MainFragment()
            supportFragmentManager.beginTransaction().replace(R.id.frameLayout, fragmentMain).commit()
        }

        menubtn.setOnClickListener {
            val fragmentMenu = MenuFragment()
            supportFragmentManager.beginTransaction().replace(R.id.frameLayout, fragmentMenu).commit()
        }


    }

    fun changeFragment(index: Int, args: String?){

        when(index) {
            1 -> {
                val fragmentMain = MainFragment()
                supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, fragmentMain).commit()
            }
            2 -> {
              supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout,  WebViewFragment().apply {
                            arguments = Bundle().apply {
                               putString(WebViewFragment.URL_TO_LOAD, args)
                            }
                        }).commit()
            }
            3 -> {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, ResultFragment().apply {
                            arguments = Bundle().apply {
                                putString(ResultFragment.code , args)
                            }
                        }).commit()
            }

            4 -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frameLayout, MenuFragment().apply {
                        arguments = Bundle().apply {
                            putString(MenuFragment.AccessTokenContent,args)
                        }
                    }).commit()
            }
        }
    }

//    fun sendInformationFragment(index: Int, code: String? , scope: String? , client_info: String? , state: String? ){
//        when(index){
//            3 -> {
//                supportFragmentManager.beginTransaction()
//                        .replace(R.id.frameLayout, ResultFragment().apply {
//                            arguments = Bundle().apply{
//                                putString(ResultFragment.code, code)
//                                putString(ResultFragment.scope, scope)
//                                putString(ResultFragment.client_info, client_info)
//                                putString(ResultFragment.state, state)
//                            }
//                        }).commit()
//            }
//        }
//    }



}
package so.ido.oauthtest_1117

import android.util.Log
import java.io.UnsupportedEncodingException
import java.net.URLEncoder
import java.util.*

object Utils {

    // Map 데이터를 url query 문자열로 변환(url encoding 포함)
    fun convertMapToQuerystring(paramMap:Map<*, *>):String {
        val sb = StringBuilder()
        for (e in paramMap.entries)
        {
            if (sb.length > 0)
            {
                sb.append("&")
            }
            sb.append(String.format("%s=%s", urlEncode(defaultString(e.key)), urlEncode(defaultString(e.value))))
        }
        return sb.toString()
    }

    // URL encoding wrapper
    fun urlEncode(src:String):String {
        var ret = ""
        try
        {
            ret = URLEncoder.encode(defaultString(src), "UTF-8")
        }
        catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }
        return ret
    }

    // Map 데이터를 url query 문자열로 변환(url encoding 미포함. 로그용)
    fun convertMapToString(paramMap:Map<*, *>):String {
        val sb = StringBuilder()
        for (e in paramMap.entries)
        {
            if (sb.length > 0)
            {
                sb.append("&")
            }
            sb.append(String.format("%s=%s", defaultString(e.key), defaultString(e.value)))
        }
        return sb.toString()
    }


    // String null 체크
    private fun defaultString(str:String?):String {
        return if (str == null) "" else str
    }
    // Object 를 string 으로 변환
    private fun defaultString(src: Any?):String {
        return defaultString(src, null)
    }
    // Object 를 string 으로 변환
    private fun defaultString(src:Any?, defaultStr:String?):String {
        if (src != null)
        {
            if (src is String)
            {
                return src as String
            }
            else
            {
                return (src).toString()
            }
        }
        else
        {
            return if ((defaultStr == null)) "" else defaultStr
        }
    }

    fun getParamValFromUrlString(url:String, paramKey:String):String {
        Log.d("## url", url)
        val urlParamPair = url.split(("\\?").toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        if (urlParamPair.size < 2)
        {
            Log.d("##", "파라미터가 존재하지 않는 URL 입니다.")
            return ""
        }
        val queryString = urlParamPair[1]
        Log.d("## queryString", queryString)
        val st = StringTokenizer(queryString, "&")
        while (st.hasMoreTokens())
        {
            val pair = st.nextToken()
            Log.d("## pair", pair)
            val pairArr = pair.split(("=").toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            if (paramKey == pairArr[0])
            {
                return pairArr[1] // 찾았을 경우
            }
        }
        return ""
    }
}
package com.cybertiger.getsunset

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    protected fun getSunSet(v: View){
        var city = etCityName.text.toString()
        val url = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)where%20text%3D%22"+city+"%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys"
        MyAsyncTask().execute(url)
    }

    inner class MyAsyncTask: AsyncTask<String, String, String>() {
        override fun onPreExecute() {
            //Before Task is started
        }
        override fun doInBackground(vararg p0: String?): String {
           try {
               val url= URL(p0[0])
               val urlConnect=url.openConnection() as HttpURLConnection
               urlConnect.connectTimeout=7000
               var inString=ConvertStreamToString(urlConnect.inputStream)
               //Cannot Access UI Here
               publishProgress(inString)
           }catch (e:Exception){

           }
            return " "
        }

        override fun onProgressUpdate(vararg values: String?) {
            try{
                var json=JSONObject(values[0])
                val query=json.getJSONObject("query")
                val results=query.getJSONObject("results")
                val channel=results.getJSONObject("channel")
                val astronomy=channel.getJSONObject("astronomy")
                var sunrise=astronomy.getString("sunrise")
                var sunset=astronomy.getString("sunset")
                tvSunSet.text = "Sunrise time is "+sunrise+"\n\nSunset time is "+sunset

            }catch(e:Exception){

            }
        }
        override fun onPostExecute(result: String?) {
            //After task completed
        }
    }

    fun ConvertStreamToString(inputStream:InputStream):String{
        val bufferReader=BufferedReader(InputStreamReader(inputStream))
        var line:String
        var AllString:String=""

        try{
            do{
                line=bufferReader.readLine()
                if(line!=null)
                    AllString += line
            } while(line!=null)
            inputStream.close()

        }catch (e:Exception){

        }

        return AllString
    }
}

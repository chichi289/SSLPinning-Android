package com.chichi289.sslpining.view

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.chichi289.sslpining.R
import com.chichi289.sslpining.databinding.ActivityMainBinding
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.URL
import java.security.KeyStore
import javax.net.ssl.HttpsURLConnection
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManagerFactory

@Suppress("unused")
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.errorData.observe(this) {
            if (it == null) return@observe
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }

        viewModel.userData.observe(this) {
            if (it == null) return@observe
            binding.apply {
                Glide.with(this@MainActivity)
                    .load(it.avatarUrl)
                    .into(imgProfile)
                tvUserName.text = it.name
                tvHandle.text = it.login
                tvFollowers.text = it.getFollowers()
                tvFollowing.text = it.getFollowing()
            }
        }

        viewModel.todoData.observe(this) {
            if (it == null) return@observe
            binding.tvTodo.text = it.toString()
        }

        binding.btnSearch.setOnClickListener {
            val userName = binding.edtProfileName.text.toString().trim()
            if (userName.isNotBlank()) {
                viewModel.getUserData(userName)
            }
        }

        binding.btnFetchTodo.setOnClickListener {
            val toDoId = binding.edtProfileName.text.toString().trim()
            if (toDoId.isNotBlank()) {
                try {
                    viewModel.getTodo(toDoId.toInt())
                } catch (e: NumberFormatException) {
                    Toast.makeText(this, "Please enter valid todo id.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun trustStoreSSLPining() {
        // get the TrustStore that you just placed in resources
        val resourceStream = resources.openRawResource(R.raw.git_certificate)

        // create an empty default KeyStore
        val keyStoreType = KeyStore.getDefaultType()
        val keyStore = KeyStore.getInstance(keyStoreType)

        // load the KeyStore from input stream of TrustStore
        keyStore.load(resourceStream, null/*getTrustStorePassword()?.toCharArray()*/)

        val trustManagerAlgorithm = TrustManagerFactory.getDefaultAlgorithm()
        val trustManagerFactory = TrustManagerFactory.getInstance(trustManagerAlgorithm)
        trustManagerFactory.init(keyStore)

        val sslContext = SSLContext.getInstance("TLS")
        sslContext.init(null, trustManagerFactory.trustManagers, null)

        val url = URL("https://jsonplaceholder.typicode.com/todos/1")

        // open connection using HttpsURLConnection
        val urlConnection = url.openConnection() as HttpsURLConnection

        // assign to sslSocketFactory
        urlConnection.sslSocketFactory = sslContext.socketFactory

        // fetch and print the response
        val inputStream: InputStream = urlConnection.inputStream
        var line: String?
        val sb = StringBuffer()
        try {
            BufferedReader(InputStreamReader(inputStream, "UTF-8")).use { br ->
                while (br.readLine().also { line = it } != null) {
                    sb.append(line)
                }
                Log.d("TrustStoreExample", "Response body is $sb")
            }
        } catch (e: IOException) {
            Log.d("TrustStoreExample", "Exception occurred " + e.stackTrace)
        }
    }

    fun trustStoreSSLPining1() {

        // Load KeyStore with the Certificate file from resources (as InputStream).
        val resourceStream = resources.openRawResource(R.raw.git_certificate)
        val keyStoreType = KeyStore.getDefaultType()
        val keyStore = KeyStore.getInstance("JKS"/*keyStoreType*/)

        keyStore.load(resourceStream, null)

        // Get TrustManagerFactory and init it with KeyStore.
        val trustManagerAlgorithm = TrustManagerFactory.getDefaultAlgorithm()
        val trustManagerFactory = TrustManagerFactory.getInstance(trustManagerAlgorithm)

        trustManagerFactory.init(keyStore)

        // Get an instance of SSLContext, bind it with TrustManager, and create an sslContext
        // with a URL connection.

        val sslContext = SSLContext.getInstance("TLS")
        sslContext.init(null, trustManagerFactory.trustManagers, null)
        val url = URL("https://jsonplaceholder.typicode.com/todos/1")
        val urlConnection = url.openConnection() as HttpsURLConnection
        urlConnection.sslSocketFactory = sslContext.socketFactory

        // fetch and print the response
        val inputStream: InputStream = urlConnection.inputStream
        var line: String?
        val sb = StringBuffer()
        try {
            BufferedReader(InputStreamReader(inputStream, "UTF-8")).use { br ->
                while (br.readLine().also { line = it } != null) {
                    sb.append(line)
                }
                Log.d("TrustStoreExample", "Response body is $sb")
            }
        } catch (e: IOException) {
            Log.d("TrustStoreExample", "Exception occurred " + e.stackTrace)
        }


    }
}
package com.moviles.retrofitapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.moviles.retrofitapp.databinding.ActivityMainBinding
import com.moviles.retrofitapp.remote.PokemonEntry
import com.moviles.retrofitapp.remote.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var editText: EditText
    lateinit var button: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        editText = findViewById(R.id.etPokemon)
        button = findViewById(R.id.btnUpdatePokemon)

        button.setOnClickListener{
            val retrofit = RetrofitBuilder.create().getPokemonById(editText.text.toString())
            retrofit.enqueue(object: Callback<PokemonEntry> {
                override fun onResponse(call: Call<PokemonEntry>, response: Response<PokemonEntry>) {
                    val resBody = response.body()
                    if(resBody != null) {
                        Log.d("retrofitresponse", "front_default: ${resBody.sprites.front_default}")
                        Log.d("retrofitresponse", "name: ${resBody.name}")
                        val typesArray = resBody.types
                        val type = typesArray[0].type.name
                        Log.d("retrofitresponse", "type: ${type}")
                        val stats = resBody.stats
                        for (stat in stats){
                            Log.d("retrofitresponse", "${stat.stat.name}: ${stat.base_stat}")
                        }
                        binding.tvPoke.setText("name: ${resBody.name}"+"type: ${type}")
                    }
                }

                override fun onFailure(call: Call<PokemonEntry>, t: Throwable) {
                    Log.e("retrofitresponse", "error: ${t.message}")

                }
            })
        }

        //val retrofit = RetrofitBuilder.create().getPokemonById("27")



    }
}
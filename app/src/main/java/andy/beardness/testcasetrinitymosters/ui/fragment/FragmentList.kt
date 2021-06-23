package andy.beardness.testcasetrinitymosters.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import androidx.room.RoomDatabase
import andy.beardness.testcasetrinitymosters.R
import andy.beardness.testcasetrinitymosters.adapter.PokemonAdapter
import andy.beardness.testcasetrinitymosters.constant.API
import andy.beardness.testcasetrinitymosters.model.PokemonList
import andy.beardness.testcasetrinitymosters.retro.PokemonRetro
import andy.beardness.testcasetrinitymosters.room.PokemonDB
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.http.PATCH
import java.lang.Exception

class FragmentList : Fragment() {

    var pageCount: Int = 0
    val pageLimit: Int = 20

    lateinit var recycler: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        recycler = view.findViewById(R.id.recycler)
        recycler.layoutManager = LinearLayoutManager(view.context)

        val db = Room.databaseBuilder(
            view.context,
            PokemonDB::class.java,
            "pokemon"
        ).allowMainThreadQueries().build()

        val pokemonDAO = db.pokemonDAO()

        val retrofit: Retrofit =
            Retrofit
                .Builder()
                .baseUrl(API.URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        try {
            val pokemonRetro: PokemonRetro = retrofit.create(PokemonRetro::class.java)
            val call: Call<PokemonList> = pokemonRetro.getPokemons(pageCount, pageLimit)
            call.enqueue(object : Callback<PokemonList> {
                override fun onResponse(call: Call<PokemonList>, response: Response<PokemonList>) {
                    if (response.isSuccessful) {
                        val pokemonList: PokemonList = response.body()!!
                        Observable.just(pokemonDAO)
                            .subscribeOn(Schedulers.newThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe {
                                it.insert(pokemonList.results)
                                recycler.adapter = PokemonAdapter(pokemonList.results)
                            }
                        Log.d("ABC", pokemonList.results.toString())
                    }
                }

                override fun onFailure(call: Call<PokemonList>, t: Throwable) {
                    Log.d("ABC", "onFailure")
                    Observable.just(pokemonDAO)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnError {
                            Log.d("ABC", "Throw" + it.message)
                        }
                        .subscribe {
                            val pokemons = it.getAll()
                            recycler.adapter = PokemonAdapter(pokemons)
                            Log.d("ABC", "pokemons = ${pokemons}")
                        }
                }
            })
        } catch (e: Exception) {

        }

    }
}
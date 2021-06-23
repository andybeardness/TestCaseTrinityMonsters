package andy.beardness.testcasetrinitymosters.retro

import andy.beardness.testcasetrinitymosters.model.Pokemon
import andy.beardness.testcasetrinitymosters.model.PokemonList
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonRetro {

    @GET("pokemon/")
    fun getPokemons(

        @Query("offset")
        offset: Int = 0,

        @Query("limit")
        limit: Int = 20

        ) : Call<PokemonList>

}
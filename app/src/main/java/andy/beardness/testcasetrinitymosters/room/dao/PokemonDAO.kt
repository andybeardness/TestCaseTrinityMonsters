package andy.beardness.testcasetrinitymosters.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import andy.beardness.testcasetrinitymosters.model.Pokemon
import javax.sql.DataSource

@Dao
interface PokemonDAO {

    @Query(value = "SELECT * FROM pokemon")
    fun getAll(): List<Pokemon>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(pokemons: List<Pokemon>)


}
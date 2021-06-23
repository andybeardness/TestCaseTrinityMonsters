package andy.beardness.testcasetrinitymosters.room

import androidx.room.Database
import androidx.room.RoomDatabase
import andy.beardness.testcasetrinitymosters.model.Pokemon
import andy.beardness.testcasetrinitymosters.room.dao.PokemonDAO

@Database(
    entities = [Pokemon::class],
    version = 1,
    exportSchema = false
)
abstract class PokemonDB : RoomDatabase() {
    abstract fun pokemonDAO(): PokemonDAO
}
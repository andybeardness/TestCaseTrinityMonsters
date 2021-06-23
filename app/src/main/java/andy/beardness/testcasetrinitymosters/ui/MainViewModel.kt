package andy.beardness.testcasetrinitymosters.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import andy.beardness.testcasetrinitymosters.model.Pokemon

class MainViewModel : ViewModel() {

    val pokemons: MutableLiveData<List<Pokemon>> by lazy {
        MutableLiveData<List<Pokemon>>()
    }

}
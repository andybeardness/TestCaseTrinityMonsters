package andy.beardness.testcasetrinitymosters.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import andy.beardness.testcasetrinitymosters.R

class FragmentDetail : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val name = arguments?.getString("name")
        val url = arguments?.getString("url")

        view.findViewById<TextView>(R.id.pokemon_name).text = name
        view.findViewById<TextView>(R.id.pokemon_url).text = url
    }
}
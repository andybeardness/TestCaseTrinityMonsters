package andy.beardness.testcasetrinitymosters.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import andy.beardness.testcasetrinitymosters.R

class FragmentMain : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nextBtn: Button = view.findViewById(R.id.btnNext)
        nextBtn.setOnClickListener {
            view.findNavController().navigate(R.id.action_fragmentMain_to_fragmentList)
        }
    }
}
package se.accepted.watcher.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import kotlinx.android.synthetic.main.main_fragment.*
import se.accepted.watcher.R

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.loggedIn.observe(viewLifecycleOwner) { loggedIn ->
            message.text = "Is the user logged in: $loggedIn"
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) { errorMessage ->
            error.text = errorMessage
        }

        viewModel.loading.observe(viewLifecycleOwner) {
            loader.visibility = if(it) View.VISIBLE else View.GONE
            message.visibility = if(!it) View.VISIBLE else View.GONE
            error.visibility = if(!it) View.VISIBLE else View.GONE
        }

        login_button.setOnClickListener {
            viewModel.login("", "")
        }
    }

}
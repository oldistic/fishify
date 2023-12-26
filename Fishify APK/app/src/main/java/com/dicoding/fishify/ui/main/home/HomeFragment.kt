package com.dicoding.fishify.ui.main.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.fishify.R
import com.dicoding.fishify.databinding.FragmentHomeBinding
import com.dicoding.fishify.ui.ViewModelFactory
import com.dicoding.fishify.ui.regis.welcome.WelcomeActivity

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val videoView: VideoView = root.findViewById(R.id.videoView)
        val videoPath =
            "android.resource://" + requireActivity().packageName + "/" + R.raw.fishify_video // Ganti 'your_video_file' dengan nama video sebenarnya di direktori raw

        videoView.setVideoPath(videoPath)

        val mediaController = MediaController(requireContext())
        mediaController.setAnchorView(videoView) // Menetapkan VideoView sebagai anchor
        videoView.setMediaController(mediaController)

        mediaController.visibility = View.GONE

        videoView.setOnPreparedListener { mp ->
            val videoRatio = mp.videoWidth.toFloat() / mp.videoHeight.toFloat()
            val screenRatio =
                402f / 271f // Rasio aspek yang sesuai dengan ukuran VideoView Anda dalam XML

            val layoutParams = videoView.layoutParams
            if (videoRatio > screenRatio) {
                // Jika rasio aspek video lebih besar dari rasio aspek layar VideoView
                layoutParams.width = videoView.width
                layoutParams.height = (videoView.width / videoRatio).toInt()
            } else {
                // Jika rasio aspek video lebih kecil dari rasio aspek layar VideoView
                layoutParams.width = (videoView.height * videoRatio).toInt()
                layoutParams.height = videoView.height
            }
            videoView.layoutParams = layoutParams

            //Mulai pemutaran video saat VideoView diklik
            videoView.setOnClickListener {
                if (!videoView.isPlaying) {
                    videoView.start()
                }
            }
        }

        // Menyembunyikan MediaController setelah selesai
        videoView.setOnCompletionListener {
            mediaController.visibility = View.GONE
        }

        return root
    }
}
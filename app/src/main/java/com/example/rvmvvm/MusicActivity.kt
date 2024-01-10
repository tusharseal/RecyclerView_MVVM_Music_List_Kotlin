package com.example.rvmvvm

import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.rvmvvm.model.MusicModel

class MusicActivity : AppCompatActivity() {

    private lateinit var play: ImageButton
    private lateinit var pause: ImageButton
    private lateinit var musicName: TextView
    private lateinit var musicTime: TextView
    private lateinit var musicPaid: TextView
    private lateinit var musicSubtitile: TextView
    private lateinit var musicImg: ImageView


    private var musicModelRecd: MusicModel.Data? = null
    private lateinit var mediaPlayer: MediaPlayer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music)

        play = findViewById(R.id.idIBPlay)
        pause = findViewById(R.id.idIBPause)
        musicName = findViewById(R.id.music_name)
        musicTime = findViewById(R.id.music_time)
        musicPaid = findViewById(R.id.music_paid)
        musicSubtitile = findViewById(R.id.music_subtitile)
        musicImg = findViewById(R.id.music_img)

        init()
    }

    private fun init() {

        val intent = intent
        val bundle: Bundle? = intent.extras
        musicModelRecd = bundle!!.getParcelable("MUSIC_ITEM")
        mediaPlayer = MediaPlayer()
        setUI()
        onClicks()
    }

    private fun onClicks() {
        play.setOnClickListener {
            // on below line we are creating a variable for our audio url
            try {
                var audioUrl = musicModelRecd?.audioFile ?: ""
            // on below line we are setting audio stream
            // type as stream music on below line.
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)

            // on below line we are running a try
            // and catch block for our media player.

                // on below line we are setting audio
                // source as audio url on below line.
                mediaPlayer.setDataSource(audioUrl)

                // on below line we are
                // preparing our media player.
                mediaPlayer.prepare()

                // on below line we are
                // starting our media player.
                mediaPlayer.start()

            } catch (e: Exception) {

                // on below line we are handling our exception.
                e.printStackTrace()
            }
            // on below line we are displaying a toast message as audio player.
            Toast.makeText(this, "Audio started playing..", Toast.LENGTH_SHORT).show()

        }

        pause.setOnClickListener {
            // on below line we are checking
            // if media player is playing.
            if (mediaPlayer.isPlaying) {
                // if media player is playing we
                // are stopping it on below line.
                try {
                    mediaPlayer.stop()

                    // on below line we are resetting
                    // our media player.
                    mediaPlayer.reset()

                    // on below line we are calling
                    // release to release our media player.
                    mediaPlayer.release()
                } catch (e: Exception) {

                    // on below line we are handling our exception.
                    e.printStackTrace()
                }

                // on below line we are displaying a toast message to pause audio/
                Toast.makeText(this, "Audio has been  paused..", Toast.LENGTH_SHORT)
                    .show()

            } else {
                // if audio player is not displaying we are displaying below toast message
                Toast.makeText(this, "Audio not played..", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun setUI() {
        Glide.with(this@MusicActivity).load(musicModelRecd?.imageFile).into(musicImg)
        musicName.text = musicModelRecd?.title
        musicTime.text = "${musicModelRecd?.minute}min ${musicModelRecd?.second}secs"
        musicPaid.text = musicModelRecd?.paidFree
        musicSubtitile.text = musicModelRecd?.subTitle
    }

    override fun onBackPressed() {
        try {
            pause.performClick()
        } catch (e: Exception) {

            // on below line we are handling our exception.
            e.printStackTrace()
        }

        super.onBackPressed()
    }

}
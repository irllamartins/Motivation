package com.example.motivation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.motivation.R
import com.example.motivation.infra.MotivationConstants
import com.example.motivation.infra.SecurityPreferences
import com.example.motivation.repository.Mock
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var mSecurityPreferences : SecurityPreferences
    private var mPhraseFilter: Int = MotivationConstants.PHRASEFILTER.ALL
    private val mMock: Mock = Mock()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //instancia
        mSecurityPreferences = SecurityPreferences(this)

        //capturar informação salva
        mSecurityPreferences.getString(MotivationConstants.KEY.PERSON_NAME)
        textName.text = mSecurityPreferences.getString(MotivationConstants.KEY.PERSON_NAME)

        //tira o actionbar que fica com a configuração padrão
        if(supportActionBar!=null){
            supportActionBar!!.hide()
        }
        imageAll.setColorFilter(resources.getColor(R.color.colorAccent))
        handleNewPhrase()

        buttonNewPhrase.setOnClickListener(this)
        imageAll.setOnClickListener(this)
        imageHappy.setOnClickListener(this)
        imageMorning.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        val id =view.id
        val listFilter = listOf(R.id.imageAll,R.id.imageHappy,R.id.imageMorning)
        if(id == R.id.buttonNewPhrase){
            handleNewPhrase()
        }
        else if(id in listFilter){
            handleFilter(id)
        }

    }
    private fun handleFilter(id:Int){
        imageAll.setColorFilter(resources.getColor(R.color.white))
        imageHappy.setColorFilter(resources.getColor(R.color.white))
        imageMorning.setColorFilter(resources.getColor(R.color.white))

        when(id) {
            R.id.imageAll -> {
                imageAll.setColorFilter(resources.getColor(R.color.colorAccent))
                mPhraseFilter = MotivationConstants.PHRASEFILTER.ALL
            }
            R.id.imageHappy -> {
                imageHappy.setColorFilter(resources.getColor(R.color.colorAccent))
                mPhraseFilter = MotivationConstants.PHRASEFILTER.HAPPY
            }
            R.id.imageMorning -> {
                imageMorning.setColorFilter(resources.getColor(R.color.colorAccent))
                mPhraseFilter = MotivationConstants.PHRASEFILTER.MORNING
            }
            else -> {
                MotivationConstants.PHRASEFILTER.MORNING
                imageMorning.setColorFilter(resources.getColor(R.color.colorAccent)
                )
            }
        }
    }
    private fun handleNewPhrase(){
        textPhrase.text = mMock.getPhrase(mPhraseFilter)
    }

}
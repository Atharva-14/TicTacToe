package com.shivtej.tictactoe.ui.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.facebook.ads.*
import com.facebook.ads.AdSize
import com.shivtej.tictactoe.R
import com.shivtej.tictactoe.databinding.FragmentModeBinding


class ModeFragment : Fragment(R.layout.fragment_mode) {

    private lateinit var binding: FragmentModeBinding
    lateinit var bundle: Bundle
    private lateinit var adView: AdView
    lateinit var interstitialAd2: InterstitialAd
    lateinit var interstitialAd: InterstitialAd

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentModeBinding.bind(view)

        AudienceNetworkAds.initialize(context)

        interstitialAd = InterstitialAd(context, "454787735580421_458422231883638")

        adView = AdView(context, "454787735580421_458479588544569", AdSize.BANNER_HEIGHT_50)

        binding.bannerContainer.addView(adView)

        adView.loadAd()

        val interstitialAdListener = object : InterstitialAdListener {
            override fun onInterstitialDisplayed(ad: Ad) {
                // Interstitial ad displayed callback
            }

            override fun onInterstitialDismissed(ad: Ad) {
                // Interstitial dismissed callback
                findNavController().navigate(R.id.action_modeFragment_to_gridFragment)
            }

            override fun onError(ad: Ad, adError: AdError) {
                // Ad error callback
            }

            override fun onAdLoaded(ad: Ad) {
                // Interstitial ad is loaded and ready to be displayed
                // Show the ad
            }

            override fun onAdClicked(ad: Ad) {
                // Ad clicked callback
            }

            override fun onLoggingImpression(ad: Ad) {
                // Ad impression logged callback
            }
        }
        interstitialAd.loadAd(
            interstitialAd.buildLoadAdConfig()
                .withAdListener(interstitialAdListener)
                .build()
        )

        binding.playerVsCpu.setOnClickListener {
            if(interstitialAd.isAdLoaded){
                interstitialAd.show()
            }else{
                findNavController().navigate(
                    R.id.action_modeFragment_to_gridFragment
                )
            }

        }

        interstitialAd2 = InterstitialAd(context, "454787735580421_458422231883638")

        val interstitialAdListener2 = object : InterstitialAdListener {
            override fun onInterstitialDisplayed(ad: Ad) {
                // Interstitial ad displayed callback
            }

            override fun onInterstitialDismissed(ad: Ad) {
                // Interstitial dismissed callback

                goToPlayerVsPlayerGrid(bundle)
            }

            override fun onError(ad: Ad, adError: AdError) {
                // Ad error callback
            }

            override fun onAdLoaded(ad: Ad) {
                // Interstitial ad is loaded and ready to be displayed
                // Show the ad
            }

            override fun onAdClicked(ad: Ad) {
                // Ad clicked callback
            }

            override fun onLoggingImpression(ad: Ad) {
                // Ad impression logged callback
            }
        }
        interstitialAd2.loadAd(
            interstitialAd2.buildLoadAdConfig()
                .withAdListener(interstitialAdListener2)
                .build()
        )

        binding.playerVsPlayer.setOnClickListener {

            val mDialogView =
                LayoutInflater.from(activity).inflate(R.layout.fragment_custom_dialog, null)
            val mBuilder = AlertDialog.Builder(activity).setView(mDialogView)
            val mAlertDialog = mBuilder.show()

            val btn = mDialogView.findViewById<Button>(R.id.dialog_button_start)
            val name1 = mDialogView.findViewById<EditText>(R.id.player1_et)
            val name2 = mDialogView.findViewById<EditText>(R.id.player2_et)

            btn.setOnClickListener {
                val p1 = name1.text.toString()
                val p2 = name2.text.toString()
                mAlertDialog.dismiss()

                bundle = Bundle()
                bundle.putString("playe1", p1)
                bundle.putString("playe2", p2)
                if(interstitialAd2.isAdLoaded){
                    interstitialAd2.show()

                }else{

                    goToPlayerVsPlayerGrid(bundle)

                }


            }
        }
    }

    override fun onDestroy() {
        if (adView != null ||  interstitialAd != null || interstitialAd2 != null) {
            adView.destroy()
            interstitialAd.destroy()
            interstitialAd2.destroy()
        }
        super.onDestroy()
    }

    private fun goToPlayerVsPlayerGrid(bundle: Bundle){
        findNavController().navigate(
            R.id.action_modeFragment_to_playerVsPlayerGridFragment,
            bundle
        )
    }

}


package com.betylf.b_corner.ui.result

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import com.betylf.b_corner.database.DatabaseHandler
import com.betylf.b_corner.databinding.FragmentDetailBinding


class DetailFragment : Fragment() {
    private lateinit var databaseHandler: DatabaseHandler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        databaseHandler = DatabaseHandler(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        val result = databaseHandler.getAllProblem()
        val result1 = result.count { it == 0 }
        val result2 = result.count { it == 1 }
        val result3 = result.count { it == 2 }

        if (result1 > result2 && result1 > result3) {
            binding.result.text = Html.fromHtml(
                "“Menjaga Lisan sama pentingnya dengan menjaga hati, sebab jika seseorang hatinya baik maka akan keluar dari lisannya perkataan yang baik. Lisan mencerminkan kebersihan hati seseorang”.- Habib Ali Zainal Abidin Bin Abdurrahman Al-Juft<br/><br/>وَأَمَّا الَّذِينَ فِي قُلُوبِهِمْ مَرَضٌ فَزَادَتْهُمْ رِجْسًا إِلَىٰ رِجْسِهِمْ وَمَاتُوا وَهُمْ كَافِرُونَ <br/><br/>“Dan Adapun orang-orang yang di dalam hati mereka ada penyakit, Maka dengan surat itu bertambah kekafiran mereka, disamping kekafirannya (yang telah ada) dan mereka mati dalam Keadaan kafir.” (QS. At-Taubah [9] : 125).",
                HtmlCompat.FROM_HTML_MODE_LEGACY
            )
        } else if (result2 > result1 && result2 > result3) {
            binding.result.text = Html.fromHtml(
                "<i>“Barang Siapa yang  beriman kepada Allah  dan hari akhir, hendaklah ia berkata baik atau lebih baik diam”</i> (HR. Riwayat Al-Bukhari Muslim). Lisan dapat mempermudah manusia menuju surga namun dapat juga menjerumuskan manusia ke dalam panasnya api neraka. Menjaga lisan adalah salah satu kunci agar individu dapat memperbanyak bekal ketaqwaan. Rasulullah saw. bersabda,<br/><br/>“Barang siapa yang beriman kepada Allah dan hari akhir maka hendaklah ia berkata baik atau hendaklah ia diam” (Muttafaq ‘alaih: HR. Bukhari dan HR. Muslim). <br/><br/>Perkataan-perkataan yang baik akan muncul dari lisan-lisan orang yang beriman dan bertaqwa kepada Allah swt. Manusia yang taat akan semua perintah Allah swt. dan menjauhi semua larangannya akan dapat menggunakan lisannya dengan penuh hati-hati. Sebab, ia mengetahui bahwa Allah swt. akan memberikan pahala yang sangat besar untuk individu yang menjaga lisannya dan ia juga mengetahui bahwa hukuman Allah swt. bagi orang-orang yang memiliki penyakit lisan juga tidak ringan. Rasulullah saw. Bersabda (Penafsiran artinya):<br/><br/><i>“Sesungguhnya seseorang mengatakan kalimat yang diridhai Allah dan ia tidak menaruh perhatian terhadapnya melainkan Allah akan mengangkatnya beberapa derajat. Sesungguhnya seorang hamba mengatakan kalimat yang dimurkai Allah dan ia tidak menaruh perhatian terhadapnya melainkan ia terjerumus dengan sebab kalimat tersebut ke jahannam.” (HR. Bukhari). <br/><br/>Surga telah menanti hamba-hamba yang selalu menjaga lisannya. “Barangsiapa bisa memberikan jaminan kepadaku (untuk menjaga) apa yang ada di antara dua janggutnya dan dua kakinya, maka kuberikan kepadanya jaminan masuk surga”</i> (HR. Bukhari).",
                HtmlCompat.FROM_HTML_MODE_LEGACY
            )
        } else {
//            binding.result.text = resources.getString(R.string.desc_perilaku)
            binding.result.text = Html.fromHtml(
                "“Pahamilah Bahwa hidup ini bukan tentang siapa yang paling baik, melainkan tentang bagaimana kita saling membangun kebaikan”. Dalam Al-Qur’an dijelaskan pada surat Al-Hujrat Ayat 11 :<br/><br/>يَا أَيُّهَا الَّذِينَ آمَنُوا لَا يَسْخَرْ قَوْمٌ مِنْ قَوْمٍ عَسَىٰ أَنْ يَكُونُوا خَيْرًا مِنْهُمْ وَلَا نِسَاءٌ مِنْ نِسَاءٍ عَسَىٰ أَنْ يَكُنَّ خَيْرًا مِنْهُنَّ ۖ وَلَا تَلْمِزُوا أَنْفُسَكُمْ وَلَا تَنَابَزُوا بِالْأَلْقَابِ ۖ بِئْسَ الِاسْمُ الْفُسُوقُ بَعْدَ الْإِيمَانِ ۚ وَمَنْ لَمْ يَتُبْ فَأُولَٰئِكَ هُمُ الظَّالِمُونَ<br /><br/>Artinya : <i>Hai orang-orang yang beriman, janganlah sekumpulan orang laki-laki merendahkan kumpulan yang lain, boleh jadi yang ditertawakan itu lebih baik dari mereka. Dan jangan pula sekumpulan perempuan merendahkan kumpulan lainnya, boleh jadi yang direndahkan itu lebih baik. Dan janganlah suka mencela dirimu sendiri dan jangan memanggil dengan gelaran yang mengandung ejekan. Seburuk-buruk panggilan adalah (panggilan) yang buruk sesudah iman dan barangsiapa yang tidak bertobat, maka mereka itulah orang-orang yang zalim.</i>",
                HtmlCompat.FROM_HTML_MODE_LEGACY
            )
        }

        binding.result.text
        return binding.root
    }

}
package com.arisurya.jetpackpro.canangbali.utils

import com.arisurya.jetpackpro.canangbali.data.source.local.entity.CanangEntity
import com.arisurya.jetpackpro.canangbali.data.source.local.entity.PhilosophyEntity
import com.arisurya.jetpackpro.canangbali.data.source.local.entity.ShopEntity
import com.arisurya.jetpackpro.canangbali.data.source.local.entity.UpakaraEntity

object DataDummy {

    fun generateDummyUpakara(): List<UpakaraEntity> {
        val upakara = ArrayList<UpakaraEntity>()

        upakara.add(
            UpakaraEntity(
                1,
                "Nyepi",
                "",
                "Lorem ipsum",
            )
        )

        upakara.add(
            UpakaraEntity(
                2,
                "Galungan",
                "",
                "Lorem ipsum",
            )
        )

        upakara.add(
            UpakaraEntity(
                3,
                "Kuningan",
                "",
                "Lorem ipsum",
            )
        )

        upakara.add(
            UpakaraEntity(
                4,
                "Purnama",
                "",
                "Lorem ipsum",
            )
        )

        upakara.add(
            UpakaraEntity(
                5,
                "Tilem",
                "",
                "Lorem ipsum",
            )
        )
        return upakara
    }

    fun generateDummyCanang(): List<CanangEntity> {

        val canang = ArrayList<CanangEntity>()

        canang.add(
            CanangEntity(
                1,
                "Canang Sari",
                "",
                "Lorem ipsum",
                "Lorem ipsum"
            )
        )

        canang.add(
            CanangEntity(
                2,
                "Canang Goak",
                "",
                "Lorem ipsum",
                "Lorem ipsum"
            )
        )

        canang.add(
            CanangEntity(
                3,
                "Canang Ituk-ituk",
                "",
                "Lorem ipsum",
                "Lorem ipsum"
            )
        )

        canang.add(
            CanangEntity(
                4,
                "Canang Genten",
                "",
                "Lorem ipsum",
                "Lorem ipsum"
            )
        )

        return canang
    }

    fun generateDummyShop(): List<ShopEntity> {
        val shop = ArrayList<ShopEntity>()

        shop.add(
            ShopEntity(
                1,
                "Toko 1",
                "",
                "Denpasar",
                "089999999",
                "Canang Sari, Canang Goak",
                "Melayani semua jenis canang"
            )
        )

        shop.add(
            ShopEntity(
                2,
                "Toko 2",
                "",
                "Gianyar",
                "089999999",
                "Canang Sari, Canang Goak",
                "Melayani semua jenis canang"
            )
        )

        shop.add(
            ShopEntity(
                3,
                "Toko 3",
                "",
                "Denpasar",
                "089999999",
                "Canang Sari, Canang Goak",
                "Melayani semua jenis canang"
            )
        )

        shop.add(
            ShopEntity(
                4,
                "Toko 4",
                "",
                "Badung",
                "089999999",
                "Canang Sari, Canang Goak",
                "Melayani semua jenis canang"
            )
        )

        shop.add(
            ShopEntity(
                5,
                "Toko 5",
                "",
                "Klungkung",
                "089999999",
                "Canang Sari, Canang Goak",
                "Melayani semua jenis canang"
            )
        )

        return shop
    }

    fun generateDummyPhilosophy(): PhilosophyEntity {
        return PhilosophyEntity(
            1,
            "Filosofi Canang",
            "",
            """
                Canang sari merupakan implementasi dari Bhagavad Gita IX.26 sebagai upakara (perlengkapan) keagamaan untuk persembahan tiap harinya. 
                      
                          
                        “patraṃ puṣpaṃ phalaṃ toyaṃ yo me 
                               bhaktyā prayacchati, tadahaṃ 
                      bhaktyupahṛtamaśnāmi prayatātmanaḥ ”
                                    Bhagawad Gita IX.26
                
               
                Arti Sloka Bhagavad Gita IX.26
                Siapapun yang sujud mempersembahkan daun, bunga, buah atau air sepenuh bhakti kepada-KU, persembahan cinta, persembahan dari hati suci murni itu akan AKU terima (Radhakrishnan, 1971:248).
                
                Canang berasal dari bahasa Jawa Kuno yang berarti sirih yang disuguhkan pada tamu yang sangat dihormati. Canang merupakan salah satu bentuk Upakāra/banten yang selalu menyertai atau melengkapi setiap sesajen/persembahan di Bali, semua Upakāra  yang dipersiapkan belum disebut lengkap apabila tidak di lengkapi dengan Canang Sari dan Canang memiliki nilai agama jika dilengkapi dengan porosan yang bahan pokoknya adalah sirih. Perlengkapan canang yang paling penting adalah alas yang dipakai berupa ceper atau daun pisang yang berbentuk segi empat, lingkaran, dan segitiga. Kemudian diatas alas tersebut disusun plawa, porosan, urasari kemudian bunga.
                
                
                Makna Perlengkapan Canang :
                
                1.	Ceper :
                Ceper adalah sebagai alas dari sebuah canang, yang memiliki bentuk segi empat. Ceper adalah sebagai lambang angga-sarira (badan), empat sisi dari pada ceper sebagai lambang/nyasa dari Panca Maha Bhuta, Panca Tan Mantra, Panca Buddhindriya, Panca Karmendriya. Keempat itulah yang membentuk terjadinya Angga-sarira (badan wadag) ini.
                
                2.	Beras :
                Beras atau wija sebagai lambang/nyasa Sang Hyang Ātma , yang menjadikan badan ini bisa hidup, Beras/wija sebagai lambang benih, dalam setiap insan/kehidupan diawali oleh benih yang bersumber dari Ida Sang Hyang Widhi Wasa yang berwujud Ātma. Ceper sebagai lambang/nyasa angga-sarira/badan tiadalah gunanya tanpa kehadiran Sang Hyang Ātma . Tak ubahnya bagaikan benda mati, yang hanya menunggu kehancurannya. Maka dari itulah di atas sebuah ceper juga diisi dengan beras, sebagai lambang/nyasa Sang Hyang Ātma . Maka dari itulah hidup kita di belenggu oleh Citta dan Klesa, Ātma menimbulkan terjadinya Citta Angga-sarira (badan kasar) menimbulkan terjadinya klesa, itulah yang menyebabkan setiap umat manusia memiliki kelebihan dan kekurangannya.
                
                3.	Porosan :
                Sebuah Porosan terbuat dari daun sirih, kapur/pamor, dan jambe atau gambir sebagai lambang/nyasa Tri-Premana, Bayu, Sabda, dan Idep (pikiran, perkataan, dan perbuatan). Daun sirih sebagai lambang warna hitam sebagai nyasa Bhatara Visnu, dalam bentuk Tri-Premana sebagai lambang/nyasa dari Sabda (perkataan). Jambe/Gambir sebagai nyasa Bhatara Brahma, dalam bentuk Tri-Premana sebagai lambang/nyasa Bayu (perbuatan). Kapur/Pamor sebagai lambang/nyasa Bhatara Iswara, dalam bentuk Tri-Premana sebagai lambang/nyasa Idep (pikiran). Suatu kehidupan tanpa dibarengi dengan Tri-Premana dan Tri Kaya, suatu kehidupan tiadalah artinya, hidup ini akan pasif karena dari adanya Tri-Premana dan Tri Kaya itulah kita bisa memiliki suatu aktivitas, tanpa kita memiliki suatu aktivitas kita tidak akan dapat menghadapi badan ini. Suatu aktivitas akan terwujud karena adanya Tri-Premana ataupun Tri-Kaya.
                
                4.	Tebu dan pisang :
                Di atas sebuah ceper telah diisi dengan beras, porosan, dan juga diisi dengan seiris tebu dan seiris pisang. Tebu atapun pisang memiliki makna sebagai lambang/nyasa amrtha. Setelah kita memiliki badan dan jiwa yang menghidupi badan kita, dan Tri Pramana yang membuat kita dapat memiliki aktivitas, dengan memiliki suatu aktivitaslah kita dapat mewujudkan Amrtha untuk menghidupi badan dan jiwa ini. Tebu dan pisang adalah sebagai lambang/ nyasa Amrtha yang diciptakan oleh kekuatan Tri Pramana dan dalam wujud Tri Kaya.
                
                5.	Sampian Uras : 
                Sampian uras dibuat dari rangkaian janur yang ditata berbentuk bundar yang biasanya terdiri dari delapan ruas atau helai, yang melambangkan roda kehidupan dengan Astaa iswaryanya/delapan karakteristik yang menyertai setiap kehidupan umat manusia. Yaitu : Dahram (Kebijaksanaan), Sathyam (Kebenaran dan kesetiaan), Pasupati (ketajaman, intelektualitas), kama Kesenangan), Eswarya (kepemimpinan), Krodha (kemarahan), Mrtyu (kedengkian, iri hati, dendam), Kala ( kekuatan). Itulah delapan karakteristik yang dimiliki oleh setiap umat manusia, sebagai pendorong melaksanakan aktivitas, dalam menjalani roda kehidupannya.
                
                6.	Bunga :
                Bunga adalah sebagai lambang/nyasa, kedamaian, ketulusan hati. Pada sebuah canang bunga akan ditaruh di atas sebuah sampian uras, sebagai lambang/nyasa di dalam kita menjalani roda kehidupan ini hendaknya selalu dilandasi dengan ketulusan hati dan selalu dapat mewujudkan kedamaian bagi setiap insan.
                
                7.	Kembang Rampai :
                Kembang rampai akan ditaruh di atas susunan/rangkaian bunga-bunga pada suatu canang, kembang rampai memiliki makna sebagai lambang/nyasa kebijaksanaan. Dari kata kembang rampai memiliki dua arti, yaitu: kembang berarti bunga dan rampai berarti macam-macam, sesuai dengan arah pengider-ideran kembang rampai di taruh di tengah sebagai simbol warna brumbun, karena terdiri dari bermacam-macam bunga. Dari sekian macam bunga, tidak semua memiliki bau yang harum, ada juga bunga yang tidak memiliki bau, begitu juga dalam kita menjalani kehidupan ini, tidak selamanya kita akan dapat menikmati kesenangan adakalanya juga kita akan tertimpa oleh kesusahan, kita tidak akan pernah dapat terhindar dari dua dimensi kehidupan ini. Untuk itulah dalam kita menata kehiupan ini hendaknya kita memiliki kebijaksanaan.
                
                8.	Lepa :
                Lepa atau boreh miyik adalah sebagai lambang/nyasa sebagai sikap dan prilaku yang baik. Boreh miyik/lulur yang harum, lalau seseorang memaki lulur, pasti akan dioleskan pada kulitnya, jadi lulur sifat di luar yang dapat disaksikan oleh setiap orang. Yang dapat dilihat ataupun disaksikan oleh orang lain adalah prilaku kita, karena prilakunyalah seseorang akan disebut baik ataupun buruk, seseorang akan dikatakan baik apabila dia selalu berbuat baik, begitu juga sebaliknya seseorang akan dikatakan buruk kalau di selalu berbuat hal-hal yang tidak baik. Boreh miyik sebagai lambang/nyasa perbuatan yang baik.
                
                9.	Minyak wangi :
                Minyak wangi/miyik-miyikan sebagai lambang/nyasa ketenangan jiwa atau pengendalian diri, minyak wangi biasanya diisi pada sebuah canang. Sebagai lambang/nyasa di dalam kita menata hidup dan kehidupan ini hendaknya dapat dijalankan dengan ketenangan jiwa dan pengendalian diri yang baik, saya umpamakan seperti air yang tenang, di dalam air yang kita akan dapat melihat jauh ke dalam air, sekecil apapun benda yang ada dalam air dengan gampang kita dapat melihatnya. Begitu juga dalam kita menjalani kehidupan ini, dengan ketenangan jiwa dan pengendalian diri yang mantap kita akan dapat menyelesaikan segala beban hidup ini.
                
                
            """.trimIndent()
        )
    }
}
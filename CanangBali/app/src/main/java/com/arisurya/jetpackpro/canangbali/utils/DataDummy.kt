package com.arisurya.jetpackpro.canangbali.utils

import com.arisurya.jetpackpro.canangbali.data.source.local.entity.CanangEntity
import com.arisurya.jetpackpro.canangbali.data.source.local.entity.ShopEntity
import com.arisurya.jetpackpro.canangbali.data.source.local.entity.UpakaraEntity

object DataDummy {

    fun generateDummyUpakara(): List<UpakaraEntity> {
        val upakara = ArrayList<UpakaraEntity>()

        upakara.add(
            UpakaraEntity(
                1,
                "Nyepi",
                "https://drive.google.com/drive/u/0/folders/12e-92gs-zZqb_lTpCB6yHMBQNG1ltEwz",
                "Lorem ipsum",
            )
        )

        upakara.add(
            UpakaraEntity(
                2,
                "Calungan",
                "https://drive.google.com/drive/u/0/folders/12e-92gs-zZqb_lTpCB6yHMBQNG1ltEwz",
                "Lorem ipsum",
            )
        )

        upakara.add(
            UpakaraEntity(
                3,
                "Kuningan",
                "https://drive.google.com/drive/u/0/folders/12e-92gs-zZqb_lTpCB6yHMBQNG1ltEwz",
                "Lorem ipsum",
            )
        )

        upakara.add(
            UpakaraEntity(
                4,
                "Purnama",
                "https://drive.google.com/drive/u/0/folders/12e-92gs-zZqb_lTpCB6yHMBQNG1ltEwz",
                "Lorem ipsum",
            )
        )

        upakara.add(
            UpakaraEntity(
                5,
                "Tilem",
                "https://drive.google.com/drive/u/0/folders/12e-92gs-zZqb_lTpCB6yHMBQNG1ltEwz",
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
}
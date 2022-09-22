package com.tahir.marvelapp.data.commonDTOs

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tahir.marvelapp.data.models.characters.BaseCharacters
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class MarvelCharacter(
    @PrimaryKey
    val id: Int? = null,
    val Name: String? = null,
    val imageUrl: String? = null
): Parcelable {
    companion object {


        fun fromCharacterBaseClasstoDTO(marvelCharacterData: BaseCharacters): ArrayList<MarvelCharacter> {
            var marvelCharacters = arrayListOf<MarvelCharacter>()
            for (character in marvelCharacterData.data?.results.orEmpty()) {
                marvelCharacters.add(
                    MarvelCharacter(
                        character.id,
                        character.name,
                        (character.thumbnail?.path?.replace(
                            "http:",
                            "https:"
                        ) + "." + character.thumbnail?.extension)
                    )
                )
            }

            return marvelCharacters
        }


    }

}
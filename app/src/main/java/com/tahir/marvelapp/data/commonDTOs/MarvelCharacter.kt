package com.tahir.marvelapp.data.commonDTOs

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tahir.marvelapp.data.models.characters.BaseCharacters
import kotlinx.android.parcel.Parcelize
/*
DTO - Only contains the fields required to show the list of characters details.
 */
@Entity
@Parcelize
data class MarvelCharacter(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val marvel_id: Int? = null,
    val Name: String? = null,
    val imageUrl: String? = null
) : Parcelable {
    companion object {


        fun fromCharacterBaseClasstoDTO(marvelCharacterData: BaseCharacters): ArrayList<MarvelCharacter> {
            var marvelCharacters = arrayListOf<MarvelCharacter>()
            for (character in marvelCharacterData.data?.results.orEmpty()) {
                marvelCharacters.add(
                    MarvelCharacter(
                        marvel_id = character.id,
                        Name = character.name,
                        imageUrl = (character.thumbnail?.path?.replace(
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
package com.tahir.marvelapp.Constants

class WebServiceConstants {
    companion object {
        const val BASE_URL = "https://gateway.marvel.com/v1/public/"
        const val POKEMON_IMG_PREFIX = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"

        const val STARTING_OFFSET_INDEX = 0
        const val PAGE_SIZE = 100

        // API Constants path variables
        const val GET_CHARACTERS = "characters"
        const val SPECIES = "pokemon-species"
        const val SPECIES_DETAILS = "pokemon-species/{id}"


    }
}
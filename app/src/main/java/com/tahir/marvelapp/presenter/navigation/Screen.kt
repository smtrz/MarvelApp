package com.tahir.marvelapp.presenter.navigation

/*
Sealed class with the variants of all the app screens.
 */
sealed class Screen(val route: String) {
	object CharacterListScreen : Screen("characterlist_screen")
	object CharacterDetailscreen : Screen("characterdetail_screen")

	/**
	 * appends the function argument that needs to be passed in the navigation route.
	 * @param args
	 */
	fun withArgs(vararg args: String): String {
		return buildString {
			append(route)
			args.forEach { arg ->
				append("/$arg")
			}
		}

	}
}

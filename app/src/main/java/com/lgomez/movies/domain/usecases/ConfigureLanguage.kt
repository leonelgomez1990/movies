package com.lgomez.movies.domain.usecases

import android.util.Log
import com.lgomez.movies.core.utils.MyResult
import com.lgomez.movies.domain.model.AppLanguage
import com.lgomez.movies.domain.repositories.MoviesRepository
import java.util.*

class ConfigureLanguage(private val moviesRepository: MoviesRepository) {

    companion object {
        const val TAG = "ConfigureLanguage"
    }

    suspend operator fun invoke() {
        when (val result = moviesRepository.getAvailableLanguages()) {
            is MyResult.Failure -> {
            }
            is MyResult.Success -> {
                val local = Locale.getDefault().toLanguageTag()
                Log.d(TAG, "Language app $local")
                if (result.data.contains(local)) {
                    AppLanguage.code = local
                } else
                    AppLanguage.code = "en-US"
                Log.d(TAG, "Set language API ${AppLanguage.code}")
            }
        }
    }

}

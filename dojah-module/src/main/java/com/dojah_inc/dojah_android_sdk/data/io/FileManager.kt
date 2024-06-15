package com.dojah_inc.dojah_android_sdk.data.io

import android.content.Context
import android.content.res.AssetManager
import android.net.Uri
import androidx.core.content.FileProvider
import com.dojah_inc.dojah_android_sdk.BuildConfig
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.*
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Its only Job is to rewrite the files from the Assets to a file directory
 */
@Singleton
class FileManager @Inject constructor(
        @ApplicationContext private val context: Context) {
    companion object {
        const val ASSET_DIR = "assets"

        const val COUNTRIES_DIR = "countries"
        const val VAS_DIR = "vas"

        const val NETWORK_PROVIDER_DIR = "vas/networkprovider"

        /**
         * Returns the storage version of the asset directory
         */
        fun Context.getAssetDirectory(): File {
            return File(getSaveDirectory(), ASSET_DIR).apply {
                if(!exists()) mkdirs()
            }
        }

        fun Context.getSaveDirectory(): File {
            return getExternalFilesDir(null)!!//if (BuildConfig.DEBUG) getExternalFilesDir(null)!! else filesDir
        }

        fun Context.getCacheDirectory(): File {
            return /*if (BuildConfig.DEBUG)*/ externalCacheDir!!// else cacheDir
        }
    }

    private val baseDir = context.getAssetDirectory()

    private var assetManager: AssetManager? = null

    private var scope = CoroutineScope(Dispatchers.Default)

    fun copyAssets(callback: () -> Unit) {
        try{
            scope.launch(Dispatchers.IO) {
                if (baseDir.directorySize <= 0) {
                    assetManager = context.resources.assets

                    listOf(
                            launch { rewriteVas() },
                            launch { rewriteCountries() }
                    ).joinAll()

                    launch(Dispatchers.Main) { callback() }

                } else launch(Dispatchers.Main) { callback() }
            }

            scope.launch(Dispatchers.IO) {
                val file = File(context.getSaveDirectory(), "notification_tone.wav")
                if(!file.exists() || file.length() <= 0) {
                    assetManager = context.resources.assets
                    copyNotificationTone()
                }
            }

        } catch(e: Exception){
            e.printStackTrace()
        }
    }

    private suspend fun rewriteCountries() {
        val countryDir = File(baseDir, COUNTRIES_DIR).apply { mkdirs() }

        assetManager?.let { manager ->
            manager.list(COUNTRIES_DIR)?.map {
                scope.launch {
                    manager.open("$COUNTRIES_DIR/$it").run {
                        val array = ByteArray(available()).also { arr -> read(arr) }

                        File(countryDir, it).also { file ->
                            val parent = file.parentFile!!
                            if(parent.exists() || parent.mkdirs()) {
                                if (file.createNewFile()) {
                                    file.outputStream().use { stream -> stream.write(array) }
                                }
                            }
                        }
                    }
                }
            }?.joinAll()
        }
    }

    private suspend fun rewriteVas() {
        //first go into the vas dir for the folder names
        assetManager?.let { manager ->
            manager.list(VAS_DIR)?.flatMap { vas ->

                val vasName = "$VAS_DIR/$vas"
                val vasDir = File(baseDir, vasName).apply { mkdirs() }

                val jobs: List<Job>? = manager.list(vasName)?.map {
                    scope.launch {
                        manager.open("$vasName/$it").run {
                            val array = ByteArray(available()).also { arr -> read(arr) }

                            File(vasDir, it).also { file ->
                                val parentFile = file.parentFile!!
                                if(parentFile.exists() || parentFile.mkdirs()) {
                                    if (file.createNewFile()) {
                                        file.outputStream().use { stream -> stream.write(array) }
                                    }
                                }
                            }
                        }
                    }
                }

                jobs ?: emptyList()

            }?.joinAll()
        }
    }

    private fun copyNotificationTone() {
        scope.launch {
            assetManager!!.open("notification_tone.wav").use { stream ->
                val file = File(context.getSaveDirectory(), "notification_tone.wav")
                if(baseDir.exists() || baseDir.mkdirs()) {
                    if(file.createNewFile()) {
                        file.outputStream().use { stream.copyTo(it) }
                    }
                }
            }
        }
    }

    var notificationToneUri : Uri? = null
        get() {
            val file = File(context.getSaveDirectory(), "notification_tone.wav")
            return if(file.exists() && file.length() > 0) FileProvider.getUriForFile(context, BuildConfig.FILE_PROVIDER_AUTHORITY, File(context.getSaveDirectory(), "notification_tone.wav"))
            else null
        }
        private set

    private val File.directorySize: Long
        get() = this.run {
            val files = listFiles()

            if(files.isNullOrEmpty()) 0
            else {
                files.map {
                    if (it.isDirectory) it.directorySize
                    else it.length()

                }.reduce { acc, l -> acc + l }
            }
        }

    fun createCameraUri(): Pair<Uri, String> {
        return context.getCacheDirectory().let {
            val userPicDir = File(it, "user").also { dir -> if (!dir.exists()) dir.mkdirs() }

            val tempFile = File.createTempFile("temp-user-pic", ".png", userPicDir)

            val uri = FileProvider.getUriForFile(context, BuildConfig.FILE_PROVIDER_AUTHORITY, tempFile)

            Pair(uri, tempFile.path)
        }
    }

    fun close() {
        try {
//            assetManager?.close()
//            assetManager = null
        } catch (e: Exception) {
        } //probably when it has already been closed
    }
}

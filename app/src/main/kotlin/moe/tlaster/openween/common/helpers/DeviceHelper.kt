package moe.tlaster.openween.common.helpers

import android.content.Context
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager

import com.zhy.http.okhttp.OkHttpUtils

import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

import okhttp3.Response

/**
 * Created by Asahi on 2016/9/27.
 */

object DeviceHelper {
    fun checkWifiOnAndConnected(context: Context): Boolean {
        val wifiMgr = context.getSystemService(Context.WIFI_SERVICE) as WifiManager
        if (wifiMgr.isWifiEnabled) {
            val wifiInfo = wifiMgr.connectionInfo
            return wifiInfo.networkId != -1
        } else {
            return false
        }
    }

    fun deleteRecursive(fileOrDirectory: File) {
        if (fileOrDirectory.isDirectory)
            for (child in fileOrDirectory.listFiles())
                deleteRecursive(child)

        fileOrDirectory.delete()
    }

    fun readFromFile(pathName: String): String {
        var ret = ""
        try {
            val file = File(pathName)
            val inputStream = FileInputStream(file)
            val inputStreamReader = InputStreamReader(inputStream)
            val bufferedReader = BufferedReader(inputStreamReader)
            val stringBuilder = StringBuilder()
            bufferedReader.readLines().forEach { stringBuilder.append(it) }
            inputStream.close()
            ret = stringBuilder.toString()
        } catch (e: FileNotFoundException) {
        } catch (e: IOException) {
        }
        return ret
    }

//    @Throws(IOException::class)
//    fun saveFile(response: Response, destFileDir: String, destFileName: String): File {
//        var `is`: InputStream? = null
//        val buf = ByteArray(2048)
//        var len = 0
//        var fos: FileOutputStream? = null
//        try {
//            `is` = response.body().byteStream()
//            val dir = File(destFileDir)
//            if (!dir.exists()) {
//                dir.mkdirs()
//            }
//            val file = File(dir, destFileName)
//            fos = FileOutputStream(file)
//            while ((len = `is`!!.read(buf)) != -1) {
//                fos.write(buf, 0, len)
//            }
//            fos.flush()
//            return file
//        } finally {
//            try {
//                response.body().close()
//                if (`is` != null) `is`.close()
//            } catch (e: IOException) {
//            }
//
//            try {
//                if (fos != null) fos.close()
//            } catch (e: IOException) {
//            }
//
//        }
//    }
}

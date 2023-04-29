package com.example.olimpiafitness.utils

import android.graphics.Bitmap
import com.example.olimpiafitness.data.model.Schedule
import java.text.SimpleDateFormat
import java.time.Duration
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

fun dateToStr(date: Date): String {
//    val locale = Locale("ru")
//    Locale.setDefault(locale)
    val localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
    return localDate.format(DateTimeFormatter.ofPattern("EEEE, dd MMMM"))
}

fun strToDate(str: String): Date? {
    val format = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault(Locale.Category.FORMAT))
    return format.parse(str)
}

fun dateToDayOfWeek(date: Date): Int {
    val localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
    return localDate.dayOfWeek.value
}

fun coachName(schedule: Schedule, coashId: String?): String =
    schedule.trainers.find {
        it.id == coashId
    }?.name ?: ""

fun setBitMapWithColor(colorOfLesson: String?): Bitmap {
    val bit = Bitmap.createBitmap(50, 300, Bitmap.Config.ARGB_8888)
    val clr = "7F${colorOfLesson?.substring(1, 7) ?: "00AA00"}".toInt(16)
    bit.eraseColor(clr)
    return bit
}

fun durationOfLesson(startTime: String?, endTime: String?): String {
    val strDate = "2023-04-28"
    val fromDate = strToDate("$strDate $startTime")
    val toDate = strToDate("$strDate $endTime")

    val fromDateT = fromDate?.toInstant()?.atZone(ZoneId.systemDefault())?.toLocalDateTime()
    val toDateT = toDate?.toInstant()?.atZone(ZoneId.systemDefault())?.toLocalDateTime()
    val duration = Duration.between(fromDateT, toDateT)
    val d = duration.toDays()
    var strD = d.toString()
    val h = duration.minusDays(duration.toDays()).toHours()
    var strH = h.toString()
    val m = duration.minusHours(duration.toHours()).toMinutes()
    var strM = m.toString()

    strH = if (h > 0) "${strH}ч. " else ""
    strM = if (m > 0) "${strM}мин. " else ""
    return "$strH$strM"
}




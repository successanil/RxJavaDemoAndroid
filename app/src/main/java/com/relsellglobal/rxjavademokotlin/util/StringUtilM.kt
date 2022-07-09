package com.relsellglobal.rxjavademokotlin.util

import java.util.*

class StringUtilM {
    companion object {
        fun reverseString(input:String) : String {
            return input.reversed()
        }

        fun makeCamelCase(input:String) : String {
            var firstString = input.substring(0,1)
            firstString = firstString.uppercase()
            var remainingString = input.substring(1)
            return firstString+remainingString
        }

        fun convertSentenceIntoCamelCase(input:String) : String {
            var strArr = input.split(" ")
            var res = ""
            for(i in strArr) {
                res += makeCamelCase(i)
            }
            return res
        }

    }
}
package main

fun main() {
    val str: Str = Str()
    println(str.size_str("a;sldfka;sldfka;sldfkj"))
    println(str.concatenation_str("a;sldfka;sldfka;sldfkj", "a;sldkf;adlfk", ";aslkf;askf"))
    println(str.add_str("привет", "мир"))
    println(str.delete_str("привет"))
    println(str.case_str(true, "привет"))
    println(str.case_str(false, str.case_str(true, "привет")))
}

class Str {

    fun size_str(str: String): Int {
        return str.count()
    }

    fun concatenation_str(vararg str: String): String{
        var res = ""
        for(i in str){
            res += i
        }
        return res
    }

    fun add_str(str: String, subStr: String): String{
        return "$str$subStr"
    }

    fun delete_str(str: String): String{
        return str.replace(oldValue = str, newValue = "", ignoreCase = false)
    }

    fun case_str(case: Boolean, str: String): String{
        if(case){
           return str.uppercase()
        }
        return str.lowercase()
    }
}

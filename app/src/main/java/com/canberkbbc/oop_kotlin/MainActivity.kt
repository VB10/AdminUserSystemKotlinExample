package com.canberkbbc.oop_kotlin

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    lateinit var sharedPreferences : SharedPreferences
    val singleton = Singleton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = this.getSharedPreferences(Singleton.PREF_NAME, Context.MODE_PRIVATE)

        val admincanberk = Admin("admincanberk","2323")
        admincanberk.changePassword("321")
        println(admincanberk.userPassword)
        admincanberk.userBan()
        println(admincanberk.getType())

        println("--------------------------")

        val canberk = StandartUser("canberk","123")

        singleton.userName = canberk.userName

        sharedPreferences.edit().putBoolean("isLogin",true).apply()

        if (sharedPreferences.getBoolean("isLogin",false)){
            canberk.changeName("patates")
        }
        println(canberk.userName)
        println(Singleton.userName)
        println(canberk.userPassword)
        println(canberk.getType())
    }

    companion object Singleton{
        const val PREF_NAME = "com.canberkbbc.oop_kotlin"
        var userName:String?=null
    }

}
class StandartUser(override var userName: String, override var userPassword: String): User() {
    override fun changeName(_userName : String) {
        this.userName = _userName
    }

    override fun logOut() {
        //sharedpref ("isLogin",false)
    }

    override fun getType(): TYPE {
        return TYPE.STANDARTUSER
    }
}
class Admin(override var userName: String, override var userPassword: String): User(),IAdmin {
    override fun changeName(_userName : String) {
        this.userName = _userName
    }

    override fun logOut() {
        //sharedpref ("isLogin",false)
    }

    override fun getType(): TYPE {
        return TYPE.ADMIN
    }

    override fun changePassword(_userPass : String){
        this.userPassword = _userPass;
    }

    override fun userCreate() {
        println("userCreate")
    }

    override fun userBan() {
        println("userBan")
    }

    override fun userMakeAdmin() {
        println("userMakeAdmin")
    }

}
abstract class User{
    abstract var userName : String
    abstract var userPassword : String
    abstract fun changeName(userName : String)
    abstract fun logOut()
    abstract fun getType(): TYPE
}

interface IAdmin{
    fun userCreate()
    fun userBan()
    fun userMakeAdmin()
    fun changePassword(_userPass : String)
}

enum class TYPE{
    ADMIN,
    STANDARTUSER
}
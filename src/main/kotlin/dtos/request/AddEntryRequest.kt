package dtos.request

import exceptions.InvalidDetailException

open class AddEntryRequest {
    private lateinit var title:String
    private lateinit var content:String

    fun setTitle(title:String){
        this.title = title
    }
    fun getTitle():String{
        return title
    }

    fun setContent(content:String){
        this.content = content
    }
    fun getContent():String{
        return content
    }
}
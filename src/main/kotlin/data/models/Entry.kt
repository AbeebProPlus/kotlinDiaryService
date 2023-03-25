package data.models

import java.time.LocalDate

class Entry{
    private var entryId:Int =0
    private lateinit var title:String
    private lateinit var content:String
    private var entryDate: LocalDate = LocalDate.now()

    fun setEntryId(id:Int){
        this.entryId = id
    }
    fun getEntryId():Int{
        return entryId
    }
    fun setTitle(title:String){
        if (title.isNotBlank()) this.title = title
    }
    fun getTitle():String{
        return this.title
    }
    fun setContent(content:String){
        if (content.isNotBlank()) this.content = content
    }
    fun getContent():String{
        return this.content
    }
}
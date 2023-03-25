package dtos.response

import data.models.Entry


open class Response {
    private lateinit var message:String
    private lateinit var statusCode:String
    private lateinit var entry: Entry

    fun setMessage(message:String){
        this.message = message
    }

    fun setStatusCode(statusCode:String){
        this.statusCode = statusCode
    }
    fun setEntry(entry: Entry){
        this.entry = entry
    }
    fun getEntry():Entry{
        return entry
    }
}
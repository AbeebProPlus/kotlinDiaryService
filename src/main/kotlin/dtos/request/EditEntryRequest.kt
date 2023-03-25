package dtos.request

class EditEntryRequest:AddEntryRequest() {
    private var entryId = 0

    fun setEntryId(entryId:Int){
        this.entryId = entryId
    }
    fun getEntryId():Int{
        return entryId
    }
}
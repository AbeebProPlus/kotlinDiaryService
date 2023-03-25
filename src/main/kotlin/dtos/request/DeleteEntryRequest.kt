package dtos.request

class DeleteEntryRequest() {
    private var entryId = 0

    fun setEntryId(entryId:Int){
        this.entryId = entryId
    }
    fun getEntryId():Int{
        return entryId
    }
}
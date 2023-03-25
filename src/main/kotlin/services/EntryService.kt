package services

import data.models.Entry
import dtos.request.AddEntryRequest
import dtos.request.DeleteEntryRequest
import dtos.request.EditEntryRequest
import dtos.response.AddEntryResponse
import dtos.response.DeleteEntryResponse
import dtos.response.EditEntryResponse

interface EntryService {
    fun addEntry(addEntryRequest: AddEntryRequest): AddEntryResponse
    fun getNumberOfEntries():Int
    fun deleteEntry(deleteEntryRequest: DeleteEntryRequest): DeleteEntryResponse
    fun editEntry(editEntryRequest: EditEntryRequest): EditEntryResponse
    fun getAllEntries():List<Entry>
}
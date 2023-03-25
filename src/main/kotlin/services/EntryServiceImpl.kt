package services

import data.models.Entry
import data.repo.EntryRepo
import data.repo.EntryRepoImpl
import dtos.request.AddEntryRequest
import dtos.request.DeleteEntryRequest
import dtos.request.EditEntryRequest
import dtos.response.AddEntryResponse
import dtos.response.DeleteEntryResponse
import dtos.response.EditEntryResponse
import exceptions.InvalidDetailException


class EntryServiceImpl : EntryService {
    private val entryRepo:EntryRepo = EntryRepoImpl.getInstance()!!

    override fun addEntry(addEntryRequest: AddEntryRequest): AddEntryResponse {
        val entry:Entry = Entry()
        if (addEntryRequest.getTitle().isBlank() || addEntryRequest.getContent().isBlank()){
            throw InvalidDetailException("Title or content cannot be empty")
        }
        entry.setTitle(addEntryRequest.getTitle())
        entry.setContent(addEntryRequest.getContent())
        entryRepo.save(entry)
        return addEntryResponse(entry)
    }

    private fun addEntryResponse(entry: Entry): AddEntryResponse {
        val addEntryResponse: AddEntryResponse = AddEntryResponse()
        addEntryResponse.setEntry(entry)
        addEntryResponse.setStatusCode("201")
        addEntryResponse.setMessage("Entry created sucessfully")
        return addEntryResponse
    }

    override fun getNumberOfEntries(): Int {
        return entryRepo.getNoOfEntries()
    }

    override fun deleteEntry(deleteEntryRequest: DeleteEntryRequest): DeleteEntryResponse {
        val foundEntry: Entry? = entryRepo.findEntryById(deleteEntryRequest.getEntryId())
        if (foundEntry != null) entryRepo.deleteEntry(foundEntry)
        val deleteEntryResponse = DeleteEntryResponse()
        deleteEntryResponse.setStatusCode("200")
        deleteEntryResponse.setMessage("Entry created sucessfully")
        return deleteEntryResponse
    }

    override fun editEntry(editEntryRequest: EditEntryRequest):EditEntryResponse {
        val foundEntry:Entry? = entryRepo.findEntryById(editEntryRequest.getEntryId())
        if (foundEntry != null) {
            foundEntry.setTitle(editEntryRequest.getTitle())
            foundEntry.setContent(editEntryRequest.getContent())
            entryRepo.save(foundEntry)
        }else throw UninitializedPropertyAccessException("Entry not found")
        return editEntryResponse(foundEntry)
    }

    override fun getAllEntries(): ArrayList<Entry> {
        return entryRepo.getAllEntries()
    }

    private fun editEntryResponse(foundEntry: Entry?): EditEntryResponse {
        val editEntryResponse = EditEntryResponse()
        if (foundEntry != null) {
            editEntryResponse.setEntry(foundEntry)
        }
        editEntryResponse.setStatusCode("200")
        editEntryResponse.setMessage("Entry created sucessfully")
        return editEntryResponse
    }

}
package services

import dtos.request.AddEntryRequest
import dtos.request.DeleteEntryRequest
import dtos.request.EditEntryRequest
import exceptions.InvalidDetailException
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class EntryServiceImplTest {
    private lateinit var entryService: EntryService
    @BeforeEach
    fun setUp() {
        entryService = EntryServiceImpl()
    }

    @AfterEach
    fun tearDown() {
    }

    @Test
    fun addEntry() {
        val addEntryRequest = AddEntryRequest()
        addEntryRequest.setTitle("This is a first title")
        addEntryRequest.setContent("This is a content")
        val addEntryResponse = entryService.addEntry(addEntryRequest)
        val entry = addEntryResponse.getEntry()
        assertEquals(1, entry.getEntryId())
        assertEquals(1, entryService.getNumberOfEntries())
    }
    @Test
    fun testThatExceptionIsThrownWhenTitleIsEmpty(){
        val addEntryRequest = AddEntryRequest()
        addEntryRequest.setTitle("")
        addEntryRequest.setContent("This is a content")
        assertThrows(InvalidDetailException::class.java) { entryService.addEntry(addEntryRequest) }
    }
    @Test
    fun testThatExceptionIsThrownWhenContentIsEmpty(){
        val addEntryRequest = AddEntryRequest()
        addEntryRequest.setTitle("Tile")
        addEntryRequest.setContent(" ")
        assertThrows(InvalidDetailException::class.java) { entryService.addEntry(addEntryRequest) }
    }
    @Test
    fun deleteEntry(){
        val addEntryRequest = AddEntryRequest()
        addEntryRequest.setTitle("This is a first title")
        addEntryRequest.setContent("This is a content")
        val addEntryResponse = entryService.addEntry(addEntryRequest)
        val entry = addEntryResponse.getEntry()
        assertEquals(1, entry.getEntryId())
        assertEquals(1, entryService.getNumberOfEntries())

        val deleteEntryRequest = DeleteEntryRequest()
        deleteEntryRequest.setEntryId(1)
        entryService.deleteEntry(deleteEntryRequest)
        assertEquals(0, entryService.getNumberOfEntries())
    }
    @Test
    fun editEntry(){
        val addEntryRequest:AddEntryRequest = AddEntryRequest()
        addEntryRequest.setTitle("A new title")
        addEntryRequest.setContent("A new content")
        val savedEntry = entryService.addEntry(addEntryRequest)
        assertEquals(1, savedEntry.getEntry().getEntryId())
        val editEntryRequest = EditEntryRequest()
        editEntryRequest.setEntryId(savedEntry.getEntry().getEntryId())
        editEntryRequest.setTitle("A new edit title")
        editEntryRequest.setContent("A new edit content")
        val editResponse = entryService.editEntry(editEntryRequest)
        assertEquals("A new edit title", editResponse.getEntry().getTitle())
        assertEquals("A new edit content", editResponse.getEntry().getContent())
    }
    @Test
    fun testThatExceptionIsThrownForUnfoundEntry(){
        val deleteEntryRequest = DeleteEntryRequest()
        deleteEntryRequest.setEntryId(5)
        assertThrows(UninitializedPropertyAccessException::class.java) {entryService.deleteEntry(deleteEntryRequest)}
    }
}
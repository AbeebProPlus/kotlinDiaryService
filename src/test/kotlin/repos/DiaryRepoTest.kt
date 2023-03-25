package repos

import data.models.Entry
import data.repo.EntryRepo
import data.repo.EntryRepoImpl
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DiaryRepoTest {
    private lateinit var entryRepo: EntryRepo

    @BeforeEach
    fun setUp() {
        entryRepo = EntryRepoImpl.getInstance()!!
    }

    @AfterEach
    fun tearDown(){
        entryRepo.getAllEntries().clear()
    }

    @Test
    fun save() {
        val entry:Entry = Entry()
        entry.setTitle("The first entry")
        entry.setContent("The first content")
        val savedEntry= entryRepo.save(entry)
        val entryId:Int = savedEntry.getEntryId()
        assertEquals(1, entryId)


        val entryTwo:Entry = Entry()
        entryTwo.setTitle("The first entry")
        entryTwo.setContent("The first content")
        val savedEntryTwo= entryRepo.save(entry)
        val entryIdTwo:Int = savedEntry.getEntryId()
        assertEquals(2, entryIdTwo)
        val noOfEntries:Int = entryRepo.getNoOfEntries()
        assertEquals(2, noOfEntries)
    }

    @Test
    fun deleteEntry(){
        val entry:Entry = Entry()
        entry.setTitle("The first entry")
        entry.setContent("The first content")
        val savedEntry= entryRepo.save(entry)
        val entryId:Int = savedEntry.getEntryId()
        assertEquals(1, entryId)

        entryRepo.deleteEntry(entry)
        assertEquals(0, entryRepo.getNoOfEntries())
    }
    @Test
    fun deleteEntryById(){
        val entry:Entry = Entry()
        entry.setTitle("The first entry")
        entry.setContent("The first content")
        val savedEntry= entryRepo.save(entry)
        val entryId:Int = savedEntry.getEntryId()
        assertEquals(1, entryId)

        val entryTwo:Entry = Entry()
        entryTwo.setTitle("The second entry")
        entryTwo.setContent("The second content")
        val savedEntryTwo= entryRepo.save(entryTwo)
        val entryIdTwo:Int = savedEntryTwo.getEntryId()
        assertEquals(2, entryIdTwo)

        entryRepo.deleteEntryById(1)
        val noOfEntries:Int = entryRepo.getNoOfEntries()
        assertEquals(1, noOfEntries)

    }

    @Test
    fun testThatEntryIdContinueToIncreaseAfterDeletingAnEntry(){
        val entry:Entry = Entry()
        entry.setTitle("The first entry")
        entry.setContent("The first content")
        val savedEntry= entryRepo.save(entry)
        val entryId:Int = savedEntry.getEntryId()
        assertEquals(1, entryId)

        val entryTwo:Entry = Entry()
        entryTwo.setTitle("The second entry")
        entryTwo.setContent("The second content")
        val savedEntryTwo= entryRepo.save(entryTwo)
        val entryIdTwo:Int = savedEntryTwo.getEntryId()
        assertEquals(2, entryIdTwo)

        entryRepo.deleteEntryById(1)
        val noOfEntries:Int = entryRepo.getNoOfEntries()
        assertEquals(1, noOfEntries)

        val entryThree:Entry = Entry()
        entryTwo.setTitle("The second entry")
        entryTwo.setContent("The second content")
        val savedEntryThree= entryRepo.save(entryThree)
        val entryIdThree:Int = savedEntryThree.getEntryId()
        assertEquals(3, entryIdThree)
        val noOfEntriesThree:Int = entryRepo.getNoOfEntries()
        assertEquals(2, noOfEntriesThree)
    }

    @Test
    fun findEntryById(){
        val entry:Entry = Entry()
        entry.setTitle("The first entry")
        entry.setContent("The first content")
        val savedEntry= entryRepo.save(entry)
        val entryId:Int = savedEntry.getEntryId()
        assertEquals(1, entryId)

        val foundEntry:Entry = entryRepo.findEntryById(1)
        assertEquals("The first entry", foundEntry.getTitle())
        assertEquals("The first content", foundEntry.getContent())
    }
    @Test
    fun editEntryByTitle(){
        val entry:Entry = Entry()
        entry.setTitle("The first entry")
        entry.setContent("The first content")
        val savedEntry= entryRepo.save(entry)
        val entryId:Int = savedEntry.getEntryId()
        assertEquals(1, entryId)
        entryRepo.editEntryTitleById(1, "Edited entry topic")
        val foundEntry:Entry = entryRepo.findEntryById(1)
        assertEquals("Edited entry topic", foundEntry.getTitle())

    }
    @Test
    fun editEntryContentById(){
        val entry:Entry = Entry()
        entry.setTitle("The first entry")
        entry.setContent("The first content")
        val savedEntry= entryRepo.save(entry)
        val entryId:Int = savedEntry.getEntryId()
        assertEquals(1, entryId)
        entryRepo.editEntryContentById(1, "Edited entry content")
        val foundEntry:Entry = entryRepo.findEntryById(1)
        assertEquals("Edited entry content", foundEntry.getContent())
    }

    @Test
    fun editEntry(){
        val entry:Entry = Entry()
        entry.setTitle("Special entry")
        entry.setContent("Special content")
        val savedEntry = entryRepo.save(entry)
        assertEquals(1, savedEntry.getEntryId())
        assertEquals(1, entryRepo.getNoOfEntries())
        entryRepo.editEntryTitleById(savedEntry.getEntryId(), "Edited special entry title")
        entryRepo.editEntryContentById(savedEntry.getEntryId(), "Edited special entry content")
        entryRepo.save(savedEntry)
        assertEquals(1, entryRepo.getNoOfEntries())
        assertEquals("Edited special entry title", savedEntry.getTitle())
    }
}
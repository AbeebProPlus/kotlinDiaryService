package data.repo

import data.models.Entry
import java.util.ArrayList

interface EntryRepo {
    fun save(entry:Entry): Entry
    fun getNoOfEntries():Int
    fun deleteEntry(entry: Entry)
    fun deleteEntryById(entryId:Int)
    fun getAllEntries(): ArrayList<Entry>
    fun findEntryById(entryId: Int): Entry
    fun editEntryTitleById(entryId:Int, newTitle:String)
    fun editEntryContentById(entryId:Int, newContent: String)
}
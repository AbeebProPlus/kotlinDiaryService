package data.repo

import data.models.Entry
import java.util.ArrayList


class EntryRepoImpl private constructor() : EntryRepo {
    companion object{
        private var entryList:ArrayList<Entry> = ArrayList()
        private var count:Int = 0
        private var instance: EntryRepoImpl? = null
        fun getInstance(): EntryRepoImpl?{
            if (instance == null){
                instance = EntryRepoImpl()
            }
            return instance
        }

    }
    override fun save(entry:Entry): Entry {
        if (entry in entryList){
            entryList[entry.getEntryId() - 1] = entry
        }else {
            entry.setEntryId(++count)
            entryList.add(entry)
        }
        return entry
    }
    override fun getNoOfEntries():Int{
        return entryList.size
    }

    override fun deleteEntry(entry: Entry) {
        entryList.remove(entry)
    }


    override fun deleteEntryById(entryId:Int){
        for(entry:Entry in entryList){
            if (entry.getEntryId() == entryId){
                entryList.remove(entry)
                break
            }
        }
    }
    override fun getAllEntries():ArrayList<Entry>{
        return entryList
    }

    override fun findEntryById(entryId: Int): Entry{
        lateinit var foundEntry:Entry
        for (entry:Entry in entryList){
            if (entry.getEntryId() == entryId){
                foundEntry = entry
            }
        }
        return foundEntry
    }

    override fun editEntryTitleById(entryId: Int, newTitle:String) {
        for (entry:Entry in entryList){
            if (entry.getEntryId() == entryId){
                entry.setTitle(newTitle)
            }
        }
    }

    override fun editEntryContentById(entryId: Int, newContent: String) {
        for (entry:Entry in entryList){
            if (entry.getEntryId() == entryId){
                entry.setContent(newContent)
            }
        }
    }
}
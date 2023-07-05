package com.viktorger.mangaverse.data.storage

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.viktorger.mangaverse.data.storage.models.ChRequestData
import com.viktorger.mangaverse.data.storage.models.ChapterData
import com.viktorger.mangaverse.data.storage.models.MangaShortcutData
import com.viktorger.mangaverse.domain.models.Chapter
import com.viktorger.mangaverse.domain.models.MangaShortcut
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import java.net.URL

class FirebaseMangaStorage : MangaStorage {
    private var TAG = "FirebaseMangaStorage"

    override fun getMangaShortcutList() = callbackFlow {
        val database: FirebaseDatabase = Firebase
            .database("https://mangaverse-8819d-default-rtdb.europe-west1.firebasedatabase.app/")
        val dbRef: DatabaseReference = database.reference


        val dataListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val array = mutableListOf<MangaShortcutData>()

                for (element in snapshot.children) {
                    element.getValue<MangaShortcutData>()?.let {
                        it.mangaId = element.key ?: "1"
                        array.add(it)
                    }
                }
                trySend(array)
            }

            override fun onCancelled(error: DatabaseError) {

            }
        }

        val mangaRef = dbRef.child("manga").child("shortcut")

        mangaRef.addListenerForSingleValueEvent(dataListener)

        awaitClose {
            mangaRef.removeEventListener(dataListener)
        }
    }

    override fun getMangaShortcut(id: String): Flow<MangaShortcutData> = callbackFlow {
        val database: FirebaseDatabase = Firebase
            .database("https://mangaverse-8819d-default-rtdb.europe-west1.firebasedatabase.app/")
        val dbRef: DatabaseReference = database.reference

        val dataListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val value = snapshot.getValue<MangaShortcutData>() ?: MangaShortcutData()

                trySend(value)
            }

            override fun onCancelled(error: DatabaseError) {

            }
        }

        val mangaRef = dbRef.child("manga").child("shortcut").child(id)
        mangaRef.addListenerForSingleValueEvent(dataListener)

        awaitClose {
            mangaRef.removeEventListener(dataListener)
        }
    }

    override fun getMangaDescription(id: String): Flow<String> = callbackFlow {
        val database: FirebaseDatabase = Firebase
            .database("https://mangaverse-8819d-default-rtdb.europe-west1.firebasedatabase.app/")
        val dbRef: DatabaseReference = database.reference

        val dataListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val value = snapshot.getValue<String>() ?: ""

                trySend(value)
            }

            override fun onCancelled(error: DatabaseError) {

            }
        }

        val mangaRef = dbRef.child("manga").child("description").child(id)
        mangaRef.addListenerForSingleValueEvent(dataListener)

        awaitClose {
            mangaRef.removeEventListener(dataListener)
        }
    }

    override fun getChapterList(id: String): Flow<List<ChapterData>> = callbackFlow {
        val database: FirebaseDatabase = Firebase
            .database("https://mangaverse-8819d-default-rtdb.europe-west1.firebasedatabase.app/")
        val dbRef: DatabaseReference = database.reference


        val dataListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val array = mutableListOf<ChapterData>()

                for (element in snapshot.children) {
                    element.getValue<ChapterData>()?.let {
                        it.id = element.key ?: "1"
                        array.add(it)
                    }
                }
                trySend(array)
            }

            override fun onCancelled(error: DatabaseError) {

            }
        }

        val mangaRef = dbRef.child("manga").child("chapter_lists").child(id)

        mangaRef.addListenerForSingleValueEvent(dataListener)

        awaitClose {
            mangaRef.removeEventListener(dataListener)
        }
    }

    override fun getChapterPagesURL(chRequestData: ChRequestData): Flow<List<String>> = callbackFlow {

        val storage = Firebase.storage
        val listRef = storage.reference.child("manga/${chRequestData
            .mangaId}/${chRequestData.chapterId}")
        listRef.listAll().addOnSuccessListener {
            val array = mutableListOf<String>()

            it.items.forEach { item ->
                array.add(item.toString())
            }


            trySend(array)
        }

        awaitClose {

        }
    }
}
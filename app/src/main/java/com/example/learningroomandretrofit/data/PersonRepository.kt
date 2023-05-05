package com.example.learningroomandretrofit.data

import javax.inject.Inject

class PersonRepository @Inject constructor(private val personDao: PersonDao) {
    suspend fun addPerson(person: Person) = personDao.addPerson(person)

    suspend fun deletePerson(person: Person) = personDao.deletePerson(person)

    suspend fun updatePerson(person: Person) = personDao.updatePerson(person)

    suspend fun getPersonById(id: Int) = personDao.getPersonById(id)

    fun getAll() = personDao.getAll()
}
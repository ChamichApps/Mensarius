package am.chamich.app.records.repositories

import am.chamich.app.records.database.api.IRecordsDatabase
import am.chamich.app.records.repositories.api.IRecordsRepository

internal class RecordsRepository(
    private val recordsDatabase: IRecordsDatabase
) : IRecordsRepository
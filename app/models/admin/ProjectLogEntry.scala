package models.admin

import java.sql.Timestamp

import db.impl.ProjectLogEntryTable
import db.impl.model.OreModel
import db.impl.table.ModelKeys._

/**
  * Represents an entry in a [[ProjectLog]].
  *
  * @param id               Unique ID
  * @param createdAt        Instant of creation
  * @param logId            ID of log
  * @param tag              Entry tag
  * @param message          Entry message
  * @param _occurrences     Amount of occurrences
  * @param _lastOccurrence  Instant of last occurrence
  */
case class ProjectLogEntry(override val id: Option[Int] = None,
                           override val createdAt: Option[Timestamp] = None,
                           logId: Int,
                           tag: String,
                           message: String,
                           private var _occurrences: Int = 1,
                           private var _lastOccurrence: Timestamp)
                           extends OreModel(id, createdAt) {

  override type T = ProjectLogEntryTable
  override type M = ProjectLogEntry

  /**
    * Returns the amount of occurrences this entry has had.
    *
    * @return Amount of occurrences
    */
  def occurrences: Int = this._occurrences

  /**
    * Sets the amount of occurrences this entry has had.
    *
    * @param occurrences Amount of occurrences
    */
  def occurrences_=(occurrences: Int) = Defined {
    this._occurrences = occurrences
    update(Occurrences)
  }

  /**
    * Returns the instant of the last occurrence.
    *
    * @return Last occurrence timestamp
    */
  def lastOccurrence: Timestamp = this._lastOccurrence

  /**
    * Sets the instant of the last occurrence.
    *
    * @param lastOccurrence Last occurrence timestamp
    */
  def lastOccurrence_=(lastOccurrence: Timestamp) = Defined {
    this._lastOccurrence = lastOccurrence
    update(LastOccurrence)
  }

  override def copyWith(id: Option[Int], theTime: Option[Timestamp]) = this.copy(id = id, createdAt = theTime)

}

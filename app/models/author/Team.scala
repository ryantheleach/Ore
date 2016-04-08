package models.author

import java.sql.Timestamp

import db.Model

/**
  * Represents a collection of developers who work on a project.
  *
  * @param id         Unique ID
  * @param createdAt  Instant of creation
  * @param name       Name of team
  */
case class Team(override val id: Option[Int], createdAt: Option[Timestamp],
                override val name: String) extends AbstractAuthor with Model

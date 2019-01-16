package repositories.batch.impl.cassandra.tables

import java.time.LocalDateTime

import com.outworkers.phantom.dsl._
import com.outworkers.phantom.streams._
import domain.soccer.Rating

import scala.concurrent.Future

abstract class RatingTable extends Table[RatingTable, Rating] with RootConnector {

  override implicit lazy val tableName = "rating"

  object teamId extends StringColumn with PartitionKey

  object rating extends DoubleColumn

  object dateCreated extends Col[LocalDateTime]

  def saveEntity(rating: Rating): Future[ResultSet] = {
    insert
      .value(_.teamId, rating.teamId)
      .value(_.rating, rating.rating)
      .value(_.dateCreated, rating.dateCreated)
      .future()
  }

  def getTeamRatings(teamId: String): Future[Seq[Rating]] = {
    select
      .where(_.teamId eqs teamId)
      .fetchEnumerator() run Iteratee.collect()
  }

}

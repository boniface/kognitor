package services.batch

import domain.soccer.Head2Head
import services.CRUDService
import services.batch.impl.master
import services.batch.impl.pseudomaster

import scala.concurrent.Future

trait Head2HeadService extends CRUDService[Head2Head] {

  def getHomeTeamMatches(homeTeamId: String): Future[Seq[Head2Head]]
}

object Head2HeadService {
  def masterImpl: Head2HeadService = new master.Head2HeadServiceImpl()
  def pseudomasterImpl: Head2HeadService = new pseudomaster.Head2HeadServiceImpl()
}
